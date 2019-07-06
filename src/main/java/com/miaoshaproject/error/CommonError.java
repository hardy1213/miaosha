package com.miaoshaproject.error;

public interface CommonError {

    int getErrorCode();
    String getErrorMessage();
    CommonError setErrorMsg(String errorMsg);
}
