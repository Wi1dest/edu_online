package com.lsy.service_order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.common.utils.OrderNoUtil;
import com.lsy.service_edu.client.MemberClient;
import com.lsy.service_edu.vo.CourseFrontVO;
import com.lsy.service_order.client.CourseClient;
import com.lsy.service_order.entity.EduOrder;
import com.lsy.service_order.mapper.EduOrderMapper;
import com.lsy.service_order.service.EduOrderService;
import com.lsy.service_ucenter.entity.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-15
 */
@Service
public class EduOrderServiceImpl extends ServiceImpl<EduOrderMapper, EduOrder> implements EduOrderService {
    @Autowired
    private MemberClient memberClient;

    @Autowired
    private CourseClient courseClient;

    @Override
    public String createOrder(String courseId, HttpServletRequest request) {
        String token = request.getHeader("token");
        MemberVo member = memberClient.getMemberInfoByToken(token);
        CourseFrontVO courseInfo = courseClient.getCourseInfoOnService(courseId);
        EduOrder eduOrder = new EduOrder();
        eduOrder.setOrderNo(OrderNoUtil.getOrderNo());
        eduOrder.setOrderNo(OrderNoUtil.getOrderNo());
        eduOrder.setCourseId(courseId);
        eduOrder.setCourseTitle(courseInfo.getTitle());
        eduOrder.setCourseCover(courseInfo.getCover());
        eduOrder.setTeacherName(courseInfo.getTeacherName());
        eduOrder.setTotalFee(courseInfo.getPrice());
        eduOrder.setMemberId(member.getId());
        eduOrder.setMobile(member.getMobile());
        eduOrder.setNickname(member.getNickname());
        eduOrder.setStatus(0);
        eduOrder.setPayType(1);
        baseMapper.insert(eduOrder);

        return eduOrder.getOrderNo();
    }
}
