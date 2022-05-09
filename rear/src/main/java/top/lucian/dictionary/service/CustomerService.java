package top.lucian.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucian.dictionary.dao.CustomerMapper;
import top.lucian.dictionary.entity.Customer;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer login(String username, String password) {
        return customerMapper.login(username, password);
    }

    public boolean register(Customer customer) {
        return customerMapper.register(customer);
    }

    public boolean updateMessage(Customer customer) {
        return customerMapper.updateMessage(customer);
    }

    public Map<String, Object> QueryUserById(Integer id) {
        return customerMapper.QueryUserById(id);
    }

    public boolean checkUserName(String username) {
        String s = customerMapper.checkUserName(username);
        if (s != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePass(String username,String newPass) {
        return customerMapper.updatePass(username, newPass);
    }

}
