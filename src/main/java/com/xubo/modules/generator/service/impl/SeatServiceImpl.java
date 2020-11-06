package com.xubo.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.xubo.modules.generator.dao.SeatDao;
import com.xubo.modules.generator.entity.SeatEntity;
import com.xubo.modules.generator.service.SeatService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.Query;


@Service("seatService")
public class SeatServiceImpl extends ServiceImpl<SeatDao, SeatEntity> implements SeatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeatEntity> page = this.page(
                new Query<SeatEntity>().getPage(params),
                new QueryWrapper<SeatEntity>()
        );

        return new PageUtils(page);
    }

}