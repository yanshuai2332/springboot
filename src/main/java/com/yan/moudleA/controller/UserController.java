package com.yan.moudleA.controller;

import com.yan.base.aop.annotation.ExceptionLog;
import com.yan.base.aop.annotation.OperationLog;
import com.yan.base.entity.Result;
import com.yan.base.service.RedisService;
import com.yan.base.util.ResultUtil;
import com.yan.moudleA.dto.UserDTO;
import com.yan.moudleA.entity.User;
import com.yan.moudleA.group.groupA;
import com.yan.moudleA.group.groupB;
import com.yan.moudleA.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yanshuai on 2017/8/3.
 */
@RestController
@RequestMapping(value = "manager/v1/app-users")
@Api(tags = "111111",description = "222222222")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "ooo")
    @GetMapping(value = "gettest")
    @ExceptionLog(description = "描述信息")
    @OperationLog(description = "描述信息")
    public User getUserById(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "user")
    @GetMapping(value = "user")
    public User getUser(@ApiParam(value = "活动参数") @Validated(value = groupA.class) UserDTO userDTO) {
        return userService.getUser(userDTO);
    }

    @ApiOperation(value = "moudleA")
    @PostMapping(value = "test")
    public Result<User> testApi(@RequestBody @Validated(value = groupB.class) User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(user);
    }

    @GetMapping(value = "testRedis")
    public Object testRedis(){
        User user=new User();
        user.setPassword(1111);
        user.setUserName("yan");
        redisService.set("user","111");
        Object o=redisService.get("user");
        return o ;
    }
}
