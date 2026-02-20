package ug.daes.OnBoardingTransactionHandler.service;

import java.lang.reflect.Method;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import ug.daes.OnBoardingTransactionHandler.constant.ErrorCode;
import ug.daes.OnBoardingTransactionHandler.constant.MethodConstant;
import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.DataFrameGetRequest;
import ug.daes.OnBoardingTransactionHandler.dto.DataFramePostRequest;
import ug.daes.OnBoardingTransactionHandler.util.AppUtil;


@Service
public class DataFrameService {

	private static Logger log = LoggerFactory.getLogger(DataFrameService.class);

	/** The Constant CLASS. */
	final static String CLASS = "DataFrameService";

	//@Autowired
	//ExceptionHandlerUtil exceptionHandlerUtil = new ExceptionHandlerUtil();

	public ApiResponse ResponseServ(DataFramePostRequest dataFrame) throws Exception {

		String method = dataFrame.getServiceMethod();
		ApiResponse res = lookUpService(method);

		if (res.isSuccess() == false) {
			return res;
		} else {
			if (res.getMessage().equals("Asynchronous")) {
				return asynchronous(dataFrame);
			}
			if (res.getMessage().equals("Synchronous")) {
				return synchronous(dataFrame);
			}
		}
		return null;
	}

	public ApiResponse ResponseServ(HttpHeaders httpHeaders,DataFramePostRequest dataFrame) throws Exception {

		String method = dataFrame.getServiceMethod();
		ApiResponse res = lookUpService(method);

		if (res.isSuccess() == false) {
			return res;
		} else {
			if (res.getMessage().equals("Asynchronous")) {
				return asynchronous(dataFrame);
			}
			if (res.getMessage().equals("Synchronous")) {
				return synchronous(httpHeaders,dataFrame);
			}
		}
		return null;
	}
	public ApiResponse ResponseServByID(HttpHeaders httpHeaders,DataFramePostRequest dataFrame, int id) throws Exception {

		String method = dataFrame.getServiceMethod();
		ApiResponse res = lookUpService(method);

		if (res.isSuccess() == false) {
			return res;
		} else {
			if (res.getMessage().equals("AsynchronousToken") || res.getMessage().equals("SynchronousToken")) {

				if (res.getMessage().equals("AsynchronousToken")) {
					return asynchronous(dataFrame);
				}
				if (res.getMessage().equals("SynchronousToken")) {
					return synchronousById(httpHeaders,dataFrame, id);
				}
			}

			if (res.getMessage().equals("Asynchronous")) {
				return asynchronous(dataFrame);
			}
			if (res.getMessage().equals("Synchronous")) {
				return synchronousById(httpHeaders,dataFrame, id);
			}
		}
		return null;
	}

	public ApiResponse lookUpService(String method) {

		if (!(MethodConstant.map.containsKey(method))) {
			return new ApiResponse(false, "Given Service method doesnt exist", null);
		} else {

			if (MethodConstant.map.get(method).getNature().equals("Asynchronous")) {
				if (MethodConstant.map.get(method).isAuth()) {
					return new ApiResponse(true, "AsynchronousToken", MethodConstant.map.get(method).getActivity());
				}
				return new ApiResponse(true, "Asynchronous", MethodConstant.map.get(method).getActivity());
			} else {
				if (MethodConstant.map.get(method).isAuth()) {
					return new ApiResponse(true, "SynchronousToken", MethodConstant.map.get(method).getActivity());
				}
				return new ApiResponse(true, "Synchronous", MethodConstant.map.get(method).getActivity());
			}
		}

	}

	public ApiResponse asynchronous(DataFramePostRequest dataFrame) throws Exception {
		String method = dataFrame.getServiceMethod();
		ApiResponse r = null;
		ResponseEntity<ApiResponse> res = null;
		try {
			String cname = MethodConstant.map.get(method).getClassName();
			System.out.println(cname);
			Class cl = Class.forName("ug.daes.OnBoardingTransactionHandler.service." + cname);
			Object o = (Object) cl.newInstance();
			if (MethodConstant.map.get(method).getPar() > 0) {
				Class[] arg = new Class[MethodConstant.map.get(method).getPar()];
				arg[0] = Object.class;

				Method method1 = o.getClass().getMethod(method, arg);
				//System.out.println(dataFrame.getRequestBody());
				r = (ApiResponse) method1.invoke(o, dataFrame.getRequestBody());
				//System.out.println(r);
			} else {
				Method method1 = o.getClass().getMethod(method);
				r = (ApiResponse) method1.invoke(o);
				//System.out.println(r);
			}

			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
			String url = "http://ekyc.digitaltrusttech.com:90/ticket-service/api/ticket/generate";
			res = rest.exchange(url, HttpMethod.POST, requestEntity, ApiResponse.class);
			if (res.getStatusCodeValue() == 400 || res.getStatusCodeValue() == 401 || res.getStatusCodeValue() == 403 || res.getStatusCodeValue() == 404 || res.getStatusCodeValue() == 415 || res.getStatusCodeValue() == 500 || res.getStatusCodeValue() == 501 || res.getStatusCodeValue() == 503) {
				return AppUtil.createApiResponse(false, "Something went wrong.Please try after sometime", (Object)null);
			}
			if (res.getStatusCodeValue() == 200 || res.getStatusCodeValue() == 201) {
				return res.getBody();
			}
		} catch (IllegalArgumentException e) {
			log.error("Unexpected exception", e);
			final String errCode = ErrorCode.map.get("Illegal Argument Exception_AppConfig-service");
			return AppUtil.createApiResponse(false,
					"Something went wrong. Please try after sometimeCode(" + errCode + ")", (Object) null);
		} catch (IllegalStateException e) {
			log.error("Unexpected exception", e);
			final String errCode = ErrorCode.map.get("Illegal State Exception_AppConfig-service");
			return AppUtil.createApiResponse(false,
					"Something went wrong. Please try after sometimeCode(" + errCode + ")", (Object) null);
		} catch (NullPointerException e) {
			log.error("Unexpected exception", e);
			final String errCode = ErrorCode.map.get("Null pointer Exception_AppConfig-service");
			return AppUtil.createApiResponse(false,
					"Something went wrong. Please try after sometimeCode(" + errCode + ")", (Object) null);
		} catch (HttpClientErrorException e) {
			log.error("Unexpected exception", e);
			final String errCode = ErrorCode.map.get(res.getStatusCode());
			return AppUtil.createApiResponse(false,
					"Something went wrong. Please try after sometimeCode(" + errCode + ")", (Object) null);
		} catch (HttpServerErrorException e) {

			log.error("Unexpected exception", e);
			final String errCode = ErrorCode.map.get(res.getStatusCode());
			return AppUtil.createApiResponse(false,
					"Something went wrong. Please try after sometimeCode(" + errCode + ")", (Object) null);
		}catch (Exception e) {

			log.error("Unexpected exception", e);
			final String errCode = ErrorCode.map.get("EXCEPTION_AppConfigService");
			return AppUtil.createApiResponse(false, "Something went wrong.Please try after (" + errCode + ")",
					(Object) null);
		}
		return AppUtil.createApiResponse(false, "Server not reachable, please try after sometime", (Object) null);
	}

	public ApiResponse synchronous(DataFramePostRequest dataFrame) throws Exception {
		String method = dataFrame.getServiceMethod();
		ApiResponse r = null;
		try {

			String cname = MethodConstant.map.get(method).getClassName();
			Class cl = Class.forName("ug.daes.OnBoardingTransactionHandler.service." + cname);
			Object o = (Object) cl.newInstance();
			if (MethodConstant.map.get(method).getPar() > 0) {
				Class[] arg = new Class[MethodConstant.map.get(method).getPar()];
				System.out.println("arg 0: "+arg[0]);
				arg[0] = Object.class;

				Method method1 = o.getClass().getMethod(method, arg);
				r = (ApiResponse) method1.invoke(o, dataFrame.getRequestBody());
				//System.out.println(r);
				return r;
			} else {
				System.out.println("method ==>> " + method);
				Method method1 = o.getClass().getMethod(method);
				r = (ApiResponse) method1.invoke(o, (Object[]) null);

				//System.out.println(r);
				return r;
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getCause());
			throw new Exception(e);
		}
	}

	public ApiResponse synchronous(HttpHeaders httpHeaders,DataFramePostRequest dataFrame) throws Exception {
		String method = dataFrame.getServiceMethod();
		ApiResponse r = null;
		try {

			String cname = MethodConstant.map.get(method).getClassName();
			Class cl = Class.forName("ug.daes.OnBoardingTransactionHandler.service." + cname);
			Object o = (Object) cl.newInstance();
//			System.out.println(o.getClass().toString());
			System.out.println(cl);
			if (MethodConstant.map.get(method).getPar() > 0) {

				Class[] arg = new Class[MethodConstant.map.get(method).getPar()];
				arg[0] = Object.class;

				arg = new Class<?>[2];
				arg[0] = HttpHeaders.class;
				arg[1] = Object.class;
				Method method1 = o.getClass().getMethod(method, arg);
				r = (ApiResponse) method1.invoke(o, httpHeaders, dataFrame.getRequestBody());

				//Method method1 = o.getClass().getMethod(method, arg);
				//r = (ApiResponse) method1.invoke(o, request,dataFrame.getRequestBody());

//				if((method.equals("verifyNewDevice") || method.equals("activateNewDevice"))  && cname.equals("OTPService"))
//				{
//					arg = new Class<?>[2]; // We need two parameters for the method
//					arg[0] = HttpServletRequest.class;
//					arg[1] = Object.class;
//					Method method1 = o.getClass().getMethod(method, arg);
//					r = (ApiResponse) method1.invoke(o, request, dataFrame.getRequestBody());
//				}
//				else{
//					arg[0] = Object.class;
//					Method method1 = o.getClass().getMethod(method, arg);
//					r = (ApiResponse) method1.invoke(o, dataFrame.getRequestBody());
//				}

				//System.out.println(r);
				return r;
			} else {
				System.out.println("method ==>> " + method);
				Method method1 = o.getClass().getMethod(method);
				r = (ApiResponse) method1.invoke(o, (Object[]) null);

				//System.out.println(r);
				return r;
			}
		} catch (Exception e) {
			log.error("Unexpected exception", e);
			System.out.println(e);
			System.out.println(e.getCause());
			throw new Exception(e);
		}
	}

	public ApiResponse synchronousById(HttpHeaders httpHeaders,DataFramePostRequest dataFrame, int id) throws Exception {
		String method = dataFrame.getServiceMethod();
		ApiResponse r = null;
		try {

			String cname = MethodConstant.map.get(method).getClassName();
			Class cl = Class.forName("ug.daes.OnBoardingTransactionHandler.service." + cname);
			Object o = (Object) cl.newInstance();
			if (MethodConstant.map.get(method).getPar() > 0) {
				Class[] arg = new Class[MethodConstant.map.get(method).getPar()];
//				arg[0] = Object.class;
//				Method method1 = o.getClass().getMethod(method, arg);
//				r = (ApiResponse) method1.invoke(o, id);
				arg[0] = Object.class;

				arg = new Class<?>[2];
				arg[0] = HttpHeaders.class;
				arg[1] = Object.class;
				//arg[1]=cname.getClass();
				Method method1 = o.getClass().getMethod(method, arg);
				r = (ApiResponse) method1.invoke(o,httpHeaders, id);
				//System.out.println(r);
				return r;
			} else {
				System.out.println("method ==>> " + method + "(" + id + ")");
				Method method1 = o.getClass().getMethod(method);
				r = (ApiResponse) method1.invoke(o, (Object[]) null);

				//System.out.println(r);
				return r;
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getCause());
			throw new Exception(e);
		}
	}

	public ApiResponse dataGetReq(DataFrameGetRequest dataFrame) throws Exception {
		String method = dataFrame.getServiceMethod();
		ApiResponse res = lookUpService(method);
		if (res.isSuccess() == false) {
			return res;
		} else {
			if (res.getMessage().equals("SynchronousToken")) {
				return asynchrGet(dataFrame);
			} else {
				return asynchrGet(dataFrame);
			}
		}
	}

	public ApiResponse asynchrGet(DataFrameGetRequest dataFrame) throws Exception {
		String method = dataFrame.getServiceMethod();
		ApiResponse r = null;
		try {
			String cname = MethodConstant.map.get(method).getClassName();
			Class cl = Class.forName("ug.daes.OnBoardingTransactionHandler.service." + cname);
			Object o = (Object) cl.newInstance();
			if (MethodConstant.map.get(method).getPar() > 0) {
				Class[] arg = new Class[MethodConstant.map.get(method).getPar()];
				arg[0] = Object.class;

				Method method1 = o.getClass().getMethod(method, arg);
				System.out.println("dataFrame.getPars()" + dataFrame.getPars());
				r = (ApiResponse) method1.invoke(o, dataFrame.getPars());
				return r;
			} else {
				Method method1 = o.getClass().getMethod(method);
				r = (ApiResponse) method1.invoke(o, (Object[]) null);

				return r;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
