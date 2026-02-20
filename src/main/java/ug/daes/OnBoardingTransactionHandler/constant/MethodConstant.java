package ug.daes.OnBoardingTransactionHandler.constant;

import java.util.HashMap;
import java.util.Map;

import ug.daes.OnBoardingTransactionHandler.dto.MethodNature;

public class MethodConstant
{

    public static  Map<String, MethodNature> map=new HashMap<>();

    static {
       
      //----------------------------------------Templates-------------------------------------------------------
        map.put("addTemplate",new MethodNature("addTemplate","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("isTemplateExist",new MethodNature("isTemplateExist","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("addTemplateApproval",new MethodNature("addTemplateApproval","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("getActiveTemplate",new MethodNature("getActiveTemplate","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("getTemplates",new MethodNature("getTemplate","Synchronous","","TemplatesService",0,"",false,"All"));
        map.put("getTemplateByID",new MethodNature("getTemplateByID","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("deleteTemplateByID",new MethodNature("deleteTemplateByID","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("publishTemplateByID",new MethodNature("publishTemplateByID","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("unPublishTemplateByID",new MethodNature("unPublishTemplateByID","Synchronous","","TemplatesService",1,"",false,"All"));
        map.put("getSteps",new MethodNature("getSteps","Synchronous","","TemplatesService",0,"",false,"All"));
        map.put("getMethods",new MethodNature("getMethod","Synchronous","","TemplatesService",0,"",false,"All"));
        
      //----------------------------------------Consent-------------------------------------------------------
        map.put("addConsent",new MethodNature("addConsent","Synchronous","","ConsentService",1,"",false,"All"));
        map.put("getConsentList",new MethodNature("getConsentList","Synchronous","","ConsentService",0,"",false,"All"));
        map.put("getConsentById",new MethodNature("getConsentById","Synchronous","","ConsentService",1,"",false,"All"));
        map.put("getActiveConsent",new MethodNature("getActiveConsent","Synchronous","","ConsentService",0,"",false,"All"));
        map.put("updateConsentActive",new MethodNature("updateConsentActive","Synchronous","","ConsentService",1,"",false,"All"));
        map.put("updateConsentInActive",new MethodNature("updateConsentInActive","Synchronous","","ConsentService",1,"",false,"All"));
        map.put("saveNiraApiLog",new MethodNature("saveNiraApiLog","Synchronous","","ConsentService",1,"",false,"All"));
        map.put("signConsentData",new MethodNature("signConsentData","Synchronous","","ConsentService",1,"",false,"All"));
//        -------------------------------------------OTP-----------------------------------------------
        
        map.put("deviceRegistration",new MethodNature("deviceRegistration","Synchronous","","OTPService",1,"",false,"All"));
        
        map.put("sendMobileOTP",new MethodNature("sendMobileOTP","Synchronous","","OTPService",1,"",false,"All"));
        map.put("saveSubscriberDetails",new MethodNature("saveSubscriberDetails","Synchronous","","OTPService",1,"",false,"All"));
        map.put("verifyNewDevice",new MethodNature("verifyNewDevice","Synchronous","","OTPService",1,"",false,"All"));
        map.put("activateNewDevice",new MethodNature("activateNewDevice","Synchronous","","OTPService",1,"",false,"All"));
        map.put("getSubscriberImage",new MethodNature("getSubscriberImage","Synchronous","","OTPService",1,"",false,"All"));
        map.put("saveSubscriberOnboarding",new MethodNature("saveSubscriberOnboarding","Synchronous","","OTPService",1,"",false,"All"));
        map.put("getSubscriberOnboarding",new MethodNature("getSubscriberOnboarding","Synchronous","","OTPService",1,"",false,"All"));
        map.put("resetPin",new MethodNature("resetPin","Synchronous","","OTPService",1,"",false,"All"));
        map.put("getLivelinessVideo",new MethodNature("getLivelinessVideo","Synchronous","","OTPService",1,"",false,"All"));
        
        map.put("saveSubscriberReOnboarding",new MethodNature("saveSubscriberReOnboarding","Synchronous","","OTPService",1,"",false,"All"));
        
        map.put("getDeviceStatus",new MethodNature("getDeviceStatus","Synchronous","","OTPService",1,"",false,"All"));
        map.put("updateSubscriberFcmToken", new MethodNature("updateSubscriberFcmToken", "Synchronous", "", "OTPService", 1, "", false, "All"));
        
        map.put("getSubscriberProfiles",new MethodNature("getSubscriberProfiles","Synchronous","","OTPService",1,"",false,"All"));
        map.put("getVerificationChannelResponse",new MethodNature("getVerificationChannelResponse","Synchronous","","OTPService",1,"",false,"All"));

//        ------------------------- ---------------------RaService------------------------------------------------
        map.put("getCertificateDetailsBySubscriberUniqueId",new MethodNature("getCertificateDetailsBySubscriberUniqueId","Synchronous","","RaService",1,"",false,"All"));
        map.put("revokeCertificate",new MethodNature("revokeCertificate","Synchronous","","RaService",1,"",false,"All"));
        map.put("setPin",new MethodNature("setPin","Synchronous","","RaService",1,"",false,"All"));
        map.put("setPinFromSMA",new MethodNature("setPinFromSMA","Synchronous","","RaService",1,"",false,"All"));
        map.put("cancelSetPinFromSMA",new MethodNature("cancelSetPinFromSMA","Synchronous","","RaService",1,"",false,"All"));
        map.put("verifyCertPin",new MethodNature("verifyCertPin","Synchronous","","RaService",1,"",false,"All"));
        map.put("genrateFailedCertificate",new MethodNature("genrateFailedCertificate","Synchronous","","RaService",1,"",false,"All"));

        map.put("setPins",new MethodNature("setPins","Synchronous","","RaService",1,"",false,"All"));
//        ---------------------------------------------SendNotification----------------------------------------------
        map.put("setSendNotification",new MethodNature("setSendNotification","Synchronous","","SendNotification",1,"",false,"All"));
        map.put("deleteRecordBySuid",new MethodNature("deleteRecordBySuid","Synchronous","","DeleteService",1,"",false,"All"));

        
        //------------------------updateApp-------------------------------
        
        map.put("checkUpdate", new MethodNature("checkUpdate", "Synchronous", "", "AppConfigService", 1, "", false, "All"));
        map.put("addAppConfig", new MethodNature("addAppConfig", "Synchronous", "", "AppConfigService", 1, "", false, "All"));
        map.put("getAppConfigList", new MethodNature("getAppConfigList", "Synchronous", "", "AppConfigService", 0, "", false, "All"));
        
        map.put("appConfigurationList", new MethodNature("appConfigurationList", "Synchronous", "", "AppConfigService", 0, "", false, "All"));
        map.put("appConfigurationListForAssistedOnboarding", new MethodNature("appConfigurationListForAssistedOnboarding", "Synchronous", "", "AppConfigService", 0, "", false, "All"));
        
        //-------------------paymentMethods--------------------
        
        map.put("payment",new MethodNature("payment","Synchronous","","PaymentService",1,"",false,"All"));
        map.put("paymentSlabPrice",new MethodNature("paymentSlabPrice","Synchronous","","PaymentService",1,"",false,"All"));
        map.put("activateUserSubscriptionBySponsorId",new MethodNature("activateUserSubscriptionBySponsorId","Synchronous","","PaymentService",1,"",false,"All"));
        map.put("getAggregatorFee", new MethodNature("getAggregatorFee", "Synchronous", "", "PaymentService", 0, "", false, "All"));
       //new changes------------updateSubscriber---------------
        map.put("updateSubscriberDetails",new MethodNature("updateSubscriberDetails","Synchronous","","UpdateSubscriberService",1,"",false,"All"));
        map.put("getOtp",new MethodNature("getOtp","Synchronous","","UpdateSubscriberService",1,"",false,"All"));
        map.put("linkEmail",new MethodNature("linkEmail","Synchronous","","UpdateSubscriberService",1,"",false,"All"));
        map.put("sendEmailLinkOtp",new MethodNature("sendEmailLinkOtp","Synchronous","","UpdateSubscriberService",1,"",false,"All"));
        map.put("getOrgList",new MethodNature("getOrgList","Synchronous","","UpdateSubscriberService",1,"",false,"All"));



        //--------------------organization------------------
        map.put("getCertificateDetails",new MethodNature("getCertificateDetails","Synchronous","","OrganizationService",1,"",false,"All"));
        map.put("getSponsorListBySuidForUserSubscription",new MethodNature("getSponsorListBySuidForUserSubscription","Synchronous","","OrganizationService",1,"",false,"All"));
        map.put("linkSponsorByBeneficiarySuid",new MethodNature("linkSponsorByBeneficiarySuid","Synchronous","","OrganizationService",1,"",false,"All"));
        map.put("getSponsorsBySuid",new MethodNature("getSponsorsBySuid","Synchronous","","OrganizationService",1,"",false,"All"));
        map.put("getAllCertificateDetails",new MethodNature("getAllCertificateDetails","Synchronous","","OrganizationService",1,"",false,"All"));

        //----------pricemodel methods---------------------


        map.put("getRemCredits",new MethodNature("getRemCredits","Synchronous","","PriceModelService",1,"",false,"All"));
        map.put("getServices",new MethodNature("getServices","Synchronous","","PriceModelService",1,"",false,"All"));
        map.put("getPriceSlabByServiceAndStakeHolder",new MethodNature("getPriceSlabByServiceAndStakeHolder","Synchronous","","PriceModelService",1,"",false,"All"));
        map.put("getPriceSlabOrg",new MethodNature("getPriceSlabOrg","Synchronous","","PriceModelService",1,"",false,"All"));
        map.put("getPayHistory",new MethodNature("getPayHistory","Synchronous","","PriceModelService",1,"",false,"All"));
        map.put("getSubscriptionFee",new MethodNature("getSubscriptionFee","Synchronous","","PriceModelService",1,"",false,"All"));
        map.put("getOrganizationRemainingCredits",new MethodNature("getOrganizationRemainingCredits","Synchronous","","PriceModelService",1,"",false,"All"));
        map.put("getServiceFee",new MethodNature("getServiceFee","Synchronous","","PriceModelService",1,"",false,"All"));



        // Temporary Table

        map.put("saveDataInTemporaryTable",new MethodNature("saveDataInTemporaryTable","Synchronous","","ProposedOnboardingService",1,"",false,"All"));
        map.put("submitOnboardingData",new MethodNature("submitOnboardingData","Synchronous","","ProposedOnboardingService",1,"",false,"All"));
        map.put("updateRecordInTemporaryTable",new MethodNature("updateRecordInTemporaryTable","Synchronous","","ProposedOnboardingService",1,"",false,"All"));
        map.put("deleteRecordInTemporaryTable",new MethodNature("deleteRecordInTemporaryTable","Synchronous","","ProposedOnboardingService",1,"",false,"All"));
        
      //-------------------------assistedOnboarding----------------------------------------
        map.put("checkAgent",new MethodNature("checkAgent","Synchronous","","AssistedOnboardingService",1,"",false,"All"));
        map.put("assistedOnboardSubscriber",new MethodNature("assistedOnboardSubscriber","Synchronous","","AssistedOnboardingService",1,"",false,"All"));
        map.put("getOnboardPrivilegedSubscriber",new MethodNature("getOnboardPrivilegedSubscriber","Synchronous","","AssistedOnboardingService",1,"",false,"All"));
        map.put("getPrivilegedSubscriberList",new MethodNature("getPrivilegedSubscriberList","Synchronous","","AssistedOnboardingService",1,"",false,"All"));
        map.put("generateFailedCertificateByAgent",new MethodNature("generateFailedCertificateByAgent","Synchronous","","AssistedOnboardingService",1,"",false,"All"));
        
        //-----------------------Agents Notification----------------------------------------------------------------------
        map.put("setAgentsSendNotification",new MethodNature("setAgentsSendNotification","Synchronous","","SendNotification",1,"",false,"All"));

       
      //---------------------Simulated Border Control------------------------------------------------------------
        map.put("getDetailsFromSimulatedBorder",new MethodNature("getDetailsFromSimulatedBorder","Synchronous","","ProposedOnboardingService",1,"",false,"All"));


        map.put("getMosipEmailOtp",new MethodNature("getMosipEmailOtp","Synchronous","","MosipService",1,"",false,"All"));

        map.put("getProfileByOTP",new MethodNature("getProfileByOTP","Synchronous","","MosipService",1,"",false,"All"));

        map.put("getProfileByFingerPrint",new MethodNature("getProfileByFingerPrint","Synchronous","","MosipService",1,"",false,"All"));

        map.put("getDetailsFromVc",new MethodNature("getDetailsFromVc","Synchronous","","ProposedOnboardingService",1,"",false,"All"));
        map.put("verificationService",new MethodNature("verificationService","Synchronous","","ProposedOnboardingService",1,"",false,"All"));
        
      
        map.put("getPreferredTitles",new MethodNature("getPreferredTitles","Synchronous","","ProposedOnboardingService",0,"",false,"All"));

        map.put("addUpdateTitle",new MethodNature("addUpdateTitle","Synchronous","","ProposedOnboardingService",1,"",false,"All"));

    }




}
