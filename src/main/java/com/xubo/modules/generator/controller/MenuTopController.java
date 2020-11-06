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

import com.xubo.modules.generator.entity.MenuTopEntity;
import com.xubo.modules.generator.service.MenuTopService;
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
@RequestMapping("generator/menutop")
public class MenuTopController {
    @Autowired
    private MenuTopService menuTopService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:menutop:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = menuTopService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{menuTopId}")
    @RequiresPermissions("generator:menutop:info")
    public R info(@PathVariable("menuTopId") Integer menuTopId){
		MenuTopEntity menuTop = menuTopService.getById(menuTopId);

        return R.ok().put("menuTop", menuTop);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:menutop:save")
    public R save(@RequestBody MenuTopEntity menuTop){
		menuTopService.save(menuTop);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:menutop:update")
    public R update(@RequestBody MenuTopEntity menuTop){
		menuTopService.updateById(menuTop);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:menutop:delete")
    public R delete(@RequestBody Integer[] menuTopIds){
		menuTopService.removeByIds(Arrays.asList(menuTopIds));

        return R.ok();
    }

}
