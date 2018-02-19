package br.com.camel.integration.credit.user.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * Class responsible to read the RabbitMQ and send the message to the aggregation route
 */
@Component
public class RabbitMqRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rabbitmq:{{RABBITMQ_ADDRESS}}/tasks?username={{RABBITMQ_USERNAME}}&password={{RABBITMQ_PSWD}}&autoDelete=false&routingKey=camel&queue={{RABBITMQ_QUEUE_IN}}&bridgeEndpoint=true")
        .id("rabbitMqRoute")
                // TODO: CONVERT MESSAGE TO POJO
                .setHeader("correlationId", xpath("/root/id/text()").stringResult())
                .marshal().json(JsonLibrary.Jackson)
                .log("${body}")
                .to("direct:integration");
    }
}
