package com.lsy.service_statistics.service;

import com.lsy.service_statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-16
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    /**
     * 创建一天注册统计
     * @param day
     */
    void createStatisticsByDay(String day);
}
