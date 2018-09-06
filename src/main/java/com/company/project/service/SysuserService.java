package com.company.project.service;
import com.company.project.model.Sysuser;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2018/09/06.
 */
public interface SysuserService extends Service<Sysuser> {
      Sysuser findUsernameByLogin(Sysuser sysuser);
}
