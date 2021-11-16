package max.costa.reto1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${reto1.ruta.imagenes}")
	private String rutaImagenes;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/logos/**", "/images/**", "/bootstrap/**", "/tinymce/**").addResourceLocations(
				"file:" + rutaImagenes, "classpath:/static/images/", "classpath:/static/bootstrap/", "classpath:/static/tinymce/");
	}
}
