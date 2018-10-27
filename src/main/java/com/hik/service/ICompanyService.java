package com.hik.service;

import java.util.List;
import java.util.Map;

import com.hik.entity.Company;
import com.hik.entity.vo.CompanyJobVO;
import com.hik.entity.vo.CompanyVO;

/**
 * @Description: 公司IService
 * @author: mxy
 *
 */
public interface ICompanyService {
	int deleteByPrimaryKey(Integer id);

	int insert(Company record);

	int insertSelective(Company record);

	Company selectByPrimaryKey(Integer id);

	//审核新注册的企业信息
	List<Company> getAllComInfoWithNewAdd(Map<String, Object> map);
	Long getAllComInfoWithNewAddCounts(Map<String, Object> map);

	int updateByPrimaryKeySelective(Company record);

	int updateByPrimaryKeyWithBLOBs(Company record);

	int updateByPrimaryKey(Company record);


	CompanyVO findJobDetail(Map<String, Object> map);

	List<CompanyVO> findCompanyRecruitJobs(Map<String, Object> map);

	CompanyVO findCompanyInfoByUserId(Integer userId);

	//审核岗位信息
	List<CompanyJobVO> getAllJobInfoWithNewAdd(Map<String, Object> map);
	Long getAllJobInfoWithNewAddCounts(Map<String, Object> map);
}
