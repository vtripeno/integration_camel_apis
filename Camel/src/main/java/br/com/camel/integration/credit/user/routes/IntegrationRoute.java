package br.com.camel.integration.credit.user.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * This route is responsible to receive two messages with the same Correlation Id and make the aggregation
 */
@Component
public class IntegrationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:integration").id("Integration")
            .to("log:foo")
            .aggregate(header("correlationId"),(oldExchange, newExchange) -> {
                System.out.println("ENTROU AGG");
                if(oldExchange != null) {
                    System.out.println("OLD " + oldExchange.getIn().getBody(String.class));
                    System.out.println("VALOR CONCATENADO: old = " + oldExchange.getIn().getBody(String.class) + " new = " + newExchange.getIn().getBody(String.class));
                }
                if(newExchange != null) {
                    System.out.println("NEW " + newExchange.getIn().getBody(String.class));
                }
                return newExchange;
            }).eagerCheckCompletion().completionSize(2)

            //  TODO: LOGIC TO SAVE DATA IN MONGODB
            .to("rabbitmq:localhost:5672/tasks?username=guest&password=guest&autoDelete=false&routingKey=camel&queue=CREDIT.USER.OUT&bridgeEndpoint=true");
    }
}
