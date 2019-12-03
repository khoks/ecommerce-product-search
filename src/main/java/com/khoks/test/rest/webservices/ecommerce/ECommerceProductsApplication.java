package com.khoks.test.rest.webservices.ecommerce;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class ECommerceProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceProductsApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {	
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/products").allowedOrigins("*");
				registry.addMapping("/products/**").allowedOrigins("*");
			}
	    };
	 }
}
