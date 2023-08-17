package com.lantu.sys.service;

import com.lantu.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nieyb
 * @since 2023-08-14
 */
public interface IRoleService extends IService<Role> {

    Map<String, Object> getRoleList(Map<String,Object> map);

    void addRole(Role role);

    Role getRoleListById(int roleId);

    void updateRole(Role role);

    void deleteRoleById(int roleId);
}
