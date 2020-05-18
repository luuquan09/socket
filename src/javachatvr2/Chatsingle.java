/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatvr2;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author LUUQUAN-PC
 */
public class Chatsingle extends javax.swing.JFrame {

    /**
     * Creates new form Chatsingle
     */
    public Chatsingle() {
        initComponents();
      //  txtdisplay.setVisible(false);
     //   txtsend.setVisible(false);
     //   btnsend.setVisible(false);
    }
    String namenhan="";
    public Chatsingle(String namenhan,Socket socketlogin){
        this.namenhan=namenhan;
        // Lấy socket login
        this.socketlogin=socketlogin;
         initComponents();
        txtdisplay.setVisible(false);
        txtsend.setVisible(false);
        btnsend.setVisible(false);
        txtdisplay.setEnabled(false);
    }
    //Socket mới
     Socket socket;
     //Socket lúc login
     Socket socketlogin;
    private ReadThread re;
    private WriteThread rw;
    private PrintWriter writer;
    private String display="";
    private String tennguoinhan="";
    private DefaultListModel<String> modellistonl= new DefaultListModel<>();
    private String namegui="";
    public class client {
    private String hostname;
    private int port;
 //   private String username;
    private String userName;
    public client(String hostname,int port){
        this.hostname=hostname;
        this.port=port;

    }
    public void execute(){
        try {
            socket = new Socket(hostname,port);
            rw = new WriteThread(socket, this);
            rw.start();
            re =new  ReadThread(socket, this);
            re.start();
            
            
        } catch (Exception e) {
        }

    }
    
     void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
   
}
     private class WriteThread extends Thread {
   // private PrintWriter writer;
    private Socket socket;
    private client client;
 
    public WriteThread(Socket socket, client client) {
        this.socket = socket;
        this.client = client;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
    public String ingetname(){
        return client.getUserName();
    }
   @Override
    public void run() {
          
        client.setUserName(namenhan);
        writer.println(namenhan);
       
       // writer.println(namegui);
    }
    
}
     
 private class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private client client;
 
    public ReadThread(Socket socket,client client) {
        this.socket = socket;
        this.client = client;
 
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

  
 
    public void run() {
        while (true) {
            
            try {
               
                String response = reader.readLine();
                
                // Kiểm tra trạng thái chờ bận
                 String[] output = response.split("-");
                 if(output[0].equals("Ketqua")){
                     if(output[1].equals("true")){
                          mointerface();
                     }else{
                        
                         baoban();
                     }
                     
                     
                 }
                 
                 // Lấy danh sách online và off
                if(output[0].equals("Online") || output[0].equals("OnlineClose")){
                   Online onllist = new Online(response);
                    onllist.start();
                   
                }else{
                

                display = display +"\n"+ response;
                txtdisplay.setText(display);
                
//                // Kiểm tra ngắt kết nối
//                String kttrangthai[] = response.split(":");
//             
//                if(kttrangthai[1].equals(" bye")){
//                    System.out.println(kttrangthai[1]);
//                    rw.close();
//                    anform();
//                }
                
                }
                
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}   
private class Online extends Thread {
   
    private String clientonl;
    public Online(String clientonl){
        this.clientonl=clientonl;
    }
    @Override
    public void run(){   
        ktinput();
    }
    
  public void ktinput(){
     String[] output = clientonl.split("-");
       if(output[0].equals("Online")){
           String[] output2 = clientonl.split(" ");
           addlistonl(output2[3]);
 
       }
        if(output[0].equals("OnlineClose")){
               String[] output3 = clientonl.split(" ");
                removelistonl(output3[1]);
       }
      
}
    
}  

public void addlistonl(String listdetail){
        modellistonl.addElement(listdetail);
        txtlistonl.setModel(modellistonl);
              
    }
public void removelistonl(String listdetail){
    modellistonl.removeElement(listdetail);
    txtlistonl.setModel(modellistonl);
}
public void mointerface(){
        txtdisplay.setVisible(true);
        txtsend.setVisible(true);
        btnsend.setVisible(true);
        txtdisplay.setEnabled(true);
        txtlistonl.setVisible(false);
       
}

//Báo người dùng bận
void baoban(){
    JOptionPane.showMessageDialog(this, "Người dùng bận !!!");
}
void anform(){
    this.dispose();
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtdisplay = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        txtname = new javax.swing.JLabel();
        txtsend = new javax.swing.JTextField();
        btnsend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtlistonl = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtdisplay.setColumns(20);
        txtdisplay.setRows(5);
        jScrollPane1.setViewportView(txtdisplay);

        jTextField1.setText("Name:");

        txtsend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsendKeyPressed(evt);
            }
        });

        btnsend.setText("Send");
        btnsend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsendMouseClicked(evt);
            }
        });

        txtlistonl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtlistonlMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txtlistonl);

        jButton1.setText("Menu");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtsend, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsend)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsend))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      
        txtname.setText(namenhan);
         try {
        client a = new client("localhost",2313);
            a.execute();
             
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server chua duoc khoi tao !!!");
        }
        
        
    }//GEN-LAST:event_formWindowOpened

    
    
    
    private void txtlistonlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtlistonlMouseClicked
     
       
        
        
        int a = JOptionPane.showConfirmDialog(this, "Are you sure ?");
        namegui=txtlistonl.getSelectedValue();
        if (a==JOptionPane.YES_OPTION) 
        {
        writer.println("Usernhan "+namegui);
       // new Singlechatform(namegui, socket).setVisible(true);
        }else {
            
           if(a==JOptionPane.NO_OPTION)
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
        
        
        
        
        
     //   writer.println(txtlistonl.getSelectedValue());
     //   System.out.println(socket);
      //  new Singlechatform(txtlistonl.getSelectedValue(),socket).setVisible(true);
       // this.dispose();
        
        
        
    }//GEN-LAST:event_txtlistonlMouseClicked

    private void btnsendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsendMouseClicked
   //    writer.print(tennguoinhan);
            String text=txtsend.getText();
            writer.println(text);
            txtsend.setText("");
            if(text.equals("bye")){
               rw.close();
               this.dispose();
            }
              display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
           if(rw.ingetname()!=null){
          txtdisplay.setText(display);
           }
    }//GEN-LAST:event_btnsendMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       
        this.dispose();
        rw.close();
        new Menu(namenhan, this.socketlogin).setVisible(true);
        
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void txtsendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsendKeyPressed
       
           if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                  
              String text=txtsend.getText();
                writer.println(text);
                txtsend.setText("");
                    if(text.equals("bye")){
                       rw.close();
                       this.dispose();
                    }
             display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
                    if(rw.ingetname()!=null){
                   txtdisplay.setText(display);
                    }
               
               
        }
        
        
        
    }//GEN-LAST:event_txtsendKeyPressed

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
            java.util.logging.Logger.getLogger(Chatsingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chatsingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chatsingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chatsingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chatsingle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsend;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea txtdisplay;
    private javax.swing.JList<String> txtlistonl;
    private javax.swing.JLabel txtname;
    private javax.swing.JTextField txtsend;
    // End of variables declaration//GEN-END:variables
}
