/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jamechat;

import java.awt.ComponentOrientation;
import java.awt.TextArea;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author LUUQUAN-PC
 */
public class Khungchat extends javax.swing.JFrame {

    /**
     * Creates new form Khungchat
     */
    client Client = null;
      String gui=""; 
    public Khungchat() {
        initComponents();
        txtchat.setEditable(false);
    
    }
   
//public class online extends Thread{
//    private final List<String> name2 = new ArrayList<String>();
//    private String onlineS=null;
//    //private String onlineS2=null;
//    
//    public online(String nameonline) throws  UnknownHostException, IOException{
//        //name2.remove(onlineS);
//        onlineS = nameonline;
//  //      txtonline.setText("");
//    }
//    
//    @Override
//    public void run(){  
////        //  for (int i = 0; i < name2.size(); i++) {
////           // 
////       name2.clear();
////       name2.add(onlineS);
////       String asString="";
////       int a=name2.size();
////       //a=name2.size();
//////        for (int i = 0; i < name2.size(); i++) {
//////
//////         
//////         asString = name2.get(i);
//////       
//////        }
////     //   txtonline.setText(asString);
//        System.out.println(onlineS);
//         
//       // 
//           
//    
//    }
//}
public class client extends Thread {
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;

        public client(String serverAddress, int serverPort) throws UnknownHostException, IOException {
        socket = new Socket(InetAddress.getByName(serverAddress), serverPort,InetAddress.getByName(serverAddress),9999);
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    private void send(String message) throws IOException {
        out.write(message.getBytes());
    }

    @Override
    public void run() {
        byte[] buffer = new byte[2048];
        try {
            while (true) {
                int receivedBytes = in.read(buffer);
                if (receivedBytes < 1)
                    break;
               String message = new String(buffer, 0, receivedBytes);
                System.out.println(message);
//                online ol = new online(gui);
//                ol.start();
          //     txtchat.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
               txtchat.setText(gui);
                System.out.println(message);
            }
        } catch (IOException e) {
        }
        close();
        System.exit(0);
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
}
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnsend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtchat = new javax.swing.JTextArea();
        txtsend = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtonline = new javax.swing.JTextArea();
        txtdcnhan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnsend.setText("Send");
        btnsend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsendMouseClicked(evt);
            }
        });

        txtchat.setColumns(20);
        txtchat.setRows(5);
        jScrollPane1.setViewportView(txtchat);

        jLabel1.setText("Online");

        txtonline.setColumns(20);
        txtonline.setRows(5);
        jScrollPane2.setViewportView(txtonline);

        jLabel2.setText("Địa chỉ nhận");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdcnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsend)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsend, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdcnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsend))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsendMouseClicked
      //    Scanner scanner = new Scanner(System.in);
        
        try {

            String message = txtsend.getText().trim();    
            Client.send(message);
            txtsend.setText("");
          //  txtchat.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         //  textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
             gui = gui + message +"\n";
            txtchat.setText(gui);
        } catch (IOException e) {
        }
    
    }//GEN-LAST:event_btnsendMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
          
        String username = "Luu Quan";
        try {
             Client = new client("localhost", 8788);
             Client.send(username);
             Client.start();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       // Client a 
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Khungchat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtchat;
    private javax.swing.JTextField txtdcnhan;
    private javax.swing.JTextArea txtonline;
    private javax.swing.JTextField txtsend;
    // End of variables declaration//GEN-END:variables
}
