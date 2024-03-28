package com.customer.kafkaPruducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.customer.bean.Membre;

@RestController
public class kafkaController {
	
	String topic_string =  "my-topic";
	private String topic_membre = "my-topic-membre";
	private String topic_animaux = "my-topic-animaux";
	
	@Autowired
	private KafkaTemplate< String, String> kafkatemplateString;
	@Autowired
	private KafkaTemplate<String, Membre> kafkatemplatemembre;

	@GetMapping ("/sendstring/{message}")
	public String sendMessageavecCleValue (@PathVariable String message) {
		kafkatemplateString.send(topic_string,"cle"+System.currentTimeMillis(), message);
		System.out.println("***********************SEND STRING *****************************" );
		System.out.println(message);
		return "message sent ....";
	}
	@GetMapping ("/sendmembre")
	public String sendMembre() {
		Membre membre = new Membre("rado", "malala");
		kafkatemplatemembre.send(topic_membre, "clecle", membre);
		System.out.println("***********************SEND membre *****************************" );
		System.out.println(membre.toString());
		return "envoie message";
	}

}
