package br.com.camel.integration.credit.user.beans;

import br.com.camel.integration.credit.user.model.CreditUser;
import br.com.camel.integration.credit.user.model.ErrorMessage;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.camel.Exchange;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Victor Tripeno
 * This class is responsible for save data in MongoDB
 */
public class Auditory {

//    @Value( "${DATABASE}" )
//    private String database;
//
//    @Value( "${COLLECTION_FAIL}" )
//    private String failCollection;
//
//    @Value("${COLLECTION}")
//    private String collection;
//
//    @Value("${spring.data.mongodb.host}")
//    private String mongoHost;
//
//    @Value("${spring.data.mongodb.port}")
//    private String mongoPort;

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

        buildMongoBody(exchange, mongoClient, mongoDatabase);

    }

    private void buildMongoBody(Exchange exchange, MongoClient mongoClient, MongoDatabase mongoDatabase) throws Exception {
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
        } else if(exchange.getIn().getBody() instanceof ErrorMessage) {
            ErrorMessage errorMessage = exchange.getIn().getBody(ErrorMessage.class);

            MongoCollection<Document> dbCollection = mongoDatabase.getCollection(String.valueOf(exchange.getContext().resolvePropertyPlaceholders("{{COLLECTION_FAIL}}")));
            Document messageError = new Document("_id", exchange.getIn().getHeader("UniqueId"))
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
