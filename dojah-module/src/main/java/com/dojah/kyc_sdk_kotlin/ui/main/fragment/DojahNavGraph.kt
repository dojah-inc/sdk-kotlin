package com.dojah.kyc_sdk_kotlin.ui.main.fragment

import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.fragment
import androidx.navigation.get
import androidx.navigation.navigation
import com.dojah.kyc_sdk_kotlin.domain.responses.Step
import com.dojah.kyc_sdk_kotlin.ui.base.NavigationViewModel
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.CaptureBackDocFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.CaptureDocumentFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.CaptureSelfieFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.DecisionErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.DojahCountryErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.DojahErrorFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.EmptyFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.EnterOtpFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.PreviewDocFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.PreviewSelfieFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.SuccessFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.UploadBackDocFragment
import com.dojah.kyc_sdk_kotlin.ui.main.fragment.datacollection.UploadFrontDocFragment
import okhttp3.logging.HttpLoggingInterceptor
import com.dojah.kyc_sdk_kotlin.ui.utils.*


object Routes {
    const val index_page = "index_page"
    const val verification_route = "verification_route"
    const val id_route = "id_navigation"
    const val capture_doc_route = "capture_doc_route"
    const val capture_back_doc_route = "capture_back_doc_route"
    const val preview_doc_route = "preview_doc_route"
    const val upload_doc_route = "upload_doc_route"
    const val upload_back_doc_route = "upload_back_doc_route"
    const val capture_selfie_fragment = "capture_selfie_fragment"
    const val preview_selfie_fragment = "preview_selfie_fragment"
    const val error_fragment = "error_fragment"
    const val decision_error_fragment = "decision_error_fragment"
    const val country_error_fragment = "error_country_fragment"
    const val success_route = "success_route"
    const val otp_route = "otp_route"

    fun getOptionRoute(
        pageName: String,
        optionPageName: String? = null,
    ): String {
        if (optionPageName == null) {
            return pageName
        }

        return "${pageName}_${optionPageName}"
    }

}

object NavArguments {
    const val next_page = "index_next_page"
    const val plant_name = "plant_name"
    const val option = "option"
    const val skipNext = "skip-next-step"
    const val option2 = "option2"
    const val optionType = "option_type"
}

class DojahNavGraph {

    companion object {

        fun navigate(
            controller: NavigationViewModel,
            route: String, argument: String? = null,
        ) {
            if (argument == null) {
                controller.navigate(route)
                return
            }
            controller.navigate("$route/$argument")
        }

        fun createErrorRoutes(
            controller: NavController,
        ): NavGraph {
            controller.graph = controller.createGraph(
                startDestination = Routes.verification_route
            ) {

                navigation(
                    startDestination = "${Routes.error_fragment}/{${NavArguments.option}}",
                    Routes.verification_route
                ) {
                    fragment<DojahErrorFragment>("${Routes.error_fragment}/{${NavArguments.option}}") {
                        argument(NavArguments.option) {
                            type = NavType.StringType
                            nullable = true
                        }
                    }

                    fragment<DojahCountryErrorFragment>("${Routes.country_error_fragment}/{${NavArguments.option}}") {
                        argument(NavArguments.option) {
                            type = NavType.StringType
                            nullable = true
                        }
                    }
                    fragment<SuccessFragment>("${Routes.success_route}/{${NavArguments.option}}") {
                        argument(NavArguments.option) {
//                            type = NavType.StringType
                            type = NavType.StringType
                            nullable = true
                        }
                    }
                }
            }
            return controller.graph
        }

        fun createRoutes(
            controller: NavController, pages: List<Step>, isReset: Boolean = false
        ): NavGraph {
//            HttpLoggingInterceptor.Logger.DEFAULT.log("pages $pages")
            controller.graph = controller.createGraph(
                startDestination = Routes.verification_route
            ) {

                navigation(
                    startDestination = Routes.index_page,
                    Routes.verification_route
                ) {
                    fragment<EmptyFragment>(Routes.index_page)
                    fragment<CaptureSelfieFragment>(Routes.capture_selfie_fragment)
                    fragment<CaptureDocumentFragment>(Routes.capture_doc_route)
                    fragment<CaptureBackDocFragment>(Routes.capture_back_doc_route)
                    fragment<UploadFrontDocFragment>(Routes.upload_doc_route)
                    fragment<UploadBackDocFragment>(Routes.upload_back_doc_route)
                    fragment<PreviewSelfieFragment>(Routes.preview_selfie_fragment)
                    fragment<PreviewDocFragment>(Routes.preview_doc_route)
                    fragment<EnterOtpFragment>(Routes.otp_route)
                    fragment<DojahErrorFragment>("${Routes.error_fragment}/{${NavArguments.option}}") {
                        argument(NavArguments.option) {
                            type = NavType.StringType
                            nullable = true
                        }
                    }
                    fragment<DecisionErrorFragment>("${Routes.decision_error_fragment}/{${NavArguments.option}}") {
                        argument(NavArguments.option) {
                            type = NavType.StringType
                            nullable = true
                        }
                    }
                    fragment<DojahCountryErrorFragment>("${Routes.country_error_fragment}/{${NavArguments.option}}") {
                        argument(NavArguments.option) {
                            type = NavType.StringType
                            nullable = true
                        }
                    }
                    fragment<SuccessFragment>("${Routes.success_route}/{${NavArguments.option}}") {
                        argument(NavArguments.option) {
//                            type = NavType.StringType
                            type = NavType.StringType
                            nullable = true
                        }
                    }

                    pages.forEachIndexed { index, step ->
                        val findPageEnum = KycPages.findPageEnum(step.name!!)
                        val nextIndex =
                            (index + 1).coerceAtMost(pages.size - 1)
                        val nextStep = pages[nextIndex]
                        if (findPageEnum != null) {
                            if (findPageEnum.optionPages == null) {
                                val customDestination =
                                    controller.navigatorProvider[FragmentNavigator::class].createDestination()
                                        .apply {
                                            route = Routes.getOptionRoute(
                                                findPageEnum.serverKey,
//                                                optionPageName = optionPage.first,
                                            )
//                                                findPageEnum.serverKey
                                            setClassName(findPageEnum.fragmentClassName)
                                            addArgument(
                                                NavArguments.next_page,
                                                NavArgument.Builder().apply {
                                                    setType(NavType.StringType)
                                                    setIsNullable(true)
                                                }.build()
                                            )

                                        }
                                addDestination(customDestination)
                            } else {
                                findPageEnum.optionPages.forEach { optionPage ->
                                    val firstDestination =
                                        controller.navigatorProvider[FragmentNavigator::class].createDestination()
                                            .apply {
                                                route = Routes.getOptionRoute(
                                                    findPageEnum.serverKey,
                                                    optionPageName = optionPage.first,
                                                )
                                                setClassName(optionPage.second)
                                                addArgument(
                                                    NavArguments.next_page,
                                                    NavArgument.Builder().apply {
                                                        setType(NavType.StringType)
                                                        setIsNullable(true)
                                                    }.build()
                                                )
                                            }
                                    addDestination(firstDestination)
                                }
                            }

                        } else {
//                            HttpLoggingInterceptor.Logger.DEFAULT.log("step ${step.name} not found")
                        }
                    }

                }
            }
            return controller.graph

        }
    }
}
