package com.company.project.model;

import javax.persistence.*;

public class Sysuser {
    @Id
    private Integer sysid;

    private String username;

    private String password;

    /**
     * @return sysid
     */
    public Integer getSysid() {
        return sysid;
    }

    /**
     * @param sysid
     */
    public void setSysid(Integer sysid) {
        this.sysid = sysid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}