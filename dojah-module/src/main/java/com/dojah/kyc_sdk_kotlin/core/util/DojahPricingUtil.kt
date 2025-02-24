package com.dojah.kyc_sdk_kotlin.core.util

import com.dojah.kyc_sdk_kotlin.domain.responses.DojahPricing
import com.dojah.kyc_sdk_kotlin.ui.main.viewmodel.GovDataViewModel
import com.dojah.kyc_sdk_kotlin.ui.utils.EventTypes
import com.dojah.kyc_sdk_kotlin.ui.utils.GovDocType
import com.dojah.kyc_sdk_kotlin.ui.utils.KycPages

object DojahPricingUtil {

    fun getPricingServices(
        page: KycPages,
        pricing: DojahPricing,
        event: EventTypes,
        govViewModel: GovDataViewModel? = null,
    ): MutableList<String> {
        val services = mutableListOf<String>()
        if (event == EventTypes.STEP_FAILED || event == EventTypes.STEP_COMPLETED) {
            when (page.serverKey) {
                KycPages.EMAIL.serverKey,
                KycPages.PHONE_NUMBER.serverKey,
                KycPages.ADDRESS.serverKey -> {
                    val doVerification =
                        govViewModel?.getCurrentPage(page.serverKey)?.config?.verification
                            ?: false
                    if (doVerification) {
                        pricing.verificationMap()[page.serverKey]?.verification
                            ?.also { price ->
                                services.add(
                                    price,
                                )
                            }
                    }
                }

                KycPages.BUSINESS_ID.serverKey,
                KycPages.ID.serverKey -> {
                    pricing.defaultMap()[page.serverKey]?.default?.also { price ->
                        services.add(price)
                    }
                }

                KycPages.BUSINESS_DATA.serverKey -> {
                    pricing.businessData?.cac?.also { cacPrice ->
                        services.add(cacPrice)
                    }
                }

                KycPages.GOVERNMENT_DATA.serverKey -> {
                    govViewModel?.selectedGovDataLiveData?.value?.name?.also {
                        val selectedId = GovDocType.enumOfValue(it)?.serverKey
                        pricing.governmentData?.toMap()?.get(selectedId)
                            ?.also { price ->
                                services.add(price)

                            }
                    }
                }

                KycPages.GOVERNMENT_DATA_VERIFICATION.serverKey -> {
                    val verificationType = govViewModel?.verificationTypeLiveData?.value?.serverKey
                    pricing.governmentDataVerification?.toMap()?.get(verificationType)
                        ?.also { price ->
                            services.add(price)
                        }
                }

                KycPages.SELFIE.serverKey -> {
                    val verificationType = govViewModel?.verificationTypeLiveData?.value?.serverKey
                    pricing.selfie?.toMap()?.get(verificationType)
                        ?.also { price ->
                            services.add(price)
                        }
                }

                else -> {
                    services.clear()
                }
            }
        }
        return services
    }
}