package com.miaoshaproject.error;

public class BusinessException extends Exception implements CommonError {

    private  CommonError commonError;

    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError,String message) {
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(message);
    }



    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return this.commonError.getErrorMessage();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
