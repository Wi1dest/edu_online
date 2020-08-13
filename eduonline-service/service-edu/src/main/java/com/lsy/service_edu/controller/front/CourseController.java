package com.lsy.service_edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.common.utils.Result;
import com.lsy.service_edu.dto.front.CourseFrontQueryDTO;
import com.lsy.service_edu.entity.EduCourse;
import com.lsy.service_edu.service.EduChapterService;
import com.lsy.service_edu.service.EduCourseService;
import com.lsy.service_edu.vo.CourseFrontVO;
import com.lsy.service_edu.vo.chapter.ChapterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname CourseController
 * @Description 前端课程控制器
 * @Date 2020/08/13 16:59
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Api(tags = "前端课程模块")
public class CourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation("前端获取课程列表")
    @PostMapping("getCoursePage/{page}/{limit}")
    public Result getCoursePage(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) CourseFrontQueryDTO courseFrontDTO){
        Page<EduCourse> coursePage = new Page<>(page,limit);
        Map<String,Object>  map = eduCourseService.getCoursePageList(coursePage,courseFrontDTO);
        return Result.success(map);
    }

    @ApiOperation("前端获取课程详情")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId){
        CourseFrontVO courseFrontVo = eduCourseService.getFrontCourseInfo(courseId);

        List<ChapterVO> chapterVOList = eduChapterService.getChapterVideoByCourseId(courseId);
        Map<String,Object> map = new HashMap<>();
        map.put("course",courseFrontVo);
        map.put("chapterVoList",chapterVOList);
        return Result.success(map);
    }
}
