package application.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class client03 {

    public static void main(String[] args) throws IOException {
   /*     ServerSocket serverSocket = new ServerSocket(8082);
        System.out.println("服务器启动");*/
        try {
            while (true) {
               /* Socket socket = serverSocket.accept();*/
                Client st = new Client("127.0.0.1",8082);
                st.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

