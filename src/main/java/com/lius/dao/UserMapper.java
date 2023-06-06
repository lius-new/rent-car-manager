package com.lius.dao;

import com.lius.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // TODO: 默认userRole=USER,userStatus=0
    @Insert("insert into user(user_name,user_password,user_email,user_role,user_status) values(#{userName},#{userPassword},#{userEmail},'USER',0)")
    int insertUser(User user);

    @Update("update user set user_name=#{userName},user_password=#{userPassword},user_email=#{userEmail},user_role=#{userRole},user_status=#{userStatus},balance=#{balance} where id = #{id}")
    int updateUser(User user);

    @Update("update user set user_role=#{userRole} where id = #{id}")
    int changeUserRole(User user);

    @Delete("delete from user where user_name=#{userName}")
    int deleteUserByUserName(String userName);

    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where user_name=#{username}")
    User selectIdByUserName(String username);

    @Select("select * from user where user_name like CONCAT('%', #{user_name},'%')")
    List<User> selectUserByUserNameLike(@Param("user_name") String username);

    @Select("select * from user where user_name=#{user_name}")
    User selectUserByUserName(@Param("user_name") String username);

    @Select("select * from user where user_name=#{userName} and user_password=#{userPassword}")
    User login(User user);

    @Select("select * from user where user_name=#{userName} and id=#{id} and user_role=#{userRole} and user_status!=1")
    List<User> checkUser(User user);

    @Update("update user set balance=balance - 40,user_role='VIP' where user_name = #{userName} and balance > 40")
    int toVip(User user);
}
