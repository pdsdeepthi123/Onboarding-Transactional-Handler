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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.constant.ErrorCode;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.ra.IssueCertDTO;
import ug.daes.OnBoardingTransactionHandler.dto.template.GetProfileDto;
import ug.daes.OnBoardingTransactionHandler.dto.template.SubscriberObRequestDTOAgents;
import ug.daes.OnBoardingTransactionHandler.dto.template.UpdateAgentFcmTokenDto;
import ug.daes.OnBoardingTransactionHandler.util.AppUtil;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

public class AssistedOnboardingService {
	@Autowired
	PropertiesUtil propertiesUtil;

	String assistedOnboardingUrl = propertiesUtil.assistedOnboardingUrl;

	ObjectMapper objectMapper = new ObjectMapper();

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();
	
	
	ExceptionHandlerUtil exceptionHandlerUtil = new ExceptionHandlerUtil();

	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse checkAgent(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			UpdateAgentFcmTokenDto updateAgentFcmTokenDto = new UpdateAgentFcmTokenDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			updateAgentFcmTokenDto = objectMapper.readValue(s, UpdateAgentFcmTokenDto.class);

			String url = assistedOnboardingUrl + "/api/check/agent/by/suid";
			// String url="http://localhost:8080/api/post/fetch/subscriber-ob-data";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(updateAgentFcmTokenDto, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
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
	
	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse assistedOnboardSubscriber(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SubscriberObRequestDTOAgents subscriberObRequestDTO = new SubscriberObRequestDTOAgents();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			//System.out.println(" s "+s);
			subscriberObRequestDTO = objectMapper.readValue(s, SubscriberObRequestDTOAgents.class);

			String url = assistedOnboardingUrl + "/api/onboard/privileged/subscriber";
			// String url="http://localhost:8080/api/post/fetch/subscriber-ob-data";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(subscriberObRequestDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
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

	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse getOnboardPrivilegedSubscriber(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			GetProfileDto getProfileDto = new GetProfileDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			getProfileDto = objectMapper.readValue(s, GetProfileDto.class);

			String url = assistedOnboardingUrl + "/api/get/profile/by/id-doc-number";
			// String url="http://localhost:8080/api/post/fetch/subscriber-ob-data";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(getProfileDto, headers);
			System.out.println(" url " + url);
			//System.out.println(" getProfileDto " + getProfileDto);
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
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

	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse getPrivilegedSubscriberList(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			JsonNode jsonArrayNode = objectMapper.readTree(data);

			String url = assistedOnboardingUrl + "/api/get/OnboardedUser-By-Agent-suid/" + jsonArrayNode.get("suid").asText();
			// String url="http://localhost:8080/api/post/fetch/subscriber-ob-data";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			System.out.println(" url " + url);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
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
	
	/**
	 * @param suid
	 * @return
	 * @throws Exception
	 */
	public ApiResponse generateFailedCertificateByAgent(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			IssueCertDTO issueCertDTO = new IssueCertDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			issueCertDTO = objectMapper.readValue(s, IssueCertDTO.class);
			
			String url = assistedOnboardingUrl + "/api/post/service/certificate/request";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(issueCertDTO, headers);
			
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
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
