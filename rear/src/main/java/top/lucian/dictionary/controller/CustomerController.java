package top.lucian.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lucian.dictionary.entity.Customer;
import top.lucian.dictionary.entity.Vocabulary;
import top.lucian.dictionary.service.CustomerService;
import top.lucian.dictionary.util.JwtUtil;
import top.lucian.dictionary.util.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public Customer login(String username, String password) {
        Customer customer = customerService.login(username, password);
        if (customer != null) {
            customer.setToken(JwtUtil.createToken());
            return customer;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/register")
    public Result register(Customer customer) {
        System.out.println(customer);
        boolean register = customerService.register(customer);
        if (register) {
            return new Result(200, "success");
        } else {
            return new Result(500, "error");
        }
    }

    @RequestMapping("/checkUserName")
    public boolean checkUserName(String username) {
        System.out.println(username);
        return customerService.checkUserName(username);
    }

    @RequestMapping("/updatePass")
    public boolean updatePass(String username,String newpass) {
       return customerService.updatePass(username, newpass);
    }

    @GetMapping("/checkToken")
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam(value = "file") MultipartFile headpic) throws IOException {
        System.out.println(headpic);
        if (headpic != null && !headpic.isEmpty()) {
            String filename = headpic.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf("."));//.jpg
            String uuid = UUID.randomUUID().toString().replace("-", "");//避免重复名字
            final String NEWFILENAME = uuid + extName;
            headpic.transferTo(new File("/Users/xuzan/IdeaProjects/dictionary/src/main/resources/static/Userimg/" + NEWFILENAME));
            HashMap<String, String> map = new HashMap<>();
            map.put("src", "http://localhost:8081/Userimg/" + NEWFILENAME);
            final Result result = new Result();
            result.setCode(0);
            result.setData(map);
            return result;
        }
        return new Result(1, "文件为空");
    }

    @RequestMapping("/updateUserInfo")
    public Customer updateUserInfo(Customer customer) {
        System.out.println(customer);
        boolean b = customerService.updateMessage(customer);
        if (b) {
            return customerService.login(customer.getUsername(), customer.getPassword());
        }
        return null;
    }

//    @RequestMapping("/updatePass")
//    public Customer updatePass(Integer id, String pass) {
//        boolean b = customerService.updatePass(id, pass);
//        if (b) {
//            return customerService.login(customer.getUsername(), customer.getPassword());
//        }
//        return null;
//    }
}
