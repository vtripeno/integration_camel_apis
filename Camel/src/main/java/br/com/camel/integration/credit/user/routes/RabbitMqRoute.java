package br.com.camel.integration.credit.user.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * Class responsible to read the RabbitMQ and send the message to the aggregation route
 */
@Component
public class RabbitMqRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rabbitmq:localhost:5672/tasks?username=guest&password=guest&autoDelete=false&routingKey=camel&queue=CREDIT.USER.IN&bridgeEndpoint=true")

                // TODO: CREATE CORRELATION ID AND FILTER THE MESSAGE TO PUT IN A POJO
                .to("direct:integration");
    }
}
