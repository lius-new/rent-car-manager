package com.lius.dao;

import com.lius.entity.Car;
import com.lius.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarMapper {
    @Insert("insert into user(car_name,car_user_id,car_desc,car_content,car_status) values(#{carName},#{carUserId},#{carDesc},#{carContent},#{carStatus})")
    int insertCar(Car car);

    @Update("update car set car_name=#{carName},car_user_id=#{carUserId},car_desc=#{carDesc},car_content=#{carContent},car_status=#{carStatus} where id = #{id}")
    int updateCar(Car car);

    @Delete("delete from car where id = #{id}")
    User deleteCarById(int id);

    @Select("select * from car")
    List<User> selectAllCar();

    @Select("select * from car where car_name like CONCAT('%', #{username},'%')")
    User selectCarByCarName(@Param("user_name") String username);
}
