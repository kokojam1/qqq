package board.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import board.interceptor.AuthorizationInterceptor;
import board.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Autowired 
	Environment env;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
		registry.addInterceptor(new AuthorizationInterceptor(env));
	}
	
	@Override 
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000")
			.allowedMethods("GET", "DELETE", "PUT", "POST");
	}
	
}
