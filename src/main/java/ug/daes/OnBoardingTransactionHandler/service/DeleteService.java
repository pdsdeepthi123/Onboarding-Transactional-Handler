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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.template.RecordDto;

import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raxit Dubey
 *
 */
public class DeleteService {
	
	private static Logger log = LoggerFactory.getLogger(DeleteService.class);

	/** The Constant CLASS. */
	final static String CLASS = "DeleteService";

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

//	@Value("${onboarding.url}")
//	private String baseUrl = "http://localhost:8081";
	
	@Autowired
	PropertiesUtil propertiesUtil;

	String baseUrl = propertiesUtil.baseUrl;

	ObjectMapper objectMapper = new ObjectMapper();
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

	public ApiResponse deleteRecordBySuid(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			RecordDto recordDto = new RecordDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			recordDto = objectMapper.readValue(s, RecordDto.class);
			
			String url = baseUrl + "/api/get/delete-record?suid=" + (String) recordDto.getSuid();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = application.restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, ApiResponse.class);
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

}
