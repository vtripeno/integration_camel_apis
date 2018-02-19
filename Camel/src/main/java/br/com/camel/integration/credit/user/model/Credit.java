package br.com.camel.integration.credit.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement
public class Credit implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("value")
    private Double value;
    @JsonProperty("anual_percentage")
    private Double anualPercentage;

}
