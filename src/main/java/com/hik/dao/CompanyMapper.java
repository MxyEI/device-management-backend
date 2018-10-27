package com.hik.dao;

import com.hik.entity.Company;

import java.util.List;
import java.util.Map;

/**
 * @Description: 公司Mapper
 * @author: mxy
 *
 */
public interface CompanyMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Company record);

	int insertSelective(Company record);

	Company selectByPrimaryKey(Integer id);

	/**
	 * @Description: 新注册的实习单位待审核的信息
	 * @author: mxy
	 *
	 */
	List<Company> getAllComInfoWithNewAdd(Map<String, Object> map);

	/**
	 * @Description: 新注册的实习单位待审核的数量
	 * @author: mxy
	 *
	 */
	Long getAllComInfoWithNewAddCounts(Map<String, Object> map);

	int updateByPrimaryKeySelective(Company record);

	int updateByPrimaryKeyWithBLOBs(Company record);

	int updateByPrimaryKey(Company record);
}