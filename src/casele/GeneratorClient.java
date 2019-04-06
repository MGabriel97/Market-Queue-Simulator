package casele;

import java.util.Random;
public class GeneratorClient {
	private int numarCase;
	private Casa casa[];

	public GeneratorClient(int nrCase, int nrMaximClienti) {

		casa = new Casa[nrCase];
		for (int i = 0; i < nrCase; i++) {
			casa[i] = new Casa();
			casa[i].start();

		}
	}

	public void schimbareStrategie(String strategie) {
		if (strategie == "random") {

		}
	}

	public int trimiteClient(Client t) {
		int x = this.numarCase;
		Random rn = new Random();
		int answer = rn.nextInt(x);
		casa[answer].addCLient(t);
		return answer;
	}

	public int trimiteCLientCoadaScurta(Client t) {
		int min;
		int x1 = 0;
		min = casa[0].getClienti().size();
		for (int i = 1; i < numarCase; i++) {
			if (casa[i].getClienti().size() < min) {
				min = casa[i].getClienti().size();
				x1 = i;
			}
		}
		casa[x1].addCLient(t);
		return x1;
	}

	public Casa[] getCase() {
		return casa;
	}

	public int getNumarCase() {
		return numarCase;
	}

	public void setNumarCase(int numarCase) {
		this.numarCase = numarCase;
	}

	public Casa[] getCasa() {
		return casa;
	}

	public void setCasa(Casa[] casa) {
		this.casa = casa;
	}

}
