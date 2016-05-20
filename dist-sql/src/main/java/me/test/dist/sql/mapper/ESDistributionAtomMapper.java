package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESDistributionAtom;

public interface ESDistributionAtomMapper {
    int deleteByPrimaryKey(BigDecimal daId);

    int insert(ESDistributionAtom record);

    int insertSelective(ESDistributionAtom record);

    ESDistributionAtom selectByPrimaryKey(BigDecimal daId);

    int updateByPrimaryKeySelective(ESDistributionAtom record);

    int updateByPrimaryKey(ESDistributionAtom record);
}