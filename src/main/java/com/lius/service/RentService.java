package com.lius.service;

import com.lius.entity.Car;
import com.lius.entity.Rent;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RentService {

    Integer insertRent(Rent rent);

    Integer updateRent(Integer rentId, Integer rentUserId, Date rentStartDate, Date rentEndDate);

    List<Car> selectAllRent();

    List<Rent> selectByCarId(String rentCarId);

    Integer rentCar(Rent rent);

    Rent selectCurrentRent(Integer rentUserId,Integer rentCarId);

    Integer deleteRent(String userName, String cId);
}
