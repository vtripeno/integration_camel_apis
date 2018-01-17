package br.com.camel.integration.credit.user.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * Class responsible to read the RabbitMQ and send the message to the aggregation route
 */
//@Component
public class RabbitMqRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rabbitmq:{{RABBITMQ_ADDRESS}}/tasks?username={{RABBITMQ_USERNAME}}&password={{RABBITMQ_PSWD}}&autoDelete=false&routingKey=camel&queue={{RABBITMQ_QUEUE_IN}}&bridgeEndpoint=true")

                // TODO: CREATE CORRELATION ID AND FILTER THE MESSAGE TO PUT IN A POJO
                .to("direct:integration");
    }
}
