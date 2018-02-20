package br.com.camel.integration.credit.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("age")
    private Integer age;
}
