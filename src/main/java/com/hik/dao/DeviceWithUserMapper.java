package com.hik.dao;

import com.hik.entity.DeviceWithUser;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户设备关联Mapper
 * @author: mxy
 *
 */
public interface DeviceWithUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(DeviceWithUser record);

	DeviceWithUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DeviceWithUser record);

	int updateByPrimaryKey(DeviceWithUser record);

	DeviceWithUser selectByuidjid(Map map);
	List<DeviceWithUser> selectByuid(Map map);
	Long selectByuidCounts(Map map);
}