package me.test.dist.sql.easysight.mapper;

import java.math.BigDecimal;

import me.test.dist.sql.easysight.model.ESMasMsg;

public interface ESMasMsgMapper {
    int deleteByPrimaryKey(BigDecimal mmId);

    int insert(ESMasMsg record);

    int insertSelective(ESMasMsg record);

    ESMasMsg selectByPrimaryKey(BigDecimal mmId);

    int updateByPrimaryKeySelective(ESMasMsg record);

    int updateByPrimaryKey(ESMasMsg record);
}