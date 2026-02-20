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
import ug.daes.OnBoardingTransactionHandler.constant.ErrorCode;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.consent.Consent;
import ug.daes.OnBoardingTransactionHandler.dto.template.NiraApiLogDto;
import ug.daes.OnBoardingTransactionHandler.util.AppUtil;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.HeaderUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raxit Dubey
 *
 */
public class ConsentService {

    private static Logger log = LoggerFactory.getLogger(ConsentService.class);

    /** The Constant CLASS. */
    final static String CLASS = "ConsentService";

    @Autowired
    PropertiesUtil propertiesUtil;

    // @Value("${url.onboarding}")
    String baseUrl = propertiesUtil.baseUrl;

    ObjectMapper objectMapper = new ObjectMapper();

    OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();
    
   //@Autowired
    ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

    public ApiResponse addConsent(HttpHeaders httpHeaders, Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            Consent consent = new Consent();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            consent = objectMapper.readValue(s, Consent.class);
            String url = baseUrl + "/api/add/consent";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(consent, headers);
            res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
            return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {
            log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {
            log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
	}


    /**
     * @return
     * @throws Exception
     */
    public ApiResponse getActiveConsent() throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            String url = baseUrl + "/api/activte/consent";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

            RestTemplate rest = new RestTemplate();
            res = application.restTemplate().exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
            return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
    }

    public ApiResponse getConsentList() throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
           
            String url = baseUrl + "/api/get/list/consent";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

            res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
            return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {
            log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
    }

    public ApiResponse getConsentById(HttpHeaders httpHeaders, Object id) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            String url = baseUrl + "/api/get/consent/id?id=" + (Integer) id;
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

            res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
            return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
    }

    public ApiResponse updateConsentActive(HttpHeaders httpHeaders, Object id) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            String url = baseUrl + "/api/update/consent/status?consentId=" + (Integer) id + "&status=ACTIVE";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

            res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
            return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
    }

    public ApiResponse updateConsentInActive(HttpHeaders httpHeaders, Object id) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            String url = baseUrl + "/api/update/consent/status?consentId=" + (Integer) id + "&status=INACTIVE";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

            res = application.restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
            return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
    }

    public ApiResponse saveNiraApiLog(HttpHeaders httpHeaders, Object id) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            NiraApiLogDto niraApiLogDto = new NiraApiLogDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(id);
            niraApiLogDto = objectMapper.readValue(s, NiraApiLogDto.class);
            String url = baseUrl + "/api/save/nira/logs";
            
            HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
            HttpEntity<Object> requestEntity = new HttpEntity<>(niraApiLogDto, headers);

            res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
            return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {log.error("Unexpected exception", e);
			return exceptionHandlerUtil.handleGenericException(e);
		}
    }

    public ApiResponse signConsentData(HttpHeaders httpHeaders , Object id) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {

            String url = baseUrl + "/sign-data/for/consent";

            HttpHeaders headers = HeaderUtil.createHeadersForSignConsentData(httpHeaders);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

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
