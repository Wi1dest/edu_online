package com.lsy.service_acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.common.utils.Result;
import com.lsy.service_acl.entity.AclRole;
import com.lsy.service_acl.service.AclRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/admin/acl/role")
@Api(tags = "角色控制器")
public class AclRoleController {
    @Autowired
    private AclRoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,@PathVariable Long limit, AclRole role) {
        Page<AclRole> pageParam = new Page<>(page, limit);
        QueryWrapper<AclRole> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("items",pageParam.getRecords());
        map.put("total",pageParam.getTotal());
        return Result.success(map);
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        AclRole role = roleService.getById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("items",role);
        return Result.success(map);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public Result save(@RequestBody AclRole role) {
        roleService.save(role);
        return Result.success();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result updateById(@RequestBody AclRole role) {
        roleService.updateById(role);
        return Result.success();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        roleService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return Result.success();
    }
}

