package com.khoks.test.rest.webservices.ecommerce.product;

import java.util.List;

import org.springframework.hateoas.EntityModel;

public class ProductsResponse {

	private List<EntityModel<Product>> products;

	private Long total;
	
	private Integer pages;
	
	public ProductsResponse() {
		super();
	}

	public ProductsResponse(List<EntityModel<Product>> products, Long total, Integer pages) {
		super();
		this.products = products;
		this.total = total;
		this.pages = pages;
	}

	public List<EntityModel<Product>> getProducts() {
		return products;
	}

	public void setProducts(List<EntityModel<Product>> products) {
		this.products = products;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "ProductsResponse [products=" + products + ", total=" + total + ", pages=" + pages + "]";
	}
	
}
