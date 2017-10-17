package com.yan.moudleA.service.impl;

import com.yan.moudleA.entity.User;
import com.yan.moudleA.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yanshuai on 2017/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUserById() throws Exception {
        User user=userService.getUserById(1);
        Assert.assertEquals("yanshuai",user.getUserName());
    }

}