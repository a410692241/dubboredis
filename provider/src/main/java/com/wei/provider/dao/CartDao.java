package com.wei.provider.dao;

import com.wei.service.bo.Cart;
import com.wei.service.bo.CartExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDao {
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}