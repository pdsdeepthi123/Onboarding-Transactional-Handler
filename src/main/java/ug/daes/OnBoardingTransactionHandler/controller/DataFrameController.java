package ug.daes.OnBoardingTransactionHandler.controller;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;
import ug.daes.OnBoardingTransactionHandler.dto.DataFramePostRequest;
import ug.daes.OnBoardingTransactionHandler.service.DataFrameService;



@RestController
@CrossOrigin
public class DataFrameController{
	
    @Autowired
    DataFrameService dataFrameService;
    
    @PostMapping("/api/post/onboarding/dataframe")
    public ApiResponse postResponse(@RequestHeader HttpHeaders httpHeaders,@RequestBody DataFramePostRequest dataFrame) throws Exception{

    	//System.out.println("dataFrame "+dataFrame);
        return dataFrameService.ResponseServ(httpHeaders,dataFrame);
    }
    
    @GetMapping("/api/get/onboarding/dataframe")
    public ApiResponse getResponse(@RequestHeader HttpHeaders httpHeaders,@RequestParam String methodname) throws Exception{
    	DataFramePostRequest dataFrame = new DataFramePostRequest();
//    	dataFrame.setAccessToken(token);
    	dataFrame.setServiceMethod(methodname);
    	
        return dataFrameService.ResponseServ(httpHeaders,dataFrame);
    }
    
    @GetMapping("/api/get/onboarding/dataframe-by-id")
    public ApiResponse getResponseByID(@RequestHeader HttpHeaders httpHeaders,@RequestParam int id ,@RequestParam String methodname) throws Exception{
    	DataFramePostRequest dataFrame = new DataFramePostRequest();
//    	dataFrame.setAccessToken(token);
    	dataFrame.setServiceMethod(methodname);
        return dataFrameService.ResponseServByID(httpHeaders,dataFrame,id);
    }
    
//    @PostMapping("/api/ekyc/dataFrameGet")
//    public ApiResponse dataFrameGet(@RequestBody DataFrameGetRequest dataFrame) throws Exception{
//        return dataFrameService.dataGetReq(dataFrame);
//    }
//    
//    @GetMapping("/api/check/mq")
//    public String checkMQ() throws KeyManagementException, NoSuchAlgorithmException, 
//			URISyntaxException, IOException, TimeoutException {
//    	System.out.println("hello");
//    	loggingService.send();
//    	return "Done";
//    }
}
