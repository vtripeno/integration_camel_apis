package br.com.camel.integration.credit.user.processors;

import br.com.camel.integration.credit.user.beans.Auditory;
import br.com.camel.integration.credit.user.model.ErrorMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @author Victor Tripeno
 * This class is responsible to manage all Fail Message
 */
public class FailExecution implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("FAIL");

        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        ErrorMessage error = new ErrorMessage(String.valueOf(exchange.getIn().getHeader("UniqueId")),
                exception.getMessage(),
                String.valueOf(exchange.getIn().getHeader("correlationId")));
        JAXBContext jaxbContext = JAXBContext.newInstance(ErrorMessage.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter result = new StringWriter();
        jaxbMarshaller.marshal(error, result);
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append(result);

//        exchange.getIn().setBody(error);
//        exchange.getOut().setBody(error);
//        Auditory auditory = new Auditory();
//        auditory.saveData(exchange);

        exchange.getIn().setHeader("rabbitmq.ROUTING_KEY", exchange.getContext().resolvePropertyPlaceholders("{{RABBITMQ_QUEUE_DLQ}}"));
//        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getIn().setBody(stringBuilder.toString());
//        exchange.getOut().setBody(stringBuilder.toString());

    }
}
