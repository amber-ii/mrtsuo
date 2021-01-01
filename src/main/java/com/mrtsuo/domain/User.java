package com.mrtsuo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
/**
 * 使用者
 * @author amber
 *
 */
@Entity
@Table(name = "t_user")
@Data
public class User {
	@Id
	@GeneratedValue
    private Long id;

    private String username;

    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private String email;

    private String avatar;

    
}