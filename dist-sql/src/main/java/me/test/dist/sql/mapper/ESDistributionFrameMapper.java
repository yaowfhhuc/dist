package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESDistributionFrame;

public interface ESDistributionFrameMapper {
    int deleteByPrimaryKey(BigDecimal dfId);

    int insert(ESDistributionFrame record);

    int insertSelective(ESDistributionFrame record);

    ESDistributionFrame selectByPrimaryKey(BigDecimal dfId);

    int updateByPrimaryKeySelective(ESDistributionFrame record);

    int updateByPrimaryKey(ESDistributionFrame record);
}