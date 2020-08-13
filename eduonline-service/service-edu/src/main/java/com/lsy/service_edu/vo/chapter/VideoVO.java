package com.lsy.service_edu.vo.chapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Lo Shu-ngan
 * @Classname VideoVo
 * @Description 章节中的小节VO
 * @Date 2020/08/06 13:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoVO {
    private String id;

    private String title;

    private String videoSourceId;
}
