package com.hik.entity;

import lombok.Data;

/**
 * @Description: 反馈表
 * @author: mxy
 *
 */
@Data
public class Assess {
    private Integer id;
    private String assesscontent;
    private Integer userid;
    private String gmtCreate;

    private String gmtModify;

    private Boolean dr;
}
