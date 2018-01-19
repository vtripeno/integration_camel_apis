# Integration Camel Apis

This project have the objective to show how the Apache Camel behavior when we try to integrate APIS, RabbitMQ and MongoDB.

It is working with Apache Camel, MongoDB, RabbitMQ, Node Js, and Spring Boot.

The lifecycle is the Node JS API will send a message to Apache Camel, the Apache Camel will wait for the other message with the same Correlation ID to make an aggregation. 
When the Spring Boot API post the message in RabbitMQ queue, the Apache Camel will take this message and verify the Correlation ID to make the aggregation with the Node Js API. 
After the aggregation, the Apache Camel will be the responsible to save the new message in a MongoDB database. And finally the Apache Camel will post the new message in the 
RabbitMQ out queue. If during this process occur some problem, the Apache Camel need to send the message to a dead letter queue, and this queue will retry send this message 
3 times, once per 5 minutes.

* IN QUEUE: CREDIT.USER.IN
* OUT QUEUE: CREDIT.USER.OUT
* Dead Letter Queue (backup): CREDIT.USER.DLQ

# RabbitMQ

For install the RabbitMQ you will need install the Erlang first of all.

After that you will download the RabbitMQ installer and execute this.

If when you try install RabbitMQ you receive the message "Insufficient system resources exist to complete the requested service", please follow this steps:

## Fixing RabbitMQ installation

1.Click Start, click Run, type regedit, and then click OK.

2.Locate and then click the following registry subkey:

```
HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\Memory Management
```

3.On the Edit menu, point to New, and then click DWORD Value.

4.In the New Value #1 box, type PoolUsageMaximum, and then press ENTER.

5.Right-click PoolUsageMaximum, and then click Modify.

6.In the Value data box, type 60, click Decimal, and then click OK.

7.If the PagedPoolSize registry entry exists, go to step 8. If the PagedPoolSize registry entry does not exist, create it.

To do this, follow these steps:

•On the Edit menu, point to New, and then click DWORD Value.

•In the New Value #1 box, type PagedPoolSize, and then press ENTER.

8.Right-click PagedPoolSize, and then click Modify.

9.In the Value data box, type ffffffff, and then click OK.

10.Exit Registry Editor, and then restart the computer. 

## Enabling the RabbitMQ Management

After the RabbitMQ installation open the RabbitMQ CMD and write this command:

```sh
rabbitmq-plugins enable rabbitmq_management
```

After that use the command:

```sh
rabbitmq-plugins start
```

Your management will be located at: 'http://server-name:15672'.

And the default user which RabbitMQ will create for you is user: 'guest' password: 'guest'.


# MongoDB

In this project you will need install MongoDB for connect on DataBase.

After install MongoDb by default you will uses the address 'http://server-name:27017'.

The camel route will work with Database: 'camel-credit-user' and Collection: 'credit-user'.

# TO DO

- Final contract spring boot API
- Final contract node js API
- Dead letter channel 
- Finish aggregations strategy
- Readme Node JS and Spring boot API
- Points to save data with MongoDB
- Create a Java Class specialist in save data in MongoDB for Fail and Retry DLC
