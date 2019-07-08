package com.miaoshaproject.error;

public enum EmBusinessError implements CommonError {
    //通用的错误类型000001
    PARAMETER_VALIDATION_ERROR(100001,"参数不合法"),
    UNKNOWN_ERROR(100002,"未知错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户名或者手机号不存在"),

    ;

    private int errCode;
    private String errorMsg;

    private EmBusinessError(int errCode,String errorMsg){
        this.errCode = errCode;
        this.errorMsg = errorMsg;
    }
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return  this;
    }
}
