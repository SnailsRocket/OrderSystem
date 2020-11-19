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

import com.xubo.modules.generator.entity.MenuEntity;
import com.xubo.modules.generator.service.MenuService;
import com.xubo.modules.generator.utils.PageUtils;
import com.xubo.modules.generator.utils.R;


/**
 *
 * @author Druid
 * @email 1837039333@qq.com
 * @date 2020-11-06 10:39:41
 * 菜品显示页
 *  menu_name money sales description image (评价另做一张表，当点击详情页的时候再去数据库里面查询,或者评论的数据丢mongoDB)
 *  
 *
 */
@RestController
@RequestMapping("generator/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:menu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = menuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("generator:menu:info")
    public R info(@PathVariable("menuId") Integer menuId){
		MenuEntity menu = menuService.getById(menuId);

        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:menu:save")
    public R save(@RequestBody MenuEntity menu){
		menuService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:menu:update")
    public R update(@RequestBody MenuEntity menu){
		menuService.updateById(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:menu:delete")
    public R delete(@RequestBody Integer[] menuIds){
		menuService.removeByIds(Arrays.asList(menuIds));

        return R.ok();
    }

}
