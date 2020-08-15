package com.lsy.service_order.service;

import com.lsy.service_order.entity.EduPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-15
 */
public interface EduPayLogService extends IService<EduPayLog> {

    /**
     * 创建微信支付二维码
     * @param orderNo
     * @return
     */
    Map createPayQRCode(String orderNo);

    /**
     * 检查支付状态
     * @param orderNo
     * @return
     */
    Map<String, String> checkPayStatus(String orderNo);

    /**
     * 更新订单状态
     * @param map
     */
    void updateOrderStatus(Map<String, String> map);
}
