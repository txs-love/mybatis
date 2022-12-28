package com.atguigu.mybatis.mapper;
import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Sherry
 * @date 2022/12/25 14:01
 */
public interface ParameterMapper {
    /**
     * 验证登录
     */
    User checkLogin(String username,String password);

    /**
     * 根据用户名查询用户
     */
    User GetUserByUsername(String username);
    /**
     * 查询所有的员工信息;
     */

    User checkLoginByMap(Map<String, Object> map);

    User insertUser(User user);
    User CheckLoginByParam(@Param("username") String username, @Param("password") String password);
}
