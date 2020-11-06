package com.xubo.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.xubo.modules.generator.dao.MenuTopDao;
import com.xubo.modules.generator.entity.MenuTopEntity;
import com.xubo.modules.generator.service.MenuTopService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.Query;


@Service("menuTopService")
public class MenuTopServiceImpl extends ServiceImpl<MenuTopDao, MenuTopEntity> implements MenuTopService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MenuTopEntity> page = this.page(
                new Query<MenuTopEntity>().getPage(params),
                new QueryWrapper<MenuTopEntity>()
        );

        return new PageUtils(page);
    }

}