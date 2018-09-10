package com.springbootwx.util;

import com.springbootwx.dto._ResultDto;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 微信工具类
 * @date 2018/7/16 11:31
 */
public class WxUtil {

    //错误code文件
    private static String filePath = "classpath:config/wx_error_code.json";

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //定义常用请求属性
    public static final String result_code = "result_code";
    public static final String out_trade_no = "out_trade_no";
    public static final String body = "body";
    public static final String device_info = "device_info";
    public static final String fee_type = "fee_type";
    public static final String total_fee = "total_fee";
    public static final String spbill_create_ip = "spbill_create_ip";
    public static final String notify_url = "notify_url";
    public static final String trade_type = "trade_type";
    public static final String product_id = "product_id";
    public static final String bill_date = "bill_date";
    public static final String bill_type = "bill_type";

    //存储扫码信息的key
    public static final String code_url = "code_url";

    //定义返回结果属性
    public static final String return_code = "return_code";
    public static final String return_msg = "return_msg";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String OK = "OK";

    //定义返回给微信支付系统的数据
    public static final String successXML = "<xml><" + WxUtil.return_code + "><![CDATA[" + WxUtil.SUCCESS + "]]></" + WxUtil.return_code + "><" + WxUtil.return_msg + "><![CDATA[" + WxUtil.OK + "]]></" + WxUtil.return_msg + "></xml> ";
    public static final String failXML = "<xml><" + WxUtil.return_code + "><![CDATA[" + WxUtil.FAIL + "]]></" + WxUtil.return_code + "><" + WxUtil.return_msg + "></" + WxUtil.return_msg + "></xml> ";

    //商户证书
    public static final String certPath = "certPath";


    /**
     * 判断返回结果是否正常
     *
     * @param data
     * @return
     */
    public static void isCheck(Map<String, String> data, _ResultDto dto) {
        HashMap<String, String> retMap = new HashMap<>();
        if (data.get(return_code).equals(SUCCESS)) {
            dto.setCode(200);
        } else {
            dto.setCode(500);
            dto.setMessage(data.get(return_msg));
        }
    }

    /**
     * 获取所有的errorCode(不使用...)
     *
     * @return
     */
    private JSONObject getErrorCode() {
        JSONObject json = null;
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile(filePath)));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String re;
            while ((re = reader.readLine()) != null) {
                sb.append(re);
            }
            in.close();
            reader.close();
            json = new JSONObject(sb.toString());
        } catch (FileNotFoundException e) {
            log.error("微信错误code文件不存在");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
