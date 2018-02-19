package br.com.camel.integration.credit.user.routes;

import br.com.camel.integration.credit.user.beans.Auditory;
import br.com.camel.integration.credit.user.model.Credit;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
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
            .log("${body}")
            .setHeader("correlationId").jsonpath("cpf")
            .marshal().json(JsonLibrary.Jackson)
            .unmarshal().json(JsonLibrary.Jackson, Credit.class)
            .log("${body}")
            .to("direct:integration");
    }
}
