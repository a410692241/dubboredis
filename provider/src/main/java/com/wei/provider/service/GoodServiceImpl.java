package com.wei.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wei.provider.dao.GoodDao;
import com.wei.service.bo.Good;
import com.wei.service.bo.GoodExample;
import com.wei.service.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodDao goodDao;
    @Override
    public long countByExample(GoodExample example) {
        return goodDao.countByExample(example);
    }

    @Override
    public int deleteByExample(GoodExample example) {
        return goodDao.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Good record) {
        return goodDao.insert(record);
    }

    @Override
    public int insertSelective(Good record) {
        return goodDao.insertSelective(record);
    }

    @Override
    public List<Good> selectByExample(GoodExample example) {
        return goodDao.selectByExample(example);
    }

    @Override
    public Good selectByPrimaryKey(Integer id) {
        return goodDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Good record, GoodExample example) {
        return goodDao.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Good record, GoodExample example) {
        return goodDao.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Good record) {
        return goodDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Good record) {
        return goodDao.updateByPrimaryKey(record);
    }
}
