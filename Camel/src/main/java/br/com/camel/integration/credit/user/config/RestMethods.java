package br.com.camel.integration.credit.user.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestMethods extends RouteBuilder {
    /**
     * REST Methods Configuration
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        rest()
                .post("/credit")
                .produces("application/json")
                .consumes("application/json")
                .toD("direct:credit");
    }
}
