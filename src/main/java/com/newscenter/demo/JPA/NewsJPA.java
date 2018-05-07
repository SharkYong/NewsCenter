package com.newscenter.demo.JPA;

import com.newscenter.demo.entity.NewsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;

public interface NewsJPA extends JpaRepository<NewsEntity, Long>
        ,JpaSpecificationExecutor<NewsEntity>
        ,Serializable
        ,QueryDslPredicateExecutor<NewsEntity> {

}
