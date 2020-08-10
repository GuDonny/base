package org.donny.base.common.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ListUtil
 *
 * @author 1792998761@qq.com
 * @date 2020/1/23 15:07
 */
public class ListUtil {
    private ListUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Remove duplicate items<String> from the list
     *
     * @param list List<String>
     * @return
     */
    public static List<String> removeRepeatStringItems(List<String> list) {
        if (list != null && list.size() > 1) {
            return list.stream().distinct().collect(Collectors.toList());
        } else {
            return list;
        }

    }
}
