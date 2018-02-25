package br.com.camel.integration.credit.user.processors;

import br.com.camel.integration.credit.user.model.ErrorMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @author Victor Tripeno
 * This class is responsible to manage all Fail Message
 */
public class FailExecution implements Processor {

    @Value( "${DATABASE_FAIL}" )
    private String database;

    @Value("${COLLECTION}")
    private String collection;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("FAIL");

        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        ErrorMessage error = new ErrorMessage(exception.getMessage(), String.valueOf(exchange.getIn().getHeader("correlationId")));
        JAXBContext jaxbContext = JAXBContext.newInstance(ErrorMessage.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter result = new StringWriter();
        jaxbMarshaller.marshal(error, result);
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append(result);

        System.out.println("FAIL " + stringBuilder.toString());

        exchange.getIn().setHeader("rabbitmq.ROUTING_KEY", exchange.getContext().resolvePropertyPlaceholders("{{RABBITMQ_QUEUE_DLQ}}"));
        exchange.getIn().setBody(stringBuilder.toString());

    }
}
