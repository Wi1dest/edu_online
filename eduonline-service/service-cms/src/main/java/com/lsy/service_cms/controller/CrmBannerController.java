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
@CrossOrigin
@Api(tags = "网站首页Banner列表")
public class CrmBannerController {
    @Autowired
    private CrmBannerService crmBannerService;

    //@ApiOperation(value = "获取Banner分页")
    //@GetMapping("/getBannerPage/{page}/{limit}")
    //public Result getBannerPage(@PathVariable Long page,@PathVariable Long limit){
    //    Page<CrmBanner> crmBannerPage = new Page<>(page,limit);
    //    crmBannerService.page(crmBannerPage,null);
    //    Map<String,Object> map = new HashMap<>();
    //    map.put("items",crmBannerPage.getRecords());
    //    map.put("total",crmBannerPage.getTotal());
    //    return Result.success(map);
    //}
    //
    //@ApiOperation(value = "新增Banner")
    //@PostMapping("/saveBanner")
    //public Result addBanner(@RequestBody CrmBanner crmBanner){
    //    boolean flag = crmBannerService.save(crmBanner);
    //    return flag == true ? Result.success() : Result.error();
    //}
    //
    //@ApiOperation(value = "删除Banner")
    //@DeleteMapping("/deleteBanner/{id}")
    //public Result deleteBanner(@PathVariable String id){
    //    boolean flag = crmBannerService.removeById(id);
    //    return flag == true ? Result.success() : Result.error();
    //}
    //
    //@ApiOperation(value = "获取Banner")
    //@GetMapping("/getBanner/{id}")
    //public Result getBannerById(@PathVariable String id){
    //    CrmBanner banner = crmBannerService.getById(id);
    //    return Result.success(banner);
    //}
    //
    //@ApiOperation(value = "修改Banner")
    //@PutMapping("/updateBanner")
    //public Result getBannerById(@RequestBody CrmBanner crmBanner){
    //    crmBannerService.updateById(crmBanner);
    //    return Result.success();
    //}

    @GetMapping("getAllBanner")
    @ApiOperation("前台查询Banner")
    public Result getAllBanner(){
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return Result.success(list);
    }
}

