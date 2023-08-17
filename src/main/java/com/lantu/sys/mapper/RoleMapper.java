package com.lantu.sys.mapper;

import com.lantu.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nieyb
 * @since 2023-08-14
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectList(Map<String,Object> map);

    void addRole(Role role);

    Role getRoleListById(int roleId);

    void updateRole(Role role);

    void deleteRoleByid(int roleId);
}
