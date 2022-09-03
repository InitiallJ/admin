package com.liu.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Date: 2022/8/25/025 下午 5:33
 * @Author: ljm
 * @Description:
 */
@Getter
@Setter
@TableName("t_menu")
public class Menu implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String path;

    private String icon;

    private String description;
    @TableField(exist = false)
    private List<Menu> children;

    private Integer pid;

    private String pagePath;

}
