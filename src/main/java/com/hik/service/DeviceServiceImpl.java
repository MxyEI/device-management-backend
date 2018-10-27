package com.hik.service;

import java.util.List;
import java.util.Map;

import com.hik.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hik.dao.DeviceMapper;

/**
 * @Description: 设备ServiceImpl
 * @author: mxy
 *
 */
@Service
public class DeviceServiceImpl implements IDeviceService {
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return deviceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Device record) {
		return deviceMapper.insertSelective(record);
	}

	@Override
	public Device selectByPrimaryKey(Integer id) {
		return deviceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Device record) {
		return deviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Device record) {
		return deviceMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Device record) {
		return deviceMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<Device> selectJobsByName(Map<String,Object> map){
		return deviceMapper.selectDevicesByName(map);
	}
	
	public Long getTotalJobsByName(Map<String,Object> map) {
		return deviceMapper.getTotalDevicesByName(map);
	}

}
