package xml;

import util.Linea;
import util.SGCoordenadasConcepto;
import util.SGCoordenadasFactura;

import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Save {

	public void write() {
		try {
			File file = new File("coordenadas.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			doc = docBuilder.newDocument();
			Element root = doc.createElement("cheques");
			doc.appendChild(root);

			Element singular = doc.createElement("Singulares");
			Element concepto = doc.createElement("lbConcepto");
			Element factura  = doc.createElement("lbFactura" );

			root.appendChild(singular);
			root.appendChild(concepto);
			root.appendChild(factura );

			if(file.exists() && renglones != null){
				setNuevoSingular(singular);
				setNuevoConcepto(concepto);
				setNuevoFactura (factura );
			} else {
				setDefaultSingular	(singular);				
				setDefaultAttr		(concepto, xyConcepto);
				setDefaultAttr		(factura , xyFactura );
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	public void read(){
		File file = new File("coordenadas.xml");
		if(file.exists()){
			try{
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();
				
				Node nSingular = doc.getElementsByTagName("Singulares").item(0);
				for(int i = 0 ; i < lbSingulares.length ; i++){
					Node inNode = ((Element) nSingular).getElementsByTagName(lbSingulares[i]).item(0);
					Element eSingular = (Element) inNode;
					
					Linea xy = new Linea("t");
					xy.setTitulo(lbSingulares[i]);
					xy.setX(Integer.parseInt(eSingular.getAttribute("X")));
					xy.setY(Integer.parseInt(eSingular.getAttribute("Y")));
					
					xySingulares.add(xy);
				}
				
				Node nConcepto = doc.getElementsByTagName("lbConcepto").item(0);
				Element eConcepto = (Element) nConcepto;
				sgConcepto.setY			(Integer.parseInt(eConcepto.getAttribute("Y"		)));
				sgConcepto.setxCuenta	(Integer.parseInt(eConcepto.getAttribute("xCuenta"	)));
				sgConcepto.setxConcepto	(Integer.parseInt(eConcepto.getAttribute("xConcepto")));
				sgConcepto.setxIngreso	(Integer.parseInt(eConcepto.getAttribute("xIngreso"	)));
				sgConcepto.setxEgreso	(Integer.parseInt(eConcepto.getAttribute("xEgreso"	)));
				
				Node nFactura = doc.getElementsByTagName("lbFactura").item(0);
				Element eFactura = (Element) nFactura;
				sgFactura.setY			(Integer.parseInt(eFactura.getAttribute("Y"			)));
				sgFactura.setxFactura	(Integer.parseInt(eFactura.getAttribute("xFactura"	)));
				sgFactura.setxImporte	(Integer.parseInt(eFactura.getAttribute("xImporte"	)));
				
			}catch (Exception exc){
				exc.printStackTrace();
			}
		} else {
			write();
			read ();
		}
	}
	
	private void setSingular(Element singular, String lb, String x, String y){
		Element element = doc.createElement(lb);
		
		element.setAttribute("X", x);
		element.setAttribute("Y", y);
		
		singular.appendChild(element);
	}
	
	private void setDefaultSingular(Element singular){
		for(String lb: lbSingulares){
			setSingular(singular, lb, "1", "1");
		}
	}
	
	private void setDefaultAttr(Element element, String[] attrs){
		for(String attr : attrs){
			element.setAttribute(attr, "1");
		}
	}
	
	private void setNuevoSingular(Element singular){
		for(String lb : lbSingulares){
			for(Linea renglon : renglones){
				if(renglon.getTitulo().equals(lb)){
					setSingular(singular, lb, renglon.getX() + "", renglon.getY() + "");
					break;
				}
			}
		}
	}
	
	private void setNuevoConcepto(Element concepto){
		concepto.setAttribute("Y"			, sgConcepto.getY			() + "");
		concepto.setAttribute("xCuenta"		, sgConcepto.getxCuenta		() + "");
		concepto.setAttribute("xConcepto"	, sgConcepto.getxConcepto	() + "");
		concepto.setAttribute("xIngreso"	, sgConcepto.getxIngreso	() + "");
		concepto.setAttribute("xEgreso"		, sgConcepto.getxEgreso		() + "");
	}
	
	private void setNuevoFactura(Element factura){
		factura.setAttribute("Y"		, sgFactura.getY		() + "");
		factura.setAttribute("xFactura"	, sgFactura.getxFactura	() + "");
		factura.setAttribute("xImporte"	, sgFactura.getxImporte	() + "");
	}
	
	public void setSavingCoordenadas(ArrayList<Linea> renglones, SGCoordenadasFactura sgFactura, SGCoordenadasConcepto sgConcepto){
		this.renglones = renglones;
		this.sgFactura = sgFactura;
		this.sgConcepto = sgConcepto;
		write();
	}

	public SGCoordenadasFactura getSGFactura(){
		return sgFactura;
	}
	
	public SGCoordenadasConcepto getSGConcepto(){
		return sgConcepto;
	}
	
	public ArrayList<Linea> getSingulares(){
		return xySingulares;
	}
	
	String lbSingulares[] = {
			"lbFecha", "lbPersona", "lbCantNum", "lbCantLet", "lbCantLet2",
			"lbBanco", "lbTitular", "lbPoliza", "lbIngreso", "lbEgreso"
			};
	String xyConcepto[] = {
			"Y", "xCuenta", "xConcepto", "xIngreso", "xEgreso"
	};
	String xyFactura[] = {
			"Y", "xFactura", "xImporte"
	};
	
	ArrayList<Linea> xySingulares = new ArrayList<Linea>();
	ArrayList<Linea> renglones;
	SGCoordenadasFactura sgFactura = new SGCoordenadasFactura();
	SGCoordenadasConcepto sgConcepto = new SGCoordenadasConcepto();
	Document doc;
}