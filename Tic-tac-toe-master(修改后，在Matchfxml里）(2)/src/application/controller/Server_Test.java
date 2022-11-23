package application.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Test {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8087);
    System.out.println("服务器启动");
    try {
      while (true) {
        Socket socket = serverSocket.accept();
        Server st = new Server(socket);
        st.start();

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

