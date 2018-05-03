package com.newscenter.demo.JPA;

import com.newscenter.demo.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;

public interface UserJPA extends JpaRepository<UserEntity, Long>
        ,JpaSpecificationExecutor<UserEntity>
        ,Serializable
        ,QueryDslPredicateExecutor<UserEntity> {

}
