package com.dojah.kyc_sdk_kotlin.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.get
import androidx.navigation.navOptions
import com.dojah.kyc_sdk_kotlin.DOJAH_CLOSED_RESULT
import com.dojah.kyc_sdk_kotlin.DOJAH_RESULT_KEY
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.R
import com.dojah.kyc_sdk_kotlin.core.Result
import com.dojah.kyc_sdk_kotlin.data.io.SharedPreferenceManager
import com.dojah.kyc_sdk_kotlin.databinding.ActivityMainDojahBinding
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.DojahNavGraph
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.NavArguments
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.Routes
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.DecisionStatus
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.VerificationViewModel
import com.dojah.kyc_sdk_kotlin.ui.splash.COUNTRY_ERROR
import com.dojah.kyc_sdk_kotlin.ui.splash.VERIFICATION_COMPLETE_ERROR
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


class DojahMainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DESTINATION = "com.dojah.sdk_kyc.MainActivity_Destination"
        const val DESTINATION_NOTIFICATION = "destination notification"
    }


    private var binding: ActivityMainDojahBinding? = null

    private val navViewModel by viewModels<NavigationViewModel> {
        DojahSdk.dojahContainer.navViewModelFactory
    }

    private val viewModel by viewModels<VerificationViewModel> {
        DojahSdk.dojahContainer.verificationViewModelFactory
    }
    private val govViewModel by viewModels<GovDataViewModel> {
        DojahSdk.dojahContainer.govViewModelFactory
    }

    private val logger = HttpLoggingInterceptor.Logger.DEFAULT


    private val preferenceManager: SharedPreferenceManager by lazy {
        DojahSdk.dojahContainer.sharedPreferenceManager
    }

    private var idleJob: Job? = null
    var waitingForOtherFragments = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarIconToDark()

//        onBackPressedCallback.isEnabled

        ActivityMainDojahBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(root)
            attachKeyboardListeners()
            findViewById<View>(R.id.nav_host_fragment).isVisible = true

            val errorExtra = intent.getStringExtra("error")
            val msgExtra = intent.getStringExtra("message")
            if (errorExtra != null) {
                ///If there is an auth error redirect to error page.
                displayError(errorExtra, msgExtra)
                return@apply
            }

            hideKeyboard(root)

            val booleanExtra = intent.getBooleanExtra("sandbox", false)
            sandboxTag.isVisible = booleanExtra

            viewModel.getDojahAppAttribute(this@DojahMainActivity)?.let {
                toolbar.logoUrl = it.logo
            }

            viewModel.authDataFromPref?.whiteLabel.let {
                footer.isVisible = it != true
            }
            val navController = findNavController(R.id.nav_host_fragment)

            handleNavigations(navController, savedInstanceState)

            toolbar.backView.setOnClickListener {
                val currentRoute = navController.currentDestination?.route
                val startDestinationRoute =
                    (navController.graph[Routes.verification_route] as NavGraph).startDestinationRoute
                logger.log("start route is $startDestinationRoute")
                if (currentRoute != startDestinationRoute) {
                    onBackPressedDispatcher.onBackPressed()
                } else {
                    setResult(RESULT_OK, Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT))
                    finish()
                }
            }
            toolbar.closeView.setOnClickListener {
                setResult(RESULT_OK, Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT))
                finish()
            }

            val brandColor =
                SharedPreferenceManager(this@DojahMainActivity).getMaterialButtonBgColor
            logger.log("BTN: Brand color: ${brandColor}")
            if (brandColor != null) {
                try {
                    progressIndicator.trackColor = Color.parseColor(brandColor)
                } catch (e: Exception) {
                    logger.log("${e.message}")
                }
            }

        }
    }

    fun handleNavigations(
        navController: NavController = findNavController(R.id.nav_host_fragment),
        savedInstanceState: Bundle? = null,
        isReset: Boolean = false,
    ) {
        val pages = viewModel.getPagesFromPrefs()!!
        logger.log("all pages: $pages")
        DojahNavGraph.createRoutes(
            navController,
            pages,
            isReset
        )
        navController.restoreState(savedInstanceState)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            destination.route?.let { onDestinationChanged(it) }
        }

        if (isReset) {
            navViewModel.finalDecisionLiveData.removeObservers(this)
            navViewModel.currentStepLiveData.removeObservers(this)
            navViewModel.autoNavigateLiveData.removeObservers(this)
            navViewModel.navigationLiveData.removeObservers(this)
            navViewModel.popBackStackLiveData.removeObservers(this)
        }
        observeNavigation()

        onBackPressedDispatcher.addCallback(this@DojahMainActivity) {
            val startDestinationRoute =
                (navController.graph[Routes.verification_route] as NavGraph).startDestinationRoute
//            HttpLoggingInterceptor.Logger.DEFAULT.log("start route is $startDestinationRoute")
            val currentRoute = navController.currentDestination?.route
            if (currentRoute == startDestinationRoute) {
                //if current route is first route, exist Dojah SDK
                setResult(RESULT_OK, Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT))
                finish()
            } else {
                val isDojahRoute =
                    pages.find { currentRoute?.contains(it.name ?: "") == true } != null
                // first check if current route is part of pages
                // fetched from server
                if (isDojahRoute) {
                    //pop last route from dojah routes
                    navViewModel.popLastDojahRoute()
                }
                //if current route is not first route, pop back stack
                navController.popBackStack()
            }
        }

    }

    private fun ActivityMainDojahBinding.displayError(
        errorExtra: String?,
        msgExtra: String?
    ) {
        val navController = findNavController(R.id.nav_host_fragment)
        DojahNavGraph.createErrorRoutes(
            navController,
        )
        onBackPressedDispatcher.addCallback {
            setResult(RESULT_OK, Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT))
            finish()
        }

//        findViewById<View>(R.id.nav_host_fragment).isVisible = false
        toolbar.backView.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT))
            finish()
        }
        toolbar.closeView.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT))
            finish()
        }
        sandboxTag.isVisible = false
        when (errorExtra) {
            COUNTRY_ERROR -> {
                navController.navigate("${Routes.country_error_fragment}/$msgExtra")
            }

            VERIFICATION_COMPLETE_ERROR -> {
                navController.navigate("${Routes.success_route}/${getString(R.string.error_verification_success)}")
            }

            else -> {
                navController.navigate("${Routes.error_fragment}/$msgExtra")
            }
        }
        return
    }

    private fun centerFragmentHost() {
        findViewById<View>(R.id.scrollable_view).updateLayoutParams {
            (this as ConstraintLayout.LayoutParams).topMargin = 0
        }
        findViewById<View>(R.id.nav_host_fragment).updateLayoutParams {
            (this as FrameLayout.LayoutParams).gravity = Gravity.CENTER
        }
    }

    private fun deCentralizeFragmentHost() {
        findViewById<View>(R.id.scrollable_view).updateLayoutParams {
            (this as ConstraintLayout.LayoutParams).topMargin = toPx(48).toInt()
        }
        findViewById<View>(R.id.nav_host_fragment).apply {
            isVisible = true
            updateLayoutParams {
                (this as FrameLayout.LayoutParams).gravity = Gravity.TOP
            }
        }
    }

    private fun changeStatusBarIconToDark() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }
    }


    private fun onDestinationChanged(route: String) {

        fun onChange() {
            logger.log("Destination changes $route")
            binding!!.apply {
                val centerRoutes = listOf(
                    Routes.error_fragment,
                    Routes.country_error_fragment,
                )

                val centerRoute = centerRoutes.find { route.contains(it) }
                if (centerRoute != null) {
                    centerFragmentHost()
                } else {
                    deCentralizeFragmentHost()
                }

                val firstPageAfterIndex = viewModel.getPagesFromPrefs()?.first()?.name
                val hasOnlyOneRouteInNavStack =
                    (navViewModel.currentStepLiveData.value?.size == 1)
                if (route == Routes.index_page) {
                    if (waitingForOtherFragments) {
                        waitingForOtherFragments = false
                        ///if the resumed page is the index page, finish the activity
                        setResult(
                            RESULT_OK,
                            Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT)
                        )
                        finish()
                    }

                } else {
                    waitingForOtherFragments =
                        route == firstPageAfterIndex && hasOnlyOneRouteInNavStack
                }

            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                if (binding == null) delay(100)

                onChange()
            }
        }
    }

    fun showLoading() {
        binding?.overlay?.apply {
            background = ContextCompat.getDrawable(this@DojahMainActivity, R.color.black)
            background?.alpha = 10
            setOnTouchListener { _, _ -> true }
        }
        binding?.progressIndicator?.isVisible = true
    }

    fun dismissLoading() {
        binding?.overlay?.apply {
            background = ContextCompat.getDrawable(this@DojahMainActivity, R.color.transparent)
            background?.alpha = 0
            setOnTouchListener { _, _ -> false }
        }
        binding?.progressIndicator?.isVisible = false
    }

    private fun observeNavigation() {
        val navController =
            findNavController(R.id.nav_host_fragment)

        navViewModel.finalDecisionLiveData.observe(this) {
            if (it == null) return@observe
            if (it is Result.Loading) {
                showLoading()
            } else {
                dismissLoading()
                if (it is Result.Success) {
                    val disableBackButton = {
                        binding?.toolbar?.showBackButton = false
                        onBackPressedDispatcher.addCallback(this@DojahMainActivity) {
                            setResult(
                                RESULT_OK,
                                Intent().putExtra(DOJAH_RESULT_KEY, DOJAH_CLOSED_RESULT)
                            )
                            finish()
                        }
                    }
                    val overallCheck = it.data.entity?.overallCheck
                    if (overallCheck != null) {
                        preferenceManager.setFinalResultStatus(overallCheck)
                    }
                    when (overallCheck) {
                        DecisionStatus.approved.name -> {
                            disableBackButton()
                            navController.navigate(
                                "${Routes.success_route}/${getString(R.string.error_verification_success)}"
                            )
                        }

                        DecisionStatus.pending.name -> {
                            disableBackButton()
                            navController.navigate(
                                "${Routes.success_route}/${getString(R.string.error_verification_pending)}"
                            )
                        }

                        DecisionStatus.failed.name -> {
                            disableBackButton()
                            navController.navigate(
                                "${Routes.decision_error_fragment}/${getString(R.string.error_verification_failed)}"
                            )
                        }

                        else -> {
                            navController.navigate(
                                "${Routes.error_fragment}/${getString(R.string.error_verification_failed)}"
                            )
                        }
                    }
                } else if (it is Result.Error) {
                    navController.navigate(
                        "${Routes.error_fragment}/${viewModel.getErrorMessage(it)}"
                    )
                }
                navViewModel.resetDecisionLiveData()
            }
        }

        navViewModel.currentStepLiveData.observe(this) { it ->
//            HttpLoggingInterceptor.Logger.DEFAULT.log("all routes click is $it")
        }
        navViewModel.autoNavigateLiveData.observe(this) { event ->
            if (!event.hasBeenHandled) {
                event.getContentIfNotHandled()?.also { eventValue ->
                    val lastStoredRoute = navViewModel.currentStepLiveData.value
                        ?.lastOrNull()
                    val currentRoute = lastStoredRoute ?: navController.currentDestination?.route

                    val pages = viewModel.getPagesFromPrefs()
                    val indexOfCurrent = pages?.indexOfFirst { it.name == currentRoute }
                    val skipNextScreen = eventValue.first?.getBoolean(
                        NavArguments.skipNext, false
                    ) ?: false

                    if (indexOfCurrent != null) {
                        val nextIndex = indexOfCurrent + (if (skipNextScreen) 2 else 1)
                        if (nextIndex <= pages.size - 1) {
                            var nextRoute = pages[nextIndex].name ?: ""
                            if (nextRoute == KycPages.COUNTRY.serverKey) {
                                // if nextRoute is Country page,
                                // check if country is more than one
                                // before launching the country page,
                                // else just jump the country page
                                val isSingleCountry =
                                    viewModel.getCountriesFullFromPrefs(this)?.size == 1
//
                                if (isSingleCountry) {
                                    //skip country page and auto-select the country
                                    val nextNextIndex = (nextIndex + 1).coerceAtMost(pages.size - 1)
                                    nextRoute =
                                        pages[nextNextIndex].name
                                            ?: ""
                                }
                            }
                            navViewModel.pushNextDojahRoute(nextRoute)
                            val bundle = eventValue.first

                            val destination =
                                if (bundle == null) {
                                    nextRoute
                                } else {

                                    val bundleOptionName = bundle.getString(
                                        NavArguments.option, null
                                    )

                                    Routes.getOptionRoute(
                                        nextRoute,
                                        optionPageName = bundleOptionName,
                                    )
                                }
                            logger.log("nextRoute in main is $nextRoute")
                            navController.navigate(
                                destination, createNavOptions(eventValue.second)
                            )
                        } else {
                            //This is the last step, make a decision
                            navViewModel.makeFinalDecision()
                        }
                    }
                }
            }
        }

        navViewModel.navigationLiveData.observe(this) { event ->
            if (!event.hasBeenHandled) {
                event.getContentIfNotHandled()?.also {
                    val argument = it.second?.getString(NavArguments.option)
                    if (argument != null) {
                        navController.navigate("${it.first}/$argument", createNavOptions(it.third))
                    } else {
                        navController.navigate(it.first, createNavOptions(it.third))
                    }
                }
            }
        }

        navViewModel.popBackStackLiveData.observe(this) { event ->
            if (!event.hasBeenHandled) {
                event.getContentIfNotHandled()?.let {
                    if (it.first == -1) navController.popBackStack()
                    else navController.popBackStack(it.first, it.second)
                }
            }
        }
    }


    private fun createNavOptions(popAction: NavigationViewModel.PopAction?): NavOptions {
        return navOptions {
            if (popAction != null) {
                popUpTo(popAction.popupTo) { inclusive = popAction.inclusive }
            }

            launchSingleTop = true

            anim {
//                enter = R.anim.enter_from_right
//                exit = R.anim.exit_to_left
//                popEnter = R.anim.enter_from_left
//                popExit = R.anim.exit_to_right
            }
        }
    }


    private fun setupIdleWatcher() {
        idleJob = lifecycleScope.launch {
            delay(60000)

//            ContextCompat.startActivity(this@MainActivity, resumeIntent, null)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Timber.d("Touch event received")
        when (ev?.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> idleJob?.cancel()
            else -> setupIdleWatcher()
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        setupIdleWatcher()
    }

    override fun onResumeFragments() {
        findNavController(R.id.nav_host_fragment).currentDestination?.let { currentDestination ->
            currentDestination.route?.let { route ->
                onDestinationChanged(
                    route
                )
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        preferenceManager.clearTemporaryPref()
        dismissLoading()
        if (keyboardListenersAttached) {
            rootLayout?.viewTreeObserver?.removeOnGlobalLayoutListener(keyboardLayoutListener);
        }
    }

//    fun Fragment.hideKeyboard() {
//        view?.let { activity?.hideKeyboard(it) }
//    }


    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private val keyboardLayoutListener =
        ViewTreeObserver.OnGlobalLayoutListener { // navigation bar height
            var navigationBarHeight = 0
            var resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                navigationBarHeight = resources.getDimensionPixelSize(resourceId)
            }

            // status bar height
            var statusBarHeight = 0
            resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                statusBarHeight = resources.getDimensionPixelSize(resourceId)
            }

            // display window size for the app layout
            val rect = Rect()
            window.decorView.getWindowVisibleDisplayFrame(rect)

            // screen height - (user app height + status + nav) ..... if non-zero, then there is a soft keyboard
            val keyboardHeight =
                rootLayout!!.rootView.height - (statusBarHeight + navigationBarHeight + rect.height())
            if (keyboardHeight <= 0) {
                onHideKeyboard()
            } else {
                onShowKeyboard(keyboardHeight)
            }
        }

    private var keyboardListenersAttached = false
    private var rootLayout: ViewGroup? = null

    private fun onShowKeyboard(keyboardHeight: Int) {
        binding?.footer?.isVisible = false
    }

    private fun onHideKeyboard() {
        binding?.footer?.isVisible = true

    }

    private fun attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return
        }
        rootLayout = binding?.root
        rootLayout?.viewTreeObserver?.addOnGlobalLayoutListener(keyboardLayoutListener)
        keyboardListenersAttached = true
    }

}

fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
)