package com.lsy.service_order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.lsy.common.exception.EduOrderException;
import com.lsy.common.utils.HttpClient;
import com.lsy.service_order.entity.EduOrder;
import com.lsy.service_order.entity.EduPayLog;
import com.lsy.service_order.mapper.EduPayLogMapper;
import com.lsy.service_order.service.EduOrderService;
import com.lsy.service_order.service.EduPayLogService;
import com.lsy.service_order.utils.ConstantPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.lsy.common.enums.OrderExceptionCode.CHECK_PAY_STATUS_ERROR;
import static com.lsy.common.enums.OrderExceptionCode.CREAT_PAY_QRCODE_ERROR;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-15
 */
@Service
public class EduPayLogServiceImpl extends ServiceImpl<EduPayLogMapper, EduPayLog> implements EduPayLogService {
    @Autowired
    private EduOrderService orderService;

    @Override
    public Map createPayQRCode(String orderNo) {
        //根据订单id获取订单信息
        QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        EduOrder order = orderService.getOne(wrapper);

        Map m = new HashMap();
        //1、设置支付参数
        m.put("appid", ConstantPropertiesUtil.WECHAT_PAY_APP_ID);
        m.put("mch_id", ConstantPropertiesUtil.WECHAT_PAY_MCH_ID);
        m.put("nonce_str", WXPayUtil.generateNonceStr());
        m.put("body", order.getCourseTitle());
        m.put("out_trade_no", orderNo);
        m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
        m.put("spbill_create_ip", "127.0.0.1");
        m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
        m.put("trade_type", "NATIVE");

        //2、HTTPClient来根据URL访问第三方接口并且传递参数
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");

        //client设置参数
        Map map = new HashMap<>();
        try {
            client.setXmlParam(WXPayUtil.generateSignedXml(m, ConstantPropertiesUtil.WECHAT_PAY_PARTNER_KEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //4、封装返回结果集
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new EduOrderException(CREAT_PAY_QRCODE_ERROR);
        }
        return map;
    }

    @Override
    public Map<String, String> checkPayStatus(String orderNo) {
        //1、封装参数
        Map m = new HashMap<>();
        m.put("appid", ConstantPropertiesUtil.WECHAT_PAY_APP_ID);
        m.put("mch_id", ConstantPropertiesUtil.WECHAT_PAY_MCH_ID);
        m.put("out_trade_no", orderNo);
        m.put("nonce_str", WXPayUtil.generateNonceStr());
        Map<String, String> resultMap = null;

        try {
            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, ConstantPropertiesUtil.WECHAT_PAY_PARTNER_KEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            resultMap = WXPayUtil.xmlToMap(xml);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EduOrderException(CHECK_PAY_STATUS_ERROR);
        }
        return resultMap;
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //获取订单id
        String orderNo = map.get("out_trade_no");
        //根据订单id查询订单信息
        QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        EduOrder order = orderService.getOne(wrapper);

        if(order.getStatus().intValue() == 1){
            return;
        }
        order.setStatus(1);
        orderService.updateById(order);

        //记录支付日志
        EduPayLog payLog=new EduPayLog();
        payLog.setOrderNo(order.getOrderNo());
        payLog.setPayTime(new Date());
        payLog.setPayType(1);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);
    }
}
