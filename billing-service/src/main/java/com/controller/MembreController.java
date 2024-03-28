package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Membre;

@RestController
public class MembreController {

	private String topic = "my-topic-object";

	@Autowired
	private KafkaTemplate<String, Membre> kafkatemplate;

	@GetMapping ("/sends")
	public String sendmessage() {

		Membre membre = new Membre("rado", "malala");
		kafkatemplate.send(topic, "clecle", membre);

		return "envoie message";
	}
	
	@GetMapping ("/send/{message}")
	public String sendMessage (@PathVariable String message) {
	
		return "message sent ....";
	}

}
