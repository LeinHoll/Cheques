package visual;

import util.Linea;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CoordenadasLabel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Linea linea;
	JSpinner spX;
	JSpinner spY;

	public CoordenadasLabel() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 263, 86);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel lblX = new JLabel("X     ");
				panel.add(lblX);
			}
			{
				spX = new JSpinner();
				spX.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spX);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JSeparator separator = new JSeparator();
				separator.setPreferredSize(new Dimension(5, 20));
				separator.setOrientation(SwingConstants.VERTICAL);
				panel.add(separator);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel lblY = new JLabel("Y     ");
				panel.add(lblY);
			}
			{
				spY = new JSpinner();
				spY.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spY);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						picoOK();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						picoNG();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public Linea getLinea(){
		return linea;
	}
	
	public void setLinea(Linea linea){
		this.linea = linea;
		spX.setValue(linea.getX());
		spY.setValue(linea.getY());
		this.setTitle("Coordenadas de " + linea.getLabel().getToolTipText());
	}
	
	private void picoOK(){
		linea.setX((int) spX.getValue());
		linea.setY((int) spY.getValue());
		this.dispose();
	}
	
	private void picoNG(){
		this.dispose();
	}
		
}
