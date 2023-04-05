package visual;

import util.TrabajaTexto;
import util.PrintFactory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;

	public Principal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 411);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 515, 299);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setCaretColor(Color.GREEN);
		scrollPane.setViewportView(textArea);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(85, 323, 395, 56);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Parse");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accionElBoton();
			}
		});
		panel.setLayout(new GridLayout(0, 2, 10, 0));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpia");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accionImprime();
			}
		});
		btnNewButton_1.setBackground(Color.GRAY);
		panel.add(btnNewButton_1);
	}
	
	private void accionElBoton(){
		try{
			TrabajaTexto elTexto = new TrabajaTexto();
			
			elTexto.setTexto((textArea));
			
			Preformato preformato = new Preformato();
			
			preformato.setRenglones(elTexto.getRenglones());
			preformato.setLocationRelativeTo(null);
			preformato.setVisible(true);
			
		} catch (Exception exc){
			JOptionPane.showMessageDialog(null, "Whops, el texto origen no cumple con los requisitos\n");                      
		}
	}
	
	private void accionImprime(){
		textArea.setText("");
		/*PrintTest pTest = new PrintTest();
		pTest.Print(textArea.getText());*/
	}
}