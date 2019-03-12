package br.com.camel.integration.credit.user.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoDbConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Bean(name = "myDb")
    public MongoClient myDb() {

        List<MongoCredential> creds = new ArrayList<>();

        MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();

        optionsBuilder.connectTimeout(1000*10);

        optionsBuilder.socketTimeout(1000*60);

        optionsBuilder.serverSelectionTimeout(1000*30);

        MongoClientOptions options = optionsBuilder.build();

        MongoClient mongoClient = new MongoClient(new ServerAddress(host , port), creds, options);

        return mongoClient;
    }
}
