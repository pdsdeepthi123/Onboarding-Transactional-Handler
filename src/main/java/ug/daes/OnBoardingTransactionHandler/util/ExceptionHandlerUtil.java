package ug.daes.OnBoardingTransactionHandler.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

import ug.daes.OnBoardingTransactionHandler.constant.ErrorCode;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;

@Component
public class ExceptionHandlerUtil {

	private static Logger log = LoggerFactory.getLogger(ExceptionHandlerUtil.class);

	/** The Constant CLASS. */
	final static String CLASS = "ExceptionHandlerUtil";

	//Set.of.(..) is available in java 9. hence it is not supported in java 8.
	//private final Set<Integer> ERROR_STATUS_CODES = Set.of(400, 401, 403, 404, 415, 500, 501, 503);
	
	private final Set<Integer> ERROR_STATUS_CODES =  new HashSet<>(Arrays.asList(400, 401, 403, 404, 415, 500, 501, 503));

	public ApiResponse handleResponse(ResponseEntity<ApiResponse> res) {
		
		@SuppressWarnings("deprecation")
		int statusCode = res.getStatusCodeValue();
		
		if (statusCode == 200 || statusCode == 201) {
			return res.getBody();
		}else {
			final String errCode = ErrorCode.map.get(res.getStatusCode());
			return AppUtil.createApiResponse(false, "Something went wrong. Please try after sometime Code(" + errCode + ")", null);
		}
//		if (ERROR_STATUS_CODES.contains(statusCode)) {
//			return AppUtil.createApiResponse(false, "Something went wrong. Please try after some time.", null);
//		}
//		if (statusCode == 200 || statusCode == 201) {
//			return res.getBody();
//		}
	}

	public ApiResponse handleHttpException(HttpStatusCodeException e) {
		HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());
		String errCode = ErrorCode.map.getOrDefault(status, "UNKNOWN_ERROR");
		return AppUtil.createApiResponse(false, "HTTP Error: " + status + " (" + errCode + ")", null);
	}

	public ApiResponse handleResourceAccessException(ResourceAccessException e) {
		return AppUtil.createApiResponse(false, "Network error: Unable to reach the server. Please try again later.",
				null);
	}

	public ApiResponse handleGenericException(Exception e) {
		log.error("Unexpected exception", e);
		return AppUtil.createApiResponse(false, "An unexpected error occurred. Please try again later.", null);
	}
}
