package casele;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
public class Casa extends Thread {

	private BlockingQueue<Client> clienti;
	private BlockingQueue<Client> clientiIesiti;
	private AtomicInteger perioadaAsteptare;

	public Casa() {
		clienti = new ArrayBlockingQueue<Client>(50);
		clientiIesiti = new ArrayBlockingQueue<>(50);
		perioadaAsteptare = new AtomicInteger();
	}

	public void addCLient(Client nouClient) {
		clienti.add(nouClient);
		perioadaAsteptare.incrementAndGet();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Client client = clienti.take();
				Thread.sleep(client.getServire() * 1000);
				clientiIesiti.add(client);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

	}

	public BlockingQueue<Client> getClienti() {
		return clienti;

	}

	public void setClienti(BlockingQueue<Client> clienti) {
		this.clienti = clienti;
	}

	public void printCoada() throws InterruptedException {
		for (int i = 0; i < clienti.size(); i++) {
			System.out.println(clienti.element().getId());
		}
	}

	public BlockingQueue<Client> getClientiIesiti() {
		return clientiIesiti;
	}

	public void setClientiIesiti(BlockingQueue<Client> clientiIesiti) {
		this.clientiIesiti = clientiIesiti;
	}

}
