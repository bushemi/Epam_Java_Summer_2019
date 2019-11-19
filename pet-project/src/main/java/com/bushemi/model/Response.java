package com.bushemi.model;

public class Response {
    private int code;
    private Object data;

    public Response() {
    }

    public Response(int code) {
        this.code = code;
    }

    public Response(int code, Object data) {
        this(code);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{"
                + "code=" + code
                + ", data=" + data
                + '}';
    }
}
