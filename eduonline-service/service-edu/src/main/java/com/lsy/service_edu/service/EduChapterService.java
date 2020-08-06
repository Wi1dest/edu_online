package com.lsy.service_edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.service_edu.entity.EduChapter;
import com.lsy.service_edu.vo.chapter.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 根据课程ID获取章节小节信息
     * @param courseId 课程ID
     * @return 章节List
     */
    List<ChapterVO> getChapterVideoByCourseId(String courseId);

    /**
     * 删除章节(章节中的小节一并删除)
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);
}
