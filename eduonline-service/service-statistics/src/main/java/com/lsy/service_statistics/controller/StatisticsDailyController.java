package com.lsy.service_statistics.controller;


import com.lsy.common.utils.Result;
import com.lsy.service_statistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-16
 */
@RestController
@Api(tags = "统计控制器")
@RequestMapping("/statisticsservice/statisticsDaily")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation("统计一天注册人数")
    @GetMapping(value = "countregister/{day}")
    public Result registerCount(@PathVariable String day){
        statisticsDailyService.createStatisticsByDay(day);
        return Result.success();
    }

    @GetMapping("chart/{begin}/{end}/{type}")
    public Result showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = statisticsDailyService.getChartData(begin, end, type);
        return Result.success(map);
    }
}

