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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.BreakNode;
/**
 *
 * @author LUUQUAN-PC
 */
public class Chatclientvs_3_1 extends javax.swing.JFrame {

    /**
     * Creates new form Chatclientvs_3_1
     */
    public Chatclientvs_3_1() {
        initComponents();
    }
    Chatclientvs_3_1.ReadThread  re;
     Chatclientvs_3_1.WriteThread rw;
     String userName;
     boolean btnclick=false;
     String message=null;
     String text;
     String display="";
     String display1;
     private PrintWriter writer;
      client2 a;
      private List<String> listonl = new ArrayList<String>();
    
     public class client2{
    private String hostname;
    private int port;
    private String userName;
     
    public client2(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
      
    }
   
    
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");
            rw = new Chatclientvs_3_1.WriteThread(socket, this);
             rw.start();
            re =new  Chatclientvs_3_1.ReadThread(socket, this);
            re.start();
      
           
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
 
    void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
    public void inread(String aString){
     //   txt.setText(aString);
    }
    
    
    }
     
      private class WriteThread extends Thread {
   // private PrintWriter writer;
    private Socket socket;
    private Chatclientvs_3_1.client2 client;
 
    public WriteThread(Socket socket, Chatclientvs_3_1.client2 client) {
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
          
// 
//        Console console = System.console();
//      Hi
//        String aString="LLLL";
     //   String a="Nhap ten:";
    //    txtt.setText(a);
     //   inread(aString);
      //  Scanner sc= new Scanner(System.in);
       
        //if(client.getUserName()==null){
      //  System.out.println("__________");
    //    txtread.setText("Nhap ten cua ban: ");
       
        userName = "Tao ne";//txtt.getText();
        client.setUserName(userName);
        writer.println(userName);
        //}
      //  txtt.setText("");
        
        
        //do {  
        
         //   System.out.println("Nhap tin nhan: "+"\n");
          
         //   writer.println(text);
           
            
//         
//        if(text.equals("bye")){
// 
//        try {
//            socket.close();
//        } catch (IOException ex) {
// 
//            System.out.println("Error writing to server: " + ex.getMessage());
//        }
//    
//         }
    }
    
}

    
 private class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    //*Sai cho nay ne//
    private Chatclientvs_3_1.client2 client;
 
    public ReadThread(Socket socket,Chatclientvs_3_1.client2 client) {
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
                   
                 String[] output = response .split("-");
                if(output[0].equals("Online") || output[0].equals("OnlineClose")){
                    Chatclientvs_3_1.Online onllist = new Chatclientvs_3_1.Online(response);
                    onllist.start();
                   
                }
                else{
                 
                    
                System.out.println("\n" + response);
                display = display +"\n"+ response;
                txtread.setText(display);
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
        inlist in = new inlist(listonl);
        in.start();
    }
    
  public void ktinput(){
     String[] output = clientonl.split("-");
       if(output[0].equals("Online")){
           String[] output2 = clientonl.split(" ");
     //      System.out.println(output2[3]);
           addlistonl(output2[3]);
 
       }
        if(output[0].equals("OnlineClose")){
               String[] output3 = clientonl.split(" ");
                listonl.remove(output3[1]);
                removelistonl(output3[1]);
       }
      
}
    
}//}
public boolean kt(String cankt){
    boolean a=false;
        for(String asd:listonl){
            System.out.print(listonl);
            if (asd.equals(cankt)){
                a=false;
            }else{
                a=true;
            }
        }
    
    
    return a; 
    
}
public void addlistonl(String listdetail){
           
            listonl.add(listdetail);
            System.out.println(listdetail);
           
    
}
public void removelistonl(String listdetail){
     
    listonl.remove(listdetail);
    
}
/*
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
    
}    */
     
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
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtread = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtonl = new javax.swing.JTextArea();
        txtsend = new javax.swing.JTextField();
        btnsend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        txtread.setColumns(20);
        txtread.setRows(5);
        jScrollPane1.setViewportView(txtread);

        txtonl.setColumns(20);
        txtonl.setRows(5);
        jScrollPane2.setViewportView(txtonl);

        txtsend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsendKeyPressed(evt);
            }
        });

        btnsend.setText("send");
        btnsend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsendMouseClicked(evt);
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
                        .addComponent(txtsend, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsend))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsend))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        
        Chatclientvs_3_1.client2 a = new Chatclientvs_3_1.client2("localhost",8788);
        a.execute();


       
    }//GEN-LAST:event_formWindowOpened

    private void btnsendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsendMouseClicked
       
           text = txtsend.getText();
                writer.println(text);
                 txtsend.setText("");
           if(text.equals("bye")){
               rw.close();
               System.exit(0);
            }

            display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
           if(rw.ingetname()!=null){
          txtread.setText(display);
           }
        
        
        
    }//GEN-LAST:event_btnsendMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
         // Bo
    }//GEN-LAST:event_formKeyPressed

    private void txtsendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsendKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER){
                  
           text = txtsend.getText();
                writer.println(text);
                 txtsend.setText("");
           if(text.equals("bye")){
               rw.close();
               System.exit(0);
            }

            display = display  +"\n"+"[" + rw.ingetname() + "]:" +  text;
           if(rw.ingetname()!=null){
          txtread.setText(display);
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
            java.util.logging.Logger.getLogger(Chatclientvs_3_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chatclientvs_3_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chatclientvs_3_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chatclientvs_3_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chatclientvs_3_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtonl;
    private javax.swing.JTextArea txtread;
    private javax.swing.JTextField txtsend;
    // End of variables declaration//GEN-END:variables
}
