package com.miaoshaproject.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModel {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotNull(message = "性别不能不填写")
    private Integer gender;

    @NotNull(message = "年龄不能不填写")
    @Min(value = 0,message = "年龄必须大于0")
    @Max(value = 150,message = "年龄必须小于150")
    private Integer age;

    @NotBlank(message = "手机号不能为空")
    private String telephone;
    private String register_mode;
    private String third_party_id;

    @NotBlank(message = "密码不能为空")
    private String encrpt_password;


    public String getEncrpt_password() {
        return encrpt_password;
    }

    public void setEncrpt_password(String encrpt_password) {
        this.encrpt_password = encrpt_password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegister_mode() {
        return register_mode;
    }

    public void setRegister_mode(String register_mode) {
        this.register_mode = register_mode;
    }

    public String getThird_party_id() {
        return third_party_id;
    }

    public void setThird_party_id(String third_party_id) {
        this.third_party_id = third_party_id;
    }
}
