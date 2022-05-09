package top.lucian.dictionary.dao;

import org.apache.ibatis.annotations.Mapper;
import top.lucian.dictionary.entity.Customer;

import java.util.*;

@Mapper
public interface CustomerMapper {
    //用户登录
    Customer login(String username,String password);

    //用户注册
    boolean register(Customer customer);

    //修改用户信息
    boolean updateMessage(Customer customer);

    //查询评论人员的信息
    Map<String, Object> QueryUserById(Integer id);

    //检验是否存在该用户
    String checkUserName(String username);

    boolean updatePass(String username,String newPass);


}
