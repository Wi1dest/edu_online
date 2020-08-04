package com.lsy.service_edu.controller;


import com.lsy.common.utils.Result;
import com.lsy.service_edu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@Api(tags = "excel导入课程")
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "excel批量导入")
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        eduSubjectService.batchImport(file);
        return Result.success();
    }
}

