package com.anthonycj.train.common.exception;

public enum BusinessExceptionEnum {

    // 枚举值（可以理解成枚举类的实例）
    // 需要增加一个自定义异常，就在此处添加一个枚举值
    MEMBER_MOBILE_EXIST("该手机号已被注册");

    // 枚举值（自定义异常）的描述信息
    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    /**
     * 获取枚举值（自定义异常）的描述信息
     * @return 枚举值（自定义异常）的描述信息
     */
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
