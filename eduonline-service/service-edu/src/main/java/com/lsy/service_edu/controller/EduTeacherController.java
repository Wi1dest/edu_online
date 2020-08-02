package com.lsy.service_edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.common.utils.Result;
import com.lsy.service_edu.entity.EduTeacher;
import com.lsy.service_edu.service.EduTeacherService;
import com.lsy.service_edu.vo.TeacherVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
@RestController
@Api(tags = "讲师控制器")
@RequestMapping("/service_edu/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return new Result(list);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @ApiOperation("分页讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable Long current,@PathVariable Long limit){
        // 创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        // 调用方法实现分页
        eduTeacherService.page(teacherPage,null);

        // 获取总记录数
        long total = teacherPage.getTotal();
        // 数据list集合
        List<EduTeacher> records = teacherPage.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return new Result(map);
    }

    @ApiOperation(value = "多条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) TeacherVO teacherVO){
        // 创建page对象
        Page<EduTeacher> teacherPage = new Page<>(current,limit);

        eduTeacherService.pageQuery(teacherPage,teacherVO);

        // 获取总记录数
        long total = teacherPage.getTotal();
        // 数据list集合
        List<EduTeacher> records = teacherPage.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return new Result(map);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public Result save(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        return flag ? Result.success() : Result.error();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public Result getById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return teacher != null ? Result.success(teacher) : Result.error("查无此人");
    }

    @ApiOperation(value = "根据ID更新讲师资料")
    @PutMapping("{id}")
    public Result updateById(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        return flag ? Result.success() : Result.error();
    }
}

