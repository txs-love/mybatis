package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sherry
 * @date 2022/12/28 20:59
 */
public interface EmpMapper {
    /**
     * 查询所有员工
     */
    List<Emp> getAllEmp();

    List<Emp> getAllEmpOld();

    /**
     * 查询员工及其对应的部门信息
     */
    Emp getEmpAndDept(@Param("eid")Integer eid);

    /**
     * 通过分步查询，员工及所对应的部门信息
     * 分步查询第一步：查询员工信息
     * @param
     * @return com.atguigu.mybatis.pojo.Emp
     * @date 2022/2/27 20:17
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);



}
