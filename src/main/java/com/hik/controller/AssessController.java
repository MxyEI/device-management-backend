package com.hik.controller;

import com.common.Result;
import com.common.ResultGenerator;
import com.hik.entity.Assess;
import com.hik.service.IAssessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 反馈评价
 * @author: mxy
 *
 */
@Controller
@RequestMapping("/assess")
public class AssessController {

    @Autowired
    private IAssessService iAssessService;
    private static final Logger log = Logger.getLogger(AssessController.class);

    /**
     * @Description: 添加反馈
     * @author: mxy
     *
     *
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Assess assess) {
        int resultCode = 0;
        resultCode = iAssessService.insert(assess);
        //log.info("request: appraisalForm/add , appraisalForm: " + appraisalForm.toString());
        if (resultCode > 0) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("添加失败！");
    }

    /**
     * @Description: 更新反馈
     * @author: mxy
     *
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public Result update(Assess assess) throws Exception {
        int resultTotal = 0;
        resultTotal = iAssessService.updateByPrimaryKeySelective(assess);
        //log.info("request: appraisalForm/update , " + appraisalForm.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }


    /**
     * @Description: 查询反馈评价信息
     * @author: mxy
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result getInfoByID(@PathVariable("id") Integer id) throws Exception {
        Assess resultAssess = iAssessService.selectByPrimaryKey(id);

        //log.info("request: assess/findById");
        if (resultAssess != null) {
            Map<String, Assess> data = new HashMap<String, Assess>();
            data.put("currentAssess", resultAssess);
            return ResultGenerator.genSuccessResult(data);
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }


    /**
     * @Description: 查询反馈评价信息by userid
     * @author: mxy
     *
     */
    @RequestMapping(value = "/assessbyuid/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public Result getInfoByUserID(@PathVariable("userid") Integer userid) throws Exception {
        Assess resultAssess = iAssessService.selectByUserid(userid);

        //log.info("request: assess/findById");
        if (resultAssess != null) {
            Map<String, Assess> data = new HashMap<String, Assess>();
            data.put("currentAssess", resultAssess);
            return ResultGenerator.genSuccessResult(data);
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }

}
