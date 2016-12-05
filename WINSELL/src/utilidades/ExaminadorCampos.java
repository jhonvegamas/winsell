package utilidades;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.text.JTextComponent;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class ExaminadorCampos {    
        
    
    public final static int VERIFICACION=1;
    public final static int LIMPIAR=2;
    public final static int HABILITAR_DESABILITAR=3;
    public final static int TIPO_HABILITAR_TODO = 4;
    public final static int TIPO_HABILITAR_POR_NOMBRE = 5;    
    /**
     * 
     * @param contenedor componente padre<br/>
     * @param conNombreNull si es true se verificara solo los componentes<br/>
     * que retornen null cuando se llame a su metodo <code><font size="4">getName()</font></code>.<br/> 
     * Si se pasa false entonces se verificará los campos que no retornen null.
     * @param limpiar true para resetear lo campos.
     * @param habilitar true para los habilitar los campos.
     * @param tipoVerificacion el tipo de verificacion que se realizara.<br/>
     * VERIFICACION: Para verificar campos obligatorios.<br/>
     * LIMPIAR: Para limpiar o reniciar el valor de un componente.<br/>
     * HABILITAR_DESABILITAR: Para habilitar o desabiliar componentes.<br/>
     * @return boolean si los campos validados tienen algun valor válido
     */
    public static boolean camposObligatorios(JComponent contenedor,boolean conNombreNull,boolean limpiar,boolean habilitar,int tipoVerificacion,Object valor)
    {
        Component[] campos = contenedor.getComponents();
        boolean todoEsValido[] = {false,true};
        for(int i=0;i<campos.length;i++)
        {   
            
            if(campos[i] instanceof JDateChooser)
                continue;
            if(campos[i] instanceof JLabel)
                continue;
            
            if(conNombreNull)
            {
                if(campos[i].getName() == null)
                {   
                    if(habilitar && !campos[i].isEnabled()&& tipoVerificacion==HABILITAR_DESABILITAR)
                    {
                       if(campos[i] instanceof JTextComponent)
                       {
                           JTextComponent tmpCmp = (JTextComponent)campos[i];                           
                           tmpCmp.setEditable(true);
                       }else
                       {
                          campos[i].setEnabled(true); 
                       }
                       
                        todoEsValido[0]=true;
                        todoEsValido[1]=true;
                    }else if(!habilitar && tipoVerificacion==HABILITAR_DESABILITAR)
                    {    
                        if(campos[i] instanceof JTextComponent)
                       {
                           JTextComponent tmpCmp = (JTextComponent)campos[i];
                           tmpCmp.setEditable(false);
                       }else
                        {
                            campos[i].setEnabled(false); 
                        }                       
                        todoEsValido[0]=true;
                        todoEsValido[1]=true;
                    }
                    
                    if(tipoVerificacion==VERIFICACION)
                    {
                        if(notNull(campos[i],false,null))
                        {
                          todoEsValido[0]=true;
                          
                        }else 
                        {
                            todoEsValido[1]=false;  
                        }
                    }   
                }
            }
            
            if(tipoVerificacion==HABILITAR_DESABILITAR && campos[i].getName() != null)
                {
                    if(habilitar){                        
                            campos[i].setEnabled(false);
                        
                    }else if(!habilitar){                        
                            campos[i].setEnabled(true);
                    }
                }
            
            if(!conNombreNull)
            {                
                if(campos[i].getName() != null)
                {     
                    
                    if(habilitar && !campos[i].isEnabled()&& tipoVerificacion==HABILITAR_DESABILITAR)
                    {
                       campos[i].setEnabled(true);
                       todoEsValido[0]=true;
                        todoEsValido[1]=true;
                    }if(!habilitar && tipoVerificacion==HABILITAR_DESABILITAR)
                    {
                        campos[i].setEnabled(false);
                       todoEsValido[0]=true;
                        todoEsValido[1]=true;
                    }
                    
                    if(tipoVerificacion==VERIFICACION)
                    {
                        if(notNull(campos[i],false,null))
                        {
                           todoEsValido[0]=true;
                           todoEsValido[1]=true; 
                        }else 
                        {
                           todoEsValido[1]=false;  
                        }                        
                    }  
                    
                    
                }
            }
            
            if(tipoVerificacion==LIMPIAR)
            {
                notNull(campos[i],limpiar,valor);
                todoEsValido[0]=true;
                todoEsValido[1]=true;
            }
           
        }      
        return todoEsValido[0]&&todoEsValido[1]?true:false;
    }
    
    public static void limpiarTexto(JComponent contenedor,boolean conNombreNull,Object valor)
    {
        Component[] campos = contenedor.getComponents();        
            for(int i=0;i<campos.length;i++)
            {
                if(campos[i] instanceof JDateChooser)
                    continue;
               if(campos[i] instanceof JPanel)
               {                   
                 limpiarTexto((JComponent)campos[i],conNombreNull,valor);
               } 
               else{
                   camposObligatorios(contenedor,conNombreNull,true,false,LIMPIAR,valor);
               }
            } 
    }
    
    public synchronized static void habilitarComponentes(JComponent contenedor,boolean conNombreNull,boolean opcion,int tipoHabilitacion)
    {
         Component[] campos = contenedor.getComponents();         
         
        for(int i=0;i<campos.length;i++)
        {
            if(campos[i] instanceof JDateChooser)
                continue;
            if(campos[i] instanceof JLabel)
                continue;
           if(campos[i] instanceof JPanel)
           {
              habilitarComponentes(contenedor,conNombreNull,opcion,tipoHabilitacion); 
           }              
           else{
               if(tipoHabilitacion==TIPO_HABILITAR_POR_NOMBRE)
               {                   
                   camposObligatorios(contenedor,conNombreNull,false,opcion,HABILITAR_DESABILITAR,null);                   
                   
               }else if(tipoHabilitacion==TIPO_HABILITAR_TODO)
               {
                   if(campos[i] instanceof JTextComponent)
                     {
                           JTextComponent tmpCmp = (JTextComponent)campos[i];                           
                           tmpCmp.setEditable(opcion);
                     }else
                        campos[i].setEnabled(opcion); 
               }               
           }              
        } 
    }
    
    public static boolean verificarCamposObligatorios(JComponent contenedor,boolean conNombreNull)
    {
        Component[] campos = contenedor.getComponents();
        boolean esValido = false;
        for(int i=0;i<campos.length;i++)
        {
            if(campos[i] instanceof JDateChooser)
                continue;
           if(campos[i] instanceof JPanel)
              verificarCamposObligatorios(contenedor,conNombreNull);
           else
              esValido = camposObligatorios(contenedor,conNombreNull,false,false,VERIFICACION,null);
        } 
        
        return esValido;
    }
    
    private static boolean notNull(Component campos,boolean limpiar,Object valor)
    {
       
        JTextComponent cmpTexto = null;
        JToggleButton cmpOpciones = null; 
        JComboBox cbCombos = null;         
        if(campos instanceof JTextComponent)
        {                
            cmpTexto = (JTextComponent)campos; 
            if(limpiar)
            {                
               cmpTexto.setText(valor.toString());               
               return true;
            }
            if(cmpTexto.getText().isEmpty() && !limpiar)
            {                            
                cmpTexto.setBackground(Color.red);               
                return false;
            }

        }
        
        if(campos instanceof JToggleButton)
        {
            cmpOpciones = (JToggleButton)campos;  
            if(limpiar)
            {
               cmpOpciones.setSelected(false);
               return true;
            }
            
            if(!cmpOpciones.isSelected())
            {                
                return false;
            }
        }
        
        if(campos instanceof JComboBox)
        {
            
            cbCombos = (JComboBox)campos; 
            if(limpiar)
            {
               cbCombos.setSelectedIndex(0);
               return true;
            }
            if(cbCombos.getSelectedIndex() != -1)
            {   
                if(cbCombos.getSelectedIndex()==0 && !limpiar)
                {
                    cbCombos.setBackground(Color.red);
                    return false;
                }
                    
            }
        }
        
//        if(campos instanceof JButton)
//        {
//            if(limpiar)
//            {
//               campos.setEnabled(false);               
//               return true;
//            }
//        }
        
        return true;
    }
            
}
