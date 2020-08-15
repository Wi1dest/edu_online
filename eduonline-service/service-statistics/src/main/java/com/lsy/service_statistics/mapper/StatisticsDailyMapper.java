package com.lsy.service_statistics.mapper;

import com.lsy.service_statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-16
 */
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

    int selectRegisterCount(String day);
}
