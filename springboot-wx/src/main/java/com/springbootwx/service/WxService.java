package com.springbootwx.service;

import com.github.wxpay.sdk.WXPay;
import com.springbootwx.dto._ResultDto;
import com.springbootwx.util.WxUtil;
import com.springbootwx.wxconfig.WxPayConfig;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * <p>
 * 该微信扫码支付采用的是模式二
 * 1.后台系统根据用户下单的订单，向微信系统调用下单接口，获得预支付交易链接code_url
 * 2.将链接生成二维码，展示给用户扫码
 * 3.客户打开微信客户端扫码后会提交一个扫码链接到微信支付系统，该过程不与商户的后台系统交互
 * 4.微信支付系统收到客户端请求，验证链接有效性后发起用户支付，要求用户授权。
 * 5.客户在微信客户端输入密码，确认支付后，微信客户端提交授权。
 * 6.微信支付系统验证授权，完成支付交易，返回支付结果，发送短信，和微信消息提示客户消费信息，
 * 7.微信支付系统通过发送异步消息通知商户后台系统支付结果。商户后台系统需回复接收情况，通知微信后台系统不再发送该单的支付通知。
 * 8.商户确认订单已支付后给用户发货。
 * @Description: 微信业务层
 * @date 2018/7/13 18:33
 */
@Service
public class WxService {

    /**
     * 调用微信支付系统的下单接口
     *
     * @return
     */
    public _ResultDto unifiedOrder() {
        _ResultDto dto = new _ResultDto();
        Map<String, String> data = new HashMap<String, String>();
        try {
            WxPayConfig config = new WxPayConfig();
            WXPay wxpay = new WXPay(config);
            //设置请求参数
            data.put(WxUtil.body, "腾讯充值中心-QQ会员充值");
            data.put(WxUtil.out_trade_no, "201807131839400000012");
            data.put(WxUtil.device_info, "");
            data.put(WxUtil.fee_type, "CNY");
            data.put(WxUtil.total_fee, "100"); // 订单总金额，单位（分）
            data.put(WxUtil.spbill_create_ip, "123.12.12.123");
            data.put(WxUtil.notify_url, "http://www.example.com/wxpay/notify");  // 通知地址
            data.put(WxUtil.trade_type, "NATIVE");  // 此处指定为扫码支付
            data.put(WxUtil.product_id, "12");
            //发送请求并获得返回的数据对象
            data = wxpay.unifiedOrder(data);
            //获取二维码内容
            String url = data.get(WxUtil.code_url);
            //判断结果是否正确
            WxUtil.isCheck(data, dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    //关闭订单
    public Map<String, String> closeOrder() {
        Map<String, String> data = new HashMap<>();
        try {
            WxPayConfig config = new WxPayConfig();
            WXPay wxpay = new WXPay(config);
            //添加参数
            data.put(WxUtil.out_trade_no, "201807131839400000012");
            data = wxpay.closeOrder(data);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //申请退款
    public Map<String, String> refund() {
        Map<String, String> data = new HashMap<String, String>();
        try {
            WxPayConfig config = new WxPayConfig();
            WXPay wxpay = new WXPay(config);
            //添加参数
            data.put(WxUtil.out_trade_no, "201807131839400000012");
            data = wxpay.refund(data);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //查询订单
    public Map<String, String> orderQuery(String outTradeNo) {
        Map<String, String> data = new HashMap<String, String>();
        try {
            WxPayConfig config = new WxPayConfig();
            WXPay wxpay = new WXPay(config);
            // 填入参数
            data.put(WxUtil.out_trade_no, outTradeNo);
            data = wxpay.orderQuery(data);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //退款查询
    public Map<String, String> refundQuery() {
        Map<String, String> data = new HashMap<String, String>();
        try {
            WxPayConfig config = new WxPayConfig();
            WXPay wxpay = new WXPay(config);
            data.put(WxUtil.out_trade_no, "201807131839400000012");
            data = wxpay.refundQuery(data);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //下载对账单
    public Map<String, String> downloadBill() {
        Map<String, String> data = new HashMap<String, String>();
        try {
            WxPayConfig config = new WxPayConfig();
            WXPay wxpay = new WXPay(config);
            //添加参数
            data.put(WxUtil.bill_date, "20140603");
            data.put(WxUtil.bill_type, "ALL");
            data = wxpay.downloadBill(data);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
