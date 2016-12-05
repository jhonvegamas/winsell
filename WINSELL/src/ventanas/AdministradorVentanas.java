package ventanas;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

/**
 * importare
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class AdministradorVentanas {

    private static JFacturas ftc;
    private static JFacturas1 ftc1;
    private static RegistroCliente rc;    
    private static RegistroVendedor rv;
    private static RegistroArticulo ra;

    public static void mostrarVentanaFactura(JDesktopPane dp) {
        if (ftc != null && !ftc.isShowing()) {
            ftc.show();
            dp.remove(ftc);
            try {
                dp.add(ftc, JLayeredPane.DEFAULT_LAYER);
            } catch (IllegalArgumentException ex) {
                dp.add(ftc, JLayeredPane.DEFAULT_LAYER);
            }
        }
        if (ftc == null) {
            ftc = new JFacturas();
            dp.add(ftc, JLayeredPane.DEFAULT_LAYER);
        }
        activarVentana(dp, ftc);
    }
    public static void mostrarVentanaFactura2(JDesktopPane dp) {
        if (ftc1 != null && !ftc1.isShowing()) {
            ftc1.show();
            dp.remove(ftc1);
            try {
                dp.add(ftc1, JLayeredPane.DEFAULT_LAYER);
            } catch (IllegalArgumentException ex) {
                dp.add(ftc1, JLayeredPane.DEFAULT_LAYER);
            }
        }
        if (ftc1 == null) {
            ftc1 = new JFacturas1();
            dp.add(ftc1, JLayeredPane.DEFAULT_LAYER);
        }
        activarVentana(dp, ftc1);
    }

    public static void mostrarVentanaVendedor(JDesktopPane dp) {
        if (rv != null && !rv.isShowing()) {
            rv.show();
            dp.add(rv, JLayeredPane.DEFAULT_LAYER);
            rv.iniciarValores();
        }

        if (rv == null) {
            rv = new RegistroVendedor();
            dp.add(rv, JLayeredPane.DEFAULT_LAYER);
        }
        activarVentana(dp, rv);
    }

    public static void mostrarVentanaCliente(JDesktopPane dp) {
        if (rc != null && !rc.isShowing()) {
            rc.show();
            dp.add(rc, JLayeredPane.DEFAULT_LAYER);
            rc.iniciarValores();
        }
        if (rc == null) {
            rc = new RegistroCliente();
            dp.add(rc, JLayeredPane.DEFAULT_LAYER);
        }
        activarVentana(dp, rc);
    }

    public static void mostrarVentanaArticulo(JDesktopPane dp) {
        if (ra != null && !ra.isShowing()) {
            ra.show();
            dp.add(ra, JLayeredPane.DEFAULT_LAYER);
            ra.iniciarValores();
        }

        if (ra == null) {
            ra = new RegistroArticulo();
            dp.add(ra, JLayeredPane.DEFAULT_LAYER);
        }
        activarVentana(dp, ra);
    }    

    private static void activarVentana(JDesktopPane dp, JInternalFrame vnt) {
        try {
            vnt.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AdministradorVentanas.class.getName()).log(Level.SEVERE, null, ex);
        }
        dp.setPosition(vnt, 0);
    }

}
