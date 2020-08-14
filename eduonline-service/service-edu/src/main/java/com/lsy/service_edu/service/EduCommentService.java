package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.entity.EduComment;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-14
 */
public interface EduCommentService extends IService<EduComment> {
    /**
     * 根据课程ID获取课程评论
     * @param courseId
     * @return
     */
    Map<String, Object> getCommentByCourseId(Page<EduComment> commentPage, String courseId);

    /**
     * 修改评论
     * @param eduComment
     * @return
     */
    boolean editCommentByMemberId(EduComment eduComment, HttpServletRequest request);

    /**
     * 根据评论Id删除
     * @param id
     * @return
     */
    boolean deleteCommentByCommentId(String id,HttpServletRequest request);

    /**
     * 添加评论
     * @param comment
     * @param request
     * @return
     */
    boolean saveComment(EduComment comment,HttpServletRequest request);
}
