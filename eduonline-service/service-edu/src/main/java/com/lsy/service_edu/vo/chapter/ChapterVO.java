package com.lsy.service_edu.vo.chapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname ChapterVO
 * @Description 章节VO
 * @Date 2020/08/06 13:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterVO implements Serializable {
    private String id;

    private String title;

    private List<VideoVO> children = new ArrayList<>();
}
