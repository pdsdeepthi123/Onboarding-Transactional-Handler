package ug.daes.OnBoardingTransactionHandler.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import ug.daes.OnBoardingTransactionHandler.OnBoardingTransactionHandlerApplication;
import ug.daes.OnBoardingTransactionHandler.constant.ErrorCode;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.TemporaryTableDTO;
import ug.daes.OnBoardingTransactionHandler.dto.TitleDto;
import ug.daes.OnBoardingTransactionHandler.dto.UpdateTemporaryTableDto;
import ug.daes.OnBoardingTransactionHandler.dto.template.MainDto;
import ug.daes.OnBoardingTransactionHandler.util.AppUtil;
import ug.daes.OnBoardingTransactionHandler.util.ExceptionHandlerUtil;
import ug.daes.OnBoardingTransactionHandler.util.PropertiesUtil;

public class    ProposedOnboardingService {


    private static Logger log= LoggerFactory.getLogger(OrganizationService.class);

    /** The Constant CLASS. */
    final static String CLASS = "ProposedOnboardingService";

    @Value("{$url.onboarding}")
    String onbordingurl;

    @Autowired
    PropertiesUtil propertiesUtil;

    String baseUrl = PropertiesUtil.baseUrl;
    String verificationChannelBaseUrl = PropertiesUtil.verificationChannelBaseUrl;
    
    
    ExceptionHandlerUtil exceptionHandlerUtil= new ExceptionHandlerUtil();

    OnBoardingTransactionHandlerApplication application = new OnBoardingTransactionHandlerApplication();

    ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponse saveDataInTemporaryTable(HttpHeaders httpHeaders, Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            TemporaryTableDTO temporaryTableDTO = new TemporaryTableDTO();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            temporaryTableDTO = objectMapper.readValue(s, TemporaryTableDTO.class);

            String url = baseUrl + "/api/save/temporary-data";
            System.out.println("URL for saving ::::::"+url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(temporaryTableDTO,headers);

            res = application.restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                    ApiResponse.class);
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

    public ApiResponse submitOnboardingData(HttpHeaders httpHeaders, Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            TemporaryTableDTO temporaryTableDTO = new TemporaryTableDTO();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            temporaryTableDTO = objectMapper.readValue(s, TemporaryTableDTO.class);

            String url = baseUrl + "/api/submit/ob-data/"+temporaryTableDTO.getIdDocNumber();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

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


    public ApiResponse updateRecordInTemporaryTable(HttpHeaders httpHeaders, Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            UpdateTemporaryTableDto updateTemporaryTableDto = new UpdateTemporaryTableDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            updateTemporaryTableDto = objectMapper.readValue(s, UpdateTemporaryTableDto.class);

            String url = baseUrl + "/api/update/temporaryTable";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(updateTemporaryTableDto,headers);

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

    public ApiResponse deleteRecordInTemporaryTable(HttpHeaders httpHeaders, Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            UpdateTemporaryTableDto updateTemporaryTableDto = new UpdateTemporaryTableDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            updateTemporaryTableDto = objectMapper.readValue(s, UpdateTemporaryTableDto.class);
            
            String url = baseUrl + "/api/delete-record/temporaryTable";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(updateTemporaryTableDto,headers);

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
    
    public ApiResponse getDetailsFromVc(HttpHeaders httpHeaders, Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {

        	MainDto mainDTO = new MainDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            mainDTO = objectMapper.readValue(s, MainDto.class);
            //https://uaeid-stg.digitaltrusttech.com/uaeid-VerificationService/VerificationService/api/verify
            String url = verificationChannelBaseUrl + "/api/verify";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(mainDTO,headers);

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

    public ApiResponse getPreferredTitles(){
        ResponseEntity<ApiResponse> res = null;
        try {

            log.info(CLASS+"getPreferredTitles req with time 1 {}",AppUtil.getDate());
            String url = baseUrl + "/api/get/preferredTitle";

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


    public ApiResponse addUpdateTitle(HttpHeaders httpHeaders, Object o) throws Exception {
        ResponseEntity<ApiResponse> res = null;
        try {
            TitleDto titleDto = new TitleDto();
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            titleDto = objectMapper.readValue(s, TitleDto.class);

            String url = baseUrl + "/api/addUpdate/title";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> requestEntity = new HttpEntity<>(titleDto,headers);

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
