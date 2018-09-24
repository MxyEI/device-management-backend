package com.hik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hik.entity.DeviceWithUser;
import com.hik.entity.vo.UserDeviceVO;
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
import com.hik.entity.PageBean;
import com.hik.service.IUserDeviceService;
import com.hik.util.DateUtil;
import com.hik.util.ResponseUtil;
import com.hik.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 用户设备关联Controller
 * @author: mxy
 *
 */
@Controller
@RequestMapping("/userDevices")
public class UserDeviceController {

	@Autowired
	IUserDeviceService userDeviceService;
	private static final Logger log = Logger.getLogger(UserDeviceController.class);

	/**
	 * @Description: 列出某个公司的申请用户和申请维修的设备信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order, UserDeviceVO userJob,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (userJob.getCompanyId() != null) {
			map.put("companyId", userJob.getCompanyId());
		}
		if (sort != null && order != null) {
			map.put("order", order);
			// 表格的字段名和数据库的字段名不一样，所以需要将sort转换成数据库中对应的字段名
			if (sort.indexOf(".") < 0) {
				sort = "v." + sort;
			} else {
				String pre = sort.substring(0, sort.indexOf("."));
				String tableName = (pre.equals("user") ? "u" : pre.equals("device") ? "j" : "v");
				sort = tableName + sort.substring(sort.indexOf("."));
			}
			map.put("sort", sort);
		}
//		if (userJob.getUser() != null && userJob.getUser().getRealname() != null) {
//			map.put("username", StringUtil.formatLike(userJob.getUser().getRealname()));
//		}

		//只按学号（用户名）搜索
		if (userJob.getUser() != null && userJob.getUser().getUsername() != "") {
			map.put("username", StringUtil.formatLike(userJob.getUser().getUsername()));
		}

//		if (userJob.getDevice() != null && userJob.getDevice().getName() != null) {
//			map.put("jobname", StringUtil.formatLike(userJob.getDevice().getName()));
//		}
		//log.info(userJob);
		List<UserDeviceVO> list = userDeviceService.findAscUserJobs(map);
		Long total = userDeviceService.getTotlaAscUserJobs(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}


	/**
	 * @Description: 列出某个公司的申请成功的用户和申请岗位信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:46:21
	 */
	@RequestMapping(value = "/datagridwithsuccess", method = RequestMethod.GET)
	public String listwithsuccess(@RequestParam(value = "page", required = false) String page,
					   @RequestParam(value = "rows", required = false) String rows,
					   @RequestParam(value = "sort", required = false) String sort,
					   @RequestParam(value = "order", required = false) String order, UserDeviceVO userJob,
					   HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (userJob.getCompanyId() != null) {
			map.put("companyId", userJob.getCompanyId());
		}
		if (sort != null && order != null) {
			map.put("order", order);
			// 表格的字段名和数据库的字段名不一样，所以需要将sort转换成数据库中对应的字段名
			if (sort.indexOf(".") < 0) {
				sort = "v." + sort;
			} else {
				String pre = sort.substring(0, sort.indexOf("."));
				String tableName = (pre.equals("user") ? "u" : pre.equals("device") ? "j" : "v");
				sort = tableName + sort.substring(sort.indexOf("."));
			}
			map.put("sort", sort);
		}
//		if (userJob.getUser() != null && userJob.getUser().getRealname() != null) {
//			map.put("realname", StringUtil.formatLike(userJob.getUser().getRealname()));
//		}
//		if (userJob.getDevice() != null && userJob.getDevice().getName() != null) {
//			map.put("jobname", StringUtil.formatLike(userJob.getDevice().getName()));
//		}

		//按学号（用户名）和真实姓名搜索
		if (userJob.getUser() != null && userJob.getUser().getUsername() != "") {
			map.put("username", StringUtil.formatLike(userJob.getUser().getUsername()));
		}
		if (userJob.getUser() != null && userJob.getUser().getRealname() != "") {
			map.put("realname", StringUtil.formatLike(userJob.getUser().getRealname()));
		}

		//log.info(userJob);
		List<UserDeviceVO> list = userDeviceService.findAscUserJobsWithSuccess(map);
		Long total = userDeviceService.getTotlaAscUserJobsWithSuccess(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}




	/**
	 * @Description: 列出某个用户所有的申请信息
	 * @author: hw
	 * @date: 2018年4月28日 下午1:46:21
	 */
	@RequestMapping(value = "/datagridwithmyapply/{userid}", method = RequestMethod.GET)
	public String listwithusersapply(@PathVariable(value = "userid") Integer userid,@RequestParam(value = "page", required = false) String page,
								  @RequestParam(value = "rows", required = false) String rows,
								  @RequestParam(value = "sort", required = false) String sort,
								  @RequestParam(value = "order", required = false) String order, UserDeviceVO userJob,
								  HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
//		if (userJob.getUserId() != null) {
//			map.put("userid", userid);
//		}
		map.put("userid", userid);
		if (sort != null && order != null) {
			map.put("order", order);
			// 表格的字段名和数据库的字段名不一样，所以需要将sort转换成数据库中对应的字段名
			if (sort.indexOf(".") < 0) {
				sort = "v." + sort;
			} else {
				String pre = sort.substring(0, sort.indexOf("."));
				String tableName = (pre.equals("user") ? "u" : pre.equals("device") ? "j" : "v");
				sort = tableName + sort.substring(sort.indexOf("."));
			}
			map.put("sort", sort);
		}
		if (userJob.getCompany() != null && userJob.getCompany().getName() != "") {
			map.put("comname", StringUtil.formatLike(userJob.getCompany().getName()));
		}
		if (userJob.getDevice() != null && userJob.getDevice().getName() != null) {
			map.put("jobname", StringUtil.formatLike(userJob.getDevice().getName()));
		}
		//log.info(userJob);
		List<UserDeviceVO> list = userDeviceService.findAscUserJobsWithUserid(map);
		Long total = userDeviceService.getTotlaAscUserJobsWithuser(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}




	/**
	 * @Description: 申请岗位
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(DeviceWithUser deviceWithUser) throws Exception {
		int resultTotal = 0;
		deviceWithUser.setGmtCreate(DateUtil.getCurrentDateStr());
		resultTotal = userDeviceService.insertSelective(deviceWithUser);
		log.info("request: device/save , " + deviceWithUser.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("添加失败");
		}
	}

	/**
	 * @Description: 获取用户申请维修的设备
	 * @param 用户设备关联表id:vId
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/{userid}/{deviceid}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable(value = "userid") Integer userid,@PathVariable(value = "deviceid") Integer deviceid) throws Exception {
		if (userid == null||deviceid == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid",userid);
		map.put("deviceid",deviceid);
		DeviceWithUser result = userDeviceService.selectByuidjid(map);
		log.info("request: userdevice/get , userid,deviceid: " + userid+","+deviceid);
		if (result != null) {
			Map<String, DeviceWithUser> data = new HashMap<String, DeviceWithUser>();
			data.put("data", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}


	/**
	 * @Description: 获取用户是否申请成功了维修,只允许申请成功一个工单
	 * @param 用户工作关联表id:vId
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/uid/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public Result getsuccessed(@PathVariable(value = "userid") Integer userid,HttpServletResponse response) throws Exception {
		if (userid == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid",userid);
		List<DeviceWithUser> list = userDeviceService.selectByuid(map);
		Long total = userDeviceService.selectByuidCounts(map);
		log.info("request: userdevice/get , userid: " + userid);

		Result result = ResultGenerator.genSuccessResult();
		result.setData(list);
		log.info("request: userjobs/list , map: " + map.toString());
		return result;
	}


	/**
	 * @Description: 取消申请
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		int resultCode = userDeviceService.deleteByPrimaryKey(id);
		log.info("request: userdevice/delete , id: " + id);
		if (resultCode > 0) {
			return ResultGenerator.genSuccessResult();
		}
		return ResultGenerator.genFailResult("ERROR");
	}

	/**
	 * @Description: 更新用户岗位信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(DeviceWithUser deviceWithUser) throws Exception {
		deviceWithUser.setGmtModify(DateUtil.getCurrentDateStr());
		int resultTotal = userDeviceService.updateByPrimaryKeySelective(deviceWithUser);
		log.info("request: userdevice/update , userdevice: " + deviceWithUser.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

}
