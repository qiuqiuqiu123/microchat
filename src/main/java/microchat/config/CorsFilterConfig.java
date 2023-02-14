package microchat.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * FilterRegistrationBean<CorsFilter> 这个是web MVC中给出的过滤器
 * 解决多个Controller中的跨域问题
 */
@Configuration
public class CorsFilterConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter>
    filterFilterRegistrationBean(){
        UrlBasedCorsConfigurationSource configSource =
                new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration =
                new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);

        configSource.registerCorsConfiguration("/**",
                corsConfiguration);
        FilterRegistrationBean<CorsFilter> fBean =
                new FilterRegistrationBean<>(
                        new CorsFilter(configSource));

        fBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return fBean;
    }
}