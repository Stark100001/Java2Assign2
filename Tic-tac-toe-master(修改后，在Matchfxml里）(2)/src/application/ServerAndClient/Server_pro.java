package application.ServerAndClient;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server_pro extends Thread {
  private static List<Socket> socketList = new ArrayList<>();
  ServerSocket server = null;
  Socket socket = null;

  String password = "1234567890";

  public Server_pro(Socket socket) {
    super("客户端 ：" + Integer.toString(socketList.size() + 1));
    this.socket = socket;
    socketList.add(socket);
    System.out.println(getName() + " 进入聊天室 ");
  }

  @Override
  public void run() {
    try {
      BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
      PrintWriter out = null;
      String ans;
      boolean flag = false;
      boolean pass = false;
      while (true) {
        int i = 0;
        while (i <= 3 && !pass) {
          System.out.println(socketList.size());
          out = new PrintWriter(socket.getOutputStream(), true);//将要发送给客户端的字符串
          out.println("Hi，你好，欢迎连接到服务器上!请输入密码:");
          ans = bfr.readLine();//来自客户端的字符串
          if (password.equals(ans)) {
            out.println("密码验证通过");
            pass = true;
            break;
          } else {
            out.println("输入的密码不正确，请重新输入！");
            i++;
            if (i == 3) {
              flag = true;
              break;
            }
          }
        }
        if (flag) {
          out = new PrintWriter(socket.getOutputStream(), true);
          out.println("密码错误次数多于3次，退出登录！");
          out.println("end");
          socketList.remove(this.socket);
          System.out.println(socketList.size());
          break;
        }
        ans = bfr.readLine();
        System.out.println(getName() + ": " + ans);
        for (Socket socket1 : socketList) {
          if (!this.socket.equals(socket1)) {
            out = new PrintWriter(socket1.getOutputStream(), true);
            out.println(getName() + ": " + ans);
          }
        }
        if ("end".equals(ans)) {
          out = new PrintWriter(socket.getOutputStream(), true);
          out.println("end");
          System.out.println(getName() + ": " + "退出聊天室");
          socketList.remove(this.socket);
          break;
        }
      }
      bfr.close();
      out.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
