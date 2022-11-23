package application.ServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class test02_Server {

  public static void main(String[] str) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8082);
//        System.out.println(serverSocket.getInetAddress());
    while (true) {
      Socket socket = serverSocket.accept();
      Server_pro st = new Server_pro(socket);
      st.start();

    }
  }
}