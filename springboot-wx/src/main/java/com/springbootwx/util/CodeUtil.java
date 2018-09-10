package com.springbootwx.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 二维码生成
 * @date 2018/7/16 10:57
 */
public class CodeUtil {


    /**
     * 生成二维码
     *
     * @param val 二维码内容
     */
    public static BitMatrix generateEWM(String val) throws WriterException {
        // 实例化对象
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        // 设置参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(val, BarcodeFormat.QR_CODE, 300, 300, hints);
        //返回二维码对象
        return bitMatrix;
    }

    /**
     * 生成jpg格式的二维码
     * @param bitMatrix
     * @param response
     * @throws IOException
     */
    public static void jpgEWM(BitMatrix bitMatrix, HttpServletResponse response) throws IOException {
        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());
    }

}
