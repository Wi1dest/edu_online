package com.lsy.service_statistics.scheduled;

import com.lsy.common.utils.DateUtil;
import com.lsy.service_statistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author : Lo Shu-ngan
 * @Classname ScheduledTask
 * @Description 定时任务
 * @Date 2020/08/16 13:52
 */
@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 在每天凌晨1点，把前一天数据进行数据查询添加
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        statisticsDailyService.createStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
