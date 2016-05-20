package me.test.dist.sql.mapper;

import me.test.dist.sql.model.ESOdfMsg;
import me.test.dist.sql.model.ESOdfMsgKey;

public interface ESOdfMsgMapper {
    int deleteByPrimaryKey(ESOdfMsgKey key);

    int insert(ESOdfMsg record);

    int insertSelective(ESOdfMsg record);

    ESOdfMsg selectByPrimaryKey(ESOdfMsgKey key);

    int updateByPrimaryKeySelective(ESOdfMsg record);

    int updateByPrimaryKey(ESOdfMsg record);
}