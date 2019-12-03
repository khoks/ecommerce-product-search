package com.khoks.test.rest.webservices.ecommerce.product;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence")
	@Column(nullable = false)
	private Integer id;
	
	@Size(max = ProductConstants.FIELD_NAME_SIZE_MAX, min = ProductConstants.FIELD_NAME_SIZE_MIN, message = ProductConstants.FIELD_NAME_SIZE_VOILATION_MESSAGE)
	@Column(nullable = false)
	private String name;
	
	@Size(max = ProductConstants.FIELD_DESCRIPTION_SIZE_MAX, min = ProductConstants.FIELD_DESCRIPTION_SIZE_MIN, message = ProductConstants.FIELD_DESCRIPTION_SIZE_VOILATION_MESSAGE)
	@Column(nullable = false)
	private String description;
	
	@Pattern(regexp = ProductConstants.FIELD_IMAGE_LINK_PATTERN_REGEXP)
	@Column(nullable = false)
	private String imageLink;
	
	@DecimalMin(inclusive = true, value = ProductConstants.FIELD_AVGRATING_SIZE_MIN, message=ProductConstants.FIELD_AVGRATING_SIZE_VOILATION_MESSAGE)
	@DecimalMax(inclusive = true, value = ProductConstants.FIELD_AVGRATING_SIZE_MAX, message=ProductConstants.FIELD_AVGRATING_SIZE_VOILATION_MESSAGE)
	@Column(nullable = false)
	private Double avgRating;
	
	@Size(max = ProductConstants.FIELD_CATEGORY_SIZE_MAX, min = ProductConstants.FIELD_CATEGORY_SIZE_MIN, message = ProductConstants.FIELD_CATEGORY_SIZE_VOILATION_MESSAGE)
	@Column(nullable = false)
	private String category;

	@DecimalMin(inclusive = true, value = ProductConstants.FIELD_PRICE_SIZE_MIN, message=ProductConstants.FIELD_PRICE_SIZE_VOILATION_MESSAGE)
	@Column(nullable = false)
	private BigDecimal price;
	
	public Product() {
		super();
	}

	public Product(Integer id,
			@Size(max = ProductConstants.FIELD_NAME_SIZE_MAX, min = ProductConstants.FIELD_NAME_SIZE_MAX, message = ProductConstants.FIELD_NAME_SIZE_VOILATION_MESSAGE) String name,
			@Size(max = ProductConstants.FIELD_DESCRIPTION_SIZE_MAX, min = ProductConstants.FIELD_DESCRIPTION_SIZE_MIN, message = ProductConstants.FIELD_DESCRIPTION_SIZE_VOILATION_MESSAGE) String description,
			@Pattern(regexp = ProductConstants.FIELD_IMAGE_LINK_PATTERN_REGEXP) String imageLink,
			@DecimalMin(inclusive = true, value = ProductConstants.FIELD_AVGRATING_SIZE_MIN, message=ProductConstants.FIELD_AVGRATING_SIZE_VOILATION_MESSAGE) @DecimalMax(inclusive = true, value = ProductConstants.FIELD_AVGRATING_SIZE_MAX, message=ProductConstants.FIELD_AVGRATING_SIZE_VOILATION_MESSAGE) Double avgRating,
			@Size(max = ProductConstants.FIELD_CATEGORY_SIZE_MAX, min = ProductConstants.FIELD_CATEGORY_SIZE_MAX, message = ProductConstants.FIELD_CATEGORY_SIZE_VOILATION_MESSAGE) String category,
			@DecimalMin(inclusive = true, value = ProductConstants.FIELD_PRICE_SIZE_MIN, message=ProductConstants.FIELD_PRICE_SIZE_VOILATION_MESSAGE) BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageLink = imageLink;
		this.avgRating = avgRating;
		this.category = category;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", imageLink=" + imageLink
				+ ", avgRating=" + avgRating + ", category=" + category + "]";
	}
	
	
}
