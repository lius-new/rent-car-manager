package com.lius.dao;

import com.lius.entity.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarMapper {
    @Insert("insert into car(car_name,car_user_id,car_desc,car_content,car_status) values(#{carName},#{carUserId},#{carDesc},#{carContent},#{carStatus});")
    int insertCar(Car car);

    @Update("update car set car_name=#{carName},car_desc=#{carDesc},car_content=#{carContent},car_status=#{carStatus},rent_id=#{rentId} where id = #{id}")
    int updateCar(Car car);

    @Update("update car set car_name=#{carName},car_desc=#{carDesc},car_content=#{carContent},car_status=#{carStatus},rent_id=#{rentId} where id = #{id}")
    int updateCarAll(Car car);

    @Update("update car set rent_id=#{rentId} where id=#{id}")
    int updateCarRentId(@Param("rentId") Integer rentId, @Param("id") Integer id);

    @Delete("delete from car where id=#{id}")
    int deleteCarById(String id);

    @Select("SELECT car.id as id, car.car_name as carName, car.car_desc as carDesc,car.car_status as carStatus , car.rent_id as rentId,user.id as uid, user.user_name as userName, rent.id as rid,rent_start_date,rent_end_date\n" +
            "    FROM `car-rent`.car\n" +
            "    INNER JOIN user ON car.car_user_id = user.id\n" +
            "    LEFT JOIN rent ON car.rent_id = rent.id;")
    @ResultMap("CarManagerTable")
    List<Car> selectAllCar();

    @Select("select * from car where car_name like CONCAT('%', #{carName},'%')")
    Car selectCarByCarName(@Param("carName") String carName);

    @Select("select car.id,user.id as uid,user_name,car_name,car_desc,car_status,rent_id from car,user where car.car_user_id=user.id and user_name=#{userName}")
    @ResultMap("CarAndUser")
    List<Car> selectCarByUser(@Param("userName") String userName);


    @Select("SELECT car.id,user.id as uid,user_name,car.rent_id, car.car_name, car.car_desc, rent.id as rid,rent_start_date,rent_end_date FROM `car-rent`.car INNER JOIN user ON car.car_user_id = user.id LEFT JOIN rent ON car.rent_id = rent.id;")
    @ResultMap("CarAndUserAndRent")
    List<Car> selectAllCarAndUserAndRent();

    @Select("select car.id,car.rent_id as rentId,r.rent_start_date,r.rent_end_date from car inner join rent r on car.rent_id = r.id;")
    @ResultMap("rentCar")
    List<Car> selectRentCar();

}
