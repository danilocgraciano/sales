package com.company.orders.base;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.company.orders.messaging.JsonMessage;
import com.company.orders.messaging.MessageType;

public abstract class BaseMessageService {

	@Autowired
	private JmsTemplate jmsTemplate;

	private String destination;

	public BaseMessageService(String destination) {
		this.destination = destination;

	}

	public void send(MessageType type, Object object) {

		jmsTemplate.setDefaultDestinationName(this.destination);
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(getMessage(type, object));
			}
		});

	}

	public String getMessage(MessageType type, Object object) {
		return new JsonMessage(type, object).toString();
	}

}
