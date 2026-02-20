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
import ug.daes.OnBoardingTransactionHandler.dto.template.SubscriberDTO;
import ug.daes.OnBoardingTransactionHandler.dto.template.TemplateApproveDTO;
import ug.daes.OnBoardingTransactionHandler.dto.template.TemplateDTO;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.HeaderUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;


/**
 * @author Raxit Dubey
 *
 */
public class TemplatesService {
	
	/** The Constant CLASS. */
	final static String CLASS = "TemplatesService";

	@Autowired
	PropertiesUtil propertiesUtil;

	String baseUrl = propertiesUtil.baseUrl;

	ObjectMapper objectMapper = new ObjectMapper();
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse addTemplate(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			TemplateDTO templateDTO = new TemplateDTO();

			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			templateDTO = objectMapper.readValue(s, TemplateDTO.class);
			
			String url = baseUrl + "/api/auth/save/template";

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(templateDTO, headers);
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

	public ApiResponse addTemplateApproval(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			TemplateApproveDTO templateDTO = new TemplateApproveDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			templateDTO = objectMapper.readValue(s, TemplateApproveDTO.class);
			String url = baseUrl + "/api/post/approve-template";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(templateDTO, headers);
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

	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse getActiveTemplate(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			SubscriberDTO subscriberDTO = new SubscriberDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			subscriberDTO = objectMapper.readValue(s, SubscriberDTO.class);
			
			String url = baseUrl + "/api/post/activte-template";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(subscriberDTO, headers);
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

	/**
	 * @return
	 * @throws Exception
	 */
	public ApiResponse getTemplates() throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/auth/get/templates";
			System.out.println("url ==>> " + url);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApiResponse getTemplateByID(HttpHeaders httpHeaders,Object id) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/auth/get/template-by-id?id=" + (Integer) id;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApiResponse deleteTemplateByID(HttpHeaders httpHeaders,Object id) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/delete/template-by-id?id=" + (Integer) id;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApiResponse publishTemplateByID(HttpHeaders httpHeaders,Object id) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/update/template-status?id=" + (Integer) id + "&status=PUBLISHED";

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApiResponse unPublishTemplateByID(HttpHeaders httpHeaders,Object id) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/update/template-status?id=" + (Integer) id + "&status=UNPUBLISHED";
		
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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

	public ApiResponse isTemplateExist(Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			TemplateDTO templateDTO = new TemplateDTO();

			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			templateDTO = objectMapper.readValue(s, TemplateDTO.class);
			
			String url = baseUrl + "/api/get/template-exist?templateName=" + templateDTO.getTemplateName()
					+ "&methodName=" + templateDTO.getTemplateMethod();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(templateDTO, headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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

	public ApiResponse getSteps() throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/auth/get/onboarding-step";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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

	public ApiResponse getMethods() throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/auth/get/methods";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity,
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
}
