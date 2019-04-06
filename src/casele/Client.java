package casele;
public class Client {

	private static int ID_COUNT = 0;
	private int sosire;
	private int servire;
	private int id;

	public Client(int sosire, int servire) {
		this.sosire = sosire;
		this.servire = servire;
		this.id = ++ID_COUNT;
	}

	public int getSosire() {
		return sosire;
	}

	public int getServire() {
		return servire;
	}

	public int getId() {
		return id;
	}

	public void setSosire(int sosire) {
		this.sosire = sosire;
	}

	public void setServire(int servire) {
		this.servire = servire;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Integer.toString(id);
	}

}