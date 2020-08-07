package com.lsy.service_edu.controller;

import com.lsy.common.utils.Result;
import com.lsy.service_edu.entity.EduChapter;
import com.lsy.service_edu.service.EduChapterService;
import com.lsy.service_edu.vo.chapter.ChapterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname EduChapterController
 * @Description 章节控制器
 * @Date 2020/08/06 14:00
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
@Api(tags = "章节管理")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation("根据课程ID获取课程章节信息")
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId){
        List<ChapterVO> chapterVOList = eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.success(chapterVOList);
    }

    @ApiOperation("添加章节")
    @PostMapping("saveChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        boolean flag = eduChapterService.save(eduChapter);
        return flag == true ? Result.success() : Result.error();
    };

    @ApiOperation("根据章节ID查询章节信息")
    @GetMapping("getChapter/{chapterId}")
    public Result getChapter(@PathVariable String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return Result.success(chapter);
    }

    @ApiOperation("根据章节ID删除章节")
    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapter(chapterId);
        return flag == true ? Result.success() : Result.error();
    }

    @ApiOperation("根据章节ID更新章节")
    @PutMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        boolean flag = eduChapterService.updateById(eduChapter);
        return flag == true ? Result.success() : Result.error();
    }
}
