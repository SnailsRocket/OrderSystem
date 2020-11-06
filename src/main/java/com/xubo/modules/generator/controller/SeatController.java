package com.xubo.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xubo.modules.generator.entity.SeatEntity;
import com.xubo.modules.generator.service.SeatService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.R;


/**
 * 
 *
 * @author Druid
 * @email 1837039333@qq.com
 * @date 2020-11-06 10:39:41
 */
@RestController
@RequestMapping("generator/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:seat:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seatService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seatId}")
    @RequiresPermissions("generator:seat:info")
    public R info(@PathVariable("seatId") Integer seatId){
		SeatEntity seat = seatService.getById(seatId);

        return R.ok().put("seat", seat);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:seat:save")
    public R save(@RequestBody SeatEntity seat){
		seatService.save(seat);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:seat:update")
    public R update(@RequestBody SeatEntity seat){
		seatService.updateById(seat);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:seat:delete")
    public R delete(@RequestBody Integer[] seatIds){
		seatService.removeByIds(Arrays.asList(seatIds));

        return R.ok();
    }

}
