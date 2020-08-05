package com.lsy.service_edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname OneSubjectVO
 * @Description 一级分类
 * @Date 2020/08/05 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneSubjectVO {
    private String id;

    private String title;

    private List<TwoSubjectVO> children;
}
