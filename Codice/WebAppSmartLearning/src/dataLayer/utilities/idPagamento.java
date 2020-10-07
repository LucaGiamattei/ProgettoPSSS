package dataLayer.utilities;

public class idPagamento {

private int id ;
	
	public idPagamento() {
		super();
	
	}
	

	@Override
	public String toString() {
		return "" + id + "";
	}

	public idPagamento(int id) {
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
