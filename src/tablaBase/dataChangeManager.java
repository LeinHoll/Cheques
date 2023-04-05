package tablaBase;

//import util.modeloTablaFolios;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultCellEditor;

public class dataChangeManager implements PropertyChangeListener {
    
    public dataChangeManager(tablaBase tabla){
        this.tabla  = tabla;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        try {
            if(evt.getOldValue() instanceof DefaultCellEditor){
                int                 fil     = tabla.convertRowIndexToModel(tabla.getEditingRow());
                int                 col     = tabla.getEditingColumn();
                DefaultCellEditor   editor  = (DefaultCellEditor) evt.getOldValue();
                        /*if(tabla.getModel() instanceof modeloTablaFolios) { 
                    modeloTablaFolios modfol = (modeloTablaFolios) tabla.getModel();
                    modfol.modifFolio(fil, col, editor.getCellEditorValue());
                }*/
            }
        } catch(Exception exc){}
    }
    
    tablaBase tabla;
}
