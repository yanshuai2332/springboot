package com.yan.moudleA.service;

import com.yan.moudleA.dto.UserDTO;
import com.yan.moudleA.entity.User;

/**
 *
 * @author yanshuai
 * @date 2017/8/7
 */
public interface UserService {

    User getUserById(Integer id);

    User getUser(UserDTO userDTO);
}
