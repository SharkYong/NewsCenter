package com.newscenter.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newscenter.demo.JPA.NewsJPA;
import com.newscenter.demo.api.NewsApi;
import com.newscenter.demo.entity.HttpResult;
import com.newscenter.demo.entity.NewsEntity;
import com.newscenter.demo.entity.QNewsEntity;
import com.newscenter.demo.entity.QUserEntity;
import com.newscenter.demo.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping()
public class NewsController {
    private String newsKey = "91e6c1a86fde69e53353087c5f9ecd12";

    @Autowired
    NewsApi mNewsApi;
    @Autowired
    NewsJPA mNewsJPA;
    @PersistenceContext
    private EntityManager mManager;

    @RequestMapping(value = "/news")
    public HttpResult<List<NewsEntity>> getTopNews(String type,boolean isrefresh){

        HttpResult<List<NewsEntity>>httpResult = new HttpResult<>();
        QNewsEntity qNewsEntity = QNewsEntity.newsEntity;
        JPAQuery<NewsEntity> jpaQuery = new JPAQuery<>(mManager);

        if (isrefresh){
            getDataFromAPI(type,httpResult);
        }else {
            //从数据库获取type类型的新闻
            List<NewsEntity> fetch = jpaQuery.select(qNewsEntity)
                    .from(qNewsEntity)
                    .where(qNewsEntity.category.eq(type))
                    .fetch();
            if (fetch == null || fetch.size() <= 0){
                getDataFromAPI(type,httpResult);
            }else {
                httpResult.setMessage("获取新闻成功!");
                httpResult.setCode(1);
                httpResult.setData(fetch);
            }
        }

        return httpResult;
    }

    public void getDataFromAPI(String type,HttpResult<List<NewsEntity>>httpResult){
        String value = mNewsApi.getdis(newsKey, type);
        JSONObject jsonObject = JSONObject.parseObject(value);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONArray data = result.getJSONArray("data");
        List<NewsEntity> newsEntities = data.toJavaList(NewsEntity.class);
        httpResult.setData(newsEntities);
        httpResult.setCode(1);
        httpResult.setMessage("获取新闻成功!");
        //最后需要放置到数据库中
        for (NewsEntity newsEntity:newsEntities){
            newsEntity.setCategory(type);
        }

        QNewsEntity qNewsEntity = QNewsEntity.newsEntity;
        JPAQuery<NewsEntity> jpaQuery = new JPAQuery<>(mManager);
        //从数据库获取type类型的新闻
        List<NewsEntity> fetch = jpaQuery.select(qNewsEntity)
                .from(qNewsEntity)
                .where(qNewsEntity.category.eq(type))
                .fetch();
        if (fetch == null || fetch.size() <= 0){
            mNewsJPA.save(newsEntities);
        }else {
            //遍历本地数据库,如果此条新闻已经存在，则删除
            for (NewsEntity newsLocal:fetch){
                for (Iterator<NewsEntity> iterator = newsEntities.iterator();iterator.hasNext();){
                    NewsEntity newsServer = iterator.next();
                    if (newsServer.getTitle().equals(newsLocal.getTitle())){
                        iterator.remove();
                    }
                }
            }
            mNewsJPA.delete(fetch);
            fetch.addAll(0,newsEntities);
            mNewsJPA.save(fetch);
            httpResult.setMessage("获取新闻成功!");
            httpResult.setCode(1);
            httpResult.setData(fetch);
        }
    }

    //新闻发布
    @PostMapping(value = "/news")
    public NewsEntity addNews(@Valid NewsEntity news){
        return mNewsJPA.save(news);
    }

    //根据ID删除新闻
    @DeleteMapping(value = "/news/{id}")
    public void newsDelete(@PathVariable("id") Long id){
        mNewsJPA.delete(id);
    }

}
