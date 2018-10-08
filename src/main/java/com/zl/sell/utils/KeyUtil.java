package com.zl.sell.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成随机数主键，使用当前时间+随机数保证唯一性.
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer randNum = random.nextInt(900000) + 100000;
        String key = System.currentTimeMillis() + String.valueOf(randNum);
        return key;
    }

}
