package com.lius.entity;

import java.util.Date;

public class Rent {
    private Integer id;
    private Integer rentUserId;
    private Integer rentCarId;
    private Date rentStartDate;
    private Date rentEndDate;

    public Rent() {
    }

    public Rent(Integer rentUserId, Integer rentCarId, Date rentStartDate, Date rentEndDate) {
        this.rentUserId = rentUserId;
        this.rentCarId = rentCarId;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRentUserId() {
        return rentUserId;
    }

    public void setRentUserId(Integer rentUserId) {
        this.rentUserId = rentUserId;
    }

    public Integer getRentCarId() {
        return rentCarId;
    }

    public void setRentCarId(Integer rentCarId) {
        this.rentCarId = rentCarId;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", rentUserId=" + rentUserId +
                ", rentCarId=" + rentCarId +
                ", rentStartDate=" + rentStartDate +
                ", rentEndDate=" + rentEndDate +
                '}';
    }
}
