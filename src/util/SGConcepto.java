package util;

public class SGConcepto {

	String cuenta, concepto, importe;
	boolean valor;
	public static final boolean POSITIVO = true	;
	public static final boolean NEGATIVO = false;

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
		if(importe.contains("-")){
			valor = NEGATIVO;
		} else {
			valor = POSITIVO;
		}
	}
	
	public boolean getValor(){
		return valor;
	}
	
}