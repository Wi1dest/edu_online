package com.lsy.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.common.exception.EduCommentException;
import com.lsy.service_edu.client.MemberClient;
import com.lsy.service_edu.entity.EduComment;
import com.lsy.service_edu.mapper.EduCommentMapper;
import com.lsy.service_edu.service.EduCommentService;
import com.lsy.service_ucenter.entity.vo.MemberVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.lsy.common.enums.CommentExceptionCode.*;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-14
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
    @Autowired
    private MemberClient memberClient;


    @Override
    public Map<String, Object> getCommentByCourseId(Page<EduComment> commentPage,String courseId) {
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_modified");
        baseMapper.selectPage(commentPage,wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentPage.getRecords());
        map.put("current", commentPage.getCurrent());
        map.put("pages", commentPage.getPages());
        map.put("size", commentPage.getSize());
        map.put("total", commentPage.getTotal());
        map.put("hasNext", commentPage.hasNext());
        map.put("hasPrevious", commentPage.hasPrevious());
        return map;
    }

    @Override
    public boolean editCommentByMemberId(EduComment eduComment, HttpServletRequest request) {
        String loginMemberId = request.getHeader("token");
        MemberVo member = memberClient.getMemberInfoByToken(loginMemberId);
        if (!eduComment.getMemberId().equals(member.getId())){
            throw new EduCommentException(COMMENT_UPDATE_ERROR);
        }
        int i = baseMapper.updateById(eduComment);
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteCommentByCommentId(String id,HttpServletRequest request) {
        String loginMemberId = request.getHeader("token");
        MemberVo member = memberClient.getMemberInfoByToken(loginMemberId);
        EduComment eduComment = baseMapper.selectById(id);
        if (!eduComment.getMemberId().equals(member.getId()) ){
            throw new EduCommentException(COMMENT_DELETE_ERROR);

        }
        int i = baseMapper.deleteById(id);
        return i > 0 ? true : false;
    }

    @Override
    public boolean saveComment(EduComment comment, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            throw new EduCommentException(COMMENT_NEED_LOGIN);
        }
        MemberVo member = memberClient.getMemberInfoByToken(token);
        comment.setMemberId(member.getId());
        comment.setAvatar(member.getAvatar());
        comment.setNickname(member.getNickname());
        int i = baseMapper.insert(comment);
        return i > 0 ? true : false;
    }

}
