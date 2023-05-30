package com.lius.service.impl;

import com.lius.dao.RentMapper;
import com.lius.entity.Car;
import com.lius.entity.Rent;
import com.lius.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentMapper rentMapper;

    @Override
    public Integer insertRent(Rent rent) {
        return rentMapper.insertRent(rent);
    }

    @Override
    public Integer updateRent(Integer rentId, Integer rentUserId, Date rentStartDate, Date rentEndDate) {
        return rentMapper.updateRent(rentId, rentUserId, rentStartDate, rentEndDate);
    }

    @Override
    public List<Car> selectAllRent() {
        return rentMapper.selectAllRent();
    }

    @Override
    public List<Rent> selectByCarId(String rentCarId) {
        return rentMapper.selectByCarId(rentCarId);
    }

    @Override
    public Integer rentCar(Rent rent) {
        return rentMapper.rentCar(rent);
    }

    @Override
    public Rent selectCurrentRent(Integer rentUserId, Integer rentCarId) {
        return rentMapper.selectCurrentRent(rentUserId, rentCarId);
    }

    @Override
    public Integer deleteRent(String userName, String cId) {
        return rentMapper.deleteRent(userName, cId);
    }
}
