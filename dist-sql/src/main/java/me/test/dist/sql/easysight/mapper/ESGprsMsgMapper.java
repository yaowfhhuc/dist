package me.test.dist.sql.easysight.mapper;

import java.math.BigDecimal;

import me.test.dist.sql.easysight.model.ESGprsMsg;

public interface ESGprsMsgMapper {
    int deleteByPrimaryKey(BigDecimal gsId);

    int insert(ESGprsMsg record);

    int insertSelective(ESGprsMsg record);

    ESGprsMsg selectByPrimaryKey(BigDecimal gsId);

    int updateByPrimaryKeySelective(ESGprsMsg record);

    int updateByPrimaryKey(ESGprsMsg record);
}