package com.lantu.xadmin;

import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XAdminApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testMapper() {
        List<User> userList=userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
