package ug.daes.OnBoardingTransactionHandler.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.template.OrganizationDto;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrganizationService {

	private static Logger logger = LoggerFactory.getLogger(OrganizationService.class);

	/** The Constant CLASS. */
	final static String CLASS = "OrganizationService";

	@Autowired
	PropertiesUtil propertiesUtil;
	ObjectMapper objectMapper = new ObjectMapper();
	String organizationBaseUrl = propertiesUtil.organizationBaseUrl;
	
	ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

	OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

	public ApiResponse getCertificateDetails(HttpHeaders httpHeaders, Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			OrganizationDto organizationDto = new OrganizationDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			organizationDto = objectMapper.readValue(s, OrganizationDto.class);
			String orgUid = organizationDto.getOrgUid();
			String url = organizationBaseUrl + "/api/get/certificate/details?orgUid=" + orgUid;
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

	public ApiResponse getSponsorListBySuidForUserSubscription(HttpHeaders httpHeaders, Object o) throws Exception {

		String data;
		ResponseEntity<ApiResponse> res = null;
		try {

			System.out.println(" getSponsors ");
			ObjectMapper objectMapper = new ObjectMapper();
			data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			JsonNode jsonArrayNode = objectMapper.readTree(data);
			String url = organizationBaseUrl + "/api/verify/onboarding-sponsorship?suid="
					+ jsonArrayNode.get("suid").asText();
			System.out.println("url " + url);
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

	public ApiResponse linkSponsorByBeneficiarySuid(HttpHeaders httpHeaders, Object o) throws Exception {

		String data;
		ResponseEntity<ApiResponse> res = null;
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			JsonNode jsonArrayNode = objectMapper.readTree(data);

			String url = organizationBaseUrl + "/api/link-sponsor?id=" + jsonArrayNode.get("id")
					+ "&beneficiaryDigitalId=" + jsonArrayNode.get("beneficiaryDigitalId").asText();
			System.out.println("url " + url);

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

	public ApiResponse getSponsorsBySuid(HttpHeaders httpHeaders, Object o) throws Exception {

		String data;
		ResponseEntity<ApiResponse> res = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			JsonNode jsonArrayNode = objectMapper.readTree(data);

			String url = organizationBaseUrl + "/api/get/all/sponsors?&suid="
					+ jsonArrayNode.get("beneficiaryDigitalId").asText();
			System.out.println("url " + url);

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

	public ApiResponse getAllCertificateDetails(HttpHeaders httpHeaders, Object o) throws Exception {
		ResponseEntity<ApiResponse> res = null;
		try {
			OrganizationDto organizationDto = new OrganizationDto();
			String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			organizationDto = objectMapper.readValue(s, OrganizationDto.class);

			String url = organizationBaseUrl + "/api/get/all/organizations/cert?orgId=" + organizationDto.getOrgUid();
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

}
