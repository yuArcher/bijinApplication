package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Role;
import com.lantu.sys.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nieyb
 * @since 2023-08-14
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/list")
    public Result<?> getRoleList(@RequestBody Map<String,Object> map){

        Map<String,Object> roleList = roleService.getRoleList(map);

        return Result.success(roleList);
    }

    @PostMapping("/add")
    public Result<?> addRole(@RequestBody Role role){

        roleService.addRole(role);

        return Result.success("新增角色成功");
    }

    @GetMapping("/{roleId}")
    public Result<?> getRoleListById(@PathVariable("roleId") Integer roleId){
        Role role=roleService.getRoleListById(roleId);

        return Result.success(role);
    }

    @PostMapping("/update")
    public Result<?> updateRole(@RequestBody Role role){

        roleService.updateRole(role);

        return Result.success("修改角色成功！");
    }

    @DeleteMapping("/delete/{roleId}")
    public Result<?> deleteRoleById(@PathVariable("roleId") int roleId){
        roleService.deleteRoleById(roleId);

        return Result.success("删除角色成功！");
    }

}
