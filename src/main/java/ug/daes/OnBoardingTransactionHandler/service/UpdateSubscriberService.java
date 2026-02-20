package ug.daes.OnBoardingTransactionHandler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.template.*;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.HeaderUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;


public class UpdateSubscriberService {
	

	/** The Constant CLASS. */
	final static String CLASS = "UpdateSubscriberService";
	
    @Autowired
    PropertiesUtil propertiesUtil;
    ObjectMapper objectMapper = new ObjectMapper();
    String baseUrl = propertiesUtil.baseUrl;
    
    ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

    String organizationBaseUrl = propertiesUtil.organizationBaseUrl;


    OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

    public ApiResponse updateSubscriberDetails(HttpHeaders httpHeaders,Object o) throws Exception {
    	ResponseEntity<ApiResponse> res = null;
        try {
            UpdateDto updateDto = new UpdateDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            updateDto = objectMapper.readValue(s, UpdateDto.class);
            
            String url = baseUrl + "/api/post/updateSubscriberDetails";
            
            HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
            
            HttpEntity<Object> requestEntity = new HttpEntity<>(updateDto, headers);
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

    public ApiResponse getOtp(HttpHeaders httpHeaders,Object o) throws Exception {
    	ResponseEntity<ApiResponse> res = null;
        try {
            UpdateOtpDto otpDto = new UpdateOtpDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            otpDto = objectMapper.readValue(s, UpdateOtpDto.class);

            String url = baseUrl + "/api/post/updateSubscriberOtp";
            HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
            
            HttpEntity<Object> requestEntity = new HttpEntity<>(otpDto, headers);
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

    public ApiResponse linkEmail(HttpHeaders httpHeaders,Object o) throws Exception {
    	ResponseEntity<ApiResponse> res = null;
        try {
            OrgUser userDto = new OrgUser();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            userDto = objectMapper.readValue(s, OrgUser.class);
            
            String url = organizationBaseUrl + "/api/post/linkEmail";
            HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
            
            HttpEntity<Object> requestEntity = new HttpEntity<>(userDto, headers);
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

    public ApiResponse sendEmailLinkOtp(HttpHeaders httpHeaders,Object o) throws Exception {
    	ResponseEntity<ApiResponse> res = null;
        try {
            EmailReqDto emailDTO = new EmailReqDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            emailDTO = objectMapper.readValue(s, EmailReqDto.class);
            
            String url = organizationBaseUrl + "/api/post/otp";
            HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
            
            HttpEntity<Object> requestEntity = new HttpEntity<>(emailDTO, headers);
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

    public ApiResponse getOrgList(HttpHeaders httpHeaders,Object o) throws Exception {
    	ResponseEntity<ApiResponse> res = null;
        try {
            OrgListDto orgListDto = new OrgListDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            orgListDto = objectMapper.readValue(s, OrgListDto.class);

            String url = organizationBaseUrl + "/api/get/org/list/by-suid"+"?suid="+orgListDto.getSuid();
            HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
            
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
