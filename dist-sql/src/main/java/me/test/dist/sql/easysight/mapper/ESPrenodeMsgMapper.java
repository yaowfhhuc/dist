package me.test.dist.sql.easysight.mapper;

import java.math.BigDecimal;

import me.test.dist.sql.easysight.model.ESPrenodeMsg;

public interface ESPrenodeMsgMapper {
    int deleteByPrimaryKey(BigDecimal pmId);

    int insert(ESPrenodeMsg record);

    int insertSelective(ESPrenodeMsg record);

    ESPrenodeMsg selectByPrimaryKey(BigDecimal pmId);

    int updateByPrimaryKeySelective(ESPrenodeMsg record);

    int updateByPrimaryKey(ESPrenodeMsg record);
}