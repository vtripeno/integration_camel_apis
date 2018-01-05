package br.com.camel.integration.credit.user.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * This route receive a JSON message to send to the aggregation route
 */
@Component
public class CreditRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:credit").id("Credit")
            .log("${body}")
            .setHeader("correlationId").jsonpath("id")
            .to("direct:integration");
    }
}
