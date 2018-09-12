package com.springbootwx.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.springbootwx.dto._ResultDto;
import com.springbootwx.service.WxService;
import com.springbootwx.util.WxUtil;
import com.springbootwx.wxconfig.WxPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 微信对外接口
 * @date 2018/7/13 19:17
 */
@RestController
@RequestMapping("/wx")
public class WxApi {

    @Autowired
    private WxService wxService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    // 异步接收微信支付结果通知的回调方法
    @GetMapping("/notify")
    public void receiveNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxPayConfig config = new WxPayConfig();
        WXPay wxpay = new WXPay(config);
        // 读取回调内容
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        // 支付结果通知的xml格式数据
        // 转换成map
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(sb.toString());

        //支付确认内容
        String resXml = "";
        //验证签名
        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {        // 签名正确
            Map<String, String> orderMap = wxService.orderQuery(notifyMap.get(WxUtil.out_trade_no)); //根据订单号查询，是否存在
            if (orderMap != null && WxUtil.SUCCESS.equals(orderMap.get(WxUtil.result_code))) {
                if ("SUCCESS".equals(notifyMap.get(WxUtil.result_code))) {    //交易成功
                    // TODO:更新订单
                    log.info("订单" + notifyMap.get(WxUtil.out_trade_no) + "微信支付成功");
                } else {    //交易失败
                    log.error("订单" + notifyMap.get(WxUtil.out_trade_no) + "微信支付失败");
                }
            }
            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
            //设置成功确认内容
            resXml = WxUtil.successXML;
        } else {  // 签名错误，如果数据里没有sign字段，也认为是签名错误
            //设置失败确认内容
            resXml = WxUtil.failXML;
            log.error("订单" + notifyMap.get(WxUtil.out_trade_no) + "微信支付失败");
        }
        //发送通知
        response.getWriter().println(resXml);
    }

    @GetMapping("/unifiedOrder")
    public _ResultDto unifiedOrder() {
        return wxService.unifiedOrder();
    }

    @GetMapping("/test/{value}")
    public void test(@PathVariable("value") String value, HttpServletResponse response) {
        //生成二维码
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        // 设置二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(value, BarcodeFormat.QR_CODE, 300, 300, hints);
            //返回二维码
            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
