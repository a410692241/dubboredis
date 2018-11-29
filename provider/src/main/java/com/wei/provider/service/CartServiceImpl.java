package com.wei.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wei.provider.dao.CartDao;
import com.wei.service.bo.Cart;
import com.wei.service.bo.CartExample;
import com.wei.service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Override
    public long countByExample(CartExample example) {
        return cartDao.countByExample(example);
    }

    @Override
    public int deleteByExample(CartExample example) {
        return cartDao.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cartDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Cart record) {
        return cartDao.insert(record);
    }

    @Override
    public int insertSelective(Cart record) {
        return cartDao.insertSelective(record);
    }

    @Override
    public List<Cart> selectByExample(CartExample example) {
        return cartDao.selectByExample(example);
    }

    @Override
    public Cart selectByPrimaryKey(Integer id) {
        return cartDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Cart record, CartExample example) {
        return cartDao.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Cart record, CartExample example) {
        return cartDao.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Cart record) {
        return cartDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Cart record) {
        return cartDao.updateByPrimaryKey(record);
    }
}
