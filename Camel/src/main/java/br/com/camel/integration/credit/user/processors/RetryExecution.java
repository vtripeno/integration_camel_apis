package br.com.camel.integration.credit.user.processors;

import br.com.camel.integration.credit.user.beans.Auditory;
import br.com.camel.integration.credit.user.enums.StatusMessage;
import br.com.camel.integration.credit.user.model.CreditUser;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

/**
 * @author Victor Tripeno
 * This class is responsible for Resend the message to the out queue
 */
public class RetryExecution implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("RETRY");
        if(exchange.getIn().getBody() instanceof CreditUser) {
            CreditUser creditUser = exchange.getIn().getBody(CreditUser.class);
            creditUser.setStatusMessage(StatusMessage.RETYING.message());
            Auditory auditory = new Auditory();
            auditory.saveData(exchange);
        } else {
            Auditory auditory = new Auditory();
            auditory.saveData(exchange);
        }

        ProducerTemplate producerTemplate = exchange.getFromEndpoint().getCamelContext().createProducerTemplate();
        producerTemplate.send("direct:out-queue", exchange);
    }
}
