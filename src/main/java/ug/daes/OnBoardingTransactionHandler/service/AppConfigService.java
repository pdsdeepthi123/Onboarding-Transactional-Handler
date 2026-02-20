package ug.daes.OnBoardingTransactionHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.ObjectMapper;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.template.AppConfigDTO;
import ug.daes.OnBoardingTransactionHandler.dto.template.AppConfigReqDTO;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

public class AppConfigService
{
    @Autowired
    PropertiesUtil propertiesUtil;
    String baseUrl;
    String appconfigUrl;
    ObjectMapper objectMapper;
    static final String CLASS = "AppConfigService";
    OnBoardingTransactionHandlerApplication application;
    
    ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();
    
    public AppConfigService() {
        final PropertiesUtil propertiesUtil = this.propertiesUtil;
        this.baseUrl = PropertiesUtil.baseUrl;
        final PropertiesUtil propertiesUtil2 = this.propertiesUtil;
        this.appconfigUrl = PropertiesUtil.appConfigUrl;
        this.objectMapper = new ObjectMapper();
        this.application = new OnBoardingTransactionHandlerApplication();
    }
    
    public ApiResponse checkUpdate(HttpHeaders httpHeaders,final Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            AppConfigReqDTO appConfigReqDTO = new AppConfigReqDTO();
            final String s = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            appConfigReqDTO = (AppConfigReqDTO)this.objectMapper.readValue(s, (Class)AppConfigReqDTO.class);
            final String url = this.appconfigUrl + "/api/check/update";
            System.out.println("appconfigUrl :: " + url);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<Object> requestEntity = (HttpEntity<Object>)new HttpEntity((Object)appConfigReqDTO, (MultiValueMap)headers);
            final OnBoardingTransactionHandlerApplication application = this.application;
            res = (ResponseEntity<ApiResponse>)OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.POST, (HttpEntity)requestEntity, (Class)ApiResponse.class, new Object[0]);
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
    
    public ApiResponse addAppConfig(HttpHeaders httpHeaders,final Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            AppConfigDTO appConfigDTO = new AppConfigDTO();
            final String s = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            appConfigDTO = (AppConfigDTO)this.objectMapper.readValue(s, (Class)AppConfigDTO.class);
            final String url = this.appconfigUrl + "/api/save";
            System.out.println("url :: " + url);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<Object> requestEntity = (HttpEntity<Object>)new HttpEntity((Object)appConfigDTO, (MultiValueMap)headers);
            final OnBoardingTransactionHandlerApplication application = this.application;
            res = (ResponseEntity<ApiResponse>)OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.POST, (HttpEntity)requestEntity, (Class)ApiResponse.class, new Object[0]);
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
    
    public ApiResponse getAppConfigList() throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
        	
            final String url = this.appconfigUrl + "/api/get/AppConfig";
            System.out.println("url :: " + url);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<Object> requestEntity = (HttpEntity<Object>)new HttpEntity((MultiValueMap)headers);
            final OnBoardingTransactionHandlerApplication application = this.application;
            res = (ResponseEntity<ApiResponse>)OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.GET, (HttpEntity)requestEntity, (Class)ApiResponse.class, new Object[0]);
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
    
    public ApiResponse appConfigurationList() throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
        	
            final String url = this.appconfigUrl + "/api/get/AppConfigurationList";
            System.out.println("url :: " + url);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("AssistedOnboarding","0");
            final HttpEntity<Object> requestEntity = (HttpEntity<Object>)new HttpEntity((MultiValueMap)headers);
            final OnBoardingTransactionHandlerApplication application = this.application;
            res = (ResponseEntity<ApiResponse>)OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.GET, (HttpEntity)requestEntity, (Class)ApiResponse.class, new Object[0]);
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
    
    public ApiResponse appConfigurationListForAssistedOnboarding() throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            final String url = this.appconfigUrl + "/api/get/AppConfigurationList";
            
            System.out.println("url :: " + url);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("AssistedOnboarding","1");
            final HttpEntity<Object> requestEntity = (HttpEntity<Object>)new HttpEntity((MultiValueMap)headers);
            final OnBoardingTransactionHandlerApplication application = this.application;
            res = (ResponseEntity<ApiResponse>)OnBoardingTransactionHandlerApplication.restTemplate.exchange(url, HttpMethod.GET, (HttpEntity)requestEntity, (Class)ApiResponse.class, new Object[0]);
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
    
//    static {
//        AppConfigService.logger = LoggerFactory.getLogger((Class)AppConfigService.class);
//    }
}
