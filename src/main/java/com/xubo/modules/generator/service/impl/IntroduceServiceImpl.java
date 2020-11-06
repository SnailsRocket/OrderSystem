package com.xubo.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xubo.modules.generator.dao.IntroduceDao;
import com.xubo.modules.generator.entity.IntroduceEntity;
import com.xubo.modules.generator.service.IntroduceService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.Query;


@Service("introduceService")
public class IntroduceServiceImpl extends ServiceImpl<IntroduceDao, IntroduceEntity> implements IntroduceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IntroduceEntity> page = this.page(
                new Query<IntroduceEntity>().getPage(params),
                new QueryWrapper<IntroduceEntity>()
        );

        return new PageUtils(page);
    }

}