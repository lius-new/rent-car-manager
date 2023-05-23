package com.lius.service.impl;

import com.lius.dao.CarMapper;
import com.lius.entity.Car;
import com.lius.entity.CarShow;
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
        return carMapper.insertCar(car);
    }

    @Override
    public int updateCar(Car car) {
        return carMapper.updateCar(car);
    }

    @Override
    public int deleteCarById(int id) {
        return carMapper.deleteCarById(id);
    }

    @Override
    public List<CarShow> selectAllCar() {
        return carMapper.selectAllCar();
    }

    @Override
    public List<CarShow> selectCarByUser(String userName) {
        return carMapper.selectCarByUser(userName);
    }

    @Override
    public Car selectCarByCarName(String carName) {
        return carMapper.selectCarByCarName(carName);
    }
}
