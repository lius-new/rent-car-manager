package com.lius.entity;

public class Car {
    private Integer id;
    private String carName;
    private String carUserId;
    private String carDesc;
    private String carContent;
    private Integer carStatus;

    private Integer rentId;

    private User user;
    private Rent rent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarUserId() {
        return carUserId;
    }

    public void setCarUserId(String carUserId) {
        this.carUserId = carUserId;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public String getCarContent() {
        return carContent;
    }

    public void setCarContent(String carContent) {
        this.carContent = carContent;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", carUserId='" + carUserId + '\'' +
                ", carDesc='" + carDesc + '\'' +
                ", carContent='" + carContent + '\'' +
                ", carStatus=" + carStatus +
                ", rentId=" + rentId +
                ", user=" + user +
                ", rent=" + rent +
                '}';
    }
}
