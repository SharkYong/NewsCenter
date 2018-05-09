package com.newscenter.demo.controller;

import com.newscenter.demo.JPA.UserJPA;
import com.newscenter.demo.entity.HttpResult;
import com.newscenter.demo.entity.QUserEntity;
import com.newscenter.demo.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserJPA userJPA;
    @PersistenceContext
    private EntityManager mManager;
    /**
     * 注册用户
     * @param entity
     * @return
     */
    @PostMapping(value = "/register")
    public HttpResult<UserEntity> register(@RequestBody UserEntity entity){
        String username = entity.getU_name();
        String password = entity.getU_password();

        QUserEntity qUserEntity = QUserEntity.userEntity;
        JPAQuery<UserEntity> jpaQuery = new JPAQuery<>(mManager);

        //此账号已经存在，注册失败
        List<UserEntity> fetch = jpaQuery.select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.u_name.eq(username))
                .fetch();
        HttpResult<UserEntity> httpResult = new HttpResult<>();
        if (fetch != null && fetch.size() > 0){
            httpResult.setCode(-1);
            httpResult.setMessage("此账号已经有人注册,请换个账号再次注册");
        }else {
            UserEntity save = userJPA.save(entity);

            if (save != null){
                httpResult.setCode(1);
                httpResult.setMessage("注册成功!");
                httpResult.setData(save);
            }else {
                httpResult.setCode(-1);
                httpResult.setMessage("注册失败!");
            }

        }

        return httpResult;
    }

    @PostMapping(value = "/login")
    public HttpResult<UserEntity> login(@RequestBody UserEntity userEntity){
        String username = userEntity.getU_name();
        String password = userEntity.getU_password();

        QUserEntity qUserEntity = QUserEntity.userEntity;
        JPAQuery<UserEntity> jpaQuery = new JPAQuery<>(mManager);

        //此账号已经存在，注册失败
        List<UserEntity> fetch = jpaQuery.select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.u_name.eq(username))
                .fetch();
        HttpResult<UserEntity> httpResult = new HttpResult<>();
        if (fetch == null || fetch.size() <= 0){
            //没有此账号，需要先登录
            httpResult.setCode(-1);
            httpResult.setMessage("此账号还没有注册，请先注册");
        }else {
            List<UserEntity> result = jpaQuery.select(qUserEntity)
                    .from(qUserEntity)
                    .where(qUserEntity.u_name.eq(username),qUserEntity.u_password.eq(password))
                    .fetch();
            if (result != null && result.size() > 0){
                httpResult.setCode(1);
                httpResult.setMessage("登陆成功!");
                httpResult.setData(result.get(0));
            }else {
                httpResult.setCode(-1);
                httpResult.setMessage("密码不正确");
            }
        }
        return httpResult;
    }

}
