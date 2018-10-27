package com.hik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hik.entity.Device;
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
import com.hik.service.IDeviceService;
import com.hik.util.DateUtil;
import com.hik.util.ResponseUtil;
import com.hik.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description:设备
 * @author: mxy
 *
 */
@Controller
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	private IDeviceService deviceService;
	private static final Logger log = Logger.getLogger(DeviceController.class);// 日志文件

	/**
	 * @Description: 列出所有设备
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
					   @RequestParam(value = "rows", required = false) String rows,
					   @RequestParam(value = "sort", required = false) String sort,
					   @RequestParam(value = "order", required = false) String order, Device device, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (device != null && device.getCompanyId() != null) {
			map.put("companyId", device.getCompanyId());
		}
		if (device != null && device.getName() != null) {
			map.put("name", StringUtil.formatLike(device.getName()));
		}
		if (sort != null && order != null) {
			map.put("order", order);
			map.put("sort", sort);
		}
		List<Device> deviceList = deviceService.selectJobsByName(map);
		Long total = deviceService.getTotalJobsByName(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(deviceList);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: device/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * @Description: 添加设备信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Device device) throws Exception {
		log.info(device.getLapse());
		int resultTotal = 0;
		device.setGmtCreate(DateUtil.getCurrentDateStr());
		resultTotal = deviceService.insertSelective(device);
		log.info("request: device/save , " + device.toString());

		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("添加失败");
		}
	}

	/**
	 * @Description: 更新设备信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(Device device) throws Exception {
		int resultTotal = 0;
		device.setGmtModify(DateUtil.getCurrentDateStr());
		resultTotal = deviceService.updateByPrimaryKeyWithBLOBs(device);
		log.info("request: device/update , " + device.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * @Description: 审核功能 批准
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/shenhe", method = RequestMethod.PATCH)
	@ResponseBody
	public Result updates(Device device) throws Exception {
		int resultTotal = 0;
		device.setGmtModify(DateUtil.getCurrentDateStr());
		resultTotal = deviceService.updateByPrimaryKeySelective(device);
		log.info("request: device/update , " + device.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}



	/**
	 * @Description: 查询设备信息
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable("id") Integer id) throws Exception {
		Device device = deviceService.selectByPrimaryKey(id);
		Result result = ResultGenerator.genSuccessResult();
		result.setData(device);
		log.info("request: device/findById");
		return result;
	}

	/**
	 * @Description: 删除设备信息
	 * @param id
	 *            设备id 若userdevice用户设备关联表中没有该设备id,则直接删除，否则设为失效
	 * @author: mxy
	 *
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable("id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		int resultCode = deviceService.deleteByPrimaryKey(id);
		log.info("request: device/delete , id: " + id);
		if (resultCode == 0) {
			Device j = new Device();
			j.setId(id);
			j.setLapse(true);
			deviceService.updateByPrimaryKeySelective(j);
			return ResultGenerator.genFailResult("此设备在使用，不能删除，将设置为失效");
		}
		return ResultGenerator.genSuccessResult();
	}
}
