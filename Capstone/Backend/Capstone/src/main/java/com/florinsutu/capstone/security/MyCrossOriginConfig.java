//package com.florinsutu.capstone.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class MyCrossOriginConfig {
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//	  
//      return new WebMvcConfigurerAdapter() {
//          @Override
//          public void addCorsMappings(CorsRegistry registry) {
//              registry.addMapping("/**")
//                  .allowedOrigins("http://localhost:4200/")
//                  .allowedMethods("GET", "POST", "PUT","PATCH", "DELETE")
//                  .allowedHeaders("h1", "h2")
//                  .allowCredentials(false)
//                  .maxAge(3600);
//	      }
//	  
//      };
//	}
//}


