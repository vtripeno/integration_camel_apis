package br.com.camel.integration.credit.user.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * This configuration is for start the Apache Camel application with the Spring Boot servlet
 */
@Component
public class RestConfiguration extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .contextPath("/")
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "REST API")
                .apiProperty("api.version", "v1")
                .apiContextRouteId("doc-api")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

    }
}
