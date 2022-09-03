package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.entity.Files;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date: 2022/8/23/023 上午 10:50
 * @Author: ljm
 * @Description:
 */
@Mapper
public interface FileMapper extends BaseMapper<Files> {

}
