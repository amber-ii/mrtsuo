package com.mrtsuo.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;


import lombok.Data;

@Entity
@Table(name = "t_product")
//@Data
public class Product{
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice1() {
		return price1;
	}

	public void setPrice1(Integer price1) {
		this.price1 = price1;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Id
	@GeneratedValue
    private Long id;
	@NotBlank(message = "產品名稱不能為空白")
    private String name;

    private Integer price1;
   
    @ManyToOne
    private Type type;

    private String picture;

    
}