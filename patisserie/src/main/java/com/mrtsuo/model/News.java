package com.mrtsuo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "t_news")
@Data
public class News {
	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String Picture;
	
	private String contents;
	private boolean shareStatement;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	
}