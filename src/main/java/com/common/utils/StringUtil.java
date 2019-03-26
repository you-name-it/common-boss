package com.common.utils;

import java.util.UUID;

/**
 * Created by laizhilong on 2017/4/29.
 */
public class StringUtil {

    /**
     * 使用uuid生成唯一的字符串(大写)
     *
     * @return
     */
    public static String uuid() {
        // replace(char arg0,char arg1) 两个参数不能是''。可替换所有字符。
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
