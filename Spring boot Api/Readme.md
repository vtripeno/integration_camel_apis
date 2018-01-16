# Spring Boot API

This Spring Boot API will be responsible to send a text/xml data for an Apache Camel route.

API is running in 'http://server-name:9091/ws'.

Use the following
request:
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:occ="http://occ.com/user/register">
   <soapenv:Header/>
   <soapenv:Body>
      <occ:getUserRequest>
         <occ:cpf>12315156671</occ:cpf>
      </occ:getUserRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

After executing succesfully the API you'll receive the following
response:
```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:getUserResponse xmlns:ns2="http://occ.com/user/register">
            <ns2:user>
                <ns2:name>Bob</ns2:name>
                <ns2:cpf>12315156671</ns2:cpf>
                <ns2:age>19</ns2:age>
            </ns2:user>
        </ns2:getUserResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
