package server.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("FOM CMS API Document")
                .version("Ver. 1.0.0")
                .description("It is about FOM CMS API");

        return new OpenAPI().info(info);
    }


}
