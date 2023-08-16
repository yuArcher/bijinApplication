package com.lantu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.User;
import com.lantu.sys.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetSupportingSqlParameter;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * @author nieyb
 * @since 2023-08-13
 */
@RestController
@RequestMapping("/user")
@Slf4j
//@CrossOrigin // 处理跨域问题
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder; // 不可逆加密

    @GetMapping("all")
    public Result<List<User>> getAllUser(){
        List<User> list=userService.list();

        return Result.success(list,"查询成功");
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data=userService.login(user);
        if (data != null){
            return Result.success(data);
        }
        return Result.fail(20002,"用户名或者密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
        // 根据token获取用户信息，redis
        Map<String,Object> data=userService.getUserInfo(token);
        if (data != null){
            return Result.success(data);
        }
        return Result.fail(20003,"登录信息无效，请重新登录");
    }

    @PostMapping("/logout") // 从header中获取token
    public Result<?> logout(@RequestHeader("X-Token") String token){

        userService.logout(token);

        return Result.success();
    }

    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                                  @RequestParam(value = "phone",required = false) String phone,
                                                  @RequestParam("pageNo") long pageNo,
                                                  @RequestParam("pageSize") long pageSize){


        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);
        wrapper.orderByDesc(User::getId);

        Page<User> page =new Page<>(pageNo,pageSize);
        userService.page(page,wrapper);

        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addUser(@RequestBody User user){

        user.setPassword(passwordEncoder.encode(user.getPassword())); // 同一个密码加密前后不一致
        userService.save(user);

        return Result.success("新增用户成功！");
    }

    @PutMapping("/update")
    public Result<?> updateUser(@RequestBody User user){
        user.setPassword(null);
        userService.updateById(user);
        return Result.success("修改用户成功！");
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User user=userService.getById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id){
        userService.removeById(id);
        return Result.success("删除用户成功！");
    }

}
