package util;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import util.Linea;
import util.SGFactura;

public class ModeloTablaFacturas extends DefaultTableModel{

	ArrayList<SGFactura> facturas = new ArrayList<SGFactura>();
	ArrayList<Linea> renglones;
	SGCoordenadasFactura xy = new SGCoordenadasFactura();
	
	public ModeloTablaFacturas(){
		setCols();
	}
	
	private void setCols(){
		String[] cols = {"Factura", "Importe"};
		for(String col : cols)
			addColumn(col);
	}
	
	public void setRenglones(ArrayList<Linea> renglones){
		this.renglones = renglones;
		setFacturas();
	}
	
	private void setFacturas(){
		for(Linea renglon : renglones){
			if(renglon.getTitulo().equals("lbFactura")){
				while(true){
					if(renglon.getTexto().length() > 0){
						SGFactura factura = new SGFactura();
						
						factura.setFactura(renglon.getCuenta().getTexto());
						factura.setImporte(renglon.getCuenta().getTexto());
						
						facturas.add(factura);
					} else {
						break;
					}
				}
			}
		}
		setFilas();
	}
	
	private void setFilas(){
		for(SGFactura factura : facturas){
			Object fila[] = new Object[2];
			
			fila[0] = factura.getFactura();
			fila[1] = factura.getImporte();
			
			addRow(fila);
		}
	}
		
	public SGCoordenadasFactura getCoordenadas(){
		return xy;
	}
	
	public void setCoordenadas(SGCoordenadasFactura xy){
		this.xy = xy;
	}
	
	public ArrayList<SGFactura> getFacturas(){
		return facturas;
	}
	
}