package com.wei.provider.dao;

import com.wei.service.bo.Good;
import com.wei.service.bo.GoodExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GoodDao {
    long countByExample(GoodExample example);

    int deleteByExample(GoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Good record);

    int insertSelective(Good record);

    List<Good> selectByExample(GoodExample example);

    Good selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Good record, @Param("example") GoodExample example);

    int updateByExample(@Param("record") Good record, @Param("example") GoodExample example);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);
}