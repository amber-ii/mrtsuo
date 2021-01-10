package com.mrtsuo.domain;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

/**
 * 最新消息
 * 
 * @author amber
 *
 */
@Entity
@Table(name = "t_news")
@Data
public class News {
	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@Lob
	private String picture;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	private String content;
	private boolean shareStatement;
	private boolean published;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

}