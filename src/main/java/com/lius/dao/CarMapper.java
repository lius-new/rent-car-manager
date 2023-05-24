package com.lius.dao;

import com.lius.entity.Car;
import com.lius.entity.CarShow;
import com.lius.entity.Temp;
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
    int deleteCarById(int id);

    @Select("select car.id,user_name,car_name,car_desc,car_status,rent_id from car,user where car.car_user_id=user.id;")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "car_name", property = "carName"),
            @Result(column = "car_desc", property = "carDesc"),
            @Result(column = "car_status", property = "carStatus"),
            @Result(column = "rent_id", property = "rentId"),
    })
    List<CarShow> selectAllCar();

    @Select("select * from car where car_name like CONCAT('%', #{carName},'%')")
    Car selectCarByCarName(@Param("carName") String carName);

    @Select("select car.id,user_name,car_name,car_desc,car_status,rent_id from car,user where car.car_user_id=user.id and user_name=#{userName}")
    @ResultMap("userMap")
    List<CarShow> selectCarByUser(@Param("userName") String userName);


    @Select("select car.id,user.id as uid,user_name,car_name,car_desc,car_status,rent_id from car,user where car.car_user_id=user.id;")
    @ResultMap("Temp")
    List<Temp> selectAllCarV2();


}
