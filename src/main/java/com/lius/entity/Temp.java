package com.lius.entity;

public class Temp {
    private Integer id;
    private String carName;
    private String carDesc;
    private String carContent;
    private Integer carStatus;

    private User users;

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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", carDesc='" + carDesc + '\'' +
                ", carContent='" + carContent + '\'' +
                ", carStatus=" + carStatus +
                ", users=" + users +
                '}';
    }
}
