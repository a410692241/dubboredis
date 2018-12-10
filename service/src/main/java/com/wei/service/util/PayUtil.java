package com.wei.service.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.stereotype.Component;

@Component
public class PayUtil {

    private static PayUtil instence;
//    private  static  AlipayClient alipayClient;

    static {
//        alipayClient = new DefaultAlipayClient(
//                Config.gateway,Config.appid,Config.privateKey,"json","UTF-8",Config.publicKey,Config.gnType);
    }


    public static String pay(String out_trade_no,double totalAmountl,String subject,String returnUrl,String notifyUrl) {
        AlipayClient alipayClient =new DefaultAlipayClient(
                Config.gateway,Config.appid,Config.privateKey,"json","UTF-8",Config.publicKey,Config.gnType);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        String bizContent = "{" +
                "    \"out_trade_no\":\""+out_trade_no+"\"," +
                "    \"scene\":\"bar_code\"," +
                "    \"total_amount\":"+totalAmountl+"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"subject\":\""+subject+"\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"" +
                "    }"+
                "  }";//填充业务参数
        request.setBizContent(bizContent);
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody();//调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    public static boolean getPayResult(String out_trade_no) {
        AlipayClient alipayClient =new DefaultAlipayClient(
                Config.gateway,Config.appid,Config.privateKey,"json","UTF-8",Config.publicKey,Config.gnType);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+out_trade_no+"\"}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            String tradeStatus = response.getTradeStatus();
            //TRADE_SUCCESS表示调用成功
            if (tradeStatus.equals("TRADE_SUCCESS")) {
                return true;
            }
        }
        return false;
    }
}
