package util;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class TrabajaTexto {
	
	ArrayList<Linea> renglones = new ArrayList<Linea>();
	JTextArea area;
	
	public void setTexto(JTextArea area){
		this.area = area;
		String texts[] = area.getText().split("\\n");
		for(String text : texts){
                    System.out.println(text);
			boolean print = true;
			for(int x=0 ; x < text.length() ; x++){
				String t = text.substring(x, x+1);
				print = t.contains(" ");
				if(!print) break;
			}
			if(!print){
				renglones.add(new Linea(text));
			}
		}
		titulaTextos();
		//formatTextArea();
	}
	
	private void titulaTextos(){
		String encavezados[] = {"lbFecha", "lbPersona","lbCantNum","lbCantLet","lbCantLet2"};
		
		renglones.add(2, renglones.get(1).getCantidad());
		
		for(int i = 0 ; i < encavezados.length ; i++){
			renglones.get(i).setTitulo(encavezados[i]);
		}
		
		String bancos[] = {"lbBanco","lbTitular","lbPoliza"};
		int e = encavezados.length;
		
		for(int i = 0 ; i < bancos.length ; i++){
			renglones.get(e + i).setTitulo(bancos[i]);
		}
		
		String conceptos = "Factura     Importe       Factura     Importe      Factura     Importe";
		e = encavezados.length + bancos.length;
		
		while(true){
			if(renglones.get(e).getTexto().contains(conceptos)){
				renglones.get(e).setTitulo("lbFactImp");
				break;
			} else {
				renglones.get(e).setTitulo("lbConcepto");
			}
			e++;
		}
		
		renglones.add(renglones.size() - 1, renglones.get(renglones.size() - 1).getCuenta());
		
		renglones.get(renglones.size() - 2).setTitulo("lbIngreso");
		renglones.get(renglones.size() - 1).setTitulo("lbEgreso");
		
		for(Linea renglon : renglones){
			if(renglon.getTitulo() == null){
				renglon.setTitulo("lbFactura");
			}
		}
	}
	
	private void formatTextArea(){
		area.setText("");
		for(Linea renglon : renglones){
			area.append("(" + renglon.getY() + "," + renglon.x + ") <" + renglon.getTitulo() + "> " + renglon.getTexto() + "\n");
		}
	}
	
	public ArrayList<Linea> getRenglones() {
		return renglones;
	}
	
}