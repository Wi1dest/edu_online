package com.lsy.service_cms.controller;


import com.lsy.common.utils.Result;
import com.lsy.service_cms.entity.CrmBanner;
import com.lsy.service_cms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/cmsservice/banner")
@Api(tags = "网站首页Banner列表")
public class CrmBannerController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    @ApiOperation("前台查询Banner")
    public Result getAllBanner(){
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return Result.success(list);
    }
}

