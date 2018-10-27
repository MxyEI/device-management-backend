package com.hik.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: 页面
 * @author: mxy
 *
 */
@Data
@AllArgsConstructor
public class PageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	// 第几页
	private int page;
	// 每页大小
	private int pageSize;

	public int getStart() {
		return (page - 1) * pageSize;
	}

}
