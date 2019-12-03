package com.khoks.test.rest.webservices.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;

import com.khoks.test.rest.webservices.ecommerce.product.ProductResource;
import com.khoks.test.rest.webservices.ecommerce.product.ProductsResponse;

@SpringBootTest
class ECommerceProductsApplicationTests {

	
	
	@Test
	void testGetProductsByName() {
		ProductResource productResource = new ProductResource();
		EntityModel<ProductsResponse> entityOfProductResponse = productResource.getProducts("filter=name eq Product27", 0, 100, "price", "ASC");
		assertEquals("Product27", entityOfProductResponse.getContent().getProducts().get(0).getContent().getName());
	}
	
	@Test
	void testGetProductsByDescription() {
		ProductResource productResource = new ProductResource();
		EntityModel<ProductsResponse> entityOfProductResponse = productResource.getProducts("filter=description eq \"unique description\"", 0, 100, "price", "ASC");
		assertEquals("unique description", entityOfProductResponse.getContent().getProducts().get(0).getContent().getDescription());
	}
	
	@Test
	void testGetProductsByPrice() {
		ProductResource productResource = new ProductResource();
		EntityModel<ProductsResponse> entityOfProductResponse = productResource.getProducts("filter=price eq 44.00", 0, 100, "price", "ASC");
		assertEquals(44.00, entityOfProductResponse.getContent().getProducts().get(0).getContent().getPrice().doubleValue());
	}
	
	@Test
	void testGetProductsByCompositeFilter() {
		ProductResource productResource = new ProductResource();
		EntityModel<ProductsResponse> entityOfProductResponse = productResource.getProducts("filter=name eq Product1 $and description eq \\\"unique description\\\" $and price eq 44.00", 0, 100, "price", "ASC");
		assertEquals("Product1", entityOfProductResponse.getContent().getProducts().get(0).getContent().getPrice());
		assertEquals(44.00, entityOfProductResponse.getContent().getProducts().get(0).getContent().getPrice().doubleValue());
		assertEquals("unique description", entityOfProductResponse.getContent().getProducts().get(0).getContent().getDescription());
	}

}
