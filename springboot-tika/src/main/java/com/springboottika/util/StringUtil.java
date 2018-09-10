package com.springboottika.util;

public class StringUtil {

    /**
     * 字符串搜索
     *
     * @param str         字符串
     * @param start       例如:摘要内容前100个长度
     * @param end         例如：摘要内容后100个长度
     * @param searchValue 要搜索的值
     * @return
     */
    public static String strSearch(String str, String searchValue, int start, int end) {
        // 获取搜索字符串的下标
        int index = str.indexOf(searchValue.trim());
        // 判断下表是否存在 存在则获取前 start长度 + searchValue + end长度
        if (index != -1) {
            return str.substring(index - start, index + (searchValue.length() - 1) + end);
        }
        return null;
    }

}
