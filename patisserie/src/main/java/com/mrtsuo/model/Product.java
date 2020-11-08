package com.mrtsuo.model;


import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "t_product")
@Data
public class Product{
	@Id
	@GeneratedValue
    private Long id;
	@NotBlank(message = "產品名稱不能為空白")
    private String name;

    private Integer price1;
   
    @ManyToOne
    private Type type_id;

    private String picture;

    
}