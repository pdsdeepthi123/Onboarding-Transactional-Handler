/**
 * 
 */
package ug.daes.OnBoardingTransactionHandler.service;

import com.fasterxml.jackson.databind.JsonNode;
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
import ug.daes.OnBoardingTransactionHandler.dto.template.GetSubscriberObDataDTO;
import ug.daes.OnBoardingTransactionHandler.dto.template.MobileOTPDto;
import ug.daes.OnBoardingTransactionHandler.dto.template.RegisterDeviceDTO;
import ug.daes.OnBoardingTransactionHandler.dto.template.SubscriberObRequestDTO;

import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.HeaderUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Raxit Dubey
 *
 */
public class OTPService {

	private static Logger log = LoggerFactory.getLogger(OTPService.class);

	/** The Constant CLASS. */
	final static String CLASS = "OTPService";

	@Autowired
	PropertiesUtil propertiesUtil;

	String baseUrl = propertiesUtil.baseUrl;


	ObjectMapper objectMapper = new ObjectMapper();
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();


	public ApiResponse deviceRegistration(HttpHeaders httpHeaders,Object o) throws Exception{
		ResponseEntity<ApiResponse> res = null;
		try {
			RegisterDeviceDTO registerDeviceDTO = new RegisterDeviceDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);

			registerDeviceDTO = objectMapper.readValue(s, RegisterDeviceDTO.class);

			//logger.info(CLASS+" deviceRegistration req with time 1 {},{}", registerDeviceDTO,AppUtil.getDate());
			String url = baseUrl + "/api/post/register/device";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(registerDeviceDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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


	public ApiResponse sendMobileOTP(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			MobileOTPDto mobileDTO = new MobileOTPDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			mobileDTO = objectMapper.readValue(s, MobileOTPDto.class);

			//logger.info(CLASS+" sendMobileDTO req with time 1 {},{}", mobileDTO,AppUtil.getDate());
			String url = baseUrl + "/api/post/register-subscriber";
			System.out.println("baseUrl: "+baseUrl);
			// String url="http://localhost:8080/api/post/register-subscriber";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(mobileDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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

	public ApiResponse saveSubscriberDetails(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			MobileOTPDto mobileDTO = new MobileOTPDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			mobileDTO = objectMapper.readValue(s, MobileOTPDto.class);
			String url = baseUrl + "/api/post/save-subscriber-details";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(mobileDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					ApiResponse.class);
			return exceptionHandlerUtil.handleResponse(res);
		} catch (HttpClientErrorException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
	}

	public ApiResponse verifyNewDevice(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			MobileOTPDto mobileDTO = new MobileOTPDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			mobileDTO = objectMapper.readValue(s, MobileOTPDto.class);

			String url = baseUrl + "/api/post/verify-new-device";

			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
				
			HttpEntity<Object> requestEntity = new HttpEntity<>(mobileDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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


	public ApiResponse activateNewDevice(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			MobileOTPDto mobileDTO = new MobileOTPDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			mobileDTO = objectMapper.readValue(s, MobileOTPDto.class);

			String url = baseUrl + "/api/post/activate-new-device";
			System.out.println(url);
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity<>(mobileDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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

	public ApiResponse getSubscriberImage(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			MobileOTPDto mobileDTO = new MobileOTPDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			mobileDTO = objectMapper.readValue(s, MobileOTPDto.class);

			String url = baseUrl + "/api/post/subscriber-image";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(mobileDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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

	public ApiResponse saveSubscriberOnboarding(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SubscriberObRequestDTO obRequestDTO = new SubscriberObRequestDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			obRequestDTO = objectMapper.readValue(s, SubscriberObRequestDTO.class);

			String url = baseUrl + "/api/post/add/subscriber-ob-data";
			System.out.println(" ob url "+url);
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity<>(obRequestDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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

	//Re-OnBoarding
	public ApiResponse saveSubscriberReOnboarding(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SubscriberObRequestDTO obRequestDTO = new SubscriberObRequestDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			obRequestDTO = objectMapper.readValue(s, SubscriberObRequestDTO.class);

			String url = baseUrl + "/api/post/reonboard/subscriber-ob-data";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity<>(obRequestDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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

	public ApiResponse getSubscriberOnboarding(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			GetSubscriberObDataDTO obRequestDTO = new GetSubscriberObDataDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			obRequestDTO = objectMapper.readValue(s, GetSubscriberObDataDTO.class);

			String url = baseUrl + "/api/post/fetch/subscriber-ob-data";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity<>(obRequestDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					ApiResponse.class);

			return exceptionHandlerUtil.handleResponse(res);
		} catch (HttpClientErrorException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
	}

	public ApiResponse resetPin(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			GetSubscriberObDataDTO obRequestDTO = new GetSubscriberObDataDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			obRequestDTO = objectMapper.readValue(s, GetSubscriberObDataDTO.class);

			String url = baseUrl + "/api/post/fetch/reset-pin";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity<>(obRequestDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
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
	
	
	public ApiResponse getDeviceStatus(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			log.info(CLASS+" saveSubscriberDetails req with time 1 {},{}");
			String url = baseUrl + "/api/get/devicestatus";
			System.out.println(url);
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					ApiResponse.class);
			return exceptionHandlerUtil.handleResponse(res);
		} catch (HttpClientErrorException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {
			log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
	}

	public ApiResponse updateSubscriberFcmToken(HttpHeaders httpHeaders, Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;

		String errCode;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			errCode = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			JsonNode jsonArrayNode = objectMapper.readTree(errCode);
			String url = this.baseUrl + "/api/post/update/fcmtoken?suid=" + jsonArrayNode.get("suid").asText() + "&fcmToken=" + jsonArrayNode.get("fcmToken").asText();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity(headers);
			OnBoardingTransactionHandlerApplication var10000 = this.application;
			res = OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class, new Object[0]);
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
	
	public ApiResponse getSubscriberProfiles(HttpHeaders httpHeaders, Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		String errCode;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			errCode = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			JsonNode jsonArrayNode = objectMapper.readTree(errCode);
			String url = this.baseUrl + "/api/get/subscriber/profile/"+jsonArrayNode.get("searchType").asText()+"/"+jsonArrayNode.get("searchValue").asText();
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity(headers);
			OnBoardingTransactionHandlerApplication var10000 = this.application;
			res = OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class, new Object[0]);
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
	
	public ApiResponse getVerificationChannelResponse(HttpHeaders httpHeaders, Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		String errCode;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			errCode = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			JsonNode jsonArrayNode = objectMapper.readTree(errCode);
			String url = this.baseUrl + "/api/get/verification-channel-response/"+jsonArrayNode.get("suid").asText();
			System.out.println(url);
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity(headers);
			OnBoardingTransactionHandlerApplication var10000 = this.application;
			res = OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class, new Object[0]);
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
