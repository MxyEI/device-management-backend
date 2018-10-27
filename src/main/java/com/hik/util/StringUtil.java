package com.hik.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: String工具类
 * @author: mxy
 *
 */
public class StringUtil {

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Description: 格式化str, %str%
	 * @author: mxy
	 *
	 */
	public static String formatLike(String str) {
		if (isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}

	/**
	 * @Description: 格式化ids
	 * @param ids
	 *            1,2,3
	 * @author: mxy
	 *
	 */
	public static List<Integer> formatIdsToList(String ids) {
		List<Integer> list = new ArrayList<Integer>();
		String[] idss = ids.split(",");
		for (String id : idss) {
			list.add(Integer.valueOf(id));
		}
		return list;
	}
}
