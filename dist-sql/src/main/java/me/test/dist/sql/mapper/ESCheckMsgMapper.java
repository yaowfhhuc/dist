package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESCheckMsg;

public interface ESCheckMsgMapper {
    int deleteByPrimaryKey(BigDecimal cmId);

    int insert(ESCheckMsg record);

    int insertSelective(ESCheckMsg record);

    ESCheckMsg selectByPrimaryKey(BigDecimal cmId);

    int updateByPrimaryKeySelective(ESCheckMsg record);

    int updateByPrimaryKey(ESCheckMsg record);
}