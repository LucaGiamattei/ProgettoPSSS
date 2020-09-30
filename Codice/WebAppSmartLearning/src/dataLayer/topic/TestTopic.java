package dataLayer.topic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataLayer.topic.controller.ControllerTopicDB;
import dataLayer.topic.entities.TopicDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idSubscription;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;

class TestTopic {
	private static ControllerTopicDB controller;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controller = new ControllerTopicDB();
	}

	@Test
	void testCreateTopic() {
		fail("Not yet implemented");
		TopicDB topic = new TopicDB("Algebra");
		if( controller.createTopic(topic)== StateResult.CREATED) {
			System.out.println("createTopic: output CREATED: id: "+ topic.getId().toString()+"\n");
		}
	}

	@Test
	void testValidTopic() {
		
		if( controller.validTopic(new idTopic(1))==StateResult.VALID) {
			System.out.println("validTopic: output VALID \n");
		};
	}

	@Test
	void testCreateSubscription() {
		fail("Not yet implemented");
		idSubscription sub = new idSubscription();
		if (controller.createSubscription(new idTopic(1), new idUser(1), sub)== StateResult.CREATED) {
			System.out.println("createSubscription: output CREATED: id: "+ sub.toString()+"\n");
		}
	}

	@Test
	void testRemoveSubscription() {
		
		
		if (controller.removeSubscription(new idSubscription(1))==StateResult.REMOVED) {
			System.out.println("removeSubscription: output REMOVED:");
		}
	}

	@Test
	void testGetMostRequestedTopic() {
		
		Vector <TopicDB> topics = new Vector <TopicDB> ();
		if ( controller.getMostRequestedTopic(topics) == StateResult.VALID) {
			System.out.println("getMostRequestedTopic: output VALID:");
			for (int i = 0; i<topics.size();i++) {
				if (controller.getTopicName(topics.get(i))==StateResult.VALID) {
					System.out.println("Topic id: "+topics.get(i).toString());
				}
				
			}
		}
	}


}
