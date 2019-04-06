package interfata;
import casele.Client;
import casele.Casa;
import casele.GeneratorClient;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class SimulationFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -5937081518783717561L;

	public JFrame frame;
	private JPanel p = new JPanel();
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JLabel pol1 = new JLabel("MinServire ");
	private JLabel pol2 = new JLabel("MaxServire ");

	private JLabel pol3 = new JLabel("Timp Rulare ");
	private JLabel pol4 = new JLabel("Strategie ");
	private JLabel pol5 = new JLabel("Numarul de case: ");
	private JLabel pol6 = new JLabel("MinSosire ");
	private JLabel pol7 = new JLabel("MaxSosire ");
	private JTextField tf1 = new JTextField(10);
	private JTextField tf2 = new JTextField(10);
	private JTextField tf3 = new JTextField(10);
	private JTextField tf4 = new JTextField(10);
	private JTextField tf5 = new JTextField(10);
	private JTextField tf6 = new JTextField(10);
	private JTextField tf7 = new JTextField(10);
	private JTextArea textAreaClients = new JTextArea(30, 30);
	private JTextArea textAreaRun = new JTextArea(30, 30);
	private JButton adunare = new JButton("Submit ");
	private JScrollPane scroll = new JScrollPane(textAreaClients);
	private JScrollPane scroll1 = new JScrollPane(textAreaRun);

	// scroll.setVerticalScrollBarPolicy(ScrollPaneconstants.VERTICAL_SCROLLBAR_ALWAYS);)
	public SimulationFrame() {
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame = new JFrame("Polinom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 650);
		p1.add(scroll);
		p3.add(scroll1);
		p2.add(pol1);
		p2.add(tf1);
		p2.add(pol2);
		p2.add(tf2);
		p2.add(pol3);
		p2.add(tf3);
		p2.add(pol4);
		p2.add(tf4);
		p2.add(pol5);
		p2.add(tf5);
		p2.add(adunare);
		p4.add(pol6);
		p4.add(tf6);
		p4.add(pol7);
		p4.add(tf7);
		p4.add(adunare);
		add(p1, BorderLayout.EAST);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.WEST);
		setLocationRelativeTo(null);
		p.add(p1);
		p.add(p3);
		p.add(p2);
		p.add(p4);
		adunare.addActionListener(this);

		frame.setContentPane(p);

		frame.setVisible(true);
	}

	public void displayData(Client[][] clienti) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SimulationManager simulationManager;
		String timp = tf3.getText();
		String minServire = tf1.getText();
		String maxServire = tf2.getText();
		String strategie = tf4.getText();
		String nrCase = tf5.getText();
		String minSos = tf6.getText();
		String maxSos = tf7.getText();
		simulationManager = new SimulationManager(Integer.parseInt(minServire), Integer.parseInt(maxServire),
				Integer.parseInt(strategie), Integer.parseInt(nrCase), Integer.parseInt(minSos),
				Integer.parseInt(maxSos));
		Thread t = new Thread(simulationManager);
		textAreaClients.setText("");
		textAreaRun.setText("");
		simulationManager.setTextAreaClients(textAreaClients);
		simulationManager.setTextAreaRunning(textAreaRun);
		if (e.getSource() == adunare) {
			simulationManager.setStrategie(Integer.parseInt(strategie));
			simulationManager.setTimeLimit(Integer.parseInt(timp));
			t.start();
		}

	}
}
