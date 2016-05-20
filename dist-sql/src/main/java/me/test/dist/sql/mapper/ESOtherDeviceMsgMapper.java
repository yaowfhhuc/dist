package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESOtherDeviceMsg;

public interface ESOtherDeviceMsgMapper {
    int deleteByPrimaryKey(BigDecimal odId);

    int insert(ESOtherDeviceMsg record);

    int insertSelective(ESOtherDeviceMsg record);

    ESOtherDeviceMsg selectByPrimaryKey(BigDecimal odId);

    int updateByPrimaryKeySelective(ESOtherDeviceMsg record);

    int updateByPrimaryKey(ESOtherDeviceMsg record);
}