package ug.daes.OnBoardingTransactionHandler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;

import ug.daes.OnBoardingTransactionHandler.dto.mosip.AuthRequestDTO;
import ug.daes.OnBoardingTransactionHandler.dto.mosip.GetMosipEmailOtpDto;
import ug.daes.OnBoardingTransactionHandler.dto.mosip.GetProfileByOTP;

import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;

import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

@Service
public class MosipService {


    @Autowired
    PropertiesUtil propertiesUtil;

    ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

    String mosipUrl = PropertiesUtil.mosipBaseUrl;

    String baseUrl = PropertiesUtil.baseUrl;

    OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

    ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponse getMosipEmailOtp (HttpHeaders httpHeaders, Object o) {
        ResponseEntity<ApiResponse> res = null;
        try {

            GetMosipEmailOtpDto getMosipEmailOtpDto = new GetMosipEmailOtpDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            getMosipEmailOtpDto = objectMapper.readValue(s, GetMosipEmailOtpDto.class);
            String url = mosipUrl + "/api/get/otp";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(getMosipEmailOtpDto,headers);
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


    public ApiResponse getProfileByOTP (HttpHeaders httpHeaders, Object o) {
        ResponseEntity<ApiResponse> res = null;
        try {

            GetProfileByOTP getProfileByOTP = new GetProfileByOTP();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            getProfileByOTP = objectMapper.readValue(s, GetProfileByOTP.class);
            String url = mosipUrl + "/api/get/profile";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(getProfileByOTP,headers);
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

    public ApiResponse getProfileByFingerPrint (HttpHeaders httpHeaders, Object o) {
        ResponseEntity<ApiResponse> res = null;
        try {

            AuthRequestDTO authRequestDTO = new AuthRequestDTO();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            authRequestDTO = objectMapper.readValue(s, AuthRequestDTO.class);
            String url = mosipUrl + "api/get/profile/fingerprint";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(authRequestDTO,headers);
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
