package com.hik.dao;

import java.util.List;
import java.util.Map;

import com.hik.entity.Device;

/**
 * @Description: 设备信息Mapper
 * @author: mxy
 *
 */
public interface DeviceMapper {

	/**
	 * @Description: 根据设备名模糊查询设备信息
	 * @author: mxy
	 *
	 */
	List<Device> selectDevicesByName(Map<String, Object> map);

	/**
	 * @Description: 根据设备名模糊查询设备数量
	 * @author: mxy
	 *
	 */
	Long getTotalDevicesByName(Map<String, Object> map);

	int deleteByPrimaryKey(Integer id);

	int insert(Device record);

	int insertSelective(Device record);

	Device selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Device record);

	int updateByPrimaryKeyWithBLOBs(Device record);

	int updateByPrimaryKey(Device record);
}