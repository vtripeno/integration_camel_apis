# Spring Boot API

This Spring Boot API will be responsible to send a text/xml data for an Apache Camel route.

API is running in 'http://server-name:9091/user'.

Use the following
request:
```json
{"cpf":"12315156671", "name": "Bob", "age":20}
```

After executing succesfully the API will send the XML to a RabbitMQ queue and you will receive the following
response:
```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <user>
            <age>20</age>
            <cpf>12315156671</cpf>
            <name>Bob</name>
        </user>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
