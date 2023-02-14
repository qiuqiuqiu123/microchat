package microchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * CorsWebFilter这个是spring webflux中的过滤器
 * 可以解决网关层面多个服务的跨域问题
 */
@Configuration
public class CorsFilterConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {

        UrlBasedCorsConfigurationSource configSource =
                new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        configSource.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(configSource);
    }
}