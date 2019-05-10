package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartGame extends JFrame {
	
	public StartGame() {
		JFrame frame = new JFrame("StartGame");
		JPanel panel = new JPanel(new BorderLayout());
		this.setLayout(new BorderLayout());
		JPanel choixPerso = new JPanel(new GridLayout(13,1));
		JPanel choixEtage = new JPanel(new GridLayout(13,1));
		JPanel jouer = new JPanel(new BorderLayout());
		JButton buttonStart = new JButton("Jouer");
		JTextField textnbBebe = new JTextField();
		JTextField textnbAdulte = new JTextField();
		JTextField textnbVieux = new JTextField();
		JLabel nbBebes = new JLabel("Nombre de bébés");
		JLabel espace = new JLabel("");
		JLabel espace2 = new JLabel("");
		JLabel espace3 = new JLabel("");
		JLabel espace4 = new JLabel("");
		JLabel nbAdultes = new JLabel("Nombre d'adultes");
		JLabel nbVieux = new JLabel("Nombre de vieux");
		JLabel nbEtages = new JLabel("Nombre d'étages");
		JTextField textnbEtages = new JTextField();
		frame.setBounds(700, 500, 800, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jouer.add(buttonStart, BorderLayout.CENTER);
		jouer.setPreferredSize(new Dimension(20,40));
		choixPerso.add(nbBebes);
		choixPerso.add(textnbBebe);
		choixPerso.add(espace);
		choixPerso.add(espace2);
		choixPerso.add(nbAdultes);
		choixPerso.add(textnbAdulte);
		choixPerso.add(espace3);
		choixPerso.add(espace4);
		choixPerso.add(nbVieux);
		choixPerso.add(textnbVieux);
		choixPerso.setPreferredSize(new Dimension(200,4));
		choixEtage.add(nbEtages);
		choixEtage.add(textnbEtages);
		choixEtage.setPreferredSize(new Dimension(200,4));
		panel.add(choixPerso, BorderLayout.LINE_START);
		panel.add(choixEtage, BorderLayout.LINE_END);
		panel.add(jouer, BorderLayout.PAGE_END);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		buttonStart.addActionListener(new StartListener(frame,textnbBebe,textnbAdulte,textnbVieux,textnbEtages));
	}
}
