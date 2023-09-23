package com.nib.runningapp.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Authorization",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        License license = new License();
        license.setName("License Apache 2.0");
        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info()
                .title("Running API")
                .version("1.0")
                .description("This API exposes endpoints to manage running activities")
                .termsOfService("")
                .license(license);

        return new OpenAPI().info(info);
    }
}
