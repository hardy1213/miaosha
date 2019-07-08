package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import com.mysql.cj.util.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户登录接口
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone") String telephone,
                                  @RequestParam(name = "encrpt_password") String encrpt_password ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //入参数教研
        if (org.apache.commons.lang3.StringUtils.isEmpty(telephone)
                || org.apache.commons.lang3.StringUtils.isEmpty(encrpt_password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //用户登录服务，用来检验用户登录是否合法
        UserModel userModel = userService.validateLogin(telephone, this.EncodeMyMd5(encrpt_password));

        //将登录凭证加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);
    }

    //用户注册接口
    @Transactional
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "otpCode") String otpcode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "encrpt_password") String encrpt_password,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和对应的otp相符哈
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        if (!com.alibaba.druid.util.StringUtils.equals(otpcode,inSessionOtpCode)) {
            throw new BusinessException( EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setRegister_mode("ByPhone");
        userModel.setEncrpt_password(this.EncodeMyMd5(encrpt_password));

        userService.register(userModel);
        return CommonReturnType.create(null);

    }

    public String EncodeMyMd5(String ex) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newS = base64Encoder.encode(md5.digest(ex.getBytes("utf-8")));
        return newS;

    }

    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone){
        //需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int i = random.nextInt(99999);
        i += 10000;
        String otpCode = String.valueOf(i);
        //将OTP验证码同对应的手机号关联,使用httpSession的方式绑定他的手机号
        httpServletRequest.getSession().setAttribute(telephone,otpCode);

        //将OTP验证码通过段兴通道发送给用户，暂时不做
        System.out.println("telephone---->" + telephone + "&otpcode--->" + otpCode);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用service服务获取对应的id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应的用户信息不存在
        if (userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
            //{"status":"fail","data":{"errMsg":"未知错误","errorCode":100002}}
//            userModel.setEncrpt_password("123");
        }
        UserVO vo =  convertFromModel(userModel);
        return CommonReturnType.create(vo);

    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null){
            return  null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }

    //定义exceptionhandle解决未被controller层吸收的exception
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handleException(HttpServletRequest request,Exception ex){
//        Map<String, Object> responseData = new HashMap<>();
//        if (ex instanceof BusinessException){
//            BusinessException businessException = (BusinessException)ex;
//            CommonReturnType commonReturnType = new CommonReturnType();
//            commonReturnType.setStatus("fail");
//            responseData.put("errorCode",businessException.getErrorCode());
//            responseData.put("errMsg",businessException.getErrorMessage());
//        }else {
//            responseData.put("errorCode",EmBusinessError.UNKNOWN_ERROR.getErrorCode());
//            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrorMessage());
//        }
//        return CommonReturnType.create(responseData,"fail");
//
//
////        commonReturnType.setData(responseData);
////        return commonReturnType;
//    }
}
