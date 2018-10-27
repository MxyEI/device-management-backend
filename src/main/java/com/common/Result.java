package com.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 结果
 * @author: mxy
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;
	private int resultCode; // 结果Code
	private String message; // 返回消息
	private Object data; // 返回数据
}
