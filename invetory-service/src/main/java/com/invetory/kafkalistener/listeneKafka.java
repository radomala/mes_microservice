package com.invetory.kafkalistener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.invetory.bean.Membre;

@Service
public class listeneKafka {
/*		@KafkaListener(topics = "my-topic", groupId = "microservice")
	public String receiveMessage(String message) {
		return "message receive";
	}

	@KafkaListener(topics = "my-topic", groupId = "microservice22")
	public String receiveMessageAvecCle(ConsumerRecord<String, String> message) {
		return "message receive";
	}
	
@KafkaListener(topics = "my-topic-membre", groupId = "microservice23")
	public String receiveMessageObject(ConsumerRecord<String, String> message) {
		
		Membre membre = deserialisationJson( message.value());
		return "message receive";
	}
	
	public Membre deserialisationJson (String jsonmessage) {
		
		Membre membre = new Membre();
		JsonMapper jsonmaper = new JsonMapper();
		try {
			membre = jsonmaper.readValue(jsonmessage, Membre.class);
		} catch (Exception e) {
		}
		return membre;
	}*/

	@KafkaListener(topics = "my-topic", groupId = "microservice23")
	public String receiveMessageObject(String message) {
		System.out.println("***********************RECEIVE STRING no KEY*****************************" );
		System.out.println("receive:=> "+message );
		return "message receive";
	}

	@KafkaListener(topics = "my-topic", groupId = "microservice25")
	public String receiveMessageAvecCle(ConsumerRecord<String, String> record) {
		System.out.println("***********************RECEIVE STRING KEY*****************************" );
		System.out.println("key:=> "+ record.key() + "  " + record.value() );
		return "message receive";
	}

	@KafkaListener(topics = "my-topic-membre", groupId = "microservice24")
	public String receiveMessageMembre(ConsumerRecord<String, Membre> record) {
		System.out.println("***********************RECEIVE MEMBRE *****************************" );
		System.out.println("key:=> "+ record.key() + "  " + record.value() );
		return "message receive";
	}
}


