package br.com.camel.integration.credit.user.routes;

import br.com.camel.integration.credit.user.model.User;
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

        from("rabbitmq:{{RABBITMQ_ADDRESS}}/tasks?username={{RABBITMQ_USERNAME}}&password={{RABBITMQ_PSWD}}&autoDelete=false&routingKey=camel&queue={{RABBITMQ_QUEUE_IN}}&bridgeEndpoint=true")
        .id("rabbitMqRoute")
                // TODO: CONVERT MESSAGE TO POJO
                .setHeader("correlationId", xpath("//*[local-name()='cpf']").stringResult())
                .setBody().xpath("//*[local-name()='user']")
                .marshal(xmlJsonFormat)
                .log("foo:${body}")
//                .unmarshal().json(JsonLibrary.Jackson, User.class)
//                .process(exchange -> {
//                    User user = exchange.getIn().getBody(User.class);
//                    System.out.println(user.getName());
//                    System.out.println(exchange.getIn().getHeaders());
//                })
                .log("${body}")
                .to("direct:integration");
    }
}
