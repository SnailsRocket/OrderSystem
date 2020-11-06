package com.xubo.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xubo.modules.generator.entity.MessageEntity;
import com.xubo.modules.generator.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author Druid
 * @email 1837039333@qq.com
 * @date 2020-11-06 10:39:41
 */
public interface MessageService extends IService<MessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

