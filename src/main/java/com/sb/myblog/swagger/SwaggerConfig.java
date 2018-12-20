package com.sb.myblog.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.sb.myblog.controller"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                      
    }
    
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Sample Spring-boot \"MyBLOG\" Project for Gemalto Interview")
            .description("This is the backend API for the MyBLOG Project. Using this API the UI team can efficiently interact with the back-end database"
            			+ " and perform the DB related operations. <br/> "
            			+ " This API exposes the folowing services: <br/>"
            			+ "	1. Create a new blog entry.<br/> "
            			+ "	2. Update a blog entry.<br/> "
            			+ "	3. Delete a blog entry.<br/>"
            			+ " 4. View a particular blog entry.<br/> "
            			+ "	5. View all the blog entry in the database.<br/> "
            			+ "All © copyrights reserved by Parth Kansara")
            .license("Apache 2.0")
            .licenseUrl("http://<your-licence-url>/license.html")
            .termsOfServiceUrl("All copyrights reserved by Parth Kansara. ")
            .version("1.0")
            .contact(new Contact("Parth Kansara", "http://www.myblog.com", "kparth01@gmail.com"))
            .build();
    }

    
}
