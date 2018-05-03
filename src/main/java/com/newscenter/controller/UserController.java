package com.newscenter.controller;

import com.newscenter.JPA.UserJPA;
import com.newscenter.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserJPA userJPA;

    /**
     * 查询
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserEntity> list(){
        return userJPA.findAll();
    }

    /**
     * 添加、更新用户方法
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(UserEntity entity, HttpServletRequest request){
        UserEntity userEntity = userJPA.save(entity);
        if(userEntity.getU_id().equals(request.getParameter("u_id"))){
            return "修改成功！";
        }else {
            return "信息有误！";
        }
    }
    /*public UserEntity save(UserEntity entity)
    {
        UserEntity userEntity = userJPA.save(entity);
        if (userEntity.equals(userEntity.getU_id())){
            return userEntity;
        }else {
            return "";
        }
        //return userJPA.save(entity);
    }*/

    /**
     * 删除用户方法
     * @param u_id 用户编号
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<UserEntity> delete(Long u_id)
    {
        userJPA.delete(u_id);
        return userJPA.findAll();
    }
}
