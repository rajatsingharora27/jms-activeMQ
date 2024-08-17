package com.jms.activemq.queue.activemq_introduction;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class ConsumerApp3 {
	
	
	public static void main(String args[]) {
		

		ActiveMQConnectionFactory brokerConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection=null;
		Session session=null;
		
		try {
			
			connection = brokerConnectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, session.AUTO_ACKNOWLEDGE);
			
			Queue queue = session.createQueue("test");
			
			MessageConsumer consumer = session.createConsumer(queue);
			
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					
					TextMessage textMessage = (TextMessage) message;
					
					try {
						System.out.println("The message we got from queue " + textMessage.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
	}
}
