package com.xubo.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Druid
 * @email 1837039333@qq.com
 * @date 2020-11-06 10:39:41
 */
@Data
@TableName("menu_top")
public class MenuTopEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer menuTopId;
	/**
	 * 
	 */
	private String menuId;
	/**
	 * 
	 */
	private String couponMoney;
	/**
	 * 
	 */
	private String totalMoney;

}
