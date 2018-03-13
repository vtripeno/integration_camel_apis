package com.api.user.controller;

import com.api.user.model.User;
import com.api.user.service.XmlTransformation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import java.io.IOException;

/**
 * @author victor tripeno
 *
 */
@RestController
public class UserController {

    @Value("${RABBIT_MQ_EXCHANGE}")
    private String exchange;

    @Value("${RABBIT_MQ_ROUTING_KEY}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserController(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping("/user")
    public String sendUser(@RequestBody User user) throws SOAPException, ParserConfigurationException, JAXBException, IOException {

        MessageProperties props = MessagePropertiesBuilder.newInstance().setContentType(MessageProperties.CONTENT_TYPE_XML).build();
        props.setHeader("Content-Type", "application/xml");

        Message msg = new Message(XmlTransformation.transform(user).getBytes(), props);
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        return XmlTransformation.transform(user);
    }
}
