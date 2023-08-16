package com.lantu.sys.mapper;

import com.lantu.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nieyb
 * @since 2023-08-13
 */
public interface UserMapper extends BaseMapper<User> {

    List<String> getRoleNameByuserId(@Param("userId") Integer userId);
}
