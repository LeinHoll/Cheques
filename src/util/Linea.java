package util;

import javax.swing.JLabel;

public class Linea {

	JLabel label ;
	String texto ;
	String titulo;
	int y = 0;
	int x = 0;
	
	public Linea(String texto){
		this.texto = texto;
		borraEspaciosFrontales();
		borraEspaciosFinales();
	}
	
	private void borraEspaciosFrontales(){
		if(texto.length() > 0){
			while(true){
				String t = texto.substring(0, 1);
				if(!t.equals(" ")) break;
				texto = texto.substring(1, texto.length());
			}
		}
	}
	
	private void borraEspaciosFinales(){
		while(true){
			String t = texto.substring(texto.length() - 1, texto.length());
			if(!t.equals(" ")) break;
			texto = texto.substring(0, texto.length() - 1);
		}
	}
	
	public Linea getCantidad(){
		String b = "";
		while(true){
			String t = texto.substring(texto.length() - 1, texto.length());
			if(t.equals(" ")) break;
			b = t + b;
			texto = texto.substring(0, texto.length() - 1);
		}
		borraEspaciosFinales();
		return new Linea(b);
	}
		
	public Linea getCuenta(){
		String b = "";
		while(true){
			if(texto.length() > 0){
				String t = texto.substring(0, 1);
				if(t.equals(" ")) break;
				b += t;
				texto = texto.substring(1, texto.length());
			} else { break;}
		}
		borraEspaciosFrontales();
		return new Linea(b);
	}
	
	public String getTexto() {
		return texto;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int row){
		this.y = row;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int col){
		this.x = col;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public JLabel getLabel() {
		return label;
	}
	
	public void setLabel(JLabel label) {
		this.label = label;
	}

}