package com.newscenter.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newscenter.demo.JPA.NewsJPA;
import com.newscenter.demo.JPA.SignJPA;
import com.newscenter.demo.api.NewsApi;
import com.newscenter.demo.entity.HttpResult;
import com.newscenter.demo.entity.NewsEntity;
import com.newscenter.demo.entity.QNewsEntity;
import com.newscenter.demo.entity.QSignEntity;
import com.newscenter.demo.entity.QUserEntity;
import com.newscenter.demo.entity.SignEntity;
import com.newscenter.demo.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping()
public class SignController {

    @Autowired
    SignJPA mSignJPA;
    @PersistenceContext
    private EntityManager mManager;

    @RequestMapping(value = "/getSign")
    public HttpResult<List<SignJPA>> getSign(String user_name){
        QSignEntity qUserEntity = QSignEntity.signEntity;
        JPAQuery<SignEntity> jpaQuery = new JPAQuery<>(mManager);

        //此账号已经存在，注册失败
        List<SignEntity> fetch = jpaQuery.select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.user_name.eq(user_name))
                .fetch();
        HttpResult httpResult = new HttpResult();
        if (fetch == null || fetch.size() <= 0){
            httpResult.setCode(-1);
            httpResult.setMessage("暂无签到");
        }else {
                httpResult.setCode(1);
                httpResult.setMessage("获取签到数据成功!");
                httpResult.setData(fetch);
        }

        return httpResult;
    }
    @RequestMapping(value = "/setSign")
    public HttpResult<List<SignEntity>> setSign(String user_name,String date){
        QSignEntity qUserEntity = QSignEntity.signEntity;
        JPAQuery<SignEntity> jpaQuery = new JPAQuery<>(mManager);

        List<SignEntity> fetch = jpaQuery.select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.user_name.eq(user_name),qUserEntity.date.eq(date))
                .fetch();
        HttpResult httpResult = new HttpResult();
        if (fetch == null || fetch.size() <= 0){
            SignEntity signEntity = new SignEntity();
            signEntity.setDate(date);
            signEntity.setUser_name(user_name);
            mSignJPA.saveAndFlush(signEntity);
            httpResult.setCode(1);
            httpResult.setMessage("签到成功!");
        }else {
            httpResult.setCode(1);
            httpResult.setMessage("您今日已经签过啦!");
        }
        JPAQuery<SignEntity> jpaQuery1 = new JPAQuery<>(mManager);
        List<SignEntity> fetch1 = jpaQuery1.select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.user_name.eq(user_name))
                .fetch();
        httpResult.setData(fetch1);
        return httpResult;
    }
}
