package com.tubugs.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuzhang on 2017/6/27.
 */
public class DateUtil {

    /**
     * 获取当前日期对应的字符串（常用的有yyyyMMdd，yyyyMMddHHmmss）
     *
     * @param pattern
     * @return
     */
    public static String getString(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(new Date());
    }

    /**
     * 获取当前时间对应的毫秒数
     *
     * @return
     */
    public static long getTime() {
        Date d = new Date();
        return d.getTime();
    }
}
