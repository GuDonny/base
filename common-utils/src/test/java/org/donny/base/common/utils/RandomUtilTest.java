package org.donny.base.common.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author 19057578
 * @date 2020/2/14 15:20
 */
public class RandomUtilTest {

    @Test
    public void random() {
        int x = RandomUtil.random(2, 10);
        assertTrue(x >= 2 && x <= 10);
    }
}