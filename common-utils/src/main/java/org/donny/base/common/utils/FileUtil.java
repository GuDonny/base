package org.donny.base.common.utils;


import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

/**
 * File使用帮助工具类
 *
 * @author 1792998761@qq.com
 * @date 2019/11/21 14:44
 */
public class FileUtil {

    /**
     * 数据存储单位类型 B
     */
    public static final int STORAGE_UNIT_TYPE_B = 0;
    /**
     * 数据存储单位类型 KB
     */
    public static final int STORAGE_UNIT_TYPE_KB = 1;
    /**
     * 数据存储单位类型 MB
     */
    public static final int STORAGE_UNIT_TYPE_MB = 2;
    /**
     * 数据存储单位类型 GB
     */
    public static final int STORAGE_UNIT_TYPE_GB = 3;
    /**
     * 数据存储单位类型 TB
     */
    public static final int STORAGE_UNIT_TYPE_TB = 4;
    /**
     * 数据存储单位类型 PB
     */
    public static final int STORAGE_UNIT_TYPE_PB = 5;
    /**
     * 数据存储单位类型 EB
     */
    public static final int STORAGE_UNIT_TYPE_EB = 6;
    /**
     * 数据存储单位类型 ZB
     */
    public static final int STORAGE_UNIT_TYPE_ZB = 7;
    /**
     * 数据存储单位类型 YB
     */
    public static final int STORAGE_UNIT_TYPE_YB = 8;
    /**
     * 数据存储单位类型 BB
     */
    public static final int STORAGE_UNIT_TYPE_BB = 9;
    /**
     * 数据存储单位类型 NB
     */
    public static final int STORAGE_UNIT_TYPE_NB = 10;
    /**
     * 数据存储单位类型 DB111
     */
    public static final int STORAGE_UNIT_TYPE_DB = 11;


    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 读取json文件
     *
     * @param fileName 完整路径
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        File jsonFile = new File(fileName);
        try (Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8)) {
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 将文件大小转为人类惯性理解方式
     *
     * @param size               大小 单位默认B
     * @param decimalPlacesScale 精确小数位
     * @return
     */
    public static String fileSizeByteConversion(Long size, Integer decimalPlacesScale) {
        int scale = 0;
        long fileSize = 0L;
        if (decimalPlacesScale != null && decimalPlacesScale >= 0) {
            scale = decimalPlacesScale;
        }
        if (size != null && size >= 0) {
            fileSize = size;
        }
        return sizeByteConversion(fileSize, scale, STORAGE_UNIT_TYPE_B);
    }

    /**
     * 将文件大小转为人类惯性理解方式
     *
     * @param size               大小
     * @param decimalPlacesScale 精确小数位
     * @param storageUnitType    起始单位类型
     * @return
     */
    public static String fileSizeByteConversion(Long size, Integer decimalPlacesScale, int storageUnitType) {
        int scale = 0;
        long fileSize = 0L;
        if (decimalPlacesScale != null && decimalPlacesScale >= 0) {
            scale = decimalPlacesScale;
        }
        if (size != null && size >= 0) {
            fileSize = size;
        }
        return sizeByteConversion(fileSize, scale, storageUnitType);
    }

    private static String sizeByteConversion(long size, int decimalPlacesScale, int storageUnitType) {
        BigDecimal fileSize = new BigDecimal(size);
        BigDecimal param = new BigDecimal(1024);
        int count = storageUnitType;
        while (fileSize.compareTo(param) > 0 && count < STORAGE_UNIT_TYPE_NB) {
            fileSize = fileSize.divide(param, decimalPlacesScale, RoundingMode.HALF_UP);
            count++;
        }
        StringBuilder dd = new StringBuilder();
        int s = decimalPlacesScale;
        dd.append("0");
        if (s > 0) {
            dd.append(".");
        }
        while (s > 0) {
            dd.append("0");
            s = s - 1;
        }
        DecimalFormat df = new DecimalFormat(dd.toString());
        String result = df.format(fileSize) + "";
        switch (count) {
            case STORAGE_UNIT_TYPE_B:
                result += "B";
                break;
            case STORAGE_UNIT_TYPE_KB:
                result += "KB";
                break;
            case STORAGE_UNIT_TYPE_MB:
                result += "MB";
                break;
            case STORAGE_UNIT_TYPE_GB:
                result += "GB";
                break;
            case STORAGE_UNIT_TYPE_TB:
                result += "TB";
                break;
            case STORAGE_UNIT_TYPE_PB:
                result += "PB";
                break;
            case STORAGE_UNIT_TYPE_EB:
                result += "EB";
                break;
            case STORAGE_UNIT_TYPE_ZB:
                result += "ZB";
                break;
            case STORAGE_UNIT_TYPE_YB:
                result += "YB";
                break;
            case STORAGE_UNIT_TYPE_DB:
                result += "DB";
                break;
            case STORAGE_UNIT_TYPE_NB:
                result += "NB";
                break;
            case STORAGE_UNIT_TYPE_BB:
                result += "BB";
                break;
            default:
                break;
        }
        return result;
    }
}