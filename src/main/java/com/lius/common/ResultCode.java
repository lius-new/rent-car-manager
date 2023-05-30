package com.lius.common;

public enum ResultCode {

    LOGIN_SUCCESS(1001, "登录成功"), LOGIN_FAIL(1002, "登录失败"),
    LOGIN_FAIL_STATUS_ERROR(10021, "登录失败,该用户被禁用"),
    REGISTER_SUCCESS(1003, "注册成功"), REGISTER_FAIL(1004, "注册失败"),
    LOGIN_ACTIVE_STATUS(1005, "登录状态中"), LOGIN_DEAD_STATUS(1006, "未在登录状态中"),
    CHECK_TOKEN_SUCCESS(1007, "token校验成功"), CHECK_TOKEN_FAIL(1008, "toke检验失败"),
    CHECK_TOKEN_EXPIRED_SUCCESS(1009, "token过期,要求重新登录"),
    CHECK_TOKEN_ERROR(1010, "token异常"),
    RUN_OPERATE_SUCCESS(1011, "执行操作完成"),
    RUN_OPERATE_ERROR(1012, "执行操作失败"),
    REQUEST_PARAM_ERROR(1013, "请求参数异常"),
    DATABASE_COL_ERROR(1101, "数据库字段错误"),
    DATABASE_OPERATE_SUCCESS(1102, "数据库返回内容"), DATABASE_OPERATE_FAIL(1103, "数据库未返回内容");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
