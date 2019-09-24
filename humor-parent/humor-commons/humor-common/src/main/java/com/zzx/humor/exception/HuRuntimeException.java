package com.zzx.humor.exception;

import com.zzx.humor.result.R;

/**
 * TODO 自定义 运行 异常
 */
public class HuRuntimeException extends RuntimeException {

    private R r;

    public HuRuntimeException(R r) {
        super(r.getMsg());
        this.r = r;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

}
