/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPclassroom;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.*;

public class client extends Thread {
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;

        public client(String serverAddress, int serverPort) throws UnknownHostException, IOException {
            String localadd = "8.8.8.8";
            String lo ="localhost";
        socket = new Socket(InetAddress.getByName(serverAddress), serverPort,InetAddress.getByName(serverAddress),99);
       // Socket socket=new Socket(InetAddress.getByName("Localhost"), serverPort,InetAddress.getByName("Localhost"), serverPort);
     //     socket = new Socket
 //       socket = new Socket(InetAddress.getByName("Luuquan"), serverPort);
   //     Socket aSocket= new Socket(ia, serverPort, ia1, serverPort)
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
        client Client = null;
     //   String username = "LuuQuan";
     //   String userNhan = "7777";
        try {
            Client = new client("localhost", 8888);
      //      Client.send(username);
         //   Client.send(userNhan);
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