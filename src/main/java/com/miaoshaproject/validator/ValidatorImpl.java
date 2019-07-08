package com.miaoshaproject.validator;


import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {


    private Validator validator;

    //实现校验方法
    public ValidationResult validate(Object object){
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> validateSet = validator.validate(object);
        if (validateSet.size()>0){
            //有错误
            result.setHasError(true);
            validateSet.forEach(constraintViolation->{
                String message = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName,message);

            });
        }

        return result;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();


    }
}
