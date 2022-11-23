package application.ServerAndClient;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client_pro extends Thread {
  Socket socket = null;

  public Client_pro(String str, int port) {
    try {
      socket = new Socket(str, port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    new sedMessThread().start();
    try {
      BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));//接受来自server的信息
      String str;//来自server的字符串
      while (true) {
        str = bfr.readLine();
        if ("end".equals(str)) {
          break;
        }else
        System.out.println("Server: " + str);
      }
      bfr.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  class sedMessThread extends Thread {
    Scanner sc = null;
    OutputStream out = null;

    @Override
    public void run() {
      Scanner sc = null;
      PrintWriter out = null;

      try {
        sc = new Scanner(System.in);
        out = new PrintWriter(socket.getOutputStream(), true);
        String in = "";
        while (true) {
          in = sc.nextLine();
          out.println(in);
          if ("end".equals(in)) {
            break;
          }
        }
        sc.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

