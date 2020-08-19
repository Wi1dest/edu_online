package com.lsy.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.service_edu.client.OrderClient;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.entity.EduVideo;
import com.lsy.service_edu.mapper.EduCourseMapper;
import com.lsy.service_edu.mapper.EduVideoMapper;
import com.lsy.service_edu.service.EduVideoService;
import com.lsy.service_ucenter.entity.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private OrderClient orderClient;

    @Override
    public boolean checkUserCanWatchVdieo(String videoId, MemberVo member) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("id",videoId);
        EduVideo eduVideo = baseMapper.selectOne(wrapper);
        if (eduVideo.getIsFree()){
            return true;
        }else {
            QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
            courseQueryWrapper.eq("id",eduVideo.getCourseId());
            EduCourse eduCourse = eduCourseMapper.selectOne(courseQueryWrapper);
            boolean buyStatus = orderClient.checkPayStatusByCourseIdAndMemberId(eduCourse.getId(), member.getId());
            if (buyStatus){
                return true;
            } else {
                return false;
            }
        }
    }
}
