/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatvr2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author LUUQUAN-PC
 */
public class Client1 extends javax.swing.JFrame {

    /**
     * Creates new form Client1
     */
      public Client1() {
        initComponents();
    }
    public class Clent11{
  
 
   // public class ChatClient{
//    String a=null;
//    private String hostname;
//    private int port;
//    private String userName;
//    Socket socket;
 
//    public Client11(String hostname, int port) {
//        this.hostname = hostname;
//        this.port = port;
//    }
 
    public void execute() {
          WriteThread22 wr = new WriteThread22();
          wr.run();
//        try {
//            socket = new Socket(hostname, port);
//            System.out.println("Connected to the chat server");
//        //     new ReadThread22(socket, this).start();
//          //   new WriteThread22(socket, this).start();
//          WriteThread22 wr = new WriteThread22();
//          wr.start();
//        } catch (UnknownHostException ex) {
//            System.out.println("Server not found: " + ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println("I/O Error: " + ex.getMessage());
//        }
// 
    }
 
//    void setUserName(String userName) {
//        this.userName = userName;
//    }
// 
//    String getUserName() {
//        return this.userName;
//    }
    //}
    // Lop doc du lieu
//    public class ReadThread22 extends Thread {
//    private BufferedReader reader;
//    private Socket socket;
//    private Client1 client;
//    private InputStream input; 
// 
//    public ReadThread22(Socket socket, Client1 client) {
//        this.socket = socket;
//        this.client = client;
// 
//        try {
//            input = socket.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(input));
//        } catch (IOException ex) {
//            System.out.println("Error getting input stream: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
//    @Override
//    public void run() {
//          byte[] buffer = new byte[2048];
//          
//        while (true) {
//            try {
//                 int receivedBytes = input.read(buffer);
//                 if(receivedBytes<0){
//                     break;}
//                 String message = new String(buffer, 0, receivedBytes);
//               
//                 
//                 String response = reader.readLine();
//                
//                 
//                System.out.println("\n" + response);
//                // a = response;
//              //  System.out.println(a);
//                System.out.println("\n___" +response);
//               txtnhap.setText(message);
//                
//                // prints the username after displaying the server's message
//                if (client.getUserName() != null) {
//                    System.out.print("[" + client.getUserName() + "]: ");
//                }
//            } catch (IOException ex) {
//                System.out.println("Error reading from server: " + ex.getMessage());
//                ex.printStackTrace();
//                break;
//            }
//        }
//        
//    }
//}
    // Gui du lieu di
    }
    public class WriteThread22 extends Thread {
//    private PrintWriter writer;
//    private Socket socket;
//    private Client1 client;
// 
//    public WriteThread22(Socket socket, Client1 client) {
//        this.socket = socket;
//        this.client = client;
// 
//        try {
//            OutputStream output = socket.getOutputStream();
//            writer = new PrintWriter(output, true);
//        } catch (IOException ex) {
//            System.out.println("Error getting output stream: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
    @Override 
    public void run() {
            String c="Luuquan";
           txtnhap.setText(c);
           System.out.println("Luuquan");
//         Scanner sc= new Scanner(System.in);
//        System.out.println("Nhap ten cua ban: ");
//        a="Nhap ten cua ban";
//        String c="LHQ";
//        try {
//         
//        txtnhap.setText(c);
//        
//        } catch (Exception e) {
//            System.out.println("Co bi gi k");
//        }
//        System.out.println("_________");
//        String userName = sc.nextLine();
//    
//       client.setUserName(userName);
//        writer.println(userName);
// 
//        String text;
// 
//        do {
//           // System.out.println("Nhap tin nhan: "+"\n");
//            text = sc.nextLine();
//            writer.println(text);
// 
//        } while (!text.equals("bye"));
// 
//        try {
//            socket.close();
//        } catch (IOException ex) {
// 
//            System.out.println("Error writing to server: " + ex.getMessage());
//        }
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
        txtdoc = new javax.swing.JTextArea();
        txtnhap = new javax.swing.JTextField();
        btnsend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtdoc.setColumns(20);
        txtdoc.setRows(5);
        jScrollPane1.setViewportView(txtdoc);

        btnsend.setText("Send");
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
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsend, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsend))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsendMouseClicked
        
         
        
        
    }//GEN-LAST:event_btnsendMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      //  try {
         String hostname = "localhost";
            int port = 8888;
        //Client1 client = new Client1(hostname, port);
      
     //   } catch (Exception e) {
      //  }
       Clent11 client = new Clent11();
         client.execute();
 
        
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client1().setVisible(true);
                  
            }
        });
        
       
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtdoc;
    private javax.swing.JTextField txtnhap;
    // End of variables declaration//GEN-END:variables
}
