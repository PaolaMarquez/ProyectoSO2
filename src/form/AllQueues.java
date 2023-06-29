/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author paola
 */
public final class AllQueues extends javax.swing.JPanel {
    
    private final JPanel[] panels;
    private final Queue[] queues;
    private final Queue queue1;
    private final Queue queue2;
    private final Queue queue3;
    private final Queue queueR;
    
    /**
     * Creates new form AllQueues
     */
    public AllQueues() {
        initComponents();
        panels = new JPanel[4];
        queues = new Queue[4];
        queue1 = new Queue();
        queue2 = new Queue();
        queue3 = new Queue();
        queueR = new Queue();
        initializePanels();
        initializeQueues();
        paintQueue();
    }

    public Queue[] getQueues(){
        return queues;
    }
    public void initializePanels(){
        this.panels[0] = this.Level1;
        this.panels[1] = this.Level2;
        this.panels[2] = this.Level3;
        this.panels[3] = this.LevelR;
    }
    
    public void initializeQueues(){
        this.queues[0] = this.queue1;
        this.queues[1] = this.queue2;
        this.queues[2] = this.queue3;
        this.queues[3] = this.queueR;
    }
    
    public void createQueue(JPanel panel, Queue pq){
        pq.setSize(270, 60);
        pq.setLocation(0, 0);
        panel.removeAll();
        panel.add(pq, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
    
    public void paintQueue(){
        for (int i = 0; i < queues.length; i++) {
            createQueue(panels[i], queues[i]);
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

        jPanel1 = new javax.swing.JPanel();
        LevelR = new javax.swing.JPanel();
        Level3 = new javax.swing.JPanel();
        Level1 = new javax.swing.JPanel();
        Level2 = new javax.swing.JPanel();

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 380));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout LevelRLayout = new javax.swing.GroupLayout(LevelR);
        LevelR.setLayout(LevelRLayout);
        LevelRLayout.setHorizontalGroup(
            LevelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        LevelRLayout.setVerticalGroup(
            LevelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(LevelR, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 270, 60));

        javax.swing.GroupLayout Level3Layout = new javax.swing.GroupLayout(Level3);
        Level3.setLayout(Level3Layout);
        Level3Layout.setHorizontalGroup(
            Level3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        Level3Layout.setVerticalGroup(
            Level3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(Level3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 270, 60));

        Level1.setPreferredSize(new java.awt.Dimension(270, 60));

        javax.swing.GroupLayout Level1Layout = new javax.swing.GroupLayout(Level1);
        Level1.setLayout(Level1Layout);
        Level1Layout.setHorizontalGroup(
            Level1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        Level1Layout.setVerticalGroup(
            Level1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(Level1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 60));

        javax.swing.GroupLayout Level2Layout = new javax.swing.GroupLayout(Level2);
        Level2.setLayout(Level2Layout);
        Level2Layout.setHorizontalGroup(
            Level2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        Level2Layout.setVerticalGroup(
            Level2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(Level2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Level1;
    private javax.swing.JPanel Level2;
    private javax.swing.JPanel Level3;
    private javax.swing.JPanel LevelR;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
