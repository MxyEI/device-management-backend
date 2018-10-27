package com.hik.entity.vo;

import java.io.Serializable;

import com.hik.entity.Company;
import com.hik.entity.Device;
import com.hik.entity.User;

import lombok.Data;

/**
 * @Description: 用户设备VO
 * @author: mxy
 *
 */
@Data
public class UserDeviceVO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer userId;
	/**
	 * 设备信息
	 */
	private Device device;

	/**
	 * 设备所在的公司
	 */
	private Integer companyId;

	private Company company;

	/**
	 * 申请者
	 */
	private User user;

	/**
	 * 申请成功
	 */
	private Boolean success;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}