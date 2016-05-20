package me.test.dist.sql.mapper;

import me.test.dist.sql.model.ESTypeFuncPort;

public interface ESTypeFuncPortMapper {
    int insert(ESTypeFuncPort record);

    int insertSelective(ESTypeFuncPort record);
}