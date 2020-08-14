package com.lsy.service_edu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.common.exception.EduCommentException;
import com.lsy.common.utils.Result;
import com.lsy.service_edu.client.MemberClient;
import com.lsy.service_edu.entity.EduComment;
import com.lsy.service_edu.mapper.EduCommentMapper;
import com.lsy.service_edu.service.EduCommentService;
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
        Map<String, String> map = this.checkTokenToGetLoginId(request);
        String loginMemberId = map.get("id");
        if (!eduComment.getMemberId().equals(loginMemberId)){
            throw new EduCommentException(COMMENT_UPDATE_ERROR);
        }
        int i = baseMapper.updateById(eduComment);
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteCommentByCommentId(String id,HttpServletRequest request) {
        Map<String, String> map = this.checkTokenToGetLoginId(request);
        String loginMemberId = map.get("id");
        EduComment eduComment = baseMapper.selectById(id);
        if (!eduComment.getMemberId().equals(loginMemberId) ){
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
        Map<String, String> map = this.checkTokenToGetLoginId(request);
        comment.setMemberId(map.get("id"));
        comment.setAvatar(map.get("avatar"));
        comment.setNickname(map.get("nickname"));
        int i = baseMapper.insert(comment);
        return i > 0 ? true : false;
    }

    /**
     * 获取登录用户信息
     * @param request
     * @return
     */
    private  Map<String,String> checkTokenToGetLoginId(HttpServletRequest request) {
        String token = request.getHeader("token");
        Result memberInfoFromToken = memberClient.getMemberInfoByToken(token);
        Object data = memberInfoFromToken.getData();
        JSONObject json = (JSONObject) JSON.toJSON(data);
        Object memberId = json.get("id");
        Object avatar = json.get("avatar");
        Object moblie = json.get("moblie");
        Object nickname = json.get("nickname");
        String loginMemberId = (String) memberId;
        String loginAvatar = (String) avatar;
        String loginMoblie = (String) moblie;
        String loginNickname = (String) nickname;
        Map<String,String> map = new HashMap<>();
        map.put("id",loginMemberId);
        map.put("avatar",loginAvatar);
        map.put("moblie",loginMoblie);
        map.put("nickname",loginNickname);
        return map;
    }

}
