package br.com.camel.integration.credit.user.processors;

import br.com.camel.integration.credit.user.enums.StatusMessage;
import br.com.camel.integration.credit.user.model.CreditUser;
import lombok.Data;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Data
public class ChangeStatus implements Processor {

    private StatusMessage status;

    public ChangeStatus(StatusMessage status) {
        this.status = status;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        CreditUser creditUser = exchange.getIn().getBody(CreditUser.class);
        creditUser.setStatusMessage(status.message());
        exchange.getOut().setBody(creditUser);
    }
}
