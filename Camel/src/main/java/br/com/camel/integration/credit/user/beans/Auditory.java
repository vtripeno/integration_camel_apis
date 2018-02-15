package br.com.camel.integration.credit.user.beans;

import br.com.camel.integration.credit.user.model.CreditUser;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author Victor Tripeno
 * This class is responsible for save data in MongoDB
 */
public class Auditory {

    private CreditUser creditUser;
    private String mongoCollection;
    private String mongoDataBase;
    private String mongoUri;

    public Auditory(CreditUser creditUser, String mongoUri, String mongoCollection, String mongoDataBase) {
        this.creditUser = creditUser;
        this.mongoCollection = mongoCollection;
        this.mongoDataBase = mongoDataBase;

        StringBuilder sb = new StringBuilder("mongodb://").append(mongoUri);
        this.mongoUri = sb.toString();
    }

    public void saveData() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri));
        MongoDatabase database = mongoClient.getDatabase(mongoDataBase);
        MongoCollection<Document> dbCollection = database.getCollection(mongoCollection);
        Document userCredit = new Document("_id", creditUser.getId())
                                    .append("cpf", creditUser.getCpf())
                                    .append("anual_percentage", creditUser.getAnualPercentage())
                                    .append("status_message", creditUser.getStatusMessage())
                                    .append("value", creditUser.getValue());
        dbCollection.insertOne(userCredit);


    }

}
