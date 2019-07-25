package com.example.mp;

import com.example.mp.entity.User;
import com.example.mp.mapper.UserMapper;
import com.example.mp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusDemoApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;



    @Test
    public void contextLoads() {
    }

    @Test
    public void queryAllUser() {
        List<User> users = userMapper.selectList(null);//userService.selectList(null);
        System.out.println(users);
    }



}
