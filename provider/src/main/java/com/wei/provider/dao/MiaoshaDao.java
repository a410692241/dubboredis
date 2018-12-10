package com.wei.provider.dao;

import com.wei.service.bo.Miaosha;
import com.wei.service.bo.MiaoshaExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MiaoshaDao {
    long countByExample(MiaoshaExample example);

    int deleteByExample(MiaoshaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Miaosha record);

    int insertSelective(Miaosha record);

    List<Miaosha> selectByExample(MiaoshaExample example);

    Miaosha selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Miaosha record, @Param("example") MiaoshaExample example);

    int updateByExample(@Param("record") Miaosha record, @Param("example") MiaoshaExample example);

    int updateByPrimaryKeySelective(Miaosha record);

    int updateByPrimaryKey(Miaosha record);
}