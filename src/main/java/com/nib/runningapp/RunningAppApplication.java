package com.nib.runningapp;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SecurityScheme(name = "Authorization", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class RunningAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningAppApplication.class, args);
    }

    @Bean
    public OpenAPI openAPI(){
        Server devServer = new Server();
        devServer.setUrl("");
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl("");
        prodServer.setDescription("Server URL in Production environment");

        Info info = new Info()
                .title("Running API")
                .version("1.0")
                .description("This API exposes endpoints to manage running")
                .termsOfService("")
                .license(new License());

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
