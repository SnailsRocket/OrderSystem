package com.xubo.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.xubo.modules.generator.dao.ComboMenuDao;
import com.xubo.modules.generator.entity.ComboMenuEntity;
import com.xubo.modules.generator.service.ComboMenuService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.Query;


@Service("comboMenuService")
public class ComboMenuServiceImpl extends ServiceImpl<ComboMenuDao, ComboMenuEntity> implements ComboMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ComboMenuEntity> page = this.page(
                new Query<ComboMenuEntity>().getPage(params),
                new QueryWrapper<ComboMenuEntity>()
        );

        return new PageUtils(page);
    }

}