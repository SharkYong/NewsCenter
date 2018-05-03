/*
package com.newscenter.controller;

import com.newscenter.Dao.UserDao;
import com.newscenter.JPA.UserJPA;
import com.newscenter.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

*/
/**
 * 登录注册的控制层
 *//*

@Controller
@RequestMapping("/front/")
public class LogResController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserJPA userJPA;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/addRegister")
    public String register(HttpServletRequest request) {
        String u_name = request.getParameter("u_name");
        String u_password = request.getParameter("u_password");
        String u_password2 = request.getParameter("u_password2");

        if (u_password.equals(u_password2)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setU_name(u_name);
            userEntity.setU_password(u_password);
            userDao.save(userEntity);
            return "login";
        } else {
            return "register";
        }
    }

    @RequestMapping("/addLogin")
    public String login(HttpServletRequest request) {
        String u_name = request.getParameter("u_name");
        String u_password = request.getParameter("u_password");
        UserEntity userEntity = userDao.findByUsernameAndPassword(u_name, u_password);
        //List<UserEntity> list = userJPA.findAll();
        String str = "";
        if (userEntity != null) {
            str = "index";
        } else {
            str = "login";
        }
        return str;
    }
}
*/
