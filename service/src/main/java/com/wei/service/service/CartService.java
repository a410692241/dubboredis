package com.wei.service.service;

import com.wei.service.bo.Cart;
import com.wei.service.bo.CartExample;
import com.wei.service.bo.Employee;
import com.wei.service.bo.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartService {
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
