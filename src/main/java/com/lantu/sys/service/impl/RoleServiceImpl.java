package com.lantu.sys.service.impl;

import com.lantu.sys.entity.Role;
import com.lantu.sys.mapper.RoleMapper;
import com.lantu.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nieyb
 * @since 2023-08-14
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, Object> getRoleList(Map<String, Object> map) {
        // 处理传入参数
        if (map.get("roleName") != null ){
            map.put("roleName","%"+map.get("roleName")+"%");
        }
        if (map.get("roleDesc") != null){
            map.put("roleDesc","%"+map.get("roleDesc")+"%");
        }


        List<?> roleList=roleMapper.selectList(map);
        log.info("roleList={}",roleList);
        log.info("total={}",roleList.get(1));


        Map<String,Object> data=new HashMap<>();
        data.put("list",roleList.get(0));
        data.put("total",roleList.get(1));

        return data;
    }

    @Override
    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    @Override
    public Role getRoleListById(int roleId) {

        Role role=roleMapper.getRoleListById(roleId);

        return role;
    }

    @Override
    public void updateRole(Role role) {

        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRoleById(int roleId) {
        roleMapper.deleteRoleByid(roleId);
    }
}
