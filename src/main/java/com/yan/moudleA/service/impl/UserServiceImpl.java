package com.yan.moudleA.service.impl;

import com.yan.moudleA.dto.UserDTO;
import com.yan.moudleA.entity.User;
import com.yan.moudleA.repository.UserMapper;
import com.yan.moudleA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanshuai on 2017/8/7.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUser(UserDTO userDTO) {
        return userMapper.selectByPrimaryKey(userDTO.getId());
    }
}
