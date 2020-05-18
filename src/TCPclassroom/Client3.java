/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPclassroom;


import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.text.Element;

public class Client3 extends Thread {
    private final List<String> name2 = new ArrayList<String>();
    private final List<String> name3 = new ArrayList<String>();
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;

    public Client3(String serverAddress, int serverPort) throws UnknownHostException, IOException {
       socket =  new Socket(InetAddress.getByName(serverAddress), serverPort,InetAddress.getByName(serverAddress), 1111);
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    private void send(String message) throws IOException {
        out.write(message.getBytes());
    }

    @Override
    public void run() {
        byte[] buffer = new byte[2048];
         List<String> wst = new ArrayList<String>();
         List<String> wstafter = new ArrayList<String>();
        try {
            while (true) {
                int receivedBytes = in.read(buffer);
                if (receivedBytes < 1)
                    break;
                String message = new String(buffer, 0, receivedBytes);
               // for(String element:name2){
                      if(!name2.contains(message)){
                           name2.add(message);
                           
                      
                }
                System.out.println(name2);
//                char character;
//             //   for (int i=0;i<=message.length();i++){
//                    character = message.charAt(0);
//                //    name2.clear();
//                    if(Character.toString(character).equals("/")) {
//                        
//                        name2.add(message);
//                       System.out.println(name2);
//                       name2.remove(message);
////                        for (int i = 0; i < name2.size(); i++) {
////                        System.out.println(name2.get(i));
////                        }
////                        wst.add(message);
////                        for (String Element :wst){
////                            if(!wstafter.contains(Element))
////                                wstafter.add(Element);
////                        }
////                        for(int g=0;g<wstafter.size();g++){
////                        System.out.print(wstafter.get(g)+"\n");
////                        }
//                    } 
//                      
//                
//               
//                  
//                  
//                  System.out.println();
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

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        Client3 Client = null;
        String username = "A";
        try {
            Client = new Client3("localhost", 8888);
            Client.send(username);
            Client.start();
            while (true) {
                String message = scanner.nextLine();
                Client.send(message);
            }
        } catch (IOException e) {
        }
        if (Client != null)
            Client.close();
        scanner.close();
    }
}