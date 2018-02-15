package br.com.camel.integration.credit.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;
import java.io.Serializable;

@Data
@JsonRootName(value = "data")
@JsonComponent
@JsonSerialize
public class Credit implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String cpf;
    @JsonProperty
    private Double value;
    @JsonProperty("anual_percentage")
    private Double anualPercentage;

}
