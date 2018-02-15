package br.com.camel.integration.credit.user.processors;

import br.com.camel.integration.credit.user.enums.StatusMessage;
import br.com.camel.integration.credit.user.model.CreditUser;
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

    @Value( "${DATABASE-FAIL}" )
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

        CreditUser creditUser = exchange.getIn().getBody(CreditUser.class);
        creditUser.setStatusMessage(StatusMessage.FAIL.message());

        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        Error error = new Error(exception.getMessage());
        JAXBContext jaxbContext = JAXBContext.newInstance(Error.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter result = new StringWriter();
        jaxbMarshaller.marshal(error, result);
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append(result);

        // TODO: transform the creditUser in XML to sendo to queue

        ProducerTemplate producerTemplate = exchange.getFromEndpoint().getCamelContext().createProducerTemplate();
        producerTemplate.sendBodyAndHeaders("direct:out-queue",
                stringBuilder.toString(),
                exchange.getIn().getHeaders());

    }
}
