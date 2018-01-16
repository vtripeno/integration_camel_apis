package br.com.camel.integration.credit.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.jackson.JsonComponent;
import java.io.Serializable;

@JsonRootName(value = "data")
@JsonComponent
@JsonSerialize
public class Credit implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String cpf;
    private Double value;
    private Double anualPercentage;

    @JsonProperty
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonProperty
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @JsonProperty("anual_percentage")
    public Double getAnualPercentage() {
        return anualPercentage;
    }

    public void setAnualPercentage(Double anualPercentage) {
        this.anualPercentage = anualPercentage;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "cpf='" + cpf + '\'' +
                ", value=" + value +
                ", anualPercentage=" + anualPercentage +
                '}';
    }
}
