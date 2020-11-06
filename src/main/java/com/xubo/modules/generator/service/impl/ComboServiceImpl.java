package com.xubo.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.xubo.modules.generator.dao.ComboDao;
import com.xubo.modules.generator.entity.ComboEntity;
import com.xubo.modules.generator.service.ComboService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.Query;


@Service("comboService")
public class ComboServiceImpl extends ServiceImpl<ComboDao, ComboEntity> implements ComboService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ComboEntity> page = this.page(
                new Query<ComboEntity>().getPage(params),
                new QueryWrapper<ComboEntity>()
        );

        return new PageUtils(page);
    }

}