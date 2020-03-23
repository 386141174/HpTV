package com.hp.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hp.utils.AlipayConfig;
import com.hp.utils.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "Alipay")
public class AlipayController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @GetMapping( value = "pay",produces="application/json")
    @ResponseBody
    public JsonResult doPost(@RequestParam("out_trade_no") String out_trade_no,
                       @RequestParam("total_amount") double total_amount,
                       @RequestParam("subject") String subject,
                       HttpServletRequest request,
                       HttpServletResponse httpResponse) throws ServletException, IOException ,Exception{
//        if (stringRedisTemplate.opsForList().leftPop(RedisKey.GOODS_KEY) == null) {
//            return new JsonResult(0, "商品已经卖完!");
//        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", "UTF-8", AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:9528/login");
        alipayRequest.setNotifyUrl("http://jvepr9.natappfree.cc/Alipay/aaaa");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":" +"\"" + out_trade_no + "\"" + "," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + "\"" + total_amount + "\"" + "," +
                "    \"subject\":" + "\"" +  subject + "\"" + "," +
                "\"timeout_express\":" + "\"1m\"" +
                "  }");//填充业务参数
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            return new JsonResult(1,result);

    }



    @GetMapping("query")
    public JsonResult query(@RequestParam("out_trade_no") String out_trade_no) throws Exception{
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,"json","GBK",AlipayConfig.ALIPAY_PUBLIC_KEY,"RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":" + "\"" + out_trade_no + "\"" +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return new JsonResult(1,response);
    }

    @PostMapping("aaaa")
    public JsonResult aaa(HttpServletRequest request) throws AlipayApiException {
//        Map<String,String> paramsMap = request.getTrailerFields();
        Map<String, String> paramsMap = new HashMap<>();
        Enumeration<String> paramsName = request.getParameterNames();
        while (paramsName.hasMoreElements()) {
            String key = paramsName.nextElement();
            String value = request.getParameter(key);
            paramsMap.put(key, value);
        }
//        System.out.println("aaaaaaaa" + paramsMap);
//        for (String str:paramsMap.keySet()
//             ) {
//
//            System.out.println(str + "-------" + JSON.toJSONString(paramsMap.get(str)));
//        }

        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            System.out.println("success");
        }else{
            System.out.println("erro");
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }

        return new JsonResult(1,"success");
    }

}
