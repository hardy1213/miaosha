package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    //后端要消费的前端的数据类型
    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    //定义exceptionhandle解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception ex){
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            CommonReturnType commonReturnType = new CommonReturnType();
            commonReturnType.setStatus("fail");
            responseData.put("errorCode",businessException.getErrorCode());
            responseData.put("errMsg",businessException.getErrorMessage());
        }else {
            responseData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrorMessage());
        }
        return CommonReturnType.create(responseData,"fail");


//        commonReturnType.setData(responseData);
//        return commonReturnType;
    }
}
