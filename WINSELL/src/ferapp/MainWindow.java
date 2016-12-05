/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ferapp;

import elaprendiz.conection.BaseConexion;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import ventanas.AdministradorVentanas;

/**
 *
 * @author El APRENDIZ www.elaprendiz.net63.net
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    private Connection con = new BaseConexion().conectar2();

    public MainWindow() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource("/IMAGENES/logo.jpg")).getImage());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mArchivo = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemDiarias = new javax.swing.JMenuItem();
        jMenuItemSemanales = new javax.swing.JMenuItem();
        jMenuItemMensuales = new javax.swing.JMenuItem();
        jMenuItemAnuales = new javax.swing.JMenuItem();
        jMenuItemCliente = new javax.swing.JMenuItem();
        jMenuItemVendedor = new javax.swing.JMenuItem();
        mVendedor = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        mClientes = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        mFacturas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Facturacion APR");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jDesktopPane1.setBackground(new java.awt.Color(77, 77, 77));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        mArchivo.setText("Archivo");

        jMenuItem5.setText("Salir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        mArchivo.add(jMenuItem5);

        jMenu2.setText("Reportes");

        jMenuItemDiarias.setText("Reporte de Ventas Diarias.");
        jMenuItemDiarias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDiariasActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemDiarias);

        jMenuItemSemanales.setText("Reporte de Ventas Semanales.");
        jMenuItemSemanales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSemanalesActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSemanales);

        jMenuItemMensuales.setText("Reporte de Ventas Mensuales.");
        jMenuItemMensuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMensualesActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemMensuales);

        jMenuItemAnuales.setText("Reporte de Ventas Anuales");
        jMenuItemAnuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnualesActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemAnuales);

        jMenuItemCliente.setText("Reporte de mejor Cliente.");
        jMenuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClienteActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCliente);

        jMenuItemVendedor.setText("Reporte de mejor Vendedor.");
        jMenuItemVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendedorActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemVendedor);

        mArchivo.add(jMenu2);

        jMenuBar1.add(mArchivo);

        mVendedor.setText("Vendedores");

        jMenuItem3.setText("Administrar Vendedor");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mVendedor.add(jMenuItem3);

        jMenuBar1.add(mVendedor);

        mClientes.setText("Clientes");

        jMenuItem2.setText("Administrar Cliente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mClientes.add(jMenuItem2);

        jMenuBar1.add(mClientes);

        jMenu1.setText("Productos");

        jMenuItem7.setText("Administrar Productos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        mFacturas.setText("Facturas");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Nueva Factura");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mFacturas.add(jMenuItem1);

        jMenuItem4.setText("Factura Prueba");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mFacturas.add(jMenuItem4);

        jMenuBar1.add(mFacturas);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        AdministradorVentanas.mostrarVentanaFactura(jDesktopPane1);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AdministradorVentanas.mostrarVentanaCliente(jDesktopPane1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        AdministradorVentanas.mostrarVentanaVendedor(jDesktopPane1);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        int op = finalizar();
        if (op == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int op = finalizar();
        if (op == JOptionPane.OK_OPTION) {
            System.exit(0);
        }

    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
//        AdministradorVentanas.mostrarVentanaArticulo(jDesktopPane1);
        AdministradorVentanas.mostrarVentanaArticulo(jDesktopPane1);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItemDiariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDiariasActionPerformed
        try {
            Map p = new HashMap();
            JasperPrint print;
            JasperReport report = JasperCompileManager.compileReport(new File("C:/prueba/reporte_venta_diario.jrxml").getAbsolutePath());
            print = JasperFillManager.fillReport(report, null, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Ventas por Dias");
            view.setVisible(true);
            view.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemDiariasActionPerformed

    private void jMenuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClienteActionPerformed
        // TODO add your handling code here:
        try {
            Map p = new HashMap();
            JasperPrint print;
            JasperReport report = JasperCompileManager.compileReport(new File("C:/prueba/report_mejor_cliente.jrxml").getAbsolutePath());
            print = JasperFillManager.fillReport(report, null, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Mejores clientes");
            view.setVisible(true);
            view.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItemClienteActionPerformed

    private void jMenuItemSemanalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSemanalesActionPerformed
        // TODO add your handling code here:
        try {
            Map p = new HashMap();
            JasperPrint print;
            JasperReport report = JasperCompileManager.compileReport(new File("C:/prueba/report_venta_semanal.jrxml").getAbsolutePath());
            print = JasperFillManager.fillReport(report, null, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Ventas Semanales");
            view.setVisible(true);
            view.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItemSemanalesActionPerformed

    private void jMenuItemAnualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnualesActionPerformed
        // TODO add your handling code here:
        try {
            Map p = new HashMap();
            JasperPrint print;
            JasperReport report = JasperCompileManager.compileReport(new File("C:/prueba/report_venta_anual.jrxml").getAbsolutePath());
            print = JasperFillManager.fillReport(report, null, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Ventas Anual");
            view.setVisible(true);
            view.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItemAnualesActionPerformed

    private void jMenuItemMensualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMensualesActionPerformed
        // TODO add your handling code here:
        try {
            Map p = new HashMap();
            JasperPrint print;
            JasperReport report = JasperCompileManager.compileReport(new File("C:/prueba/report_venta_mensual.jrxml").getAbsolutePath());
            print = JasperFillManager.fillReport(report, null, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Ventas Mensuales");
            view.setVisible(true);
            view.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItemMensualesActionPerformed

    private void jMenuItemVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendedorActionPerformed
        // TODO add your handling code here:
        try {
            Map p = new HashMap();
            JasperPrint print;
            JasperReport report = JasperCompileManager.compileReport(new File("C:/prueba/report_mejor_vendedor.jrxml").getAbsolutePath());
            print = JasperFillManager.fillReport(report, null, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Mejores Vendedores");
            view.setVisible(true);
            view.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItemVendedorActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        AdministradorVentanas.mostrarVentanaFactura2(jDesktopPane1);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public int finalizar() {
        return JOptionPane.showConfirmDialog(this, "Si existe algun dato sin guardar "
                + "se perderán.\n ¿Desea salir del programa?");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItemAnuales;
    private javax.swing.JMenuItem jMenuItemCliente;
    private javax.swing.JMenuItem jMenuItemDiarias;
    private javax.swing.JMenuItem jMenuItemMensuales;
    private javax.swing.JMenuItem jMenuItemSemanales;
    private javax.swing.JMenuItem jMenuItemVendedor;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mClientes;
    private javax.swing.JMenu mFacturas;
    private javax.swing.JMenu mVendedor;
    // End of variables declaration//GEN-END:variables
}
