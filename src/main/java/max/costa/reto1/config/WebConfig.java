package max.costa.reto1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**", "/bootstrap/**", "/tinymce/**").addResourceLocations(
				"classpath:/static/images/", "classpath:/static/bootstrap/", "classpath:/static/tinymce/");
	}
}
