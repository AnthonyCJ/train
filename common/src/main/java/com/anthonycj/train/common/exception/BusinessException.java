package com.anthonycj.train.common.exception;

public class BusinessException extends RuntimeException {

    private com.anthonycj.train.common.exception.BusinessExceptionEnum e;

    public BusinessException(com.anthonycj.train.common.exception.BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 获取枚举值
     * @return 枚举值
     */
    public com.anthonycj.train.common.exception.BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(com.anthonycj.train.common.exception.BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，取消原本递归调用打印日志，直接返回当前异常类，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
