package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * @author Sherry
 * @date 2022/12/28 20:59
 */
public interface DeptMapper {
    /**
     * 通过分步查询，员工及所对应的部门信息
     * 分步查询第二步：通过did查询员工对应的部门信息
     * @param
     * @return com.atguigu.mybatis.pojo.Emp
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);
    /**
     * 获取部门及其所有的员工信息
     */
    Dept getDeptAndEmp(@Param("did") Integer did);

}
