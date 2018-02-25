package br.com.camel.integration.credit.user.processors;

import br.com.camel.integration.credit.user.beans.Auditory;
import br.com.camel.integration.credit.user.enums.StatusMessage;
import br.com.camel.integration.credit.user.model.CreditUser;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Victor Tripeno
 * This class is responsible for Resend the message to the out queue
 */
public class RetryExecution implements Processor {

//    @Value( "${DATABASE}" )
//    private String database;
//
//    @Value("${COLLECTION}")
//    private String collection;
//
//    @Value("${spring.data.mongodb.host}")
//    private String mongoHost;
//
//    @Value("${spring.data.mongodb.port}")
//    private String mongoPort;

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("RETRY");
//        CreditUser creditUser = exchange.getIn().getBody(CreditUser.class);
//        creditUser.setStatusMessage(StatusMessage.RETYING.message());
//        StringBuilder address = new StringBuilder(mongoHost).append(":").append(mongoPort);
//        Auditory auditory = new Auditory(creditUser, address.toString(), collection, database);
//        auditory.saveData();
        ProducerTemplate producerTemplate = exchange.getFromEndpoint().getCamelContext().createProducerTemplate();
        producerTemplate.send("direct:out-queue", exchange);
    }
}
