package com.lsy.service_edu.controller;


import com.lsy.common.utils.Result;
import com.lsy.service_edu.service.EduSubjectService;
import com.lsy.service_edu.vo.subject.OneSubjectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-04
 */
@RestController
@CrossOrigin
@Api(tags = "分类管理控制器")
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "excel批量导入分类")
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        eduSubjectService.batchImport(file);
        return Result.success();
    }

    @ApiOperation(value = "获取分类列表")
    @GetMapping("getSubjectList")
    public Result getSubjectList(){
        List<OneSubjectVO> list = eduSubjectService.getList();
        return Result.success(list);
    }
}

