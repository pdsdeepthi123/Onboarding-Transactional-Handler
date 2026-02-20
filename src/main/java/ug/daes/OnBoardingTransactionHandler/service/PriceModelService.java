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
import ug.daes.OnBoardingTransactionHandler.dto.template.GetPriceSlabOrg;
import ug.daes.OnBoardingTransactionHandler.dto.template.PricingSlabDefinitions;
import ug.daes.OnBoardingTransactionHandler.dto.template.RecordDto;
import ug.daes.OnBoardingTransactionHandler.dto.template.ServiceFeeReqDto;
import ug.daes.OnBoardingTransactionHandler.util.AppUtil;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.HeaderUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceModelService {
	
	private static Logger logger = LoggerFactory.getLogger(PriceModelService.class);

	/** The Constant CLASS. */
	final static String CLASS = "PriceModelService";
	
	@Autowired
	PropertiesUtil propertiesUtil;
	String baseUrl = propertiesUtil.pricemodelBaseUrl;
	ObjectMapper objectMapper = new ObjectMapper();
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();
	
	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

	public ApiResponse getServices(HttpHeaders httpHeaders,Object o) {
		ResponseEntity<ApiResponse> res = null;
		try {
			RecordDto recordDto = new RecordDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			recordDto = objectMapper.readValue(s, RecordDto.class);
			
			String url = baseUrl + "/api/get-services?suid=" + (String) recordDto.getSuid();
			RestTemplate rest = new RestTemplate();
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = rest.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
			
			return exceptionHandlerUtil.handleResponse(res);

		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (HttpServerErrorException e) {
			e.printStackTrace();
			return exceptionHandlerUtil.handleHttpException(e);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return exceptionHandlerUtil.handleResourceAccessException(e);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandlerUtil.handleGenericException(e);
		}
	}

	public ApiResponse getRemCredits(HttpHeaders httpHeaders,Object o) {
		ResponseEntity<ApiResponse> res = null;
		try {
			RecordDto recordDto = new RecordDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			recordDto = objectMapper.readValue(s, RecordDto.class);
			
			logger.info(CLASS+" getRemCredits req with time 1 {},{}",(String) recordDto.getSuid(),AppUtil.getDate());
			String url = baseUrl + "/api/get/remaining-credits?suid=" + (String) recordDto.getSuid();
			RestTemplate rest = new RestTemplate();
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			res = rest.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
			
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

	public ApiResponse getPriceSlabByServiceAndStakeHolder(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			PricingSlabDefinitions pricingSlabDefinitions = new PricingSlabDefinitions();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			pricingSlabDefinitions = objectMapper.readValue(s, PricingSlabDefinitions.class);
			
			String url = baseUrl + "/api/get-price-slab?serviceId=" + pricingSlabDefinitions.getServiceId()
					+ "&stakeHolder=" + pricingSlabDefinitions.getStakeHolder();
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(pricingSlabDefinitions, headers);
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

	public ApiResponse getPriceSlabOrg(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			GetPriceSlabOrg getPriceSlabOrg = new GetPriceSlabOrg();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			getPriceSlabOrg = objectMapper.readValue(s, GetPriceSlabOrg.class);
			
			String url = baseUrl + "/api/get-price-slab-org?orgId=" + getPriceSlabOrg.getOrgId() + "&serviceId="
					+ getPriceSlabOrg.getServiceId();
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			HttpEntity<Object> requestEntity = new HttpEntity<>(getPriceSlabOrg, headers);
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

	public ApiResponse getPayHistory(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			RecordDto recordDto = new RecordDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			recordDto = objectMapper.readValue(s, RecordDto.class);
			
			String url = baseUrl + "/api/get-payment-history?suid=" + recordDto.getSuid();
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(recordDto, headers);
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

	public ApiResponse getSubscriptionFee(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			RecordDto recordDto = new RecordDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			recordDto = objectMapper.readValue(s, RecordDto.class);
			
			String url = baseUrl + "/api/get/onboarding-subscription-fee?suid=" + recordDto.getSuid();
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(recordDto, headers);
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

	public ApiResponse getOrganizationRemainingCredits(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			GetPriceSlabOrg getPriceSlabOrg = new GetPriceSlabOrg();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			getPriceSlabOrg = objectMapper.readValue(s, GetPriceSlabOrg.class);
			
			String url = baseUrl + "/api/get-org-rem-credits?orgId=" + getPriceSlabOrg.getOrgId();
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(getPriceSlabOrg, headers);
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
	
	public ApiResponse getServiceFee(HttpHeaders httpHeaders,Object o) {
		ResponseEntity<ApiResponse> res = null;
    	try {
    		ServiceFeeReqDto serviceFeeReqDto = new ServiceFeeReqDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			serviceFeeReqDto = objectMapper.readValue(s, ServiceFeeReqDto.class);
			
			String url = baseUrl + "/api/get-service-fee";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(serviceFeeReqDto,headers);
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

}
