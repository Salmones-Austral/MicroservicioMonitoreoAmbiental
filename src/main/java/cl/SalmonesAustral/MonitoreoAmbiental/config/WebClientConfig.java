package cl.SalmonesAustral.MonitoreoAmbiental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebClientConfig {

    @Bean
    ("jaulasWebClient")
    public WebClient jaulasWebClient(WebClient.Builder builder,
        @Value("${jaulas.service.url}") String jaulasServiceUrl) {
            return builder.baseUrl(jaulasServiceUrl).build();
        }

    @Bean
    ("alertasWebClient")
    public WebClient alertasWebClient(WebClient.Builder builder,
        @Value("${alertas.service.url}") String alertasServiceUrl) {
            return builder.baseUrl(alertasServiceUrl).build();
        }
}
