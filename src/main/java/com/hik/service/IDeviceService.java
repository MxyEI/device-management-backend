package com.hik.service;

import java.util.List;
import java.util.Map;

import com.hik.entity.Device;

/**
 * @Description:设备IService
 * @author: mxy
 *
 */
public interface IDeviceService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Device record);

	Device selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Device record);

	int updateByPrimaryKeyWithBLOBs(Device record);

	int updateByPrimaryKey(Device record);

	List<Device> selectJobsByName(Map<String, Object> map);

	Long getTotalJobsByName(Map<String, Object> map);
}
