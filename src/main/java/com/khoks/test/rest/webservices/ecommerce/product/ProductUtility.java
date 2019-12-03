package com.khoks.test.rest.webservices.ecommerce.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.khoks.test.rest.webservices.ecommerce.exception.ProductSearchNotValidException;

@Component
public class ProductUtility {

	
	public EntityModel<Product> buildProductResource(ProductResource productResourceClassInstance, Product product){
		EntityModel<Product> entityModel = new EntityModel<Product>(product);
		
		WebMvcLinkBuilder linkToThisProduct = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(productResourceClassInstance.getClass()).getProduct(product.getId().intValue()));
		
		entityModel.add(linkToThisProduct.withRel("this-product"));
		return entityModel;
	}
	
	public List<EntityModel<Product>> buildProductResources(ProductResource productResourceClassInstance, List<Product> products){
		EntityModel<Product> entityModel = null;
		List<EntityModel<Product>> entityModels = new ArrayList<EntityModel<Product>>();
		for(Product product: products) {
			entityModel = buildProductResource(productResourceClassInstance, product);
			entityModels.add(entityModel);
		}
		
		return entityModels;
	}
	
	public Product populateProductWithParams(String filter) {
		Product product = new Product();
		
		try {
			String[] expressions = filter.split("(\\$and)");
			if(expressions.length == 0) {
				throw new ProductSearchNotValidException("Invalid search criteria for products. Please check your input.");
			}
			for(String expression: expressions) {
				String[] tokens = expression.split("eq");
				if(tokens.length != 2) {
					throw new ProductSearchNotValidException("Invalid search criteria for products. Please check your input.");
				}
				String key = tokens[0].replaceAll("\"", "").trim();
				String value = tokens[1].replaceAll("\"", "").trim();
				switch(key) {
					case "name" :
						product.setName(value);
						break;
					case "description" :
						product.setDescription(value);
						break;
					case "category" :
						product.setCategory(value);
						break;
					case "avgrating" :
						product.setAvgRating(Double.parseDouble(value));
						break;
					case "price" :
						product.setPrice(new BigDecimal(value));
						break;
					default :
						throw new ProductSearchNotValidException("Invalid search criteria for products. Please check your input.");
				}
			}
		} catch (Exception ex) {
			throw new ProductSearchNotValidException("Invalid search criteria for products. Please check your input.");
		}
		
		return product;
	}
}
