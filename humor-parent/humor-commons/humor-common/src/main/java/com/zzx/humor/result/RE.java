package com.zzx.humor.result;


/**
 * TODO 返回结果 枚举
 */
public enum RE {
    /**
     * 操作成功！
     */
    OK(200, "OK"),

    /**
     * 访问被禁止
     */
    FORBIDDEN(403,"Access is prohibited"),

    /**
     * 未授权的请求
     */
    UNAUTHORIZED(401,"Unauthorized requests"),

    /**
     * 超时
     */
    TIMEOUT(504,"timeout"),

    /**
     * 操作失败！
     */
    FAILED(500, "operation failed");

    /**
     * 状态码
     */
    private long code;

    /**
     * 消息
     */
    private String msg;

    RE(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
}
