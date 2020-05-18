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

public class client2 extends Thread {
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;

    public client2(String serverAddress, int serverPort) throws UnknownHostException, IOException {
        socket =  new Socket(InetAddress.getByName(serverAddress), serverPort,InetAddress.getByName(serverAddress), 7777);
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
        client2 Client = null;
        String username = "Thuy Ngoan" ;
        try {
            Client = new client2("localhost", 8888);
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