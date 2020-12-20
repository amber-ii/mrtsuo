package com.mrtsuo.vo;

import lombok.Data;

//封裝查詢產品
@Data
public class ProductQuery {
	private String name;
	private Long typeId;
}
