package cl.SalmonesAustral.MonitoreoAmbiental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class WebClientConfig {

    @Value("${jaulas.service.url}") 
        private String jaulasUrl;

    @Bean(name = "jaulasWebClient")
    public WebClient jaulasWebClient() {
        return WebClient.builder()
        .baseUrl(jaulasUrl)
        .build();
    }
        


    @Value("${alertas.service.url}")
        private String alertasUrl;

    @Bean(name = "alertasWebClient")
    public WebClient alertasWebClient() {
        return WebClient.builder()
        .baseUrl(alertasUrl)
        .build();
    }
   
        
}
