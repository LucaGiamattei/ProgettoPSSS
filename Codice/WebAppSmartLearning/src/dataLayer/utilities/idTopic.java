package dataLayer.utilities;

public class idTopic {

private int id ;
	



	@Override
	public String toString() {
		return "" + id + "";
	}

	public idTopic(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
