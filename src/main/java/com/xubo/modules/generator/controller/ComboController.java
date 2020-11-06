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

import com.xubo.modules.generator.entity.ComboEntity;
import com.xubo.modules.generator.service.ComboService;

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
@RequestMapping("generator/combo")
public class ComboController {
    @Autowired
    private ComboService comboService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:combo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = comboService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{comboId}")
    @RequiresPermissions("generator:combo:info")
    public R info(@PathVariable("comboId") Integer comboId){
		ComboEntity combo = comboService.getById(comboId);

        return R.ok().put("combo", combo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:combo:save")
    public R save(@RequestBody ComboEntity combo){
		comboService.save(combo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:combo:update")
    public R update(@RequestBody ComboEntity combo){
		comboService.updateById(combo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:combo:delete")
    public R delete(@RequestBody Integer[] comboIds){
		comboService.removeByIds(Arrays.asList(comboIds));

        return R.ok();
    }

}
