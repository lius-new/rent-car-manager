package com.lius.dao;

import com.lius.entity.Car;
import com.lius.entity.Rent;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RentMapper {

    @Insert("insert into rent(rent_user_id,rent_car_id,rent_start_date,rent_end_date) values(#{rentUserId},#{rentCarId},#{rentStartDate},#{rentEndDate})")
    Integer insertRent(Rent rent);

    @Update("update rent set rent_user_id=#{rentUserId} ,rent_start_date=#{rentStartDate},rent_end_date=#{rentEndDate}  where id=#{rentId}")
    Integer updateRent( @Param("rentId") Integer rentId,@Param("rentUserId") Integer rentUserId, @Param("rentStartDate") Date rentStartDate, @Param("rentEndDate") Date rentEndDate);

    @Select("select rent.id as rid,user_name,car_name,rent_start_date,rent_end_date from rent,user,car where rent_car_id = car.id and rent.rent_user_id = user.id;")
    @ResultMap("Rent")
    List<Car> selectAllRent();

    @Insert("insert into rent(rent_user_id, rent_car_id, rent_start_date, rent_end_date) values (#{rentUserId},#{rentCarId},#{rentStartDate},#{rentEndDate});")
    Integer rentCar(Rent rent);

    @Select("select * from rent where rent_user_id=#{rentUserId} and rent_car_id=#{rentCarId};")
    Rent selectCurrentRent(@Param("rentUserId") Integer rentUserId, @Param("rentCarId") Integer rentCarId);

    @Select("select * from rent where rent_car_id=#{rentCarId}")
    List<Rent> selectByCarId(String rentCarId);

    //@Delete("delete rent from rent left join car c on c.rent_id = rent.id where c.rent_id is null and rent_car_id = #{cId} and now() > rent_end_date;")
    @Delete("delete rent from rent left join car c on c.rent_id = rent.id where c.rent_id is null and rent_car_id = #{cId};")
    Integer deleteRent(@Param("userName") String userName, @Param("cId") String cId);

}
