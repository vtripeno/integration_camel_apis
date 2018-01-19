package br.com.camel.integration.credit.user.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "ERROR")
@XmlType(propOrder = {"message"})
public class Error implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1988118778324665102L;
    private String message;

    public Error(){}

    public Error(String message) {
        this.message = message;
    }

    @XmlElement(name="message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                '}';
    }
}
