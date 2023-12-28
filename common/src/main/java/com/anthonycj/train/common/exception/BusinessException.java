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

}
