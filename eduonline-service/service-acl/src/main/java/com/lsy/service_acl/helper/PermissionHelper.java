package com.lsy.service_acl.helper;

import com.lsy.service_acl.entity.AclPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Lo Shu-ngan
 * @Classname PermissionHelper
 * @Description TODO
 * @Date 2020/08/17 19:51
 */
public class PermissionHelper {
    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<AclPermission> bulid(List<AclPermission> treeNodes) {
        List<AclPermission> trees = new ArrayList<>();
        for (AclPermission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static AclPermission findChildren(AclPermission treeNode,List<AclPermission> treeNodes) {
        treeNode.setChildren(new ArrayList<AclPermission>());

        for (AclPermission it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
