package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESDataTraffic;

public interface ESDataTrafficMapper {
    int deleteByPrimaryKey(BigDecimal dtId);

    int insert(ESDataTraffic record);

    int insertSelective(ESDataTraffic record);

    ESDataTraffic selectByPrimaryKey(BigDecimal dtId);

    int updateByPrimaryKeySelective(ESDataTraffic record);

    int updateByPrimaryKey(ESDataTraffic record);
}