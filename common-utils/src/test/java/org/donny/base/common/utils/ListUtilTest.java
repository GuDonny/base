package org.donny.base.common.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author 19057578
 * @date 2020/2/14 15:18
 */
public class ListUtilTest {

    @Test
    public void removeRepeatStringItems() {
        List a = new ArrayList();
        a.add("1");
        a.add("1");
        a.add("1");
        a.add("1");
        a.add("2");
        a = ListUtil.removeRepeatStringItems(a);
        assertTrue("1".equals(a.get(0)) && a.size() == 2 && "2".equals(a.get(1)));
    }
}