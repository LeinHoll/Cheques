package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class AdaptMouse extends MouseAdapter{

	Component comp;
	JMenuItem menu;
	JPopupMenu pop;
	
	public AdaptMouse(Component comp, JPopupMenu pop, JMenuItem menu){
		this.comp = comp;
		this.pop = pop;
		this.menu = menu;
	}
	
	@Override
	public void mouseClicked(MouseEvent evt) {
		if(SwingUtilities.isRightMouseButton(evt)){
			menu.setText("Coordenadas " + ((JComponent) comp).getToolTipText());
			pop.setLocation(evt.getLocationOnScreen());
			pop.setVisible(true);
		}
	}
		
}