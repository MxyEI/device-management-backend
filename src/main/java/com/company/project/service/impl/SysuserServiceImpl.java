package com.company.project.service.impl;

import com.company.project.dao.SysuserMapper;
import com.company.project.model.Sysuser;
import com.company.project.service.SysuserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/09/06.
 */
@Service
@Transactional
public class SysuserServiceImpl extends AbstractService<Sysuser> implements SysuserService {
    @Resource
    private SysuserMapper sysuserMapper;

    @Override
    public Sysuser findUsernameByLogin(Sysuser sysuser) {
        return sysuserMapper.findUsernameByLogin(sysuser);
    }

}
