package ventanas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class JPanelImagen extends JPanel{
    private Image fondo;
    private ImageIcon icono;

    public JPanelImagen() {
    }
    
    public JPanelImagen(JFileChooser sa) {
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if(fondo != null)
        {
            
            g2.drawImage(fondo,0, 0, this.getWidth(), this.getHeight(), null);
        }else
        {
           g2.drawString("NO HAY IMAGEN", getWidth()/2-10, 0);
        }
    }
    
    public ImageIcon getIcon()
    {
        return icono;
    }
    
    public void setIcon(ImageIcon icono)
    {
        this.icono = icono;
        if(icono!=null)
            fondo = icono.getImage();
    }
}
