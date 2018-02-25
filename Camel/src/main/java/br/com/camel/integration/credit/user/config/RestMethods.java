package br.com.camel.integration.credit.user.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * REST Methods Configuration
 */
@Component
public class RestMethods extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest()
                .post("/credit")
                .consumes("application/json")
                .toD("direct:credit");
    }
}
