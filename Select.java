package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Select extends JFrame implements Serializable{
	
	public Select() {
		JFrame frame = new JFrame("Sélection");
		JPanel panel = new JPanel(new BorderLayout());
		this.setLayout(new BorderLayout());
		JButton nouv = new JButton("Nouvelle partie");
		JButton reload = new JButton("Lancer la dernière sauvegarde");
		JPanel j = new JPanel();
		j.setPreferredSize(new Dimension(40,70));
		JPanel j2 = new JPanel();
		j2.setPreferredSize(new Dimension(40,70));
		frame.setBounds(700, 500, 800, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(nouv,BorderLayout.LINE_START);
		panel.add(reload,BorderLayout.LINE_END);
		panel.add(j,BorderLayout.PAGE_START);
		panel.add(j2,BorderLayout.PAGE_END);
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(500,250));
		frame.setVisible(true);
		nouv.addActionListener(new NewListener(frame));
		reload.addActionListener(new ReloadListener(frame));
	}

}
