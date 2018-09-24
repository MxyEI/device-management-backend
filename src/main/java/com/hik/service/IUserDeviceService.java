package com.hik.service;

import java.util.List;
import java.util.Map;

import com.hik.entity.DeviceWithUser;
import com.hik.entity.vo.UserDeviceVO;

/**
 * @Description: 用户设备service接口
 * @author: mxy
 *
 */
public interface IUserDeviceService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(DeviceWithUser record);

	DeviceWithUser selectByPrimaryKey(Integer id);

	DeviceWithUser selectByuidjid(Map map);
	List<DeviceWithUser> selectByuid(Map map);
	Long selectByuidCounts(Map map);

	int updateByPrimaryKeySelective(DeviceWithUser record);

	int updateByPrimaryKey(DeviceWithUser record);

	List<UserDeviceVO> findAscUserJobs(Map<String, Object> map);

	List<UserDeviceVO> findAscUserJobsWithSuccess(Map<String, Object> map);

	Long getTotlaAscUserJobs(Map<String,Object> map);

	Long getTotlaAscUserJobsWithSuccess(Map<String,Object> map);

	List<UserDeviceVO> findAscUserJobsByUserid(Map<String, Object> map);


	List<UserDeviceVO> findAscUserJobsWithUserid(Map<String,Object> map);
	Long getTotlaAscUserJobsWithuser(Map<String,Object> map);

}
