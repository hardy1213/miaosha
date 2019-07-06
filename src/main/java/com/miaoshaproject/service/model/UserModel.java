package com.miaoshaproject.service.model;

public class UserModel {

    private Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private String telephone;
    private String register_mode;
    private String third_party_id;
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
