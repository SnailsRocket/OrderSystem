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

import com.xubo.modules.generator.entity.IntroduceEntity;
import com.xubo.modules.generator.service.IntroduceService;
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
@RequestMapping("generator/introduce")
public class IntroduceController {
    @Autowired
    private IntroduceService introduceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:introduce:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = introduceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{introduceId}")
    @RequiresPermissions("generator:introduce:info")
    public R info(@PathVariable("introduceId") Integer introduceId){
		IntroduceEntity introduce = introduceService.getById(introduceId);

        return R.ok().put("introduce", introduce);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:introduce:save")
    public R save(@RequestBody IntroduceEntity introduce){
		introduceService.save(introduce);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:introduce:update")
    public R update(@RequestBody IntroduceEntity introduce){
		introduceService.updateById(introduce);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:introduce:delete")
    public R delete(@RequestBody Integer[] introduceIds){
		introduceService.removeByIds(Arrays.asList(introduceIds));

        return R.ok();
    }

}
