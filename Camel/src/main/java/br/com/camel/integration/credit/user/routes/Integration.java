package br.com.camel.integration.credit.user.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Integration extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:agregador")
            .to("log:foo")
            .aggregate(header("meuId"),(oldExchange, newExchange) -> {
                System.out.println("ENTROU AGG");
                if(oldExchange != null) {
                    System.out.println("OLD " + oldExchange.getIn().getBody(String.class));
                    System.out.println("VALOR CONCATENADO: old = " + oldExchange.getIn().getBody(String.class) + " new = " + newExchange.getIn().getBody(String.class));
                }
                if(newExchange != null) {
                    System.out.println("NEW " + newExchange.getIn().getBody(String.class));
                }
                return newExchange;
            }).eagerCheckCompletion().completionSize(2)
            .to("log:foo")
        .end();
    }
}
