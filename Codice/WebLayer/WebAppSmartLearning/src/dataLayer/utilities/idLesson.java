package dataLayer.utilities;


public class idLesson {
private int id ;
	



	@Override
	public String toString() {
		return "" + id + "";
	}

	public idLesson(int id) {
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
