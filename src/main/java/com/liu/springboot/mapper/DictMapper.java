package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.springboot.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date: 2022/8/26/026 上午 10:30
 * @Author: ljm
 * @Description:
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}
