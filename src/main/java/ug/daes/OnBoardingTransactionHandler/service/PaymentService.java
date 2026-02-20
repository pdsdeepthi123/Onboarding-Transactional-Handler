package ug.daes.OnBoardingTransactionHandler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.template.BenificiariesDTO;
import ug.daes.OnBoardingTransactionHandler.dto.template.PaymentRequestDTO;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.HeaderUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentService.class);

	/** The Constant CLASS. */
	final static String CLASS = "PaymentService";
	
	@Autowired
	PropertiesUtil propertiesUtil;

	String baseUrl = propertiesUtil.paymentBaseUrl;

	//String pricemodelBaseUrl = propertiesUtil.pricemodelBaseUrl;

	ObjectMapper objectMapper = new ObjectMapper();

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

	public ApiResponse payment(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			paymentRequestDTO = objectMapper.readValue(s, PaymentRequestDTO.class);
			
			String url = baseUrl + "/api/post/payment";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(paymentRequestDTO, headers);
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

	public ApiResponse paymentSlabPrice(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);

			paymentRequestDTO = objectMapper.readValue(s, PaymentRequestDTO.class);
			
			String url = baseUrl + "/api/post/payment/slab";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(paymentRequestDTO, headers);
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

	public ApiResponse getAggregatorFee() throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			String url = baseUrl + "/api/get/aggregator-fee";
			
			HttpHeaders headers = new HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			
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


	public ApiResponse activateUserSubscriptionBySponsorId(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			BenificiariesDTO paymentRequestDTO = new BenificiariesDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);

			paymentRequestDTO = objectMapper.readValue(s, BenificiariesDTO.class);
			
			String url = baseUrl + "/api/post/sponsor/payment";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(paymentRequestDTO, headers);
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
