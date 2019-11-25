package meep.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfiguration {

	@Bean("restTemplate")
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.errorHandler(new DefaultResponseErrorHandler()).build();
	}
}