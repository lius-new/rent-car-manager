package com.lius.service.impl;

import com.lius.dao.CarMapper;
import com.lius.entity.Car;
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
    public int updateCarAll(Car car) {
        return carMapper.updateCarAll(car);
    }

    @Override
    public int deleteCarById(String id) {
        return carMapper.deleteCarById(id);
    }

    @Override
    public List<Car> selectAllCar() {
        return carMapper.selectAllCar();
    }

    @Override
    public List<Car> selectRentCar() {
        return carMapper.selectRentCar();
    }

    @Override
    public List<Car> selectAllCarAndUserAndRent() {
        return carMapper.selectAllCarAndUserAndRent();
    }

    @Override
    public List<Car> selectCarByUser(String userName) {
        return carMapper.selectCarByUser(userName);
    }

    @Override
    public Car selectCarByCarName(String carName) {
        return carMapper.selectCarByCarName(carName);
    }

    @Override
    public int updateCarRentId(Integer rentId, Integer id) {
        return carMapper.updateCarRentId(rentId, id);
    }


}
