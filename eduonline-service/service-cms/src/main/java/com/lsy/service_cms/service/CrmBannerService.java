package com.lsy.service_cms.service;

import com.lsy.service_cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-10
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 前台查询Banner
     * @return
     */
    List<CrmBanner> selectAllBanner();
}
