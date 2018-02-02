package ua.lviv.courierdelivery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SecurityConfiguration {

    @Value("${app.origin.url}")
    private String url;

    @Bean
    public WebMvcConfigurer CORSConfig() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(url)
                        .allowCredentials(true)
                        .allowedHeaders("Access-Control-Allow-Credentials", "Content-Type", "Access-Control-Allow-Headers", "X-Requested-With", "Origin", "Accept")
                        .allowedMethods("PUT", "DELETE", "GET", "POST");
            }
        };
    }
}