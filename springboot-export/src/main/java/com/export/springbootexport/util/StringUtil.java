package com.export.springbootexport.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 字符串工具类
 * @date 2018/7/30 19:44
 */
public class StringUtil {

    /**
     * 获取随机长度的字符串
     *
     * @param length
     * @return
     */
    public static StringBuffer randomStr(int length) {
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * 26);
            sb.append(str[index]);
        }
        return sb;
    }

    /**
     * 判断字符串不等于空字符串
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return (null != str && !str.trim().equals(""));
    }

    /**
     * 判断字符串等于空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (null == str && str.trim().equals(""));
    }

    /**
     * 将date转换成年月日时分秒
     *
     * @param date
     * @return
     */
    public static String getDateStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static void main(String[] args) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        String format = getDateStr(date);
        System.err.println(format);
    }

    /**
     * 从左边截取字符串
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String subLeft(String str, int start, int end) {
        return str.substring(start, end != -1 ? end : str.length());
    }

    /**
     * 从左边截取字符串
     *
     * @param str
     * @param start
     * @param end   根据字符串查找最后下标
     * @return
     */
    public static String subLeft(String str, int start, String end) {
        int index = str.indexOf(end);
        return str.substring(start, index != -1 ? index : str.length());
    }

    /**
     * 拼接solr查询条件
     *
     * @param highlighting 结果map
     * @return
     */
    public static String append(Map<String, Map<String, List<String>>> highlighting) {
        // 将key拼接成查询条件
        StringBuilder sb = new StringBuilder();
        for (String key : highlighting.keySet()) {
            sb.append(" OR fileName:").append(key);
        }
        return sb.substring(4);
    }


}
