package com.dojah.sdk_kyc.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.dojah.sdk_kyc.R
import com.dojah.sdk_kyc.data.io.SharedPreferenceManager
import com.dojah.sdk_kyc.databinding.ActivityMainDojahBinding
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.utils.makeDebugToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

            hideKeyboard(root)

            val booleanExtra = intent.getBooleanExtra("sandbox", false)
            sandboxTag.isVisible = booleanExtra

            val navController = findNavController(R.id.nav_host_fragment)

            navController.restoreState(savedInstanceState)

            observeNavigation()

            setupToolbarForNavigation(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                onDestinationChanged(destination.id)
            }

            if (intent.hasExtra(EXTRA_DESTINATION)) handleDestinationIntent()

            toolbar.backView.setOnClickListener {
                navController.popBackStack()
            }
            toolbar.closeView.setOnClickListener {
                finish()
            }


        }
        //handle deep linking
//        DeepLinkService
//            .getDojahLinks(this)
//            .navigateLinks(navViewModel)
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
        val menuDest = listOf<Int>(
//            R.id.frag_home,
//            R.id.frag_status,
//            R.id.frag_dispute_detail,
//            R.id.frag_kyc
        )

        val noAppBarDest = listOf<Int>(
//            R.id.frag_kyc_bank_details,
//            R.id.frag_kyc_upload_pic,
//            R.id.frag_accept_challenge,
//            R.id.frag_daily_reward_dashboard,
//            R.id.frag_kyc_select_documents,
//            R.id.frag_kyc_upload_documents,
//            R.id.frag_kyc_upload_cac,
//            R.id.frag_kyc_upload_utility,
//            R.id.frag_analysis,
//            R.id.frag_leaderboard,
//            R.id.frag_gamification_home,
//            R.id.frag_reward_wallet_history,
        )

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
        val navController = findNavController(R.id.nav_host_fragment)

        navViewModel.navigationLiveData.observe(this) { event ->
            if (!event.hasBeenHandled) {
//                Toast.makeText(this,"Navigation just get handled", Toast.LENGTH_SHORT).show()

                event.getContentIfNotHandled()?.also {
                    navController.navigate(it.first, it.second, createNavOptions(it.third))

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
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}