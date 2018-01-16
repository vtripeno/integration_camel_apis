# Camel

This is the Apache Camel project, which will be responsible to make the integration with two APIs, a Node Js Api and an
Spring boot Api, which will send data for a RabbitMQ and the Apache Camel route will be responsible to read this queue and
after concatenate the two messages the Route will save the data in a MongoDB Database and send the response to a RabbitMQ out queue.

    This project is working whit Spring Boot and it is running in 'http://server-name:9090'.
