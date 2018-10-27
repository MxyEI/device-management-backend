package com.hik.dao;

import com.hik.entity.vo.CompanyJobVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 公司设备关联AstMapper
 * @author: mxy
 *
 */
@Repository
public interface CompanyWithJobMapper {

	/**
	 * @Description: 管理部门查询企业发布的新设备信息并审核
	 * @author: mxy
	 *
	 */
	List<CompanyJobVO> getAllJobInfoWithNewAdd(Map<String, Object> map);

	/**
	 * @Description: 管理部门查询实习单位发布的新岗位信息的数量
	 * @author: mxy
	 *
	 */
	Long getAllJobInfoWithNewAddCounts(Map<String, Object> map);
}
