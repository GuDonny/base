package org.donny.base.common.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * RandomUtil
 *
 * @author 1792998761@qq.com
 * @date 2019/6/3
 */
public class RandomUtil {


    private RandomUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 功能描述: <br>
     * 生成随机数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 在[min, max]区间内的随机数
     * @date 2019/6/3
     * @author 1792998761@qq.com
     */
    public static int random(int min, int max) {
        Random random = new SecureRandom();
        return random.nextInt(max) % (max - min + 1) + min;
    }

}
