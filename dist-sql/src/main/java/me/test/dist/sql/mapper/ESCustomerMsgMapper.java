package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESCustomerMsg;

public interface ESCustomerMsgMapper {
    int deleteByPrimaryKey(BigDecimal basicId);

    int insert(ESCustomerMsg record);

    int insertSelective(ESCustomerMsg record);

    ESCustomerMsg selectByPrimaryKey(BigDecimal basicId);

    int updateByPrimaryKeySelective(ESCustomerMsg record);

    int updateByPrimaryKey(ESCustomerMsg record);
}