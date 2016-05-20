package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESTransferTraffice;

public interface ESTransferTrafficeMapper {
    int deleteByPrimaryKey(BigDecimal ttId);

    int insert(ESTransferTraffice record);

    int insertSelective(ESTransferTraffice record);

    ESTransferTraffice selectByPrimaryKey(BigDecimal ttId);

    int updateByPrimaryKeySelective(ESTransferTraffice record);

    int updateByPrimaryKey(ESTransferTraffice record);
}