package com.lius.entity;

import java.util.Date;

public class Rent {
    private Integer id;
    private Integer rent_user_id;
    private Integer rent_car_id;
    private Date rent_start_date;
    private Date rent_end_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRent_user_id() {
        return rent_user_id;
    }

    public void setRent_user_id(Integer rent_user_id) {
        this.rent_user_id = rent_user_id;
    }

    public Integer getRent_car_id() {
        return rent_car_id;
    }

    public void setRent_car_id(Integer rent_car_id) {
        this.rent_car_id = rent_car_id;
    }

    public Date getRent_start_date() {
        return rent_start_date;
    }

    public void setRent_start_date(Date rent_start_date) {
        this.rent_start_date = rent_start_date;
    }

    public Date getRent_end_date() {
        return rent_end_date;
    }

    public void setRent_end_date(Date rent_end_date) {
        this.rent_end_date = rent_end_date;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", rent_user_id=" + rent_user_id +
                ", rent_car_id=" + rent_car_id +
                ", rent_start_date=" + rent_start_date +
                ", rent_end_date=" + rent_end_date +
                '}';
    }
}
