package ug.daes.OnBoardingTransactionHandler.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import ug.daes.OnBoardingTransactionHandler.dto.ApiResponse;

public class AppUtil
{
    public static Date getCurrentDate() {
        return new Date();
    }

    public static boolean getExpiryDate(Date date) {
        if (date.before(getCurrentDate())) {

            return true;
        }

        return false;
    }

    public static Date setExpiryDate() {
        //1440000 means 24 hrs
        //60 * 1000 means one min
        return new Date(new Date().getTime() + 1440000 * 7);
    }

    public static String getDate(){
    	SimpleDateFormat smpdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
//		System.out.println("AppUtil.getCurrentDate() = " + smpdate.format(date));
    	
		return smpdate.format(date);
    }
    
    public static ApiResponse createApiResponse(boolean success, String msg, Object object) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setResult(object);
        apiResponse.setSuccess(success);
        return apiResponse;

    }
}
