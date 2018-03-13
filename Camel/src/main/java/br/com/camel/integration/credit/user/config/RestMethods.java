package br.com.camel.integration.credit.user.config;

import br.com.camel.integration.credit.user.model.Credit;
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
                .post("/credit").type(Credit.class)
                .consumes("application/json")
                .toD("direct:credit");
    }
}
