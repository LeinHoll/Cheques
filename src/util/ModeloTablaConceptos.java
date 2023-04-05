package util;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import util.SGConcepto;
import util.Linea;

public class ModeloTablaConceptos extends DefaultTableModel {

	ArrayList<SGConcepto> conceptos = new ArrayList<SGConcepto>();
	ArrayList<Linea> renglones;
	SGCoordenadasConcepto xy = new SGCoordenadasConcepto();
	
	public ModeloTablaConceptos(){
		setCols();
	}
	
	private void setCols(){
		String cols[] = {"Cuenta", "Concepto", "Importe"};
		for(String col : cols)
			addColumn(col);
	}

	public void setRenglones(ArrayList<Linea> renglones) {
		this.renglones = renglones;
		setConceptos();		
	}
		
	private void setConceptos(){
		for(Linea renglon : renglones){
			if(renglon.getTitulo().equals("lbConcepto")){
				
				SGConcepto concepto = new SGConcepto();
				
				concepto.setImporte	(renglon.getCantidad().getTexto()	);
				concepto.setCuenta	(renglon.getCuenta	().getTexto()	);
				concepto.setConcepto(renglon.getTexto	()				);
				
				conceptos.add(concepto);
			}
		}
		setFilas();
	}
	
	private void setFilas(){
		for(SGConcepto concepto : conceptos){
			Object fila[] = new Object[3];
			
			fila[0] = concepto.getCuenta();
			fila[1] = concepto.getConcepto();
			fila[2] = concepto.getImporte();
			
			addRow(fila);
		}
	}
		
	public SGCoordenadasConcepto getCoordenadas() {
		return xy;
	}
	
	public void setCoordenadas(SGCoordenadasConcepto xy){
		this.xy = xy;
	}
	
	public ArrayList<SGConcepto> getConceptos(){
		return conceptos;
	}
	
}