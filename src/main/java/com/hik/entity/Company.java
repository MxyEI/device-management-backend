package com.hik.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 公司
 * @author: mxy
 *
 */
@Data
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 公司名称
	 */
	private String name;

	/**
	 * 领域
	 */
	private String world;

	/**
	 * 规模
	 */
	private String scale;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 简介
	 */
	private String about;

	/**
	 * 审核状态
	 */
	private Boolean success;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}