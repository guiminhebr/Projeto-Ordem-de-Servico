/*
 * The MIT License
 *
 * Copyright 2025 Guilherme.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.Color;
//importei a bilbioteca pra trabalhar com o sql e o dal.
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class TelaLogin extends javax.swing.JFrame {

    //aqui vou usar a variavel conexao que criei no modulo conexao
    Connection conexao = null;
    PreparedStatement pst = null; //mais um framework pra manipular instruções sql
    ResultSet rs = null; //exibe o resultado das instruções sql
    // usei tres frameworks dentro do pacote java.sql.*;
    //conexao e a variavel la do modulo conexao, pst e rs são usados pra instruções sql, pra atualiar as tabelas.
    //método pra logar

    public void logar() {
        //vou criar uma variável que vai executar a instrução SQL onde eu pesquiso no banco de dados o usuario e senha correspondente, usando a função and
        String sql = "select *from tbusuarios where login = ? and senha = ?";
        //essa variavel na parte "?" vai ser substituida pelo o input da caixa de etxto da tela login
        // ou seja, quando eu digitar o usuario e senha na tela, vai ter como referencia os "?"
        //sempre vamos trabalhar com as tres variaveis ali em cima.
        //tudo que envole bd, pode ocorrer exceção. por isso uso o try
        try {
            //as linhas abaixo preparam a consulta ao banco em função do que foi digitado nas caixas de texto
            // o ? é substituido pelo conteudo das variaveis..
            pst = conexao.prepareStatement(sql); // prepara o comando sql
            pst.setString(1, txtUsuario.getText()); //obtenha o que foi digitado no campo usuario
            String captura = new String(txtSenha.getText());
            pst.setString(2, captura);//obtenha o que foi digitado no campo senha
            // a variavel pst prepara a consulta ao BD em sua primeira linha.
            //agora executamos esse comando com a segunda variavel, no caso, executar a query 
            rs = pst.executeQuery();
            //se existir usuario e senha correspondente
            if (rs.next()) {
                //a linha abaixo obtem o contéudo do campo perfil da tabela tbusuarios
                String perfil = rs.getString(6); //pega o valor do campo 6 da tabela, o perfil
                //System.out.println(perfil); //pra verirficar se está tudo correto
                //a estrutura abaixo faz o tratamento do perfil do usuario
                if (perfil.equals("admin")){
                     // se existir usuario e senha corrrspondente, libera uma nova tela.
                     TelaPrincipal principal = new TelaPrincipal();
                     principal.setVisible(true);
                     TelaPrincipal.MenRel.setEnabled(true);
                     TelaPrincipal.MenCadUsu.setEnabled(true);
                     TelaPrincipal.lblUsuario.setText(rs.getString(2));
                     TelaPrincipal.lblUsuario.setForeground(Color.red);
                     //essas duas lihas abrem a tela principal, se validado o acesso.
                     this.dispose(); //garanta que o formulario feche ao chamar a tela principal
                     //preciaamos fechar tambem a conexão com o bd 
                     conexao.close();
                     
                    
                
                } else {
                    //caso não seja admin, não libera os recursos.
                    TelaPrincipal principal = new TelaPrincipal();
                     principal.setVisible(true);
                     TelaPrincipal.lblUsuario.setText(rs.getString(2));
                     this.dispose(); 
                     conexao.close();
                
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido");
                // se bão existir, não libera.

            }

        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);

        }
        //preciso executar o método logar no botão.

    }
    

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {

        initComponents();
        conexao = ModuloConexao.conector();
        // em resumo, o modulo de conexão sabe o tipo, caminho, nome, usuario e senha, e ele vai me retornar atraves do metodo conector, a conexão
        //cASO ele nao ache essa conexão, ele me retorna nulo. o resutaldo é uma string de conexão ou nulo.
        //cada formulario preciso importar aquelas bibliotecas e o modulo de conexão (pois e atraves dele que me eonecto ao bd)
        // e declarar tres variaveis especias. já posso testar se tá funcionando a conexão.
        //a linha de abaixo serve de apoio ao status da conexão.
        if (conexao != null) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dbok.png")));

        } else {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dberror.png")));

        }

        // System.out.println(conexao); //se conexao for nulo, houve erro. não encontrou o bd. se for uma string de conexão, conseguiu conectar.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("X System - Login");
        setResizable(false);

        jLabel1.setText("Usuário");

        jLabel2.setText("Senha");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/dberror.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtUsuario)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(396, 180));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        //chamando metodo logar
        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
