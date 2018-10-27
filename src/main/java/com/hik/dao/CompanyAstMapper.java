package com.hik.dao;

import java.util.List;
import java.util.Map;

import com.hik.entity.vo.CompanyVO;

/**
 * @Description: 公司多表关联Mapper
 * @author: mxy
 *
 */
public interface CompanyAstMapper {

	/**
	 * @Description: 获取某个设备信息和公司信息
	 * @author: mxy
	 *
	 */
	CompanyVO findJobDetail(Map<String, Object> map);

	/**
	 * @Description: 获取某个公司下的招聘信息和生效的岗位信息
	 * @author: mxy
	 *
	 */
	List<CompanyVO> findCompanyRecruitJobs(Map<String, Object> map);

	/**
	 * @Description: 通过用户id获取该用户所在的公司
	 * @author: mxy
	 *
	 */
	CompanyVO findCompanyInfoByUserId(Integer userId);

}
