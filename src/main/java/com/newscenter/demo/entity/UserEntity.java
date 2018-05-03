package com.newscenter.demo.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体类
 */
@Entity
@Table(name = "user_table")
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "u_id")
    private Long u_id;

    @Column(name = "u_name")
    private String u_name;

    @Column(name = "u_password")
    private String u_password;

    public Long getU_id() {
        return u_id;
    }

    public void setU_id(Long u_id) {
        this.u_id = u_id;
    }

    public String getu_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getu_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }
}
