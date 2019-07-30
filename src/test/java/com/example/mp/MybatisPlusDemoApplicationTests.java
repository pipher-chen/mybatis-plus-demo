package com.example.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mp.entity.User;
import com.example.mp.mapper.UserMapper;
import com.example.mp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void queryAllUserTest() {
        List<User> users = userMapper.selectList(null);//userService.selectList(null);
        System.out.println(users);
    }

    @Test
    public void TestQueryById() {
        User user = userMapper.selectById("1");
        System.out.println(user);


        System.out.println("---------------------------------");
        User user1 = userService.getById(1);
        System.out.println(user1);


        User one = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getId, 1));
        System.out.println("---------------------------------");
        System.out.println(one);
    }

    @Test
    public void testQueryGtId() {
        List<User> userList = userService.selectList(new QueryWrapper<User>().lambda().gt(User::getId, 1));
        System.out.println(userList);
        System.out.println("---------------------------------");

        userList.forEach(System.out::println);
        System.out.println("---------------------------------");

        List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().gt(User::getId, 1));
        System.out.println(users);
        System.out.println("---------------------------------");

        users.forEach(System.out::println);

    }

    @Test
    public void testQuery() {
        //and
        List<User> users = userService.selectList(new QueryWrapper<User>().lambda().eq(false,User::getId, 1)
                .eq(User::getUsername, "张三"));
        System.out.println(users);
        System.out.println("---------------------------------");
        //or
        List<User> userList = userService.selectList(new QueryWrapper<User>().lambda().eq(User::getId, 1).or()
                .eq(User::getUsername, "lisi"));
        System.out.println(userList);
        System.out.println("---------------------------------");
        //condition
        List<User> users1 = userService.selectList(new QueryWrapper<User>().lambda().eq(false,User::getId, 1)
                .eq(false,User::getUsername, "张三"));
        System.out.println(users1);
    }

    @Test
    public void testQueryByMap(){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("username","赵柳");
        columnMap.put("age",66);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        IPage<User> page = new Page<>(1,2);
        IPage<User> page1 = userMapper.selectPage(page, new QueryWrapper<>());
        System.out.println(page1);

    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(20);
        userMapper.insert(user);
        System.out.println("lisi：" + user.getId());
        // 成功直接拿会写的 ID
        assertThat(user.getId()).isNotNull();

        User user1 = new User();
        user1.setUsername("zhaoliu");
        user1.setAge(21);
        boolean save1 = userService.save(user1);
        System.out.println("zhaoliu：" + user1.getId());
        // 成功直接拿会写的 ID
        assertThat(user1.getId()).isNotNull();
    }

    @Test
    public void testDelete() {
        int delete = userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getId, 8));
        assertThat(delete).isGreaterThan(0);
        System.out.println(delete);

    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUsername("赵六");
        userMapper.update(null,Wrappers.<User>lambdaUpdate().eq(User::getId,10).set(User::getAge,35));
        userMapper.update(user,new QueryWrapper<User>().lambda().eq(User::getUsername,"赵柳"));
    }












}
