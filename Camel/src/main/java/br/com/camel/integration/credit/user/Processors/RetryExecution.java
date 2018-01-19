package br.com.camel.integration.credit.user.Processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

public class RetryExecution implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("RETRY");
        ProducerTemplate producerTemplate = exchange.getFromEndpoint().getCamelContext().createProducerTemplate();
        producerTemplate.send("direct:out-queue", exchange);
    }
}
