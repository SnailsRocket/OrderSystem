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

import com.xubo.modules.generator.entity.OrderMenuEntity;
import com.xubo.modules.generator.service.OrderMenuService;
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
@RequestMapping("generator/ordermenu")
public class OrderMenuController {
    @Autowired
    private OrderMenuService orderMenuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:ordermenu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderMenuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    @RequiresPermissions("generator:ordermenu:info")
    public R info(@PathVariable("orderId") Integer orderId){
		OrderMenuEntity orderMenu = orderMenuService.getById(orderId);

        return R.ok().put("orderMenu", orderMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:ordermenu:save")
    public R save(@RequestBody OrderMenuEntity orderMenu){
		orderMenuService.save(orderMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:ordermenu:update")
    public R update(@RequestBody OrderMenuEntity orderMenu){
		orderMenuService.updateById(orderMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:ordermenu:delete")
    public R delete(@RequestBody Integer[] orderIds){
		orderMenuService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
