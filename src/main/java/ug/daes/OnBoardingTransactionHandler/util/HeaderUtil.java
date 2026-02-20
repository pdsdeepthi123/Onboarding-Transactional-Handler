package ug.daes.OnBoardingTransactionHandler.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeaderUtil {

	 public static HttpHeaders createHeaders(HttpHeaders sourceHeaders) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("deviceId", sourceHeaders.getFirst("deviceId"));
	        headers.set("appVersion", sourceHeaders.getFirst("appVersion"));
	        headers.set("osVersion", sourceHeaders.getFirst("osVersion"));
	        headers.set("fcmToken", sourceHeaders.getFirst("fcmToken"));
	        headers.set("platform", sourceHeaders.getFirst("platform"));
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        return headers;
	    }
	 
	 public static HttpHeaders createHeadersForSignConsentData(HttpHeaders sourceHeaders) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("adminugpassemail", sourceHeaders.getFirst("subscriberEmail"));
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        return headers;
	    }
}
