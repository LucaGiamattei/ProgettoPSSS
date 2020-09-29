package dataLayer.utilities;

public class idFasciaOraria {
	private int id ;
	



	@Override
	public String toString() {
		return "" + id + "";
	}

	public idFasciaOraria(int id) {
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
