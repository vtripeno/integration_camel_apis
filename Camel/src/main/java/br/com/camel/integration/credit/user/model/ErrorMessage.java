package br.com.camel.integration.credit.user.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "ERROR")
@XmlType(propOrder = {"correlationId", "message"})
public class ErrorMessage implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1988118778324665102L;

    private String message;
    private String correlationId;

    @XmlElement(name="message")
    public String getMessage() {
        return message;
    }

    @XmlElement(name="correlationId")
    public String getCorrelationId() {
        return correlationId;
    }

    public ErrorMessage(String message, String correlationId) {
        this.message = message;
        this.correlationId = correlationId;
    }

    public ErrorMessage(){}
}
