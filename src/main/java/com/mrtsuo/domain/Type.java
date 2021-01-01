package com.mrtsuo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品類型
 * @author amber
 *
 */
@Entity
@Table(name = "t_type")
@Getter
@Setter
public class Type {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "分類名稱不能為空")
	private String name;
	
//	一個分類可以對應多個產品
	@OneToMany(mappedBy = "type")
	private List<Product> products = new ArrayList<>();

	@Override
	public String toString() {
		return name;
	}


}