package pl.coderslab.legoinvestormanager.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // dokumentacja i testowanie aplikacji pod adresem: http://localhost:8080/swagger-ui/index.html#/

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("LEGO Investor API").description(
                        "Application to support investing in LEGO Sets and tracking lowest prices of every Sets You want.")
                        .version("1.0.0"));
    }
}
