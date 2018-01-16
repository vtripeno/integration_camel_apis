package br.com.camel.integration.credit.user.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

@JsonRootName(value = "data")
@JsonComponent
@JsonSerialize
public class CreditUser implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private Double value;
    private Double anualPercentage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getAnualPercentage() {
        return anualPercentage;
    }

    public void setAnualPercentage(Double anualPercentage) {
        this.anualPercentage = anualPercentage;
    }


}
