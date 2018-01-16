package br.com.camel.integration.credit.user.routes;

import br.com.camel.integration.credit.user.aggregation.IntegrationAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Victor Tripeno
 * This route is responsible to receive two messages with the same Correlation Id and make the aggregation
 */
@Component
public class IntegrationRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // TODO: CRESTE THE ROUTE FOR DLQ


        from("direct:integration").id("Integration")
            .to("log:foo")
            .aggregate(header("correlationId"), new IntegrationAggregationStrategy()).eagerCheckCompletion().completionSize(2)

            //  TODO: LOGIC TO SAVE DATA IN MONGODB
            /* MESSAGE NEED TO HAVE STATUS EQUALS 'IN PROGRESS' */
            .to("mongodb:myDb?database={{DATABASE}}&collection={{COLLECTION}}&operation=save")
            .to("rabbitmq:{{RABBITMQ_ADDRESS}}/tasks?username={{RABBITMQ_USERNAME}}&password={{RABBITMQ_PSWD}}&autoDelete=false&routingKey=camel&queue={{RABBITMQ_QUEUE_OUT}}&bridgeEndpoint=true")
            /* MESSAGE NEED TO HAVE STATUS EQUALS 'FINISHED' */
            .to("mongodb:myDb?database={{DATABASE}}&collection={{COLLECTION}}&operation=save");
    }





    /*
    * @Override
    *
	public void configure() throws Exception {

		// Dead Letter Channel, para fazer 3 tentativas de entreta a cada 60 segundos
		errorHandler(
		    deadLetterChannel("wmq:queue:{{filaErro}}")
		        .logExhaustedMessageHistory(true)
		        .maximumRedeliveries(3)
	            .redeliveryDelay(60000)
	            .onPrepareFailure(new ExecucaoFalha())
		        .onRedelivery(new ExecucaoRetentativa())
		);

		// Rota que recebe dados da fila de entrada
		from("wmq:queue:{{filaEntrada}}").id("in-mq-fila-{{filaEntrada}}")
		.bean(Auditoria.class, "salvaDadosEntrada")
		.to("log:request")
		.setProperty("msgEntrada", simple("${body}"))
		.setHeader("JMSCorrelationID", simple("${header.JMSMessageID}"))
		.unmarshal().jacksonxml(Pessoa.class)
		.bean(MontagemSoap.class, "montarSoapEntrada")
		.toD("direct:execucao");

		// Rota que faz a conexão com o Web Service
		from("direct:execucao").id("execucao-WS")
		.to("http4://{{serverWs}}")
		.convertBodyTo(String.class)
		.setBody().xpath("//score")
		.bean(MontagemSoap.class, "montarXmlSaida")
		.to("log:response")
		.bean(Auditoria.class, "salvaDadosSaida")
		.toD("direct:out-mq");

		// Rota que posat a mensagem na fila de saída
		from("direct:out-mq").id("out-mq-fila-{{filaSaida}}")
		.to("wmq:queue:{{filaSaida}}?exchangePattern=InOnly");
	}
    * */
}
