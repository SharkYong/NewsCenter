package com.newscenter.Dao;

import com.newscenter.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 数据操作
 */
@Repository
public interface UserDao extends CrudRepository<UserEntity,Long>{
    public UserEntity findByUsernameAndPassword(String u_name,String u_password);



}
