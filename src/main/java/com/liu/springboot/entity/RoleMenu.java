package com.liu.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Date: 2022/8/26/026 下午 12:47
 * @Author: ljm
 * @Description:
 */
@TableName("t_role_menu")
@Data
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;
}
