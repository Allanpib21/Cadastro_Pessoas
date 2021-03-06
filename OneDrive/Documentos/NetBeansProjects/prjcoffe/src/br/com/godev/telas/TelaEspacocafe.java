/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.godev.telas;

import java.sql.*;
import br.com.godev.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaEspacocafe extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaEspacocafe() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    //método para adicionar clientes

    private void adicionar() {

        String sql = "insert into tbcafe(nomeespcafe,lotacaoespcafe) values (?,?)";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCafNome.getText());
            pst.setString(2, txtCafLot.getText());
            
            if ((txtCafNome.getText().isEmpty()) || (txtCafLot.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Espaço adicionado com sucesso");
                    txtCafNome.setText(null);
                    txtCafLot.setText(null);
                   
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //pesquisar clientes pelo nome com filtro
    private void pesquisar_salas() {
        String sql = "select * from tbcafe where nomeespcafe like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCafPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tbCafe.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
// metodo para setar os campos  do formulario com o conteudo da tabela

    public void setar_campos() {
        int setar = tbCafe.getSelectedRow();
        txtIdCafe.setText(tbCafe.getModel().getValueAt(setar, 0).toString());
        txtCafNome.setText(tbCafe.getModel().getValueAt(setar, 1).toString());
        txtCafLot.setText(tbCafe.getModel().getValueAt(setar, 2).toString());
        
        // a linha abaixo desabilita o botão adicionar
        btnCafCreate.setEnabled(false);
    }

    //metodo para alterar dados do cliente
    private void alterar() {
        String sql = "update tbcafe set nomeespcafe=?,lotacaoespcafe=? where idcafe=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCafNome.getText());
            pst.setString(2, txtCafLot.getText());
            pst.setString(3, txtIdCafe.getText());

            if ((txtCafNome.getText().isEmpty()) || (txtCafLot.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Atualizados com sucesso");
                    txtCafNome.setText(null);
                    txtCafLot.setText(null);
                    btnCafCreate.setEnabled(true);
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // metodo para remover clientes
    private void remover() {
        // a estrutura abaixo confirma a remoçao do cliente
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover essa sala?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbcafe where idcafe=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdCafe.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Sala Removida com Sucesso");
                    txtCafNome.setText(null);
                    txtCafLot.setText(null);
                    btnCafCreate.setEnabled(true);
                }
            } catch (HeadlessException | SQLException e) {
            }
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCafNome = new javax.swing.JTextField();
        txtCafLot = new javax.swing.JTextField();
        btnCafCreate = new javax.swing.JButton();
        btnCafUpdate = new javax.swing.JButton();
        btnCafDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCafPesquisar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCafe = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtIdCafe = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Espaço Café");
        setMinimumSize(new java.awt.Dimension(842, 664));
        setPreferredSize(new java.awt.Dimension(842, 664));

        jLabel2.setText("*NOME SALA:");

        jLabel3.setText("*LOTAÇÃO SALA:");

        txtCafLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCafLotActionPerformed(evt);
            }
        });

        btnCafCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/godev/icones/add.png"))); // NOI18N
        btnCafCreate.setToolTipText("Adicionar");
        btnCafCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCafCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCafCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafCreateActionPerformed(evt);
            }
        });

        btnCafUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/godev/icones/update.png"))); // NOI18N
        btnCafUpdate.setToolTipText("Alterar");
        btnCafUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCafUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCafUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafUpdateActionPerformed(evt);
            }
        });

        btnCafDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/godev/icones/del.png"))); // NOI18N
        btnCafDelete.setToolTipText("Deletar");
        btnCafDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCafDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCafDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCafDeleteActionPerformed(evt);
            }
        });

        jLabel1.setText("* Campo Obrigatórios");

        txtCafPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCafPesquisarKeyReleased(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/godev/icones/find.png"))); // NOI18N

        tbCafe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCafe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCafeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCafe);

        jLabel8.setText("ID SALA");

        txtIdCafe.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCafLot)
                            .addComponent(txtCafNome, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addGap(266, 437, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btnCafCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(btnCafUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(btnCafDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 205, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCafPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCafe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(txtCafPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdCafe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCafNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCafLot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCafCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCafDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCafUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        setBounds(0, 0, 842, 664);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCafLotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCafLotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCafLotActionPerformed

    private void btnCafCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafCreateActionPerformed
        // adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnCafCreateActionPerformed

    private void txtCafPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCafPesquisarKeyReleased
        pesquisar_salas();
    }//GEN-LAST:event_txtCafPesquisarKeyReleased

    private void tbCafeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCafeMouseClicked
        setar_campos();
    }//GEN-LAST:event_tbCafeMouseClicked

    private void btnCafUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafUpdateActionPerformed
        //alterar clientes
        alterar();
    }//GEN-LAST:event_btnCafUpdateActionPerformed

    private void btnCafDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCafDeleteActionPerformed
        // remover pessoas
        remover();
    }//GEN-LAST:event_btnCafDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCafCreate;
    private javax.swing.JButton btnCafDelete;
    private javax.swing.JButton btnCafUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCafe;
    private javax.swing.JTextField txtCafLot;
    private javax.swing.JTextField txtCafNome;
    private javax.swing.JTextField txtCafPesquisar;
    private javax.swing.JTextField txtIdCafe;
    // End of variables declaration//GEN-END:variables
}
