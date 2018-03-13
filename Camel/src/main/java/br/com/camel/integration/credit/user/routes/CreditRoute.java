package br.com.camel.integration.credit.user.routes;

import org.apache.camel.ExchangePattern;
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
        from("direct:credit").id("creditRoute")
            .setHeader("correlationId").jsonpath("cpf")
            .to(ExchangePattern.InOnly, "direct:integration")
        .end();
    }
}
