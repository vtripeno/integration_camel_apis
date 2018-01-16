package br.com.camel.integration.credit.user.aggregation;

import br.com.camel.integration.credit.user.model.Credit;
import br.com.camel.integration.credit.user.model.CreditUser;
import br.com.camel.integration.credit.user.model.User;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class IntegrationAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        System.out.println("ENTROU AGG");
        if(oldExchange != null) {
            System.out.println("OLD " + oldExchange.getIn().getBody(String.class));
            System.out.println("VALOR CONCATENADO: old = " + oldExchange.getIn().getBody(String.class) + " new = " + newExchange.getIn().getBody(String.class));
            try {
                Credit credit = newExchange.getIn().getBody(Credit.class);
                User user = oldExchange.getIn().getBody(User.class);
            } catch (Exception e) {
                Credit credit = oldExchange.getIn().getBody(Credit.class);
                User user = newExchange.getIn().getBody(User.class);
            }

            CreditUser creditUser = new CreditUser();

            newExchange.getIn().setBody(creditUser);
        } else {
            System.out.println("NEW " + newExchange.getIn().getBody(String.class));
            try {
                Credit credit = newExchange.getIn().getBody(Credit.class);
            } catch (Exception e) {
                User user = newExchange.getIn().getBody(User.class);
            }
        }
        return newExchange;
    }
}
