package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.UserDaoMapper;
import com.miaoshaproject.dao.UserPasswordDaoMapper;
import com.miaoshaproject.dataobject.UserDao;
import com.miaoshaproject.dataobject.UserPasswordDao;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoMapper userDaoMapper;

    @Autowired
    private UserPasswordDaoMapper userPasswordDaoMapper;

    @Autowired
    private ValidatorImpl validator;


    @Override
    public UserModel getUserById(Integer id) {
        //调用userdomapper获取dataobject对象
        UserDao userDao = userDaoMapper.selectByPrimaryKey(id);

        if (userDao == null){
            return null;
        }
        //通过用户id获取对应的用户加密密码信息
        UserPasswordDao userPasswordDao = userPasswordDaoMapper.selectByUserId(userDao.getId());
        return convertFromDataObject(userDao,userPasswordDao);


    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
//        if (StringUtils.isEmpty(userModel.getName()) || userModel.getGender() == null
//                || userModel.getAge() == null || StringUtils.isEmpty(userModel.getTelephone())){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }

        //上面的if判断换做validator实现
        ValidationResult result = validator.validate(userModel);


        if (result.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }


        //实现model --dataobject 方法
        UserDao userDao = convertFromModel(userModel);
        try {
            //手机号唯一索引，会引起的异常
            userDaoMapper.insertSelective(userDao);
        }catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号重复注册！");
        }


        userModel.setId(userDao.getId());


        UserPasswordDao userPasswordDao = convertPasswordFromModel(userModel);
        System.out.println(userPasswordDao.getEncrptPassword() + userPasswordDao.getId() + userPasswordDao.getUserId());
        userPasswordDaoMapper.insertSelective(userPasswordDao);

        return;

    }

    @Override
    public UserModel validateLogin(String telephone, String password) throws BusinessException {
        //通过用户的手机获取用户信息
        UserDao userDao = userDaoMapper.selectByTelePhone(telephone);
        if (userDao == null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        UserPasswordDao userPasswordDao = userPasswordDaoMapper.selectByUserId(userDao.getId());
        UserModel userModel = convertFromDataObject(userDao, userPasswordDao);

        //比对用户信息加密的密码是否和传输进来的密码匹配
        if (!StringUtils.equals(password,userModel.getEncrpt_password())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        return userModel;

    }

    private UserPasswordDao convertPasswordFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserPasswordDao userPasswordDao = new UserPasswordDao();
        userPasswordDao.setEncrptPassword(userModel.getEncrpt_password());
        userPasswordDao.setUserId(userModel.getId());
        System.out.println("userModel.getId()----->" + userModel.getId() );
        return userPasswordDao;
    }

    private UserDao convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserDao userDao = new UserDao();
        BeanUtils.copyProperties(userModel,userDao);
        return userDao;
    }

    private UserModel convertFromDataObject(UserDao userDo, UserPasswordDao userPasswordDao){
        if (userDo == null){
            return  null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDo,userModel);
        if (userPasswordDao != null){
            userModel.setEncrpt_password(userPasswordDao.getEncrptPassword());
        }

        return userModel;


    }
}
