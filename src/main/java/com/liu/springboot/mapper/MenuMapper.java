package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date: 2022/8/25/025 下午 5:37
 * @Author: liu
 * @Description:
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
