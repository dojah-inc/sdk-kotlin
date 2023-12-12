package com.dojah.sdk_kyc.ui.main.fragment

import android.os.Bundle
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.fragment
import androidx.navigation.get
import androidx.navigation.navigation
import com.dojah.sdk_kyc.domain.responses.Step
import com.dojah.sdk_kyc.ui.base.NavigationViewModel
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.CaptureBackDocFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.CaptureDocumentFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.CaptureSelfieFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.EmptyFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.PreviewDocFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.PreviewSelfieFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.SuccessFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.UploadBackDocFragment
import com.dojah.sdk_kyc.ui.main.fragment.datacollection.UploadFrontDocFragment
import com.dojah.sdk_kyc.ui.main.viewmodel.KycPages
import okhttp3.logging.HttpLoggingInterceptor

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
    const val success_route = "success_route"

    fun getOptionRoute(pageName: String, optionPageName: String?, arg: Bundle? = null): String {
        if (optionPageName == null) {
            return "${pageName}/${NavArguments.next_page}"
        }
        return "${pageName}_${optionPageName}/${NavArguments.next_page}"
    }

}

object NavArguments {
    const val next_page = "index_next_page"
    const val plant_name = "plant_name"
    const val option = "option"
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

        fun createRoutes(
            controller: NavController, singleCountry: Boolean, pages: List<Step>
        ): NavGraph {

            controller.graph = controller.createGraph(
                startDestination = Routes.verification_route
            ) {
                fragment<EmptyFragment>("${Routes.index_page}/${NavArguments.next_page}") {
                    argument(NavArguments.next_page) {
                        type = NavType.StringType
                    }
                }

                navigation(
                    startDestination = if (singleCountry) "${KycPages.USER_DATA.serverKey}" else "${KycPages.COUNTRY.serverKey}",
                    Routes.verification_route
                ) {

                    fragment<CaptureDocumentFragment>(Routes.capture_doc_route)
                    fragment<CaptureBackDocFragment>(Routes.capture_back_doc_route)
                    fragment<PreviewDocFragment>(Routes.preview_doc_route)
                    fragment<UploadFrontDocFragment>(Routes.upload_doc_route)
                    fragment<UploadBackDocFragment>(Routes.upload_back_doc_route)
                    fragment<CaptureSelfieFragment>(Routes.capture_selfie_fragment)
                    fragment<PreviewSelfieFragment>(Routes.preview_selfie_fragment)
                    fragment<SuccessFragment>("${Routes.success_route}/{${NavArguments.next_page}}") {
                        argument("arg1") {
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
                            if (findPageEnum.optionpages == null) {
                                val customDestination =
                                    controller.navigatorProvider[FragmentNavigator::class].createDestination()
                                        .apply {
                                            route =
                                                "${findPageEnum.serverKey}"
                                            setClassName(findPageEnum.fragmentClassName)
                                            addArgument(
                                                NavArguments.next_page,
                                                NavArgument.Builder().apply {
                                                    setType(NavType.StringType)
                                                    setIsNullable(true)
//                                                    if (index <= pages.size - 1) {
//                                                        val nextArgIndex =
//                                                            (nextIndex + 1).coerceAtMost(pages.size - 1)
//                                                        var nextArg = ""
//                                                        if (nextArgIndex <= pages.size - 1) {
//                                                            nextArg =
//                                                                "/${pages[nextArgIndex].name}"
//                                                        }
//                                                        HttpLoggingInterceptor.Logger.DEFAULT.log(
//                                                            "next step is ${nextStep.name}$nextArg"
//                                                        )
//                                                        setDefaultValue("${nextStep.name}$nextArg")
//                                                    }
                                                }.build()
                                            )

                                        }
                                addDestination(customDestination)
                            } else {
                                findPageEnum.optionpages.forEach { optionPage ->
                                    val firstDestination =
                                        controller.navigatorProvider[FragmentNavigator::class].createDestination()
                                            .apply {
                                                route = Routes.getOptionRoute(
                                                    findPageEnum.serverKey, optionPage.first
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
                            HttpLoggingInterceptor.Logger.DEFAULT.log("step ${step.name} not found")
                        }
                    }

                }
            }
            return controller.graph

        }
    }
}
