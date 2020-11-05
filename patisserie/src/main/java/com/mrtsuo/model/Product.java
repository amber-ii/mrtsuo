package com.mrtsuo.model;

import javax.persistence.*;


import lombok.Data;

@Entity
@Table(name = "t_product")
@Data
public class Product {
	
	@Id
	@GeneratedValue
    private Long id;

    private String name;

    private Integer price1;

    private Integer price2;
    @ManyToOne
    private Type type;

    private String picture;

    
}