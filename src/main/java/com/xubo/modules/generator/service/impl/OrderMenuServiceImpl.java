package com.xubo.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.xubo.modules.generator.dao.OrderMenuDao;
import com.xubo.modules.generator.entity.OrderMenuEntity;
import com.xubo.modules.generator.service.OrderMenuService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.Query;


@Service("orderMenuService")
public class OrderMenuServiceImpl extends ServiceImpl<OrderMenuDao, OrderMenuEntity> implements OrderMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderMenuEntity> page = this.page(
                new Query<OrderMenuEntity>().getPage(params),
                new QueryWrapper<OrderMenuEntity>()
        );

        return new PageUtils(page);
    }

}