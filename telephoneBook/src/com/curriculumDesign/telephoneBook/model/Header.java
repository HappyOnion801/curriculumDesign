package com.curriculumDesign.telephoneBook.model;

import java.io.Serializable;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public class Header implements Serializable {
    private static final long serialVersionUID = 6215558965566862529L;

    public static final int CODE_REQUESTS = 600;

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_OPERATION_ERROR = 400;
    public static final int CODE_TIME_ERROR = 401;
    public static final int CODE_DATA_ERROR = 402;

    public static final int OPERATION_LOGIN = 1;
    public static final int OPERATION_LOGOUT = -1;
    public static final int OPERATION_INSERT = 2;
    public static final int OPERATION_DELETE = -2;
    public static final int OPERATION_UPDATE = 0;
    public static final int OPERATION_GET = 3;
    public static final int OPERATION_NULL = 500;

    private int code;
    private int operation;
    private String ip;
    private long time;
    private String cookie;

    public Header(int code, int operation, String ip, long time) {
        this.code = code;
        this.operation = operation;
        this.ip = ip;
        this.time = time;
    }

    public Header(int code, int operation, String ip, long time, String cookie) {
        this.code = code;
        this.operation = operation;
        this.ip = ip;
        this.time = time;
        this.cookie = cookie;
    }

    public int getCode() {
        return code;
    }

    public int getOperation() {
        return operation;
    }

    public String getIp() {
        return ip;
    }

    public long getTime() {
        return time;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCookie() {
        return cookie;
    }

    @Override
    public String toString() {
        return "Header{" + "code=" + code + ", operation=" + operation + ", ip='" + ip + '\'' + ", Time=" + time + '}';
    }
}
