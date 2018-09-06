package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Sysuser;
import com.company.project.service.SysuserService;
import com.company.project.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/09/06.
 */
@RestController
@RequestMapping("manageback/sysuser")
public class SysuserController {
    @Resource
    private SysuserService sysuserService;

    @PostMapping("/login")
    public Result login(Sysuser sysuser) {
        try {
            sysuser.setPassword(MD5Util.MD5Encode(sysuser.getPassword(), "UTF-8"));
        } catch (Exception e) {
            sysuser.setPassword("");
        }
        Sysuser rs = sysuserService.findUsernameByLogin(sysuser);
        if(rs==null){
            return ResultGenerator.genFailResult("请认真核对用户名或密码");
        }
        rs.setPassword("");
        return ResultGenerator.genSuccessResult(rs);
    }

    @PostMapping("/add")
    public Result add(Sysuser sysuser) {
        sysuserService.save(sysuser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sysuserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Sysuser sysuser) {
        sysuserService.update(sysuser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Sysuser sysuser = sysuserService.findById(id);
        return ResultGenerator.genSuccessResult(sysuser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Sysuser> list = sysuserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


}
