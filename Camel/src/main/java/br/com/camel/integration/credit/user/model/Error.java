package br.com.camel.integration.credit.user.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@Data
@XmlRootElement(name = "ERROR")
@XmlType(propOrder = {"message"})
public class Error implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1988118778324665102L;
    @XmlElement(name="message")
    private String message;

}
