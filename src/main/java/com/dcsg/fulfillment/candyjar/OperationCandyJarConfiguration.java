package com.dcsg.fulfillment.candyjar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Component
public class OperationCandyJarConfiguration {
	
	@Value("${settings.cors_origin}")
	private String allowedOrigin;

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(allowedOrigin);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Value("${unix.username}")
    private String unixUsername;
    @Value("${unix.password}")
    private String unixPassword;
    @Value("${unix.host}")
    private String unixHost;
    @Value("${esb.url}")
    private String esbUrl;
	public String getUnixUsername() {
		return unixUsername;
	}
	public String getUnixHost() {
		return unixHost;
	}
	public String getUnixPassword() {
		return unixPassword;
	}
	public String getEsbUrl() {
		return esbUrl;
	}
    
}
