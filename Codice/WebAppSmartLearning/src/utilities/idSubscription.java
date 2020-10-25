package utilities;

public class idSubscription {
private int id ;
	
	
	

	public idSubscription() {
	super();
	// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "" + id + "";
	}

	public idSubscription(int id) {
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
