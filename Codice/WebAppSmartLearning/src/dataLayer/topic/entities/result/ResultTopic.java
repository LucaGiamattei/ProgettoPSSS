package dataLayer.topic.entities.result;

import dataLayer.topic.entities.TopicDB;
import dataLayer.utilities.StateResult;

public class ResultTopic {

	private StateResult stateResult;
	private TopicDB topic;
	public ResultTopic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StateResult getStateResult() {
		return stateResult;
	}
	public void setStateResult(StateResult stateResult) {
		this.stateResult = stateResult;
	}
	public TopicDB getTopic() {
		return topic;
	}
	public void setTopic(TopicDB topic) {
		this.topic = topic;
	}
	
	
}
