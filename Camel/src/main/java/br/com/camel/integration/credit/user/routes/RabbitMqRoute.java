package br.com.camel.integration.credit.user.routes;

import br.com.camel.integration.credit.user.model.User;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Victor Tripeno
 * Class responsible to read the RabbitMQ and send the message to the aggregation route
 */
@Component
public class RabbitMqRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setForceTopLevelObject(true);
        xmlJsonFormat.setTrimSpaces(true);
        xmlJsonFormat.setRootName("newRoot");
        xmlJsonFormat.setSkipNamespaces(true);
        xmlJsonFormat.setRemoveNamespacePrefixes(true);
        xmlJsonFormat.setExpandableProperties(Arrays.asList("d", "e"));

        from("rabbitmq:{{RABBITMQ_ADDRESS}}/{{RABBITMQ_EXCHANGE}}?routingKey={{RABBITMQ_QUEUE_IN_ROUTING_KEY}}&username={{RABBITMQ_USERNAME}}&password={{RABBITMQ_PSWD}}&autoDelete=false&queue={{RABBITMQ_QUEUE_IN}}")
        .id("rabbitMqRoute")
                .setHeader("correlationId", xpath("//*[local-name()='cpf']").stringResult())
                .setBody().xpath("//*[local-name()='user']")
                .marshal(xmlJsonFormat)
                .setBody().jsonpath("user")
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, User.class)
                .to(ExchangePattern.InOnly, "direct:integration")
        .end();
    }
}
