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

    /**
     * 根据课程ID和会员ID查询订单是否支付
     * @param courseId
     * @param memberId
     * @return
     */
    boolean checkPayStatus(String courseId, String memberId);
}
