package com.we.weblog.mapping;


import com.we.weblog.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 *    @TODO 用户数据Mapper 处理用户的登陆什么的
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 根据用户名搜索User
     * @param username
     * @return
     */
    @Select({"select * from t_user where username=#{name}"})
    User searchUserByName(@Param("name") String username);



    //TODO 修改密码
}