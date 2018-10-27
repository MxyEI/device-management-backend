package com.hik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hik.entity.vo.CompanyJobVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Result;
import com.common.ResultGenerator;
import com.hik.entity.Company;
import com.hik.entity.PageBean;
import com.hik.entity.vo.CompanyVO;
import com.hik.service.ICompanyService;
import com.hik.util.ResponseUtil;
import com.hik.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 公司
 * @author: mxy
 *
 */
@Controller
@RequestMapping("/companys")
public class CompanyController {

	@Autowired
	private ICompanyService companyService;
	private static final Logger log = Logger.getLogger(CompanyController.class);

	/**
	 * @Description: 查询所有公司信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(rows)) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}

		if (StringUtil.isNotEmpty(sort) && StringUtil.isNotEmpty(order)) {
			map.put("order", order);
			map.put("sort", sort);
		}
		List<Company> companyList = companyService.getAllComInfoWithNewAdd(map);
		Long total = companyService.getAllComInfoWithNewAddCounts(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(companyList);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: company/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * @Description: 添加公司信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Company company) {
		int resultCode = 0;
		resultCode = companyService.insert(company);
		log.info("request: company/update , company: " + company.toString());
		if (resultCode > 0) {
			return ResultGenerator.genSuccessResult();
		}
		return ResultGenerator.genFailResult("添加失败！");
	}

	/**
	 * @Description: 更新公司信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(Company company) throws Exception {
		int resultTotal = 0;
		resultTotal = companyService.updateByPrimaryKeyWithBLOBs(company);
		log.info("request: company/update , " + company.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}


	/**
	 * @Description: 审核公司的信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/shenhecominfo", method = RequestMethod.POST)
	@ResponseBody
	public Result shenhecominfo(Company company) throws Exception {
		int resultTotal = 0;
		resultTotal = companyService.updateByPrimaryKeySelective(company);
		log.info("request: company/shenhecominfo , " + company.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * @Description: 查询某个公司信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Company result = companyService.selectByPrimaryKey(id);
		log.info("request: recruit/get , id " + id);
		if (result != null) {
			Map<String, Company> data = new HashMap<String, Company>();
			data.put("currentCompany", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	/**
	 * @Description: 查询某个公司的设备列表
	 * 失效的设备信息不会查询出来
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result getDetail(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<CompanyVO> result = companyService.findCompanyRecruitJobs(map);
		log.info("request: companyVO/get , id " + id);
		if (result != null && result.size() > 0) {
			Map<String, CompanyVO> data = new HashMap<String, CompanyVO>();
			data.put("data", result.get(0));
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("此公司无在招岗位");
		}
	}



}
