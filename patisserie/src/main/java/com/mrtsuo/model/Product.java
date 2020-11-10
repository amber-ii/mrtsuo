package com.mrtsuo.model;


import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_product")
@Getter
@Setter
@ToString
public class Product{
	@Id
	@GeneratedValue
    private Long id;
	@NotBlank(message = "產品名稱不能為空白")
    private String name;

    private Integer price;
   
    @ManyToOne
    private Type type;

    private String picture;

	

    
}