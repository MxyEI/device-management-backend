package com.hik.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 用户设备关联
 * @author: mxy
 *
 */
@Data
public class DeviceWithUser implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * userid
	 */
	private Integer userid;

	/**
	 * 设备id
	 */
	private Integer deviceid;

	/**
	 * 申请成功
	 */
	private Boolean success;

	private String gmtCreate;

	private String gmtModify;

	private Boolean dr;
}