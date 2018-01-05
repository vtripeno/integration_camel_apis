package br.com.camel.integration.credit.user.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CreditRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:credit")
            .log("${body}")
            .setHeader("meuId").jsonpath("id")
            .to("direct:agregador");
    }
}
