package com.khoks.test.rest.webservices.ecommerce.product;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.khoks.test.rest.webservices.ecommerce.exception.ProductNotFoundException;
import com.khoks.test.rest.webservices.ecommerce.exception.ProductSearchNotValidException;

@RestController
public class ProductResource {

	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductUtility productUtility;
	
	@GetMapping(path=ProductConstants.PATH_GET_PRODUCTS_BY_ID)
	public EntityModel<Product> getProduct(@PathVariable Integer productId) {
		Optional<Product> retrievedProduct = productRepository.findById(productId);
		if(retrievedProduct.isEmpty()) {
			throw new ProductNotFoundException(ProductConstants.NOT_FOUND_PRODUCT_BY_ID + productId);
		}
		EntityModel<Product> productResource = productUtility.buildProductResource(this, retrievedProduct.get());
		
		return productResource;
	}
	
	@GetMapping(path=ProductConstants.PATH_GET_PRODUCTS)
	public EntityModel<ProductsResponse> getProducts(@RequestParam(name = ProductConstants.PATH_GET_PRODUCTS_FILTER, required = false, defaultValue = ProductConstants.PATH_GET_PRODUCTS_FILTER_DEFAULT) String filter, 
			@RequestParam(name = ProductConstants.PATH_GET_PRODUCTS_PAGENUM, required = false, defaultValue = ProductConstants.PATH_GET_PRODUCTS_PAGENUM_DEFAULT) Integer pageNum,
			@RequestParam(name = ProductConstants.PATH_GET_PRODUCTS_PAGESIZE, required = false, defaultValue = ProductConstants.PATH_GET_PRODUCTS_PAGESIZE_DEFAULT) Integer pageSize,
			@RequestParam(name = ProductConstants.PATH_GET_PRODUCTS_SORTBY, required = false, defaultValue = ProductConstants.PATH_GET_PRODUCTS_SORTBY_DEFAULT) String sortBy,
			@RequestParam(name = ProductConstants.PATH_GET_PRODUCTS_SORTBY_ORDER, required = false, defaultValue = ProductConstants.PATH_GET_PRODUCTS_SORTBY_ORDER_DEFAULT) String sortByOrder){
		
		Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
		List<Product> retrievedProducts = null;
		Page<Product> pageProducts = null;
		String checkedFilter = null;
		
		
		
		if(null != filter && !filter.isEmpty() && !filter.isBlank() && !filter.equalsIgnoreCase(ProductConstants.PATH_GET_PRODUCTS_FILTER_DEFAULT)) {
			checkedFilter = ProductConstants.PATH_GET_PRODUCTS_FILTER_DEFAULT;
			try {
				checkedFilter = java.net.URLDecoder.decode(filter, StandardCharsets.UTF_8.name());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			};
			System.out.println(checkedFilter);
			
			Product inputProduct = new Product();
			inputProduct = productUtility.populateProductWithParams(checkedFilter);
			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withMatcher(ProductConstants.FIELD_NAME, ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
					.withIgnorePaths(ProductConstants.FIELD_ID, ProductConstants.FIELD_IMAGELINK);
			Example<Product> example = Example.of(inputProduct, ignoringExampleMatcher);
			pageProducts = productRepository.findAll(example, page);
		} else {
			checkedFilter = ProductConstants.PATH_GET_PRODUCTS_FILTER_DEFAULT;
			pageProducts = productRepository.findAll(page);
		}
		
		retrievedProducts = pageProducts.getContent();
		
		List<EntityModel<Product>> modeledProducts = new ArrayList<EntityModel<Product>>();
		if(null == retrievedProducts || retrievedProducts.isEmpty()) {
			throw new ProductNotFoundException(ProductConstants.NOT_FOUND_PRODUCTS);
		} else {
			modeledProducts = productUtility.buildProductResources(this, retrievedProducts);
		}
		
		ProductsResponse modeledProductsResponse = new ProductsResponse(modeledProducts, pageProducts.getTotalElements(), pageProducts.getTotalPages());
		EntityModel<ProductsResponse> linkedUserResources = new EntityModel<ProductsResponse>(modeledProductsResponse);
		if((pageProducts.getNumber() + 1) <  pageProducts.getTotalPages()) {
			WebMvcLinkBuilder linkToNextPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProducts(checkedFilter,
					pageProducts.getNumber() + 1, pageProducts.getSize(), sortBy, sortByOrder ));
			linkedUserResources.add(linkToNextPage.withRel(ProductConstants.PATH_GET_PRODUCTS_LINK_NEXT_PAGE));
		}
		if((pageProducts.getNumber() - 1) >= ProductConstants.ZERO) {
			WebMvcLinkBuilder linkToPreviousPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProducts(checkedFilter,
					pageProducts.getNumber() - 1, pageProducts.getSize(), sortBy, sortByOrder ));
			linkedUserResources.add(linkToPreviousPage.withRel(ProductConstants.PATH_GET_PRODUCTS_LINK_PREVIOUS_PAGE));
		}
		WebMvcLinkBuilder linkToAllProducts = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProducts(checkedFilter,
				ProductConstants.PATH_GET_PRODUCTS_PAGENUM_ALLPRODUCTS, ProductConstants.PATH_GET_PRODUCTS_PAGESIZE_ALLPRODUCTS, ProductConstants.PATH_GET_PRODUCTS_SORTBY_DEFAULT, ProductConstants.PATH_GET_PRODUCTS_SORTBY_ORDER_DEFAULT ));
		linkedUserResources.add(linkToAllProducts.withRel(ProductConstants.PATH_GET_PRODUCTS_LINK_ALL_PRODUCTS));
		
		return linkedUserResources;
	}

	@PostMapping(path=ProductConstants.PATH_POST_PRODUCTS)
	public ResponseEntity createProduct(@Valid @RequestBody Product product) {
		Product createdProduct = productRepository.save(product);
		
		URI createdProductURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProduct.getId().intValue()).toUri();
		
		return ResponseEntity.created(createdProductURI).body(createdProduct);
	}
	
	@DeleteMapping(path=ProductConstants.PATH_DELETE_PRODUCTS_BY_ID)
	public ResponseEntity deleteProduct(@PathVariable Integer productId) {
		productRepository.deleteById(productId);
		return ResponseEntity.noContent().build();
	}
}
