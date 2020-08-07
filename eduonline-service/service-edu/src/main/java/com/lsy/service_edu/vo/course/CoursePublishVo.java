package com.lsy.service_edu.vo.course;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : Lo Shu-ngan
 * @Classname CoursePublishVo
 * @Description 课程发布VO
 * @Date 2020/08/07 23:32
 */
@Data
public class CoursePublishVo implements Serializable {
    private String title;

    private String cover;

    private Integer lessonNum;

    private String subjectLevelOne;

    private String subjectLevelTwo;

    private String teacherName;

    // 只用于显示 采用String
    private String price;
}
