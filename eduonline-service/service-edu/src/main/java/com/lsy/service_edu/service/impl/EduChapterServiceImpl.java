package com.lsy.service_edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.service_edu.entity.EduChapter;
import com.lsy.service_edu.entity.EduVideo;
import com.lsy.service_edu.mapper.EduChapterMapper;
import com.lsy.service_edu.service.EduChapterService;
import com.lsy.service_edu.service.EduVideoService;
import com.lsy.service_edu.vo.chapter.ChapterVO;
import com.lsy.service_edu.vo.chapter.VideoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-02
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVO> getChapterVideoByCourseId(String courseId) {
        // 根据传过来的课程ID 查到该课程ID里的所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        wrapperChapter.orderByAsc("sort");
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);

        // 根据传过来的课程ID 查到该课程ID里的所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id" ,courseId);
        wrapperVideo.orderByAsc("sort");
        List<EduVideo> videoList = eduVideoService.list(wrapperVideo);

        // 封装结果List
        List<ChapterVO> list = new ArrayList<>();
            // 遍历EduChapter,把每个EduChapter里的值Copy到ChapterVO
        for (EduChapter eduChapter : chapterList) {
            ChapterVO chapterVO = new ChapterVO();
            // 此时ChapterVO中的id和title属性已经赋值
            BeanUtils.copyProperties(eduChapter,chapterVO);

            // ChapterVO中的第三个属性是List<VideoVO>,在遍历EduVideo前先声明
            List<VideoVO> videoVoList = new ArrayList<>();
            // 遍历EduVideo
            for (EduVideo eduVideo : videoList) {
                // 判断EduVideo中章节ID
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVO videoVo = new VideoVO();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVoList.add(videoVo);
                }
            }

            // 此时ChapterVO中的第三个属性List<VideoVO>已经赋值
            chapterVO.setChildren(videoVoList);

            // 将完全赋值完毕的chapterVO放入集合
            list.add(chapterVO);
        }
        return list;
    }

    @Override
    @Transactional
    public boolean deleteChapter(String chapterId) {
        // 根据章节ID查找该章节下的所有小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        List<EduVideo> list = eduVideoService.list(wrapper);
        for (EduVideo eduVideo : list) {
            // 将该章节下的小节一个一个删除
            eduVideoService.removeById(eduVideo.getId());
        }
        // 删除章节
        int i = baseMapper.deleteById(chapterId);
        return i>0 ? true : false;
    }
}
