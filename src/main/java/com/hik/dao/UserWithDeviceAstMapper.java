package com.hik.dao;

import com.hik.entity.vo.UserDeviceVO;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户设备关联AstMapper
 * @author: mxy
 *
 */
public interface UserWithDeviceAstMapper {

	/**
	 * @Description: 某个公司的所有申请实习的用户信息
	 * @author: mxy
	 *
	 */
	List<UserDeviceVO> findAscUserJobs(Map<String, Object> map);

	/**
	 * @Description: 某个公司的所有申请实习成功的用户信息
	 * @author: mxy
	 *
	 */
	List<UserDeviceVO> findAscUserJobsWithSuccess(Map<String, Object> map);

	/**
	 * @Description: 某个公司的所有申请实习的用户数量
	 * @author: mxy
	 *
	 */
	Long getTotlaAscUserJobs(Map<String, Object> map);

	/**
	 * @Description: 某个公司的所有申请实习成功的用户数量
	 * @author: mxy
	 *
	 */
	Long getTotlaAscUserJobsWithSuccess(Map<String, Object> map);

	/**
	 * @Description: 某个用户的所有申请信息
	 * @author: mxy
	 *
	 */
	List<UserDeviceVO> findAscUserJobsByUserid(Map<String, Object> map);


	List<UserDeviceVO> findAscUserJobsWithUserid(Map<String,Object> map);
	/**
	 * @Description: 某个用户的所有申请信息数量
	 * @author: mxy
	 *
	 */
	Long getTotlaAscUserJobsWithuser(Map<String,Object> map);
}
