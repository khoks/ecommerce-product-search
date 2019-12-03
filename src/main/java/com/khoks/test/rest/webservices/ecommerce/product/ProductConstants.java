package com.khoks.test.rest.webservices.ecommerce.product;

public class ProductConstants {
	
	//Field constants
	
	public static final int FIELD_NAME_SIZE_MIN = 2;
	
	public static final int FIELD_NAME_SIZE_MAX = 255;
	
	public static final String FIELD_NAME_SIZE_VOILATION_MESSAGE = "Name should be between "+FIELD_NAME_SIZE_MIN+" and "+FIELD_NAME_SIZE_MAX+" characters.";
	
	public static final int FIELD_DESCRIPTION_SIZE_MIN = 15;
	
	public static final int FIELD_DESCRIPTION_SIZE_MAX = 9999;
	
	public static final String FIELD_DESCRIPTION_SIZE_VOILATION_MESSAGE = "Description should be between "+FIELD_DESCRIPTION_SIZE_MIN+" and "+FIELD_DESCRIPTION_SIZE_MAX+" characters.";
	
	public static final String FIELD_IMAGE_LINK_PATTERN_REGEXP = ".*";
	
	public static final String FIELD_AVGRATING_SIZE_MIN = "0.0";
	
	public static final String FIELD_AVGRATING_SIZE_MAX = "5.0";
	
	public static final String FIELD_AVGRATING_SIZE_VOILATION_MESSAGE = "Average Rating should be between "+FIELD_AVGRATING_SIZE_MIN+" and "+FIELD_AVGRATING_SIZE_MAX+" characters.";
	
	public static final int FIELD_CATEGORY_SIZE_MIN = 2;
	
	public static final int FIELD_CATEGORY_SIZE_MAX = 255;
	
	public static final String FIELD_CATEGORY_SIZE_VOILATION_MESSAGE = "Category should be between "+FIELD_CATEGORY_SIZE_MIN+" and "+FIELD_CATEGORY_SIZE_MAX+" characters.";
	
	public static final String FIELD_PRICE_SIZE_MIN = "0.0";
	
	public static final String FIELD_PRICE_SIZE_VOILATION_MESSAGE = "Price of product cannot be below $"+FIELD_PRICE_SIZE_MIN+".";
	
	public static final String FIELD_NAME = "name";
	
	public static final String FIELD_DESCRIPTION = "description";
	
	public static final String FIELD_CATEGORY = "category";
	
	public static final String FIELD_AVGRATING = "avgRating";
	
	public static final String FIELD_PRICE = "price";
	
	public static final String FIELD_IMAGELINK = "imageLink";
	
	public static final String FIELD_ID = "id";
	
	//Exception constants
	public static final String NOT_FOUND_PRODUCT_BY_ID = "Could not find product with id = ";
	
	public static final String NOT_FOUND_PRODUCTS = "Could not find any products.";
	
	
	//Path constants
	public static final String PATH_GET_PRODUCTS_BY_ID = "/products/{productId}";

	public static final String PATH_GET_PRODUCTS = "/products";
	
	public static final String PATH_POST_PRODUCTS = "/products";
	
	public static final String PATH_DELETE_PRODUCTS_BY_ID = "/products/{productId}";
	
	public static final String PATH_GET_PRODUCTS_FILTER = "filter";
	
	public static final String PATH_GET_PRODUCTS_FILTER_DEFAULT = "none";
	
	public static final String PATH_GET_PRODUCTS_PAGENUM = "pagenum";
	
	public static final String PATH_GET_PRODUCTS_PAGENUM_DEFAULT = "0";
	
	public static final Integer PATH_GET_PRODUCTS_PAGENUM_ALLPRODUCTS = 0;
	
	public static final String PATH_GET_PRODUCTS_PAGESIZE = "pagesize";
	
	public static final String PATH_GET_PRODUCTS_PAGESIZE_DEFAULT = "100";
	
	public static final Integer PATH_GET_PRODUCTS_PAGESIZE_ALLPRODUCTS = 100;
	
	public static final String PATH_GET_PRODUCTS_SORTBY = "sortby";
	
	public static final String PATH_GET_PRODUCTS_SORTBY_DEFAULT = "price";
	
	public static final String PATH_GET_PRODUCTS_SORTBY_ORDER = "sortbyorder";
	
	public static final String PATH_GET_PRODUCTS_SORTBY_ORDER_DEFAULT = "ASC";
	
	public static final String PATH_GET_PRODUCTS_LINK_NEXT_PAGE = "next-page";
	
	public static final String PATH_GET_PRODUCTS_LINK_PREVIOUS_PAGE = "previous-page";
	
	public static final String PATH_GET_PRODUCTS_LINK_ALL_PRODUCTS = "all-products";
	
	//Misc constants
	public static final Integer ZERO = 0;
}
