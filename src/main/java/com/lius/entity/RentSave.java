package com.lius.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RentSave {
    private Integer carId;
    private String userName;
    private Date startTime;
    private Date endTime;
//    private final SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
//        this.endTime = simpleDateFormat(this.endTime);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RentSave{" +
                "carId=" + carId +
                ", userName='" + userName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
