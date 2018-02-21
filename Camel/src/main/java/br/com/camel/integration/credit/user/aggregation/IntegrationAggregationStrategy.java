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
        Credit credit = new Credit();
        User user = new User();
        if(oldExchange != null) {

            if(oldExchange.getIn().getBody() instanceof User &&
            newExchange.getIn().getBody() instanceof Credit) {
                credit = newExchange.getIn().getBody(Credit.class);
                user = oldExchange.getIn().getBody(User.class);
            } else if (oldExchange.getIn().getBody() instanceof Credit &&
            newExchange.getIn().getBody() instanceof User) {
                credit = oldExchange.getIn().getBody(Credit.class);
                user = newExchange.getIn().getBody(User.class);
            }

            CreditUser creditUser = constructCreditUser(user, credit);

            // Exit Message
            newExchange.getIn().setHeader("UniqueId", creditUser.getId());
            newExchange.getIn().setBody(creditUser);

            System.out.println(newExchange.getIn().getHeaders());
            System.out.println(newExchange.getIn().getBody(CreditUser.class));
        }
        return newExchange;
    }

    private CreditUser constructCreditUser(User user, Credit credit) {
        CreditUser creditUser = new CreditUser();
        creditUser.setId(String.valueOf(UUID.randomUUID()));
        creditUser.setStatusMessage(StatusMessage.IN_PROGRESS.message());
        creditUser.setValue(credit.getValue());
        creditUser.setCpf(user.getCpf());
        creditUser.setAnualPercentage(credit.getAnualPercentage());
        creditUser.setAge(user.getAge());
        creditUser.setName(user.getName());

        return creditUser;
    }
}
