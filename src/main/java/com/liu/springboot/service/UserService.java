package com.liu.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.dto.UserDTO;
import com.liu.springboot.dto.UserPasswordDTO;
import com.liu.springboot.entity.User;

/**
 * @Date: 2022/8/12/012 下午 4:49
 * @Author: liu
 * @Description:
 */
public interface UserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);
}
