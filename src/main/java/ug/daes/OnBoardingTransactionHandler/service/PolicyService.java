package ug.daes.OnBoardingTransactionHandler.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.policy.PolicyDTO;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;


public class PolicyService {

    private static Logger logger = LoggerFactory.getLogger(TemplatesService.class);

    /** The Constant CLASS. */
    final static String CLASS = "PolicyService";

    @Autowired
    PropertiesUtil propertiesUtil;

    String baseUrl = PropertiesUtil.baseUrl;

    ObjectMapper objectMapper = new ObjectMapper();
    
    ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

    OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

    public ApiResponse verifyServiceForDevice(HttpHeaders httpHeaders, Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			PolicyDTO policyDTO = new PolicyDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);

			policyDTO = objectMapper.readValue(s, PolicyDTO.class);

			String url = baseUrl + "/api/get/verify-policy";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			policyDTO.setDeviceUid(httpHeaders.getFirst("deviceId"));
			policyDTO.setSuid(httpHeaders.getFirst("suid"));
			HttpEntity<Object> requestEntity = new HttpEntity<>(policyDTO, headers);
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
}
