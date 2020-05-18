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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author LUUQUAN-PC
 */
public class Singlechatform extends javax.swing.JFrame {

    /**
     * Creates new form Singlechatform
     */
    private String namenhan = ""; 
    private Socket socket;
    private DefaultListModel<String> modellistonl= new DefaultListModel<>();
    public Singlechatform(String namenhan, Socket socket) {
        initComponents();
        this.namenhan=namenhan;
        this.socket=socket;
    }
    private ReadThread re;
    private WriteThread rw;
    private PrintWriter writer;
    private String display="";
    public class client{
        Socket socketreal;
        public client(Socket socket){
            this.socketreal=socket;
            
        }
       private String userName;
       public void execute(){
        try {
            writer.println("Singlechat-"+namenhan);
            rw= new  WriteThread(socket, this);
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
       //  System.out.println("TỚi đâyyyyy"); 
        client.setUserName(namenhan);
     //   writer.println(namenhan);
 
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
                // Phân tích dữ liệu đầu vào 
                   
//                 String[] output = response .split("-");
//                if(output[0].equals("Online") || output[0].equals("OnlineClose")){
//                   Online onllist = new Online(response);
//                    onllist.start();
//                   
//                }
//                else{
          //      while(response!="True" || response!="False"){ 
                    //Thiết lập tin nhắn hiện thị
             //   System.out.println("\n" + response);
                display = display +"\n"+ response;
                txtdisplay.setText(display);
          //      }
              //  }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
    
//    
// private class Online extends Thread {
//   
//    private String clientonl;
//    public Online(String clientonl){
//        this.clientonl=clientonl;
//    }
//    @Override
//    public void run(){   
//        ktinput();
//    }
//    
//  public void ktinput(){
//     String[] output = clientonl.split("-");
//       if(output[0].equals("Online")){
//           String[] output2 = clientonl.split(" ");
//           addlistonl(output2[3]);
// 
//       }
//        if(output[0].equals("OnlineClose")){
//               String[] output3 = clientonl.split(" ");
//                removelistonl(output3[1]);
//       }
//      
//}
//    
//}  
//    
//public void addlistonl(String listdetail){
//        modellistonl.addElement(listdetail);
//    //    txtlistonl.setModel(modellistonl);
//              
//    }
//public void removelistonl(String listdetail){
//    modellistonl.removeElement(listdetail);
//   // txtlistonl.setModel(modellistonl);
//}
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
        txtsend = new javax.swing.JTextField();
        btnsend = new javax.swing.JButton();
        txtname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtdisplay.setColumns(20);
        txtdisplay.setRows(5);
        jScrollPane1.setViewportView(txtdisplay);

        btnsend.setText("Send");
        btnsend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsendMouseClicked(evt);
            }
        });

        txtname.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtsend, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnsend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsend))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsendMouseClicked
        String text;
        writer.println(txtsend.getText());
               text = txtsend.getText();
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    //   writer.println("Singlechat-"+namenhan);
        txtname.setText("Gui den" + namenhan);
        try {
            client a=new client(socket);
            a.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server chua duoc khoi tao !!!");
        }
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
            java.util.logging.Logger.getLogger(Singlechatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Singlechatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Singlechatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Singlechatform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             //   new Singlechatform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtdisplay;
    private javax.swing.JLabel txtname;
    private javax.swing.JTextField txtsend;
    // End of variables declaration//GEN-END:variables
/*  Đầu tiên chọn chat single hiện nên menu client đang online chọn 1 clinet nào đó đồng thời gửi thông điệp "Singlechat" 
    Tới userThread, userThread phân tích => tạo ra một luồng chat single mới là UserThreadsingle 
    UserThreadsingle đầu tiên gửi thông điệp đến người nhận " Tôi muốn kết nói để chat riêng tư với bạn" thôi qua Boarchcasdsingle
    mà Borachsingle - Đầu tiên dùng tên người nhận để xác định index trong mang username, lấy được index, sử dụng indexx đó với mẩng UserThread
    lấy được UserThread từ tên của người nhận, sau đó UserThreadsingle gửi thông điệp  
    // Sử dụng socket của chatsingle cho singlechatform nhưng đang bị lỗi không gửi được
    =>Socket truyền từ client qua singlechatfrom và từ Chatsingle qua singlechatformm (Kiểm tra lại)
            
*/
}
 
