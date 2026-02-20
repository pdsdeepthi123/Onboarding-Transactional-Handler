/**
 * 
 */
package ug.daes.OnBoardingTransactionHandler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ug.daes.OnBoardingTransactionHandler.conf.PropertiesConfiguration;

/**
 * @author Raxit Dubey
 *
 */
@Component
public class PropertiesUtil implements CommandLineRunner {

	public static String baseUrl;

	public static String raBaseUrl;

	public static String signBaseUrl;

	public static String appConfigUrl;

	public static String paymentBaseUrl;

	public static String notificationBaseUrl;

	public static String organizationBaseUrl;

	public static String pricemodelBaseUrl;

	public static String assistedOnboardingUrl;

	public static String agentsNotificationBaseUrl;
	
	public static String simulatedBorderControlBaseUrl;
	
	public static String verificationChannelBaseUrl;
	
	public static String verificationService;
	public static String mosipBaseUrl;

	public static String idpTokenValidation;

	@Autowired
	PropertiesConfiguration propertiesConfiguration;

	@Override
	public void run(String... args) throws Exception {
		baseUrl = propertiesConfiguration.getOnboarding();
		raBaseUrl = propertiesConfiguration.getRaauthority();
		signBaseUrl = propertiesConfiguration.getSigning();
		appConfigUrl = propertiesConfiguration.getAppConfig();
		paymentBaseUrl = propertiesConfiguration.getPayment();
		notificationBaseUrl = propertiesConfiguration.getNotification();
		organizationBaseUrl = propertiesConfiguration.getOrganization();
		pricemodelBaseUrl = propertiesConfiguration.getPricemodel();
		
		assistedOnboardingUrl = propertiesConfiguration.getAssistedOnboarding();
		agentsNotificationBaseUrl = propertiesConfiguration.getAgentsNotification();
		simulatedBorderControlBaseUrl = propertiesConfiguration.getSimulatedBorderControl();
		mosipBaseUrl = propertiesConfiguration.getMosipBaseUrl();
		verificationChannelBaseUrl = propertiesConfiguration.getVerificationChannel();
		verificationService = propertiesConfiguration.getVerificationService();
		idpTokenValidation = propertiesConfiguration.getIdpTokenValidation();

	}
}
