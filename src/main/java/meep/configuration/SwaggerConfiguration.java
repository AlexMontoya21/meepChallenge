package meep.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger configuration. Please don't change it if you don't know what are you
 * doing.
 */
@Configuration
public class SwaggerConfiguration {

	public static final String API_BASE_PATH = "/api/";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("meep.controller")).paths(PathSelectors.any()).build();
	}
}