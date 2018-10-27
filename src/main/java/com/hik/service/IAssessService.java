package com.hik.service;

import com.hik.entity.Assess;

public interface IAssessService {
    int deleteByPrimaryKey(Integer pk);

    int insert(Assess assess);

    int insertSelective(Assess assess);

    Assess selectByPrimaryKey(Integer pk);

    int updateByPrimaryKeySelective(Assess assess);

    int updateByPrimaryKey(Assess assess);

    Assess selectByUserid(Integer pk);
}
