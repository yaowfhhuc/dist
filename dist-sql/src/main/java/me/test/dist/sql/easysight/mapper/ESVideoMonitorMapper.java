package me.test.dist.sql.easysight.mapper;

import java.math.BigDecimal;

import me.test.dist.sql.easysight.model.ESVideoMonitor;

public interface ESVideoMonitorMapper {
    int deleteByPrimaryKey(BigDecimal vmId);

    int insert(ESVideoMonitor record);

    int insertSelective(ESVideoMonitor record);

    ESVideoMonitor selectByPrimaryKey(BigDecimal vmId);

    int updateByPrimaryKeySelective(ESVideoMonitor record);

    int updateByPrimaryKey(ESVideoMonitor record);
}