package com.hp.controller;



import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hp.pojo.Order;
import com.hp.service.OrderService;
import com.hp.utils.AlipayConfig;
import com.hp.utils.JsonResult;
import com.hp.vo.RedisKey;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;



@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;



    @GetMapping( value = "pay",produces="application/json")
    @ResponseBody
    public JsonResult Alipay(@RequestParam("total_amount") BigDecimal total_amount,
                             @RequestParam("out_trade_no") String out_trade_no,
                             @RequestParam("subject") String subject
    )  throws AlipayApiException{

        if (stringRedisTemplate.opsForList().leftPop(RedisKey.GOODS_KEY) == null) {
            return new JsonResult(0, "商品已经卖完!");
        }else {
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", "UTF-8", AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
            alipayRequest.setReturnUrl("http://localhost:9528/login");
            alipayRequest.setNotifyUrl("http://jvepr9.natappfree.cc/Alipay/aaaa");//在公共参数中设置回跳和通知地址
            alipayRequest.setBizContent("{" +
                    "    \"out_trade_no\":" +"\"" + out_trade_no + "\"" + "," +
                    "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                    "    \"total_amount\":" + "\"" + total_amount + "\"" + "," +
                    "    \"subject\":" + "\"" +  subject + "\"" + "," +
                    "\"timeout_express\":" + "\"5m\"" +
                    "  }");//填充业务参数
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            return new JsonResult(1, result);
        }

    }

    @PostMapping("createOrder")
    public JsonResult createOrder(@RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "courseName", required = false) String courseName,
                                  @RequestParam("teacherUser") String teacherUser,
                                  @RequestParam("total_amount") BigDecimal total_amount,
                                  @RequestParam("out_trade_no") String out_trade_no,
                                  @RequestParam("liveType") String order_liveType) throws AlipayApiException{

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,"json","GBK",AlipayConfig.ALIPAY_PUBLIC_KEY,"RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":" + "\"" + out_trade_no + "\"" +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Order order = new Order();
            order.setUsername(username);
            order.setOrder_amount(total_amount);
            order.setOrder_status("已支付");
            order.setCourse(courseName);
            order.setOrderId(out_trade_no);
            order.setCreateTime(dateFormat.format(date));
            order.setTeacher(teacherUser);
            order.setOrder_liveType(order_liveType);
            amqpTemplate.convertAndSend("active", order);
            return new JsonResult(1,"success");
        } else {
            System.out.println("调用失败");
            orderService.losePay();
            return new JsonResult(0,"支付失败订单取消");
        }
    }


    @GetMapping("losePay")
    public void losePay () {
        orderService.losePay();
    }


    @GetMapping("activeFinsh")
    public void activeFinsh() {
        boolean ifcount = orderService.saveOrder();
        System.out.println(ifcount);
    }

    @GetMapping("selectOrder")
    public JsonResult selectOrder(@RequestParam("username")  String usernmae) {
//        System.out.println(stringRedisTemplate.opsForList().leftPop(RedisKey.ORDER_KEY));
        return new JsonResult(1, orderService.queryOrderByUsername(usernmae));
    }

//    @PostMapping("payOrder")
//    public JsonResult payOrder(
//            @RequestParam("course") String course,
//            @RequestParam("order_amount") BigDecimal order_amount,
//            @RequestParam("order_videoType") String order_videoType,
//            @RequestParam("order_liveType") String order_liveType,
//            @RequestParam("username") String username) {
//        Order order = new Order();
//        order.setCourse(course);
//        order.setUsername(username);
//        order.setOrder_amount(order_amount);
//        order.setOrder_liveType(order_liveType);
//        order.setOrder_videoType(order_videoType);
//        int count = orderService.payOrder(order);
//        return count != 0 ? new JsonResult(1, "success") : new JsonResult(0, "error");
//    }

    @PostMapping("drawback")
    public JsonResult drawback(
            @RequestParam("out_trade_no") String out_trade_no
//            @RequestParam("refund_amount") BigDecimal refund_amount,
            ) {
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,"json",AlipayConfig.CHARSET,AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGN_TYPE);
//        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//        request.setBizContent("{" +
//                "    \"out_trade_no\":" + "\"" + out_trade_no + "\"," +
//                "    \"refund_amount\":" + "\"" + refund_amount + "\"," +
//                "    \"refund_reason\":\"正常退款\" " +
//                "  }");
//        AlipayTradeRefundResponse response = alipayClient.execute(request);
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//            return new JsonResult(1,"退款成功");
//        } else {
//            System.out.println("调用失败");
//            System.out.println(JSON.toJSONString(response.getBody()));
//            return new JsonResult(0,response.getBody());
//        }
        int count = orderService.deleteOrder(out_trade_no);
        return count != 0? new JsonResult(1,"succesa") : new JsonResult(0,"error");
    }


    @GetMapping("recommend")
    public JsonResult teacher(@RequestParam("username") String username) {
        return new JsonResult(1,orderService.selectTeacherByUsername(username));
    }

    @GetMapping("ifBuy")
    public JsonResult ifBuy(@RequestParam("teacherName") String teacherName,
                            @RequestParam("order_liveType") String order_liveType,
                            @RequestParam("userName") String userName) {

        int count = orderService.ifBuy(teacherName,order_liveType,userName);
        return count != 0 ? new JsonResult(1,"success") : new JsonResult(0,"error");
    }



    @GetMapping("queryStudentInfo")
    public JsonResult queryStudentInfo(@RequestParam("teacher") String teacher) {
        List<Order> orderList = orderService.queryStudentInfo(teacher);
        return new JsonResult(1,orderList);
    }
}
