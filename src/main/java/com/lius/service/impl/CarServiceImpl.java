package com.lius.service.impl;

import com.lius.dao.CarMapper;
import com.lius.entity.Car;
import com.lius.entity.User;
import com.lius.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public int insertCar(Car car) {
        return 0;
    }

    @Override
    public int updateCar(Car car) {
        return 0;
    }

    @Override
    public User deleteCarById(int id) {
        return null;
    }

    @Override
    public List<User> selectAllCar() {
        return null;
    }

    @Override
    public User selectCarByCarName(String username) {
        return null;
    }
}
