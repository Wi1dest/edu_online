package com.lsy.service_acl.controller;


import com.lsy.common.utils.Result;
import com.lsy.service_acl.entity.AclPermission;
import com.lsy.service_acl.service.AclPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色权限 前端控制器
 * </p>
 *
 * @author Lo Shu-ngan
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/admin/acl/permission")
@Api(tags = "角色权限控制器")
public class AclRolePermissionController {
    @Autowired
    private AclPermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
        List<AclPermission> list =  permissionService.queryAllMenuGuli();
        Map<String,Object> map = new HashMap<>();
        map.put("children",list);
        return Result.success(map);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return Result.success();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return Result.success();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<AclPermission> list = permissionService.selectAllMenu(roleId);
        Map<String,Object> map = new HashMap<>();
        map.put("children",list);
        return Result.success(map);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody AclPermission permission) {
        permissionService.save(permission);
        return Result.success();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody AclPermission permission) {
        permissionService.updateById(permission);
        return Result.success();
    }

}

