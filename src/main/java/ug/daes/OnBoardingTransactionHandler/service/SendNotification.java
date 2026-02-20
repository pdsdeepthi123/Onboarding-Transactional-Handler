/**
 * 
 */
package ug.daes.OnBoardingTransactionHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.ObjectMapper;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.sendNotification.PushNotificationRequest;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Raxit Dubey
 *
 */
public class SendNotification {
	
	private static Logger logger = LoggerFactory.getLogger(SendNotification.class);

	/** The Constant CLASS. */
	final static String CLASS = "SendNotification";

	@Autowired
	PropertiesUtil propertiesUtil;

	String baseUrl = propertiesUtil.notificationBaseUrl;
	
	String agentsBaseUrl = propertiesUtil.agentsNotificationBaseUrl;

	ObjectMapper objectMapper = new ObjectMapper();
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

	public ApiResponse setSendNotification(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			PushNotificationRequest notificationRequest = new PushNotificationRequest();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			notificationRequest = objectMapper.readValue(s, PushNotificationRequest.class);
			
			System.out.println("OnBoardingTransactionHandler in SendNotification service req :: " +notificationRequest);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(notificationRequest, headers);
			res = application.restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity,
					ApiResponse.class);
			return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {
			return exceptionHandlerUtil.handleGenericException(e);
		}
	}
	
	public ApiResponse setAgentsSendNotification(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			PushNotificationRequest notificationRequest = new PushNotificationRequest();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			notificationRequest = objectMapper.readValue(s, PushNotificationRequest.class);
			System.out.println("OnBoardingTransactionHandler in SendNotification service req :: " +notificationRequest);

			System.out.println("baseUrl notification "+agentsBaseUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(notificationRequest, headers);
			return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {
			return exceptionHandlerUtil.handleGenericException(e);
		}
	}

}
