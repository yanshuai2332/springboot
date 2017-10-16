package com.yan.test.service;

import com.yan.test.dto.UserDTO;
import com.yan.test.entity.User;

/**
 * Created by yanshuai on 2017/8/7.
 */
public interface UserService {
    User getUserById(Integer id);
    User getUser(UserDTO userDTO);
}
