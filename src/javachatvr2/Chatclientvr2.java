/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatvr2;

import Jamechat.Clickprogram;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import jdk.nashorn.internal.ir.BreakNode;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author LUUQUAN-PC
 */
public class Chatclientvr2 extends  javax.swing.JFrame{


    public Chatclientvr2() {
        initComponents();
        
    }
    public Chatclientvr2(String ten) {
        this.nameuser=ten;
         initComponents();
    }
    
    
    String nameuser="";
     ReadThread  re;
     WriteThread rw;
     String userName;
     boolean btnclick=false;
     String message=null;
     String text;
     //Display là hệ thống chuổi của textarea 
     String display="";
     
     private PrintWriter writer;
        public client a;
      private List<String> listonl = new ArrayList<String>();
      //Chuỗi trong List
      private DefaultListModel<String> modellistonl= new DefaultListModel<>();
      Socket socket;
    public class client{
    private String hostname;
    private int port;
    private String userName;
     
    public client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
      
    }
   
    
    public void execute() {
        try {
           socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");
      
            rw = new WriteThread(socket, this);
            rw.start();
            re =new  ReadThread(socket, this);
            re.start();
      
           
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(jbn, "Không tìm thấy server");
            System.exit(0);
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
    //Thiết lập tên của client này
    void setUserName(String userName) {
        this.userName = userName;
    }
    //Lấy tên của client này 
    String getUserName() {
        return this.userName;
    }
    public void inread(String aString){
        txt.setText(aString);
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
            //Dùng writer để gửi thông điệp
            //Sử dụng ở Btn
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
    
    // Hàm lấy tên của client này
    public String ingetname(){
        return client.getUserName();
    }
   @Override
    public void run() {
        // Gọi hàm và thiết lập tên cho client này bên trên
        client.setUserName(nameuser);
        // Gửi cho server biết tên của mình
        writer.println(nameuser);
 
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
        txtonl.setText("Nobody !!!");
        while (true) {
            try {
               
                String response = reader.readLine();
                // Phân tích dữ liệu đầu vào 
                 
                 String[] output = response.split("-");
                //New Single
                
//                if(output[0].equals("Singlechat")){ 
//          
//                    
//                    System.out.println("Ten thằng yêu cầu single: " + output[1]);
//                    new Singlechatform(output[1],socket).setVisible(true);
//  
//                  
//                }
                
                
                 
                 //New single
                 if(output[0].equals("Online") || output[0].equals("OnlineClose")){
                    Online onllist = new Online(response);
                    onllist.start();
                   
                }
                else{
                    
                    
          //      while(response!="True" || response!="False"){ 
                    
                System.out.println("\n" + response);
                display = display +"\n"+ response;
                txtread.setText(display);
          //      }
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
 // Luồng online để phân tích dữ liệu người truy cập
private class Online extends Thread {
   
    private String clientonl;
    public Online(String clientonl){
        this.clientonl=clientonl;
    }
    @Override
    public void run(){   
        ktinput();
        inlist in = new inlist(listonl);
        in.start();
    }
    
  public void ktinput(){
     String[] output = clientonl.split("-");
       if(output[0].equals("Online")){
           String[] output2 = clientonl.split(" ");
           addlistonl(output2[3]);
 
       }
        if(output[0].equals("OnlineClose")){
               String[] output3 = clientonl.split(" ");
                listonl.remove(output3[1]);
                removelistonl(output3[1]);
       }
      
}
    
}
//Thêm vào danh sách người đã truy cập
public void addlistonl(String listdetail){
              listonl.add(listdetail);  
              modellistonl.addElement(listdetail);
              txtlistonline.setModel(modellistonl);
              
    }
//Loại bỏ những người không truy cập
public void removelistonl(String listdetail){
    listonl.remove(listdetail);
    modellistonl.removeElement(listdetail);
    txtlistonline.setModel(modellistonl);
}
//In ra textarea người hiện tại đang truy cập 
private class inlist extends Thread{
    List<String> listonline;
    
    public inlist(List<String> listonl){
        this.listonline=listonl;
     
        
    }

    @Override
    public void run(){
        
             String list="";
             try {
                 if(listonline.size()==0){
                     txtonl.setText("Nobody !!!");
            
                 }
                 else{
               
                 for(int i=0;i<listonline.size();i++){
               
                    list = list + listonline.get(i) +"\n";
                if(list!=""){
                 txtonl.setText(list);
                
                 }
                 }
                 
             }
                 
                 
         
        } catch (Exception e) {
        }
            

    }
    
}
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        txtt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtread = new javax.swing.JTextArea();
        jbn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtonl = new javax.swing.JTextArea();
        txtchatsingle = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtclasv2 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtsingle = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtlistonline = new javax.swing.JList<>();
        txtten = new javax.swing.JLabel();

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttKeyPressed(evt);
            }
        });

        txtread.setColumns(20);
        txtread.setRows(5);
        jScrollPane2.setViewportView(txtread);

        jbn.setText("Send");
        jbn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbnMouseClicked(evt);
            }
        });

        txtonl.setColumns(20);
        txtonl.setRows(5);
        jScrollPane3.setViewportView(txtonl);

        txtchatsingle.setText("Log out");
        txtchatsingle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtchatsingleMouseClicked(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        txtclasv2.setColumns(20);
        txtclasv2.setRows(5);
        jScrollPane4.setViewportView(txtclasv2);

        jTabbedPane1.addTab("Class", jScrollPane4);

        txtsingle.setColumns(20);
        txtsingle.setRows(5);
        jScrollPane5.setViewportView(txtsingle);

        jTabbedPane1.addTab("Chat single", jScrollPane5);

        jTextField1.setText("jTextField1");

        txtlistonline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtlistonlineMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(txtlistonline);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTabbedPane1)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(txtt, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtchatsingle, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(txtten, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbn)
                    .addComponent(txtchatsingle))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtten.setText(nameuser);
        try {
         client a = new client("localhost",9999);
            a.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server chua duoc khoi tao !!!");
        }
       
       
    }//GEN-LAST:event_formWindowOpened

    private void jbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbnMouseClicked
         
         
                text = txtt.getText();
                writer.println(text);
                 txtt.setText("");
           if(text.equals("bye")){
               rw.close();
               this.dispose();
            }
            
            display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
           if(rw.ingetname()!=null){
          txtread.setText(display);
           }

    }//GEN-LAST:event_jbnMouseClicked
    
    private void txttKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                  
                text = txtt.getText();
                writer.println(text);
                 txtt.setText("");
           if(text.equals("bye")){
               rw.close();
               this.dispose();
            }

            display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
           if(rw.ingetname()!=null){
          txtread.setText(display);
           }
        }
    }//GEN-LAST:event_txttKeyPressed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        
        JOptionPane.showMessageDialog(this, "Hello");
        
        
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void txtchatsingleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtchatsingleMouseClicked
        this.hide();
        rw.close();
       new LoginChatclientvs_2().setVisible(true);
       
    }//GEN-LAST:event_txtchatsingleMouseClicked
    // Sự kiện nhấn item của list online  
    private void txtlistonlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtlistonlineMouseClicked
        
        
    //    new Chatsingle(txtlistonline.getSelectedValue()).setVisible(true);
        
        
        
    }//GEN-LAST:event_txtlistonlineMouseClicked

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
            java.util.logging.Logger.getLogger(Chatclientvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chatclientvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chatclientvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chatclientvr2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chatclientvr2().setVisible(true);
              //  try {
              //  new Chatclientvr2().main();
               // } catch (Exception e) {
               // }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbn;
    private javax.swing.JTextArea txt;
    private javax.swing.JButton txtchatsingle;
    private javax.swing.JTextArea txtclasv2;
    private javax.swing.JList<String> txtlistonline;
    private javax.swing.JTextArea txtonl;
    private javax.swing.JTextArea txtread;
    private javax.swing.JTextArea txtsingle;
    private javax.swing.JTextField txtt;
    private javax.swing.JLabel txtten;
    // End of variables declaration//GEN-END:variables
}
