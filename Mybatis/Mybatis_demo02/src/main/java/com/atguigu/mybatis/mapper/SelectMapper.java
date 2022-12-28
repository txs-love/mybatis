package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author Sherry
 * @date 2022/12/28 0:11
 */
public interface SelectMapper {
    /**
     * 根据id查询用户信息
     */
    List<User> getUserById(@Param("id") Integer id);

    /**
     * 查询所有用户信息
     */
    List<User> getAllUser();

    /**
     * 查询所有用户条数
     */
    Integer getCount();

    /**
     * 根据用户id查询用户信息为map集合
     * @param id
     * @return
     */
    Map<String, Object> getUserToMap(@Param("id") int id);

    /**
     * 查询所有用户信息为map集合
     * @return
     * 将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，此时可以将这些map放在一个list集合中获取
     */
    List<Map<String, Object>> getAllUserToMap();

    /**
     * 查询所有用户信息为map集合
     *
     * @return 将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，并且最终要以一个map的方式返回数据，此时需要通过@MapKey注解设置map集合的键，值是每条数据所对应的map集合
     */
    @MapKey("id")
    Map<String, Object> getAllUserToMap1();

    /**
     * 根据用户名进行模糊查询
     * @param username
     * @return java.util.List<com.atguigu.mybatis.pojo.User>
     */
    List<User> getUserByLike(@Param("username") String username);

    /**
     * 根据id批量删除
     * @param ids
     * @return int
     */
    int deleteMore(@Param("ids") String ids);
}
