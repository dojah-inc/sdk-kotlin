package com.dojah.sdk_kyc.ui.main

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.get
import androidx.navigation.navOptions
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.databinding.ActivityMainDojahBinding
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.DojahNavGraph
import com.dojah.sdk_kyc.ui.main.fragment.NavArguments
import com.dojah.sdk_kyc.ui.main.fragment.Routes
import com.dojah.sdk_kyc.ui.utils.*
import com.dojah.sdk_kyc.ui.main.viewmodel.VerificationViewModel
import com.dojah.sdk_kyc.ui.splash.COUNTRY_ERROR
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DESTINATION = "com.dojah.sdk_kyc.MainActivity_Destination"
        const val DESTINATION_NOTIFICATION = "destination notification"
    }


    private var binding: ActivityMainDojahBinding? = null

    private val navViewModel by viewModels<NavigationViewModel>()

    private val viewModel by viewModels<VerificationViewModel>()

    @Inject
    lateinit var preferenceManager: SharedPreferenceManager

    private var idleJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarIconToDark()

//        onBackPressedCallback.isEnabled

        ActivityMainDojahBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(root)

            val errorExtra = intent.getStringExtra("error")
            val msgExtra = intent.getStringExtra("message")
            if (errorExtra != null) {
                ///If there is an auth error redirect to error page.
                findViewById<View>(R.id.nav_host_fragment).isVisible = false
                if (errorExtra == COUNTRY_ERROR) {
                    errorStub.layoutInflater.inflate(R.layout.fragment_error_country, root).apply {
                        findViewById<TextView>(R.id.msg).text = msgExtra
                    }
                } else {
                    errorStub.layoutInflater.inflate(R.layout.fragment_error, root).apply {
                        findViewById<TextView>(R.id.msg).text = msgExtra
                    }
                }
                return@apply
            } else {
//                Toast.makeText(this@MainActivity, "Error is null", Toast.LENGTH_SHORT)
//                    .show()
                findViewById<View>(R.id.nav_host_fragment).isVisible = true
            }


            hideKeyboard(root)

            val booleanExtra = intent.getBooleanExtra("sandbox", false)
            sandboxTag.isVisible = booleanExtra

            val navController = findNavController(R.id.nav_host_fragment)
            val isSingleCountry = viewModel.getCountriesFullFromPrefs(this@MainActivity)?.size == 1
//            if (isSingleCountry) {
//                navController.navigate(
//                    R.id.frag_bio_data,
//                )
//            } else {
//                navController.navigate(
//                    R.id.frag_country,
//                )
//            }
            val pages = viewModel.getPagesFromPrefs()!!
            DojahNavGraph.createRoutes(
                navController,
                isSingleCountry,
                pages
            )
            navController.restoreState(savedInstanceState)

            observeNavigation()

            setupToolbarForNavigation(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                onDestinationChanged(destination.id)
            }

            if (intent.hasExtra(EXTRA_DESTINATION)) handleDestinationIntent()

            onBackPressedDispatcher.addCallback(this@MainActivity) {
                val startDestinationRoute =
                    (navController.graph[Routes.verification_route] as NavGraph).startDestinationRoute
                val currentRoute = navController.currentDestination?.route
                if (currentRoute == startDestinationRoute) {
                    //if current route is first route, exist Dojah SDK
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
            toolbar.backView.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            toolbar.closeView.setOnClickListener {
                finish()
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


    private fun handleDestinationIntent() {
//        makeDebugToast("Has extra")
//        when (intent.getStringExtra(EXTRA_DESTINATION)) {
//            DESTINATION_NOTIFICATION -> navViewModel.navigate(R.id.frag_notification)
//        }
    }

    private fun onDestinationChanged(id: Int) {
        val noAppBarDest = listOf<Int>()

        val navView = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.view

        fun onChange() {
            binding!!.apply {

                toolbar.showBackButton = id != R.id.frag_country
//                if(id==R.id.frag_country){
//                    toolbar.alignLogoLeft()
//                }

//                toolbar.backView.isVisible = id != R.id.frag_country

                navView?.let { toggleAppBarVisibility(it, !noAppBarDest.contains(id)) }

                val marginGone =
                    resources.getDimension(R.dimen.margin_gone_bottom_widget_toolbar_text_subtitle)
                        .toInt()
//                toolbar.setLayoutGoneMarginBottom(
//                    R.id.text_subtitle,
//                    if (id == R.id.frag_account) marginGone else 0
//                )
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                if (binding == null) delay(100)

                onChange()
            }
        }
    }

    private fun toggleAppBarVisibility(navView: View, isVisible: Boolean) {
        binding?.apply {
//            appBar.isVisible = isVisible

//            navView.updateLayoutParams<CoordinatorLayout.LayoutParams> {
//                behavior = if (isVisible) AppBarLayout.ScrollingViewBehavior() else null
//            }
        }
    }

    private fun observeNavigation() {
        val navController =
            findNavController(R.id.nav_host_fragment)



        navViewModel.currentStepLiveData.observe(this) { it ->
            HttpLoggingInterceptor.Logger.DEFAULT.log("all routes click is ${it.toString()}")
        }
        navViewModel.autoNavigateLiveData.observe(this) { event ->
            if (!event.hasBeenHandled) {
                event.getContentIfNotHandled()?.also {
                    val lastStoredRoute = navViewModel.currentStepLiveData.value
                        ?.lastOrNull()
                    val currentRoute = lastStoredRoute ?: navController.currentDestination?.route

                    val pages = viewModel.getPagesFromPrefs()
                    val indexOfCurrent = pages?.indexOfFirst { it.name == currentRoute }
                    if (indexOfCurrent != null) {
                        val nextIndex = indexOfCurrent + 1
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
                                    val nextNextIndex = (nextIndex + 1).coerceAtMost(pages.size - 1)
                                    nextRoute =
                                        pages[nextNextIndex].name
                                            ?: ""
                                }
                            }
                            navViewModel.pushNextDojahRoute(nextRoute)
                            val destination =
                                if (it.first == null) nextRoute else Routes.getOptionRoute(
                                    nextRoute, it.first?.getString(
                                        NavArguments.option, null
                                    )
                                )
                            navController.navigate(
                                destination, createNavOptions(it.second)
                            )
                        } else {
                            Toast.makeText(this, "No more steps!", Toast.LENGTH_SHORT).show()
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

    private fun setupToolbarForNavigation(navController: NavController) {

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
        findNavController(R.id.nav_host_fragment).currentDestination?.let { onDestinationChanged(it.id) }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        preferenceManager.clearTemporaryPref()
    }

//    fun Fragment.hideKeyboard() {
//        view?.let { activity?.hideKeyboard(it) }
//    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}