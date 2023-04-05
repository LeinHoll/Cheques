package visual;

import util.SGCoordenadasConcepto;
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

public class CoordenadasConceptos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	SGCoordenadasConcepto xy;
	JSpinner spY		;
	JSpinner spCuenta	;
	JSpinner spConcepto	;
	JSpinner spIngreso	;
	JSpinner spEgreso	;
	
	public CoordenadasConceptos() {
		setModal(true);
		setTitle("Coordenadas de Conceptos");
		setBounds(100, 100, 248, 275);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(200, 200));
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(5, 10, 5, 5));
			{
				JLabel lblY = new JLabel("Pos Y");
				panel.add(lblY);
			}
			{
				spY = new JSpinner();
				spY.setPreferredSize(new Dimension(50, 20));
				spY.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spY);
			}
			{
				JLabel lblCuenta = new JLabel("Cuenta");
				panel.add(lblCuenta);
			}
			{
				spCuenta = new JSpinner();
				spCuenta.setPreferredSize(new Dimension(50, 20));
				spCuenta.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spCuenta);
			}
			{
				JLabel lblConcepto = new JLabel("Concepto");
				panel.add(lblConcepto);
			}
			{
				spConcepto = new JSpinner();
				spConcepto.setPreferredSize(new Dimension(50, 20));
				spConcepto.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spConcepto);
			}
			{
				JLabel lblIngreso = new JLabel("Ingreso");
				panel.add(lblIngreso);
			}
			{
				spIngreso = new JSpinner();
				spIngreso.setPreferredSize(new Dimension(50, 20));
				spIngreso.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spIngreso);
			}
			{
				JLabel lblEgreso = new JLabel("Egreso");
				panel.add(lblEgreso);
			}
			{
				spEgreso = new JSpinner();
				spEgreso.setPreferredSize(new Dimension(50, 20));
				spEgreso.setFont(new Font("Dialog", Font.BOLD, 20));
				panel.add(spEgreso);
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


	public void setCoordenadas(SGCoordenadasConcepto xy){
		this.xy = xy;
		
		spY			.setValue(xy.getY			());
		spCuenta	.setValue(xy.getxCuenta		());
		spConcepto	.setValue(xy.getxConcepto	());
		spIngreso	.setValue(xy.getxIngreso	());
		spEgreso	.setValue(xy.getxEgreso		());
	}
	
	private void picoOK(){
		xy.setY			((int) spY			.getValue());
		xy.setxCuenta	((int) spCuenta		.getValue());
		xy.setxConcepto	((int) spConcepto	.getValue());
		xy.setxIngreso	((int) spIngreso	.getValue());
		xy.setxEgreso	((int) spEgreso		.getValue());
		
		this.dispose();
	}
	
	private void picoNG(){
		this.dispose();
	}
	
}