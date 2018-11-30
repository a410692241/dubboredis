package com.wei.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wei.provider.dao.OrderDao;
import com.wei.service.bo.Order;
import com.wei.service.bo.OrderExample;
import com.wei.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public long countByExample(OrderExample example) {
        return orderDao.countByExample(example);
    }

    @Override
    public int deleteByExample(OrderExample example) {
        return orderDao.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Order record) {
        return orderDao.insert(record);
    }

    @Override
    public int insertSelective(Order record) {
        return orderDao.insertSelective(record);
    }

    @Override
    public List<Order> selectByExample(OrderExample example) {
        return orderDao.selectByExample(example);
    }

    @Override
    public Order selectByPrimaryKey(Integer id) {
        return orderDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Order record, OrderExample example) {
        return orderDao.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Order record, OrderExample example) {
        return orderDao.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderDao.updateByPrimaryKey(record);
    }
}
