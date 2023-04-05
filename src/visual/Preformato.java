package visual;

import xml.Save;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import util.Linea;
import tablaBase.tablaBase;
import util.ModeloTablaConceptos;
import util.ModeloTablaFacturas;
import util.PrintFactory;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Preformato extends JDialog {


	ArrayList<Linea> renglones;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public Preformato() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				imprimele();
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);

		setBounds(100, 100, 574, 682);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lbFecha = new JLabel("lbFecha");
		lbFecha.setName("lbFecha");
		lbFecha.setToolTipText("Fecha");
		lbFecha.setBounds(12, 12, 155, 15);
		contentPanel.add(lbFecha);
		
		lbPersona = new JLabel("lbPersona");
		lbPersona.setName("lbPersona");
		lbPersona.setToolTipText("Persona");
		lbPersona.setBounds(12, 39, 550, 15);
		contentPanel.add(lbPersona);
		
		lbCantNum = new JLabel("lbCantNum");
		lbCantNum.setName("lbCantNum");
		lbCantNum.setToolTipText("Cantidad");
		lbCantNum.setBounds(273, 12, 164, 15);
		contentPanel.add(lbCantNum);
		
		lbCantLet = new JLabel("lbCantLet");
		lbCantLet.setName("lbCantLet");
		lbCantLet.setToolTipText("Cantidad en Letra");
		lbCantLet.setBounds(12, 61, 550, 15);
		contentPanel.add(lbCantLet);
		
		lbCantLet2 = new JLabel("lbCantLet2");
		lbCantLet2.setName("lbCantLet2");
		lbCantLet2.setToolTipText("Cantidad en Letra 2");
		lbCantLet2.setBounds(12, 83, 550, 15);
		contentPanel.add(lbCantLet2);
		
		lbBanco = new JLabel("lbBanco");
		lbBanco.setName("lbBanco");
		lbBanco.setToolTipText("Banco");
		lbBanco.setBounds(12, 124, 365, 15);
		contentPanel.add(lbBanco);
		
		lbTitular = new JLabel("lbTitular");
		lbTitular.setName("lbTitular");
		lbTitular.setToolTipText("Titular");
		lbTitular.setBounds(12, 136, 365, 15);
		contentPanel.add(lbTitular);
		
		lbPoliza = new JLabel("lbPoliza");
		lbPoliza.setName("lbPoliza");
		lbPoliza.setToolTipText("Poliza");
		lbPoliza.setBounds(12, 151, 365, 15);
		contentPanel.add(lbPoliza);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Conceptos");
		scrollPane.setBounds(12, 178, 550, 240);
		contentPanel.add(scrollPane);
		
		tbConceptos = new tablaBase(modcon, tablaBase.INSENSITIVO);
		tbConceptos.setEnabled(false);
		tbConceptos.setToolTipText("Conceptos");
		
		scrollPane.setViewportView(tbConceptos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setToolTipText("Facturas");
		scrollPane_1.setBounds(12, 430, 290, 197);
		contentPanel.add(scrollPane_1);
		
		tbFacturas = new tablaBase(modfac, tablaBase.INSENSITIVO);
		tbFacturas.setEnabled(false);
		tbFacturas.setToolTipText("Facturas");
		
		scrollPane_1.setViewportView(tbFacturas);
		
		lbIngreso = new JLabel("lbIngreso");
		lbIngreso.setName("lbIngreso");
		lbIngreso.setToolTipText("Ingreso");
		lbIngreso.setBounds(321, 430, 129, 15);
		contentPanel.add(lbIngreso);
		
		lbEgreso = new JLabel("lbEgreso");
		lbEgreso.setName("lbEgreso");
		lbEgreso.setToolTipText("Egreso");
		lbEgreso.setBounds(433, 457, 129, 15);
		contentPanel.add(lbEgreso);
		
		JPanel panel = new JPanel();
		panel.setBounds(314, 484, 248, 90);
		contentPanel.add(panel);
		
		JButton btnImprmele = new JButton("Imprimir");
		btnImprmele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimele();
			}
		});
		panel.add(btnImprmele);
		
		JButton btnSalva = new JButton("Salva Coordenadas");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salva();
			}
		});
		panel.add(btnSalva);
		
		popLabel = new JPopupMenu();
		getContentPane().add(popLabel, BorderLayout.NORTH);
		
		mntmCoordenadas = new JMenuItem("");
		mntmCoordenadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				coordenadasMenu(evt);
			}
		});
		popLabel.add(mntmCoordenadas);
		
		popFacturas = new JPopupMenu();
		getContentPane().add(popFacturas, BorderLayout.NORTH);
		
		JMenuItem mntmFacturas = new JMenuItem("Coordenadas Facturas");
		mntmFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				coordenadasMenu(evt);
			}
		});
		popFacturas.add(mntmFacturas);
		
		popConceptos = new JPopupMenu();
		getContentPane().add(popConceptos, BorderLayout.NORTH);
		
		JMenuItem mntmConceptos = new JMenuItem("Coordenadas Conceptos");
		mntmConceptos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				coordenadasMenu(evt);
			}
		});
		popConceptos.add(mntmConceptos);
	}

	public void setRenglones(ArrayList<Linea> renglones) {
		this.renglones = renglones;
		init();
	}
		
	private void init(){
		addClicker();	
		JLabel labels[] = {lbFecha, lbPersona, lbCantNum, lbCantLet, lbCantLet2, lbBanco, lbTitular, lbPoliza, lbIngreso, lbEgreso};
		for(JLabel label : labels){
			label.setText(reLabel(label));
		}
                for(Linea renglon : renglones){
                    System.out.println(renglon.getTitulo() + " " + renglon.getTexto());
                }
		
		modcon.setRenglones(renglones);
		modfac.setRenglones(renglones);
		
		setCoordenadas();
	}
	
	private String reLabel(JLabel label){
		for(Linea renglon : renglones){
			if(renglon.getTitulo().equals(label.getText())){
				renglon.setLabel(label);
				return renglon.getTexto();
			}
		}
		return "";
	}
	
	private void addClicker(){
		tbConceptos .setPop(popConceptos);
		tbFacturas	.setPop(popFacturas	);
		
		Component comps[] = contentPanel.getComponents();
		for(Component comp : comps){
			if(comp instanceof JLabel){
				comp.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent evt) {
						if(SwingUtilities.isRightMouseButton(evt)){
							mntmCoordenadas.setText("Coordenadas " + ((JLabel) comp).getToolTipText());
							mntmCoordenadas.setName(((JLabel) comp).getName()) ;
							popLabel.show(evt.getComponent(), evt.getX(), evt.getY());
						}
					}
					
				});
			}
		}
	}

	private void coordenadasMenu(ActionEvent evt){
		JMenuItem menu = (JMenuItem) evt.getSource();
		if(menu.getText().contains("Conceptos")){
			CoordenadasConceptos cConcepto = new CoordenadasConceptos();
			
			cConcepto.setCoordenadas(modcon.getCoordenadas());
			
			cConcepto.setLocationRelativeTo(null);
			cConcepto.setVisible(true);
		} else if(menu.getText().contains("Facturas")){
			CoordenadasFacturas cFactura = new CoordenadasFacturas();
			
			cFactura.setCoordenadas(modfac.getCoordenadas());
			
			cFactura.setLocationRelativeTo(null);
			cFactura.setVisible(true);
		} else {
			for(Linea renglon : renglones){
				if(renglon.getLabel() != null && menu.getName().equals(renglon.getTitulo())){
					CoordenadasLabel clabel = new CoordenadasLabel();
					
					clabel.setLinea(renglon);
					clabel.setLocationRelativeTo(null);
					clabel.setVisible(true);
					
					break;
				}
			}
		}
		
	}
	
	private void imprimele(){
		ArrayList<Linea> elementos = new ArrayList<Linea>();
		for(Linea renglon : renglones){
			if(renglon.getLabel() != null) {
				elementos.add(renglon);
			}
		}
		PrintFactory printFactory = new PrintFactory(elementos, modcon, modfac);
		printFactory.Print();
	}
	
	private void setCoordenadas(){
		Save save = new Save();
		save.read();
		
		modcon.setCoordenadas(save.getSGConcepto());
		modfac.setCoordenadas(save.getSGFactura());
		
		for(Linea renglon : renglones){
			for(Linea singular : save.getSingulares()){
				if(renglon.getTitulo().equals(singular.getTitulo())){
					renglon.setX(singular.getX());
					renglon.setY(singular.getY());
					break;
				}
			}
		}
	}
	
	private void salva(){
		Save save = new Save();
		save.setSavingCoordenadas(renglones, modfac.getCoordenadas(), modcon.getCoordenadas());
	}
	
	JLabel lbFecha	 ;
	JLabel lbPersona ;
	JLabel lbCantNum ;
	JLabel lbCantLet ;
	JLabel lbCantLet2;
	JLabel lbBanco	 ;
	JLabel lbTitular ;
	JLabel lbPoliza  ;
	JLabel lbIngreso ;
	JLabel lbEgreso  ;
	tablaBase tbConceptos;
	tablaBase tbFacturas ;
	ModeloTablaFacturas  modfac = new ModeloTablaFacturas ();
	ModeloTablaConceptos modcon = new ModeloTablaConceptos();
	JMenuItem mntmCoordenadas;
	JPopupMenu popLabel;
	JPopupMenu popFacturas;
	JPopupMenu popConceptos;
}
