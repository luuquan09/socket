/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPclassroom;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.jar.Attributes;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Server {
    private final ServerSocket serverSocket;
    private final List<Worker> workers = new ArrayList<Worker>();
     private final List<String> name2 = new ArrayList<String>();
    //private final List<String> name = new ArrayList<String>();
    //private final List<Worker><Integer> t =new ArrayList<Worker>()<>();
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
        // Chờ để kết nối khi đồng ý kết nối tạo một worker bao gom ca 
        //socket được gửi đến thêm woker vào danh sách và bắt đầu woker
        //Quá trình này lặp lại nhiều lần cho nhiều client
    private void waitForConnection() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            Worker worker = new Worker(socket);
            addWorker(worker);
//            String b = socket.getInetAddress().toString();
//            int a = socket.getPort();
            worker.start();
//           name.add(b+":"+a);
           // System.out.println(b+":"+a);
        
//                for (int j=0;j<name.size();j++){
//                  //  for (int i=0;i<workers.size();i++){
//                         aa = name.get(j);    
//                         
//                 
//               //}
//                }
//                Worker w=null;
//                for (int i=0;i<workers.size();i++){
//                     w = workers.get(i);   
//                }
//                System.out.print(aa+"\n");
//                w.send(aa);
//                //System.out.print(aa+"\n");
////               System.out.print(workers.size());
////                
            
            
        }
    }
    //Them woker moi vao list
    private void addWorker(Worker worker) {
        synchronized (this) {
            workers.add(worker);
     //       System.out.print(worker);
        }
    }
    //Xoa woker ra khoi list va dong lai worker đó
    private void removeWorker(Worker worker) {
        synchronized (this) {
            workers.remove(worker);
            worker.close();
        }
    }
    //Gửi thông điệp cho cac client da ket noi va khong gui lai cho nguoi gui
    //Nếu worker bị lỗi sẽ giảm kích thước worker.size và đóng worker đó
    //Dùng hàm for để gửi hết tắt cả worker trong list
    private void broadcastMessage(Worker from, String message) {
        synchronized (this) {
            message = String.format("%s: %s", from.username, message);
            for (int i = 0; i < workers.size(); i++) {
                Worker worker = workers.get(i);
                
                if (!worker.equals(from)) {
                    try {
                        worker.send(message);
                    } catch (IOException e) {
                        workers.remove(i--);
                        worker.close();
                    }
                }
            }
        }
    }
    
      private void broadcastMessage2(Worker from, String message) {
        synchronized (this) {
            message = String.format("%s: %s", from.username, message);
            for(int i=0;i < workers.size();i++){
                Worker worker = workers.get(i);
               
                if(!worker.equals(from)){
                if (worker.username.equals(from.usernamenhan)){
                    try {
                        System.out.println(worker.username+"");
                       // worker.send(message);
                    } catch (Exception e) {
                         workers.remove(i--);
                        worker.close();
                    }
                }
                }
                
            }
                
            
            /*  for (int i = 0; i < workers.size(); i++) {
                Worker worker = workers.get(i);
                
                if (!worker.equals(from)) {
                   try {
                        worker.send(message);
                    } catch (IOException e) {
                        workers.remove(i--);
                        worker.close();
                    }
                }
            }*/
        }
    }
    
    //Tao lop Worker sư dung ham xay dung sao chep doi tuòn
    //Đọc dữ liệu từ client input và xuất dữ liệu ra khỏi client outinput 
    //Thêm vào tên username cho người gửi 
     private class onlinename extends Thread{
         private final List<String> name = new ArrayList<String>();
       //  private final List<String> name3 = new ArrayList<String>();
        
         private String nameeString="";
         private String aString="";
           Worker w = null;
        
      //   String aString2=""; 
       //  private String aString2="";
         private String wt = "sd";
         Worker worker=null,worker2=null;
         public onlinename(String nameonline) throws  UnknownHostException, IOException{
          nameeString=nameonline;
          name.add(nameeString);
         }
       
         public void sString(){
              String aString2="";
          //   workers.clear();
             //System.out.println(workers.size());
             String[] b = new String[name.size()];
            // int a=0;
             for (int i = 0; i < name.size(); i++) {
                    b[i]=name.get(i);
             }
             for (int j=0; j < name.size();j++){
              
             aString=b[j];
             }
               
             System.out.println(aString);
             
               
             for (int i = 0; i < workers.size(); i++) {
                w = workers.get(i);        
                 if(name2.size()>0){
                     
                                try {
                                  
                                   for(int k=0;k<name2.size();k++){
                                               w.send(name2.get(k));
                                   }
                                } catch (IOException ex) {
                              //      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                }

                             }         
                            try {

                                  w.send(aString);    

                            } catch (IOException ex) {

                            }
                 
             }
              name2.add(aString);
               //aString2=aString;
             System.out.println("2________"+name2);
              
             
//            x`
//             try {
//                 w.send(nameeString);
//             } catch (IOException ex) {
//          //       Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//             }
         }
          //   for (int i=0;i<name.size();i++){
                 //   name.clear();
              //   System.out.print(name);
                // aString=name;
          //   nameeString = name.get(i);
             //if(nameeString.equals(name.get(i))){
                 
       //  }
          //   }
         
         @Override
         public void run(){
                sString();
//                  Worker a =null;
//             //   for(int i=0;i < workers.size();i++){
//           //     Worker worker = workers.get(i);
//                    try {
//               //         for (int j = 0; j < name.size(); j++) {
//                            
//                        System.out.println(name.size());
//                        worker.send(wt);
//                        
//                    } catch (IOException ex) {
//                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                
//        
     }
}
    private class Worker extends Thread {
        private final Socket socket;
        private final InputStream in;
        private final OutputStream out;
        private String username = null;
        private String usernamenhan=null;
        
       // int gui = 0;
        
//
        
       // public themdiachi(Socket socket) throws  IOException{
            
            
        //}
        public Worker(Socket socket) throws IOException {
            this.socket = socket;
            in = socket.getInputStream();
            out = socket.getOutputStream();
        }
 
        // Ham để gửi thông điệp
        private void send(String message) throws IOException {
            out.write(message.getBytes());
        }
        //Hàm để nhận thông điệp và truyền cho các client khác 
        @Override
        public void run() {
            String b = socket.getInetAddress().toString();
            int a = socket.getPort();
            //Tao vung nho cho thông điệp được gửi đến 
            byte[] buffer = new byte[2018];
            try {
                while (true) {
                    //Nhan thông điệp từ client
                    int receivedBytes = in.read(buffer);
                    if (receivedBytes < 1)
                        break;
                    //Chuyen thông điệp kiểu byte thành kiểu String để truyen
                    // cho cac client 
                    String message = new String(buffer, 0, receivedBytes);
                        onlinename oln = new onlinename(b+":"+a);
                        oln.start();
                    if (username == null)
                    {  
                        username=message;
 
                    }
                    else
                        broadcastMessage(this, message);
                }
              //  System.out.println(username+usernamenhan);
            } catch (IOException e) {
            }
            removeWorker(this);
        }

        private void close() {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        try {
           // Worker aWorker=new Worker(3393);
            Server server = new Server(8888);
            server.waitForConnection();
        } catch (IOException e) {
        }
    }

     }
