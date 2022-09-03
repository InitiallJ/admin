package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.dto.UserPasswordDTO;
import com.liu.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @Date: 2022/8/12/012 下午 4:31
 * @Author: liu
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Update("update t_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);
}
