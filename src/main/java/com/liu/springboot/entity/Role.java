package com.liu.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Date: 2022/8/25/025 下午 4:38
 * @Author: ljm
 * @Description:
 */
@Getter
@Setter
@TableName("t_role")
public class Role implements Serializable {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private String flag;
}
