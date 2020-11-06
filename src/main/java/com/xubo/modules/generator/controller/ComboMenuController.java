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

import com.xubo.modules.generator.entity.ComboMenuEntity;
import com.xubo.modules.generator.service.ComboMenuService;
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
@RequestMapping("generator/combomenu")
public class ComboMenuController {
    @Autowired
    private ComboMenuService comboMenuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:combomenu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = comboMenuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{comboId}")
    @RequiresPermissions("generator:combomenu:info")
    public R info(@PathVariable("comboId") Integer comboId){
		ComboMenuEntity comboMenu = comboMenuService.getById(comboId);

        return R.ok().put("comboMenu", comboMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:combomenu:save")
    public R save(@RequestBody ComboMenuEntity comboMenu){
		comboMenuService.save(comboMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:combomenu:update")
    public R update(@RequestBody ComboMenuEntity comboMenu){
		comboMenuService.updateById(comboMenu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:combomenu:delete")
    public R delete(@RequestBody Integer[] comboIds){
		comboMenuService.removeByIds(Arrays.asList(comboIds));

        return R.ok();
    }

}
