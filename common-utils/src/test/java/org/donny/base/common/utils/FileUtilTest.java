package org.donny.base.common.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author 19057578
 * @date 2020/2/14 15:18
 */
public class FileUtilTest {

    @Test
    public void fileSizeByteConversion() {
        assertTrue("1024B".equals(FileUtil.fileSizeByteConversion(1024L, null)));
        assertTrue("1024.0B".equals(FileUtil.fileSizeByteConversion(1024L, 1)));
        assertTrue("1024.00B".equals(FileUtil.fileSizeByteConversion(1024L, 2)));
        assertTrue("1024.000B".equals(FileUtil.fileSizeByteConversion(1024L, 3)));
        assertTrue("1024.0000B".equals(FileUtil.fileSizeByteConversion(1024L, 4)));
        assertTrue("1024.00000B".equals(FileUtil.fileSizeByteConversion(1024L, 5)));
        assertTrue("6.86KB".equals(FileUtil.fileSizeByteConversion(7025L, 2)));
    }

    @Test
    public void fileSizeByteConversion2() {
        String a = FileUtil.fileSizeByteConversion(1888L, 2, FileUtil.STORAGE_UNIT_TYPE_MB);
        String b = FileUtil.fileSizeByteConversion(1888 * 1024 * 1024L, 2, FileUtil.STORAGE_UNIT_TYPE_B);
        assertTrue(a.equals(b));
    }
}