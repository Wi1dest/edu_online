package com.lsy.service_order.service;

import com.lsy.service_order.entity.EduOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-15
 */
public interface EduOrderService extends IService<EduOrder> {

    /**
     * 创建订单
     * @param courseId
     * @param request
     * @return
     */
    String createOrder(String courseId, HttpServletRequest request);
}
