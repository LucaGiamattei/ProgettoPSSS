package dataLayer.topic.entities;

import dataLayer.utilities.idLesson;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;

public class TopicDB {

	private idTopic id;
	private String nome;
	
	public TopicDB(idTopic id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public idTopic getId() {
		return id;
	}
	public void setId(idTopic id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
