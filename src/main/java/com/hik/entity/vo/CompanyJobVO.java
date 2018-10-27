package com.hik.entity.vo;

import com.hik.entity.Company;
import com.hik.entity.Device;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyJobVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 申请的岗位信息
     */
    private Device device;

    /**
     * 申请岗位所在的公司
     */

    private Company company;

    /**
     * 审核状态
     */
    private Boolean success;

    private String gmtCreate;

    private String gmtModify;

}
