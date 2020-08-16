package com.lsy.service_statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.service_statistics.client.MemberClient;
import com.lsy.service_statistics.entity.StatisticsDaily;
import com.lsy.service_statistics.mapper.StatisticsDailyMapper;
import com.lsy.service_statistics.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-16
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {


    @Autowired
    private MemberClient memberClient;

    @Override
    public void createStatisticsByDay(String day) {
        //删除已存在的统计对象
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);


        //获取统计信息
        int registerNum = memberClient.registerCount(day);
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.between("date_calculated", begin, end);
        dayQueryWrapper.select("date_calculated",type);

        List<StatisticsDaily> dayList = baseMapper.selectList(dayQueryWrapper);

        // 前端需要data数据和时间数据
        Map<String, Object> map = new HashMap<>();
        List<Integer> dataList = new ArrayList<Integer>();
        List<String> dateList = new ArrayList<String>();
        map.put("dataList", dataList);
        map.put("dateList", dateList);

        for (StatisticsDaily statisticsDaily : dayList) {
            dateList.add(statisticsDaily.getDateCalculated());
            switch (type) {
                case "register_num":
                    dataList.add(statisticsDaily.getRegisterNum());
                    break;
                case "login_num":
                    dataList.add(statisticsDaily.getLoginNum());
                    break;
                case "video_view_num":
                    dataList.add(statisticsDaily.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(statisticsDaily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        return map;
    }
}
