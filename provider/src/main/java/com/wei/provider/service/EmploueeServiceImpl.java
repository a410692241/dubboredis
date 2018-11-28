package com.wei.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wei.provider.dao.EmployeeDao;
import com.wei.service.bo.Employee;
import com.wei.service.bo.EmployeeExample;
import com.wei.service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class EmploueeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public long countByExample(EmployeeExample example) {
        return employeeDao.countByExample(example);
    }

    @Override
    public int deleteByExample(EmployeeExample example) {
        return employeeDao.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer employeeId) {
        return employeeDao.deleteByPrimaryKey(employeeId);
    }

    @Override
    public int insert(Employee record) {
        return employeeDao.insert(record);
    }

    @Override
    public int insertSelective(Employee record) {
        return employeeDao.insertSelective(record);
    }

    @Override
    public List<Employee> selectByExample(EmployeeExample example) {
        return employeeDao.selectByExample(example);
    }

    @Override
    public Employee selectByPrimaryKey(Integer employeeId) {
        return employeeDao.selectByPrimaryKey(employeeId);
    }

    @Override
    public int updateByExampleSelective(Employee record, EmployeeExample example) {
        return employeeDao.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Employee record, EmployeeExample example) {
        return employeeDao.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {
        return employeeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return employeeDao.updateByPrimaryKey(record);
    }
}
