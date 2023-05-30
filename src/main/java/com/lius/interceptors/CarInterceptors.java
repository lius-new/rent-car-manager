package com.lius.interceptors;

import com.lius.entity.Car;
import com.lius.service.CarService;
import com.lius.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Component
public class CarInterceptors implements HandlerInterceptor {

    @Autowired
    private CarService carService;

    // 请求前判断, 直接返回true
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断车辆租借是否过期，如果过期就将指定车辆变为未租借的状态, 主要还是修改rentId
        List<Car> cars = carService.selectRentCar();
        for (Car c : cars) {
            Date now = new Date();
            if (now.compareTo(c.getRent().getRentEndDate()) > 0) {
                carService.updateCarRentId(null,c.getId());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
