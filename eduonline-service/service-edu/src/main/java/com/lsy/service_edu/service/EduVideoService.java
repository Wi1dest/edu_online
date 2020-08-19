package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.entity.EduVideo;
import com.lsy.service_ucenter.entity.vo.MemberVo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
public interface EduVideoService extends IService<EduVideo> {
    /**
     * 查询用户是否有权观看此小节视频
     * @param videoId
     * @param memberVo
     * @return
     */
    boolean checkUserCanWatchVdieo(String videoId, MemberVo member);
}
