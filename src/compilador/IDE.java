package compilador;

import emptystack.NumeroLinea;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class IDE extends javax.swing.JFrame {
    
    NumeroLinea lineNumber;
    Directorio dir;
   
    public IDE() {
        initComponents();
        init();
    }

    
    private void init()  {
    dir = new Directorio();
    
    setTitle("#Compilador");
    String[] options = new String[]{"Guardar y continuar","Descargar"};
    
    lineNumber = new NumeroLinea(jtpCode);
    jScrollPane1.setRowHeaderView(lineNumber);
}
    
     //METODO PARA ENCONTRAR LAS ULTIMAS CADENAS
    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            //  \\W = [A-Za-Z0-9]
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    //METODO PARA ENCONTRAR LAS PRIMERAS CADENAS 
    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    //METODO PARA PINTAS LAS PALABRAS RESEVADAS
    private void colors() {

        final StyleContext cont = StyleContext.getDefaultStyleContext();

        //COLORES 
        final AttributeSet attred = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(255, 0, 35));
        final AttributeSet attgreen = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 255, 54));
        final AttributeSet attblue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 147, 255));
        final AttributeSet attblack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 0, 0));

        //STYLO 
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(SI|HAZ|ENTONCES|LOOP|A)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attblue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(INT|DEC|CAD)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attgreen, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(RET|ETD|SLD)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attred, false);
                        } else {
                            setCharacterAttributes(wordL, wordR - wordL, attblack, false);
                        }
                        wordL = wordR;

                    }
                    wordR++;
                }
            }

            public void romeve(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
            }
        };

        JTextPane txt = new JTextPane(doc);
        String temp = jtpCode.getText();
        jtpCode.setStyledDocument(txt.getStyledDocument());
        jtpCode.setText(temp);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnReserved = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnCompile = new javax.swing.JButton();
        btnIdent = new javax.swing.JButton();
        btnTokens = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaCompile = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/Icon/icons8_save_48px.png"))); // NOI18N
        btnSave.setText("Guardar");
        btnSave.setToolTipText("Guardar documento");
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/pressed/icons8_save_48px_p.png"))); // NOI18N
        btnSave.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/On Layer/icons8_save_48px_on.png"))); // NOI18N
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 90, 80));

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/Icon/icons8_code_file_48px.png"))); // NOI18N
        btnNew.setText("Nuevo");
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/pressed/icons8_code_file_48px_p.png"))); // NOI18N
        btnNew.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/On Layer/icons8_code_file_48px_on.png"))); // NOI18N
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 90, 80));

        btnReserved.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/Icon/icons8-text-color-48.png"))); // NOI18N
        btnReserved.setText("Reservadas");
        btnReserved.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReserved.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/pressed/icons8-text-color-48.png"))); // NOI18N
        btnReserved.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/On Layer/icons8-text-color-48.png"))); // NOI18N
        btnReserved.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReserved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservedActionPerformed(evt);
            }
        });
        getContentPane().add(btnReserved, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 100, 80));

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/Icon/icons8_opened_folder_48px.png"))); // NOI18N
        btnOpen.setText("Abrir");
        btnOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpen.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/pressed/icons8_opened_folder_48px_P.png"))); // NOI18N
        btnOpen.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/On Layer/icons8_opened_folder_48px_ON.png"))); // NOI18N
        btnOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        getContentPane().add(btnOpen, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 90, 80));

        btnCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/Icon/icons8_code_48px.png"))); // NOI18N
        btnCompile.setText("Compilar");
        btnCompile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompile.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/pressed/icons8_code_48px_p.png"))); // NOI18N
        btnCompile.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/On Layer/icons8_code_48px_on.png"))); // NOI18N
        btnCompile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompileActionPerformed(evt);
            }
        });
        getContentPane().add(btnCompile, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 90, 80));

        btnIdent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/Icon/icons8-text-cursor-48.png"))); // NOI18N
        btnIdent.setText("Identear");
        btnIdent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIdent.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/pressed/icons8-text-cursor-48.png"))); // NOI18N
        btnIdent.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/On Layer/icons8-text-cursor-48.png"))); // NOI18N
        btnIdent.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIdent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIdentActionPerformed(evt);
            }
        });
        getContentPane().add(btnIdent, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 90, 80));

        btnTokens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/Icon/icons8-index-48.png"))); // NOI18N
        btnTokens.setText("Tokens");
        btnTokens.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTokens.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/pressed/icons8-index-48.png"))); // NOI18N
        btnTokens.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/compilador/Icons/On Layer/icons8-index-48.png"))); // NOI18N
        btnTokens.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTokensActionPerformed(evt);
            }
        });
        getContentPane().add(btnTokens, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 90, 80));

        jtpCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtpCodeKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtpCode);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 1170, 330));

        jtaCompile.setColumns(20);
        jtaCompile.setRows(5);
        jScrollPane2.setViewportView(jtaCompile);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 1170, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        dir.Guardar(this);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        jtaCompile.setText("");
        dir.Nuevo(this);
        clearAllComp();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnReservedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservedActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        dir.Abrir(this);
        clearAllComp();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompileActionPerformed

    private void btnIdentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIdentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIdentActionPerformed

    private void btnTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTokensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTokensActionPerformed

    private void jtpCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtpCodeKeyReleased
        int keyCode = evt.getKeyCode();
        if((keyCode >= 65 && keyCode <= 90) || (keyCode >=48 && keyCode <=57)
                || (keyCode >=97 && keyCode <=122) || (keyCode !=27 && !(keyCode >=37
                && keyCode <=40) && !(keyCode >=16
                && keyCode <=18) && keyCode !=524
                && keyCode !=20)) {
            
            if(!getTitle().contains("*")) {
                setTitle(getTitle() + "*");
            }
        }
    }//GEN-LAST:event_jtpCodeKeyReleased

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
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IDE().setVisible(true);
            }
        });
    }
    
    public void clearAllComp(){
        jtaCompile.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompile;
    private javax.swing.JButton btnIdent;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnReserved;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTokens;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea jtaCompile;
    public javax.swing.JTextPane jtpCode;
    // End of variables declaration//GEN-END:variables
}
