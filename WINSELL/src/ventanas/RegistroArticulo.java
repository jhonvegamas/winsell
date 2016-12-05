/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import controller.ArticuloController;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utilidades.ExaminadorCampos;
import utilidades.FiltraEntrada;
import utilidades.Helper;

/**
 *
 * @author Yatogami
 */
public class RegistroArticulo extends javax.swing.JInternalFrame {

    ArticuloController acl;
    private boolean esActualizacion = false;
    private int posicionRegistro = 0;

    /**
     * Creates new form RegistroArticulo
     */
    public RegistroArticulo() {
        initComponents();
        acl = new ArticuloController();
        iniciarValores();
    }

    public void setArticuloObtenido(PanelVistaArticulo pvc) {
        if (pvc != null) {
            this.txtCodigo.setText(pvc.getAr().getId());
            this.txtArticulo.setText(pvc.getAr().getDescripcion());
            this.txtCantidad.setText(pvc.getAr().getStock() + "");
            this.txtPrecio.setText(pvc.getAr().getPrecioVenta() + "");
            resetPosicionRegistro();
            return;
        }
        this.txtCodigo.setText(ArticuloController.getArticulo().getId());
        this.txtArticulo.setText(ArticuloController.getArticulo().getDescripcion());
        this.txtCantidad.setText(ArticuloController.getArticulo().getStock() + "");
        this.txtPrecio.setText(ArticuloController.getArticulo().getPrecioVenta() + "");
    }

    private void getUltimoArticulo() {

        acl.getUltimoArticulo();
        setArticuloObtenido(null);
    }

    public RegistroArticulo getContenedor() {
        return this;
    }

    private void setFiltroCampos() {
        Helper.setFiltraEntrada(txtArticulo.getDocument(), FiltraEntrada.SOLO_LETRAS, 30, true);
        Helper.setFiltraEntrada(txtCantidad.getDocument(), FiltraEntrada.SOLO_NUMEROS, 4, true);
        Helper.setFiltraEntrada(txtPrecio.getDocument(), FiltraEntrada.SOLO_NUMEROS, 7, true);

    }

    private void removerFiltroCampos() {
        Helper.removerFiltraEntrada(txtArticulo.getDocument());
        Helper.removerFiltraEntrada(txtCantidad.getDocument());
        //  Helper.removerFiltraEntrada(txtPrecio.getDocument());
    }

    private void gaRegistro(boolean actualizar) {
        String mensaje = "¿Desea registrar nuevo cliente?";
        String titulo = "¿Desea Registrar?";

        if (actualizar) {
            mensaje = "¿Desea actualizar los datos de cliente?";
            titulo = "Desea Actualizar";
            if (!verficarCambio()) {
                JOptionPane.showInternalMessageDialog(this, "Ningun dato ha sido cambiado", "no se puede actualizar", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        boolean valido = ExaminadorCampos.verificarCamposObligatorios(pnlEntradas, true);
        System.out.println("es valido = " + valido);
        if (valido) {
            // falta generar codigo
            ArticuloController.getArticulo().setId(this.txtCodigo.getText());
            ArticuloController.getArticulo().setDescripcion(this.txtArticulo.getText());
            ArticuloController.getArticulo().setStock(Integer.parseInt(this.txtPrecio.getText().replaceAll("\\.", "")));
            ArticuloController.getArticulo().setPrecioVenta(Double.parseDouble(this.txtPrecio.getText().replaceAll("\\.", "")));
            ArticuloController cc = new ArticuloController();
            int opcion = JOptionPane.showInternalConfirmDialog(getContenedor(), mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.OK_OPTION) {
                if (!actualizar) {
                    int resultado = cc.grabarRegistro(ArticuloController.getArticulo());
                    if (resultado != 0) {
                        JOptionPane.showMessageDialog(getContenedor(), "Articulo aregado correctamente",
                                "Registro guardado", JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    int resultado = cc.actualizarRegistro(txtCodigo.getText(), ArticuloController.getArticulo());
                    if (resultado == 1) {
                        JOptionPane.showMessageDialog(getContenedor(), "Articulo Actualizado correctamente",
                                "Registro Actualizado", JOptionPane.INFORMATION_MESSAGE);

                    }
                }

            }

        } else {
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    JOptionPane.showMessageDialog(getContenedor(), "Los campos en rojo, son obligatorios",
                            "Ingrese todos los datos Obligatorios", JOptionPane.ERROR_MESSAGE);

                }
            });
        }
    }

    private boolean verficarCambio() {
        if (!ArticuloController.getArticulo().getDescripcion().equals(this.txtArticulo.getText())) {
            return true;
        } else if (!ArticuloController.getArticulo().getStock().equals(this.txtPrecio.getText())) {
            return true;
        } else if (!ArticuloController.getArticulo().getPrecioVenta().equals(this.txtPrecio.getText())) {
            return true;
        } else {
            return false;
        }
    }

    public void resetPosicionRegistro() {
        posicionRegistro = 2;
    }

    public void habilitarPaginador(boolean opcion) {
        ExaminadorCampos.habilitarComponentes(pnlBuscar, true, opcion, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        jButton3.setEnabled(opcion);
        if (opcion) {
            posicionRegistro = acl.getNumArticulos(false);
            jButton3.setEnabled(opcion);
        }
    }

    public void generarNuevoArticulo() {
        removerFiltroCampos();
        ExaminadorCampos.limpiarTexto(pnlEntradas, true, "");
        ExaminadorCampos.habilitarComponentes(pnlBotones, true, true, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, true, ExaminadorCampos.TIPO_HABILITAR_TODO);
        bntSalir.setEnabled(true);
        String ultimoId = acl.getCodigoUltimoRegistro();
        ultimoId = Helper.generarCodigo(ultimoId, Helper.PREFIJO_ARTICULO);
        txtCodigo.setText(ultimoId);
        setFiltroCampos();
    }

    public void iniciarValores() {
        ExaminadorCampos.habilitarComponentes(pnlBotones, true, false, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, false, ExaminadorCampos.TIPO_HABILITAR_TODO);
        acl = new ArticuloController();
        bntSalir.setEnabled(true);
        getUltimoArticulo();
        posicionRegistro = acl.getNumArticulos(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlBuscar = new javax.swing.JPanel();
        bntPrimero = new javax.swing.JButton();
        bntAnt = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        bntSig = new javax.swing.JButton();
        bntUltimo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnlBotones = new javax.swing.JPanel();
        bntNuevo = new javax.swing.JButton();
        bntModificar = new javax.swing.JButton();
        bntGuardar = new javax.swing.JButton();
        bntCancelar = new javax.swing.JButton();
        bntEliminar = new javax.swing.JButton();
        bntSalir = new javax.swing.JButton();
        pnlEntradas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Registro Articulo");
        setVisible(true);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        pnlBuscar.setLayout(new javax.swing.BoxLayout(pnlBuscar, javax.swing.BoxLayout.LINE_AXIS));

        bntPrimero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/PRIMERO.JPG"))); // NOI18N
        bntPrimero.setToolTipText("Primero");
        bntPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrimeroActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntPrimero);

        bntAnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/ANTERIOR.JPG"))); // NOI18N
        bntAnt.setToolTipText("Anterior");
        bntAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAntActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntAnt);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/BUSCAR.JPG"))); // NOI18N
        jButton3.setToolTipText("Buscar");
        jButton3.setName("bntBcr"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pnlBuscar.add(jButton3);

        bntSig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/SIGUIENTE.JPG"))); // NOI18N
        bntSig.setToolTipText("Siguiente");
        bntSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSigActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntSig);

        bntUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/ULTIMO.JPG"))); // NOI18N
        bntUltimo.setToolTipText("Ultimo");
        bntUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntUltimoActionPerformed(evt);
            }
        });
        pnlBuscar.add(bntUltimo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 3, 0);
        jPanel2.add(pnlBuscar, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        pnlBotones.setLayout(new java.awt.GridLayout());

        bntNuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntNuevo.setText("Nuevo");
        bntNuevo.setName("bntn"); // NOI18N
        bntNuevo.setPreferredSize(new java.awt.Dimension(67, 40));
        bntNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNuevoActionPerformed(evt);
            }
        });
        pnlBotones.add(bntNuevo);

        bntModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntModificar.setText("Modificar");
        bntModificar.setName("bntUpdate"); // NOI18N
        bntModificar.setPreferredSize(new java.awt.Dimension(79, 40));
        bntModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntModificarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntModificar);

        bntGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntGuardar.setText("Guardar");
        bntGuardar.setEnabled(false);
        bntGuardar.setPreferredSize(new java.awt.Dimension(75, 40));
        bntGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGuardarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntGuardar);

        bntCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntCancelar.setText("Cancelar");
        bntCancelar.setEnabled(false);
        bntCancelar.setPreferredSize(new java.awt.Dimension(77, 40));
        bntCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntCancelar);

        bntEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntEliminar.setText("Eliminar");
        bntEliminar.setEnabled(false);
        bntEliminar.setName("btnDelete"); // NOI18N
        bntEliminar.setPreferredSize(new java.awt.Dimension(73, 40));
        bntEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarActionPerformed(evt);
            }
        });
        pnlBotones.add(bntEliminar);

        bntSalir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bntSalir.setText("Salir");
        bntSalir.setName("bntSlr"); // NOI18N
        bntSalir.setPreferredSize(new java.awt.Dimension(53, 40));
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });
        pnlBotones.add(bntSalir);

        jPanel3.add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        pnlEntradas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Articulo:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Cantidad:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Precio:");

        txtArticulo.setEditable(false);
        txtArticulo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtArticulo.setPreferredSize(new java.awt.Dimension(320, 21));

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCantidad.setPreferredSize(new java.awt.Dimension(320, 21));

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCodigo.setEnabled(false);
        txtCodigo.setPreferredSize(new java.awt.Dimension(180, 21));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Codigo:");

        txtPrecio.setEditable(false);
        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecio.setPreferredSize(new java.awt.Dimension(119, 20));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasLayout = new javax.swing.GroupLayout(pnlEntradas);
        pnlEntradas.setLayout(pnlEntradasLayout);
        pnlEntradasLayout.setHorizontalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        pnlEntradasLayout.setVerticalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel3.add(pnlEntradas, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrimeroActionPerformed
        acl.getPaginadorArticulo(0, 1, true);
        setArticuloObtenido(null);
        posicionRegistro = 1;
    }//GEN-LAST:event_bntPrimeroActionPerformed

    private void bntAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAntActionPerformed
        removerFiltroCampos();
        if (posicionRegistro == 1) {
            JOptionPane.showInternalMessageDialog(this, "Este es el Primer articulo", "Primer articulo seleccioando", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        acl.getPaginadorArticulo(posicionRegistro - 2, 1, true);

        setArticuloObtenido(null);
        System.out.println(posicionRegistro);
        posicionRegistro--;
    }//GEN-LAST:event_bntAntActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //       VistaArticulo dialog = new VistaArticulo(new javax.swing.JFrame(),this, true);
        //       dialog.setVisible(true);
        PanelVistaArticulo pvc = new PanelVistaArticulo();
        JLabel aviso = new JLabel("Doble click para seleccionar");
        aviso.setEnabled(false);
        aviso.setOpaque(true);
        JOptionPane.showInternalOptionDialog(this, pvc, "Seleccione un Articulo", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso}, null);
        if (pvc.getAr() != null) {
            setArticuloObtenido(pvc);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bntSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSigActionPerformed
        if (posicionRegistro == acl.getNumArticulos(false)) {
            JOptionPane.showInternalMessageDialog(this, "Este es el Ultimo articulo", "Ultimo articulo seleccioando", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        acl.getPaginadorArticulo(posicionRegistro, 1, true);
        setArticuloObtenido(null);
        posicionRegistro++;
    }//GEN-LAST:event_bntSigActionPerformed

    private void bntUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntUltimoActionPerformed
        getUltimoArticulo();
        posicionRegistro = acl.getNumArticulos(false);
    }//GEN-LAST:event_bntUltimoActionPerformed

    private void bntNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNuevoActionPerformed
        generarNuevoArticulo();
    }//GEN-LAST:event_bntNuevoActionPerformed

    private void bntModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntModificarActionPerformed
        ExaminadorCampos.habilitarComponentes(pnlBotones, false, true, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, true, ExaminadorCampos.TIPO_HABILITAR_TODO);
        bntSalir.setEnabled(true);
        bntEliminar.setEnabled(true);
        bntGuardar.setEnabled(true);
        bntCancelar.setEnabled(true);
        bntModificar.setEnabled(false);
        setFiltroCampos();
        this.esActualizacion = true;
    }//GEN-LAST:event_bntModificarActionPerformed

    private void bntGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntGuardarActionPerformed

        this.gaRegistro(this.esActualizacion);
    }//GEN-LAST:event_bntGuardarActionPerformed

    private void bntCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelarActionPerformed

        removerFiltroCampos();
        ExaminadorCampos.habilitarComponentes(pnlBotones, true, false, ExaminadorCampos.TIPO_HABILITAR_POR_NOMBRE);
        ExaminadorCampos.habilitarComponentes(pnlEntradas, true, false, ExaminadorCampos.TIPO_HABILITAR_TODO);
        ExaminadorCampos.limpiarTexto(pnlEntradas, true, "");
        bntSalir.setEnabled(true);
        txtCodigo.setText("");
        if (ArticuloController.getArticulo() == null) {
            getUltimoArticulo();
        } else {
            setArticuloObtenido(null);
        }

        this.esActualizacion = false;
    }//GEN-LAST:event_bntCancelarActionPerformed

    private void bntEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarActionPerformed
        if (txtCodigo.getText().equals(ArticuloController.getArticulo().getId())) {
            int opcion = JOptionPane.showInternalConfirmDialog(getContenedor(), "¿Esta seguro que desea borrar al:\nCiiente: " + ""
                    + ArticuloController.getArticulo().toString() + "\nCodigo: " + txtCodigo.getText() + " ?", "¿Desea Eliminar?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.OK_OPTION) {
                int resultado = acl.eliminarRegistro(txtCodigo.getText());
                if (resultado != 1) {
                    JOptionPane.showInternalMessageDialog(getContenedor(), "No se pudo eliminar", "Error: al eliminar", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    JOptionPane.showInternalMessageDialog(getContenedor(), "Articulo Eliminado Correctamente", "Eliminacion Correcta", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        getUltimoArticulo();
        this.esActualizacion = false;
    }//GEN-LAST:event_bntEliminarActionPerformed

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        if (this != null) {
            if (this.isShowing()) {
                this.dispose();
            }
        }
    }//GEN-LAST:event_bntSalirActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAnt;
    private javax.swing.JButton bntCancelar;
    private javax.swing.JButton bntEliminar;
    private javax.swing.JButton bntGuardar;
    private javax.swing.JButton bntModificar;
    private javax.swing.JButton bntNuevo;
    private javax.swing.JButton bntPrimero;
    private javax.swing.JButton bntSalir;
    private javax.swing.JButton bntSig;
    private javax.swing.JButton bntUltimo;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
