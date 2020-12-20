package com.mrtsuo.model;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
/**
 * 商品
 * @author amber
 *
 */
@Entity
@Table(name = "t_product")
@Data
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