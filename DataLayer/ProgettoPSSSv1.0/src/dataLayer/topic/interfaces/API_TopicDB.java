package dataLayer.topic.interfaces;

import dataLayer.topic.entities.TopicDB;
import dataLayer.utilities.StateResult;
import dataLayer.utilities.idSubscription;
import dataLayer.utilities.idTopic;
import dataLayer.utilities.idUser;

public interface API_TopicDB {

	StateResult createTopic(TopicDB topic);
	StateResult validTopic(idTopic id);
	StateResult createSubscription(idTopic id, idUser idU, idSubscription idS);
	StateResult removeSubscription(idSubscription id);
	StateResult getMostRequestedTopic(idTopic[] id);
}
