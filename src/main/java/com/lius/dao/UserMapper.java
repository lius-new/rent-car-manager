package com.lius.dao;

import com.lius.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(user_name,user_password,user_email,user_role,user_status) values(#{userName},#{userPassword},#{userEmail},#{userRole},#{userStatus})")
    int insertUser(User user);

    @Update("update user set user_name=#{userName},user_password=#{userPassword},user_email=#{userEmail},user_role=#{userRole},user_status=#{userStatus} where id = #{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{id}")
    User deleteUserById(int id);

    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where user_name like CONCAT('%', #{user_name},'%')")
    User selectUserByUserName(@Param("user_name") String username);

    @Select("select * from user where user_name=#{userName} and user_password=#{userPassword} and user_status!='1'")
    User login(User user);


}
