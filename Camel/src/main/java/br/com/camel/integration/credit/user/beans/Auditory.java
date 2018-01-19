package br.com.camel.integration.credit.user.beans;

import br.com.camel.integration.credit.user.model.CreditUser;

/**
 * @author Victor Tripeno
 * This class is responsible for save data in MongoDB
 */
public class Auditory {

    private CreditUser creditUser;
    private String mongoCollection;
    private String mongoDataBase;

    public Auditory(CreditUser creditUser, String mongoCollection, String mongoDataBase) {
        this.creditUser = creditUser;
        this.mongoCollection = mongoCollection;
        this.mongoDataBase = mongoDataBase;
    }

    public void saveDataFail() {

    }

    public void saveDataRetry() {

    }

}
