package com.zzx.humor.result;

import lombok.Data;

import java.io.Serializable;

/**
 *  TODO 返回值
 */
@Data
public class R<T> implements Serializable {

    private long code;
    private T data;
    private String msg;

    private R(){}

    public static <T> R<T> failed(String msg){
        return restResult(null,RE.FAILED.getCode(),msg);
    }
    public static <T> R<T> failed(){
        return restResult(null,RE.FAILED.getCode(),RE.FAILED.getMsg());
    }
    public static <T> R<T> ok(){
        return restResult(null,RE.OK.getCode(),RE.OK.getMsg());
    }
    public static <T> R<T> ok(String msg){
        return restResult(null,RE.OK.getCode(),msg);
    }
    public static <T> R<T> ok(T data){
        return restResult(data,RE.OK.getCode(),RE.OK.getMsg());
    }
    public static <T> R<T> ok(T data,long code,String msg){
        return restResult(data,code,msg);
    }
    public static <T> R<T> other(RE re){
        return restResult(null,re.getCode(),re.getMsg());
    }
    private static <T> R<T> restResult(T data, long code, String msg) {
        R<T> r = new R();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
}
