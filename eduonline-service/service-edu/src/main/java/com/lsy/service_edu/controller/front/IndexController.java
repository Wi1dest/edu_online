package com.lsy.service_edu.controller.front;

import com.lsy.common.utils.Result;
import com.lsy.service_edu.service.EduIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author : Lo Shu-ngan
 * @Classname IndexController
 * @Description 首页
 * @Date 2020/08/10 21:59
 */
@RestController
@RequestMapping("/eduservice/index")
@Api(tags = "网站首页")
public class IndexController {
    @Autowired
    private EduIndexService indexService;


    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    @ApiOperation("首页")
    public Result index() {
        Map<String, Object> map = indexService.getIndexInfo();
        return Result.success(map);
    }
}
