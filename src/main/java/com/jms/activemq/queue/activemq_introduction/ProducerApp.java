package com.jms.activemq.queue.activemq_introduction;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.Connection;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class ProducerApp {

	public static void main(String[] args) throws JMSException {

		ActiveMQConnectionFactory brokerConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		try {
			connection = brokerConnectionFactory.createConnection();
			System.out.println("Connection to activemq is successful");

			// create Session
			/**
			 * to create a produer , cosumer
			 */

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// create queue will create a queue in broker
			// if the queue with the passed name is not present in the broker
			// new Queue will be created with that name
			// else if already present nothing will be created and will be used whatever is
			// there in broker with the same name that is being passed

			Destination queue = session.createQueue("test");

			MessageProducer producer = session.createProducer(queue);

			String[] message = new String[6];

			for (int i = 0; i < message.length; i++) {
				message[i] = "This is message number" + i;
				TextMessage textMessage = session.createTextMessage(message[i]);
				producer.send(textMessage);
			}
			System.out.println("Message has been sent to queue");

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null && connection != null) {
				connection.close();
			}
		}

	}
}
