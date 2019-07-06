package com.miaoshaproject.response;

public class CommonReturnType {

    //表明对应请求的返回结果  success fail
    private String status;
    //若status - success 那么data返回前端需要的json数据，否则返回通用的错误码格式
    private  Object data;

    //通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");

    }

    public static CommonReturnType create(Object result,String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(result);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
