package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.UserDao;
import com.miaoshaproject.dataobject.UserDaoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDaoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    long countByExample(UserDaoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int deleteByExample(UserDaoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int insert(UserDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int insertSelective(UserDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    List<UserDao> selectByExample(UserDaoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    UserDao selectByPrimaryKey(Integer id);

    UserDao selectByTelePhone(String telephone);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserDao record, @Param("example") UserDaoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int updateByExample(@Param("record") UserDao record, @Param("example") UserDaoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int updateByPrimaryKeySelective(UserDao record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Sat Jul 06 14:16:19 CST 2019
     */
    int updateByPrimaryKey(UserDao record);
}