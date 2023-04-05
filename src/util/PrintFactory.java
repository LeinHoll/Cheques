package util;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintFactory  {
	
	ArrayList<Linea> 	 elementos	;
	ModeloTablaConceptos modcon		;
	ModeloTablaFacturas  modfac		;

	public PrintFactory(ArrayList<Linea> elementos, ModeloTablaConceptos modcon, ModeloTablaFacturas modfac){
		this.elementos 	= elementos	;
		this.modcon 	= modcon	;
		this.modfac 	= modfac	;
	}
	
	public void Print(){
        PrinterJob job = PrinterJob.getPrinterJob();
        GraphicsHandler print;
        print = new GraphicsHandler();
        
        PageFormat  pf      = new PageFormat()  ;
        Paper       p       = new Paper     ()  ;
        
        p   .setSize      		(Convertidor.inchToPix(8.5),
                Convertidor.inchToPix(11));
        p   .setImageableArea	(0, 0, p.getWidth(), p.getHeight());
        pf  .setOrientation		(PageFormat.PORTRAIT);
        pf  .setPaper           (p);
        
        
        job.setPrintable(print, pf);
        
        if(job.printDialog()){
	        try { job.print(); }
	        catch(Exception ex) {}
        }
    }
	
	private class GraphicsHandler implements Printable {
		Graphics2D g2d;
		
	    @Override
	    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
	    	if(page > 0){
	    		return NO_SUCH_PAGE;
	    	} else {
	    		g2d = (Graphics2D) g;
		        g2d.translate(pf.getImageableX(), pf.getImageableY());
		        g2d.setFont(new Font("Consolas", Font.PLAIN, 11));
		        g2d.setPaint(Color.BLACK);
		        
		        for(Linea elemento : elementos){
		        	g2d.drawString(
		        			elemento.getTexto(),
		        			elemento.getX(),
		        			elemento.getY());
		        }
		        
		        int yConcepto = modcon.getCoordenadas().getY();
		        for(int i = 0 ; i < modcon.getConceptos().size() ; i ++){
		        	SGConcepto concepto = modcon.getConceptos().get(i);
		        	
		        	g2d.drawString(concepto.getCuenta(),
		        			modcon.getCoordenadas().getxCuenta(),
		        			yConcepto + i * 11);
		        	g2d.drawString(concepto.getConcepto(),
		        			modcon.getCoordenadas().getxConcepto(),
		        			yConcepto + i * 11);
		        			        	
		        	int xConceptoImporte;
		        	if(concepto.getValor() == SGConcepto.POSITIVO){
		        		xConceptoImporte = modcon.getCoordenadas().getxIngreso();
		        	} else {
		        		xConceptoImporte = modcon.getCoordenadas().getxEgreso();
		        	}
		        	
		        	g2d.drawString(concepto.getImporte(),
		        			xConceptoImporte,
		        			yConcepto + i * 11);
		        }
		        
		        int yFactura = modfac.getCoordenadas().getY();
		        g2d.drawString("Factura", modfac.getCoordenadas().getxFactura(), yFactura);
		        g2d.drawString("Importe", modfac.getCoordenadas().getxImporte(), yFactura);		        
		        yFactura += 11;
		        
		        for(int i = 0 ; i < modfac.getFacturas().size() ; i++) {
		        	SGFactura factura = modfac.getFacturas().get(i);
		        	g2d.drawString(factura.getFactura(),
		        			modfac.getCoordenadas().getxFactura(),
		        			yFactura + i * 11);
		        	
		        	g2d.drawString(factura.getImporte(),
		        			modfac.getCoordenadas().getxImporte(),
		        			yFactura + i * 11);
		        }
		        return PAGE_EXISTS;
	    	}
	    }
	}
    
}