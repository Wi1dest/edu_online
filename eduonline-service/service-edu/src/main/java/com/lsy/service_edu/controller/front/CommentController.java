package com.lsy.service_edu.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.common.utils.Result;
import com.lsy.service_edu.entity.EduComment;
import com.lsy.service_edu.service.EduCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
@Api(tags = "前端评论模块")
public class CommentController {
    @Autowired
    private EduCommentService commentService;

    @ApiOperation("添加评论")
    @PostMapping("saveComment")
    public Result saveComment(@RequestBody EduComment comment,HttpServletRequest request){
        boolean flag = commentService.saveComment(comment,request);
        return flag == true ? Result.success() : Result.error();
    }

    @ApiOperation("获取评论")
    @GetMapping("getCommentList/{courseId}/{page}/{limit}")
    public Result getCommentList(@PathVariable String courseId,@PathVariable Long page,@PathVariable Long limit){
        Page<EduComment> commentPage = new Page<>(page,limit);
        Map<String, Object> commentList = commentService.getCommentByCourseId(commentPage, courseId);
        return Result.success(commentList);
    }

    @ApiOperation("修改评论")
    @PutMapping("updateComment")
    public Result updateComment(@RequestBody EduComment comment,HttpServletRequest request){
        boolean flag = commentService.editCommentByMemberId(comment, request);
        return flag == true ? Result.success() : Result.error();
    }

    @ApiOperation("删除评论")
    @DeleteMapping("deleteComment/{commentId}")
    public Result deleteComment(@PathVariable String commentId,HttpServletRequest request){
        boolean flag = commentService.deleteCommentByCommentId(commentId, request);
        return flag == true ? Result.success() : Result.error();
    }
}

