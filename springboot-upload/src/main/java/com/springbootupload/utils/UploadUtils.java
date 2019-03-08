package com.springbootupload.utils;

import java.io.*;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 文件上传工具类
 * @date 2018/12/11 16:43
 */
public class UploadUtils {

    /**
     * 保存文件到本地
     *
     * @param input
     * @param directory
     * @param filename
     * @return
     */
    public static boolean saveFile(InputStream input, String directory, String filename) {
        BufferedOutputStream out = null;
        try {
            mkdirs(directory);
            out = new BufferedOutputStream(new FileOutputStream(directory + filename));
            byte[] b = new byte[1024];
            int length;
            while ((length = input.read(b)) != -1) {
                out.write(b, 0, length);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 目录不存在则新建
     *
     * @param directory
     */
    private static void mkdirs(String directory) {
        File file = new File(directory);
        if (!file.exists()) file.mkdirs();
    }

}
