package interfata;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JTextArea;
import casele.Casa;
import casele.Client;
import casele.GeneratorClient;

public class SimulationManager implements Runnable {
	private JTextArea textAreaClients;
	private JTextArea textAreaRunning;
	private int timeLimit = 50;
	private int maxServireTime;
	private int minServireTime;
	private int maxSosireTime;
	private int minSosireTime;

	private int nrCase;
	private int numberOfClients = 45;
	private int strategie;
	// entity responsible with queue management and client distribution
	private GeneratorClient generatorClient;
	// pool of tasks(client shopping in the store)
	private List<Client> generatedClienti = new ArrayList<Client>();

	public SimulationManager(int minServireTime, int maxServireTime, int Strategie, int nrCase, int minSos,
			int maxSos) {
		this.minServireTime = minServireTime;
		this.maxServireTime = maxServireTime;
		this.strategie = Strategie;
		this.nrCase = nrCase;
		this.minSosireTime = minSos;
		this.maxSosireTime = maxSos;

		generatorClient = new GeneratorClient(nrCase, numberOfClients);
		generatorClient.setNumarCase(nrCase);
		generateNClienti(numberOfClients);
		for (int i = 0; i < generatedClienti.size(); i++) {
			System.out.println("Client " + generatedClienti.get(i).getId() + " " + generatedClienti.get(i).getSosire()
					+ " " + generatedClienti.get(i).getServire());
		}

	}

	public void setNrCase(int x) {
		this.nrCase = x;
	}

	public void minServireTime(int minServire) {
		this.minServireTime = minServire;
	}

	private void generateNClienti(int n) {
		for (int i = 0; i < n; i++) {
			int randomServire = ThreadLocalRandom.current().nextInt(minServireTime, maxServireTime + 1);
			int randomSosire = ThreadLocalRandom.current().nextInt(minSosireTime, maxSosireTime + 1);
			Client t = new Client(randomSosire, randomServire);
			generatedClienti.add(t);
		}
		generatedClienti.sort(Comparator.comparing(Client::getSosire));
	}

	@Override
	public void run() {
		int curentTime = 0;
		while (curentTime < timeLimit + 10) {
			afisarePersoaneIesiteCasa(curentTime);
			generareClientiTimpCurent(curentTime);
			afisarePersoaneCasa(curentTime);

			curentTime++;
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		textAreaRunning.append(String.format("Simulare finalizata la secunda %d\n", curentTime));
	}

	private void afisarePersoaneCasa(int curentTime) {
		String persoaneCasa = curentTime + "\n";
		for (int i = 0; i < nrCase; i++) {
			persoaneCasa = persoaneCasa + "Casa " + i + " " + generatorClient.getCasa()[i].getClienti() + " \n";
		}
		textAreaClients.setText(persoaneCasa);
	}

	private void generareClientiTimpCurent(int curentTime) {
		int coada = 0;
		for (int i = 0; i < generatedClienti.size(); i++)
			if (generatedClienti.get(i).getSosire() == curentTime) {
				if (strategie == 1) {
					coada = generatorClient.trimiteCLientCoadaScurta(generatedClienti.get(i));
				}
				if (strategie == 2) {
					coada = generatorClient.trimiteClient(generatedClienti.get(i));
				}
				textAreaRunning.append("Clientul " + generatedClienti.get(i).toString() + " a intrat la secunda "
						+ curentTime + " in casa " + coada + "\n");

			}
	}

	private void afisarePersoaneIesiteCasa(int curentTime) {
		for (int i = 0; i < nrCase; i++) {
			Casa casa = generatorClient.getCasa()[i];
			while (!casa.getClientiIesiti().isEmpty()) {
				try {
					textAreaRunning.append(String.format("Clientul %d a iesit din casa %d la secunda %d\n",
							casa.getClientiIesiti().take().getId(), casa.getId(), curentTime));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}
		}
	}

	public int getStrategie() {
		return strategie;
	}

	public void setStrategie(int strategie) {
		this.strategie = strategie;
	}

	public JTextArea getTextAreaClients() {
		return textAreaClients;
	}

	public void setTextAreaClients(JTextArea textAreaClients) {
		this.textAreaClients = textAreaClients;
	}

	public JTextArea getTextAreaRunning() {
		return textAreaRunning;
	}

	public void setTextAreaRunning(JTextArea textAreaRunning) {
		this.textAreaRunning = textAreaRunning;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

}
