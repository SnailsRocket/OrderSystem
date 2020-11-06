package com.xubo.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer orderId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer seatId;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer orderStatus;
	/**
	 * 
	 */
	private BigDecimal totalMoney;

}
