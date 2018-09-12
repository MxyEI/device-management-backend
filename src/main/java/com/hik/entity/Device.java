package com.hik.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 设备
 * @author: mxy
 *
 */
@Data
public class Device implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 企业id
	 */
	private Integer companyId;

	/**
	 * 设备名称
	 */
	private String name;


	/**
	 * 设备编码
	 */
	private Integer devicecode;

	/**
	 * 设备地点
	 */
	private String address;


	/**
	 * 设备描述
	 */
	private String deviceDescribe;

	/**
	 * 失效
	 */
	private Boolean lapse;

	/**
	 * 设备状态
	 */
	private Boolean success;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}