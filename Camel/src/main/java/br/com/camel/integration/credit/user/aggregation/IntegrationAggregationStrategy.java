package br.com.camel.integration.credit.user.aggregation;

import br.com.camel.integration.credit.user.enums.StatusMessage;
import br.com.camel.integration.credit.user.model.Credit;
import br.com.camel.integration.credit.user.model.CreditUser;
import br.com.camel.integration.credit.user.model.User;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.UUID;

public class IntegrationAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Credit credit;
        User user;
        if(oldExchange != null) {

            try {
                credit = newExchange.getIn().getBody(Credit.class);
                user = oldExchange.getIn().getBody(User.class);
            } catch (Exception e) {
                credit = oldExchange.getIn().getBody(Credit.class);
                user = newExchange.getIn().getBody(User.class);
            }

            // Exit Message
            newExchange.getIn().setBody(constructCreditUser(user, credit));
        }
        return newExchange;
    }

    private CreditUser constructCreditUser(User user, Credit credit) {
        CreditUser creditUser = new CreditUser();
        creditUser.setId(String.valueOf(UUID.randomUUID()));
        creditUser.setStatusMessage(StatusMessage.IN_PROGRESS.message());

        return creditUser;
    }
}
