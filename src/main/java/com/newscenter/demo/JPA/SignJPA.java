package com.newscenter.demo.JPA;

import com.newscenter.demo.entity.SignEntity;
import com.newscenter.demo.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;

public interface SignJPA extends JpaRepository<SignEntity, Long>
        ,JpaSpecificationExecutor<SignEntity>
        ,Serializable
        ,QueryDslPredicateExecutor<SignEntity> {

}
