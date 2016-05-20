package me.test.dist.sql.mapper;

import java.math.BigDecimal;
import me.test.dist.sql.model.ESVoiceTraffic;

public interface ESVoiceTrafficMapper {
    int deleteByPrimaryKey(BigDecimal vtId);

    int insert(ESVoiceTraffic record);

    int insertSelective(ESVoiceTraffic record);

    ESVoiceTraffic selectByPrimaryKey(BigDecimal vtId);

    int updateByPrimaryKeySelective(ESVoiceTraffic record);

    int updateByPrimaryKey(ESVoiceTraffic record);
}