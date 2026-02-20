package ug.daes.OnBoardingTransactionHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.databind.ObjectMapper;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.ra.*;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.HeaderUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raxit Dubey
 *
 */
public class RaService {
	
	private static Logger log = LoggerFactory.getLogger(RaService.class);

	/** The Constant CLASS. */
	final static String CLASS = "RaService";

	@Autowired
	PropertiesUtil propertiesUtil;

	String baseRaSigningUrl = propertiesUtil.signBaseUrl;

	String baseRaAuthorityUrl = propertiesUtil.raBaseUrl;

	ObjectMapper objectMapper = new ObjectMapper();
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

	/**
//	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApiResponse getCertificateDetailsBySubscriberUniqueId(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			// String
			// url="http://localhost:9090/onboarding/api/auth/get/template-by-id?id="+(Integer)id;
			RevokeCertDTO revokeCertDTO = new RevokeCertDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			revokeCertDTO = objectMapper.readValue(s, RevokeCertDTO.class);
			
			String url = baseRaAuthorityUrl + "/api/get/service/certificate/details/by-subscriber-unique-id/"
					+ revokeCertDTO.getSubscriberUniqueId();
			System.out.println("url ==> " + url);
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
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

	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse revokeCertificate(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			RevokeCertDTO revokeCertDTO = new RevokeCertDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			revokeCertDTO = objectMapper.readValue(s, RevokeCertDTO.class);
			
			String url = baseRaAuthorityUrl + "/api/post/service/certificate/revoke";
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(revokeCertDTO, headers);
			
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
	public ApiResponse setPin(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			SignatureServiceRequestDTO signatureServiceRequestDTO = new SignatureServiceRequestDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			signatureServiceRequestDTO = objectMapper.readValue(s, SignatureServiceRequestDTO.class);
			
			String url = baseRaAuthorityUrl + "/api/post/service/certificate/set-pin";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(signatureServiceRequestDTO, headers);
			
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
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


	/**
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public ApiResponse setPins(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			SetPinModelDto setPinModelDto = new SetPinModelDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			setPinModelDto = objectMapper.readValue(s, SetPinModelDto.class);

			String url = baseRaAuthorityUrl + "/api/post/certificate/set-pins";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(setPinModelDto, headers);

			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
			
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


	public ApiResponse setPinFromSMA(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			SetPinSMADTO setPinSMA = new SetPinSMADTO();
			SetPinFromSMA setPinFromSMA = new SetPinFromSMA();

			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			setPinSMA = objectMapper.readValue(s, SetPinSMADTO.class);
			
			setPinFromSMA.setSigningPin(setPinSMA.getSignPin());
			setPinFromSMA.setAuthPin(setPinSMA.getAuthPin());
			setPinFromSMA.setSubscriberUniqueId(setPinSMA.getSuid());
			setPinFromSMA.setSignHash(setPinSMA.isSignHash());
			setPinFromSMA.setSignType(setPinSMA.getSignType());
			setPinFromSMA.setUserSelfie(setPinSMA.getUserSelfie());
			
			String url = baseRaSigningUrl + "/api/digital/signature/post/setPinFromSMA";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(setPinFromSMA, headers);
			
			res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
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

	public ApiResponse cancelSetPinFromSMA(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			SetPinSMADTO setPinSMA = new SetPinSMADTO();
			SetPinFromSMA setPinFromSMA = new SetPinFromSMA();

			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			setPinSMA = objectMapper.readValue(s, SetPinSMADTO.class);
			
			setPinFromSMA.setSubscriberUniqueId(setPinSMA.getSuid());
			setPinFromSMA.setSignHash(setPinSMA.isSignHash());
			
			String url = baseRaSigningUrl + "/api/digital/signature/post/cancelSetPinFromSMA";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(setPinFromSMA, headers);
			
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

	public ApiResponse verifyCertPin(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			VerifyCertPinDTO verifyCertPinDTO = new VerifyCertPinDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			verifyCertPinDTO = objectMapper.readValue(s, VerifyCertPinDTO.class);
			
			String url = baseRaAuthorityUrl + "/api/post/service/certificate/verify-certificate-pin";
			
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
			HttpEntity<Object> requestEntity = new HttpEntity<>(verifyCertPinDTO, headers);
			
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

	public ApiResponse genrateFailedCertificate(HttpHeaders httpHeaders,Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			IssueCertDTO issueCertDTO = new IssueCertDTO();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			issueCertDTO = objectMapper.readValue(s, IssueCertDTO.class);
			
			String url = baseRaAuthorityUrl + "/api/post/service/certificate/request";
			HttpHeaders headers = HeaderUtil.createHeaders(httpHeaders);
			
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
