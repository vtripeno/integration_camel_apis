package br.com.camel.integration.credit.user.beans;

import br.com.camel.integration.credit.user.model.CreditUser;
import br.com.camel.integration.credit.user.model.MessageError;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.camel.Exchange;
import org.bson.Document;

/**
 * @author Victor Tripeno
 * This class is responsible for save data in MongoDB
 */
public class Auditory {

    public Auditory() {}

    /**
     * Metod responsible to save data in mongoDB
     * @param exchange
     */
    public void saveData(Exchange exchange) throws Exception {
        StringBuilder sb = new StringBuilder("mongodb://")
                .append(String.valueOf(exchange.getContext().resolvePropertyPlaceholders("{{spring.data.mongodb.host}}")))
                .append(":")
                .append(String.valueOf(exchange.getContext().resolvePropertyPlaceholders("{{spring.data.mongodb.port}}")));
        String uri = sb.toString();

        MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
        MongoDatabase mongoDatabase = mongoClient.getDatabase(String.valueOf(exchange.getContext().resolvePropertyPlaceholders("{{DATABASE}}")));

        buildMongoBody(exchange, mongoDatabase);

    }

    private void buildMongoBody(Exchange exchange, MongoDatabase mongoDatabase) throws Exception {
        if(exchange.getIn().getBody() instanceof CreditUser) {
            CreditUser creditUser = exchange.getIn().getBody(CreditUser.class);

            MongoCollection<Document> dbCollection = mongoDatabase.getCollection(String.valueOf(exchange.getContext().resolvePropertyPlaceholders("{{COLLECTION}}")));
            Document userCredit = new Document("_id", creditUser.getId())
                    .append("cpf", creditUser.getCpf())
                    .append("name", creditUser.getName())
                    .append("statusMessage", creditUser.getStatusMessage())
                    .append("anualpercentage", creditUser.getAnualPercentage())
                    .append("statusmessage", creditUser.getStatusMessage())
                    .append("value", creditUser.getValue());
            dbCollection.insertOne(userCredit);
        } else if(exchange.getIn().getBody() instanceof MessageError) {
            MessageError errorMessage = exchange.getIn().getBody(MessageError.class);

            MongoCollection<Document> dbCollection = mongoDatabase.getCollection(String.valueOf(exchange.getContext().resolvePropertyPlaceholders("{{COLLECTION_FAIL}}")));
            Document messageError = new Document("_id", errorMessage.getId())
                    .append("correlationId", errorMessage.getCorrelationId())
                    .append("message", errorMessage.getMessage());
            dbCollection.insertOne(messageError);
        } else {
            String message = exchange.getIn().getBody(String.class);

            MongoCollection<Document> dbCollection = mongoDatabase.getCollection(String.valueOf(exchange.getContext().resolvePropertyPlaceholders("{{COLLECTION}}")));
            Document messageError = new Document("_id", exchange.getIn().getHeader("UniqueId"))
                    .append("message", message);
            dbCollection.insertOne(messageError);
        }
    }

}
