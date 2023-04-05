package visual;

import util.SGCoordenadasFactura;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class CoordenadasFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	SGCoordenadasFactura xy;
	JSpinner spY		;
	JSpinner spFactura	;
	JSpinner spImporte	;

	public CoordenadasFacturas() {
		setModal(true);
		setBounds(100, 100, 233, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(200, 100));
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(3, 6, 5, 5));
			{
				JLabel lblPosY = new JLabel("Pos Y");
				panel.add(lblPosY);
			}
			{
				spY = new JSpinner();
				spY.setPreferredSize(new Dimension(50, 20));
				spY.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spY);
			}
			{
				JLabel lblFacturas = new JLabel("Facturas");
				panel.add(lblFacturas);
			}
			{
				spFactura = new JSpinner();
				spFactura.setPreferredSize(new Dimension(50, 20));
				spFactura.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spFactura);
			}
			{
				JLabel lblImporte = new JLabel("Importe");
				panel.add(lblImporte);
			}
			{
				spImporte = new JSpinner();
				spImporte.setPreferredSize(new Dimension(50, 20));
				spImporte.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spImporte);
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

	
	public SGCoordenadasFactura getCoordenadas() {
		return xy;
	}
	

	public void setCoordenadas(SGCoordenadasFactura xy) {
		this.xy = xy;
		
		spY			.setValue(xy.getY		());
		spFactura	.setValue(xy.getxFactura());
		spImporte	.setValue(xy.getxImporte());
	}
	
	private void picoOK(){
		xy.setY			((int) spY		.getValue());
		xy.setxFactura	((int) spFactura.getValue());
		xy.setxImporte	((int) spImporte.getValue());
		
		this.dispose();
	}
	
	private void picoNG(){
		this.dispose();
	}

}
