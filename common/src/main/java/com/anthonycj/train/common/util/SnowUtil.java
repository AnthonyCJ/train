package com.anthonycj.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * 封装hutool雪花算法
 * 封装的目的在于隐藏雪花算法用到的2个参数：数据中心、机器标识
 */
public class SnowUtil {

    private static long dataCenterId = 1;  // 数据中心
    private static long workerId = 1;     // 机器标识

    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextId();
    }

    public static String getSnowflakeNextIdStr() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextIdStr();
    }
}
