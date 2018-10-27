package com.hik.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.hik.entity.Device;

import lombok.Data;

/**
 * @Description: 公司VO
 * @author: mxy
 *
 */
@Data
public class CompanyVO implements Serializable{
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


	/**
	 * 公司旗下设备
	 */
	private List<Device> devices;

	/**
	 * 申请该公司的用户设备关联
	 */
	private List<UserDeviceVO> userDeviceVOS;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}