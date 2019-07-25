package com.example.mp.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.mp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pipher
 * @since 2019-07-25
 */
public interface UserService extends IService<User> {

    @Override
    default List<User> list() {
        return null;
    }

    List<User> selectList(Wrapper<User> queryWrapper);

}
