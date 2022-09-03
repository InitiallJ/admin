package com.liu.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.entity.Menu;

import java.util.List;

/**
 * @Date: 2022/8/25/025 下午 5:38
 * @Author: liu
 * @Description:
 */
public interface MenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
