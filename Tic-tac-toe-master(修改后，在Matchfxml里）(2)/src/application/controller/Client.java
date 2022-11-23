package application.controller;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;

public class Client extends Thread {

  Socket socket = null;
  Controller controller = getController();


  public Client(String str, int port) {
    try {
      socket = new Socket(str, port);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Connecting failed!");
    }
  }


  public Controller getController(){
    FXMLLoader f = new FXMLLoader(getClass().getResource("mainUI.fxml"));
    f.setLocation(getClass().getClassLoader().getResource("mainUI.fxml"));
//    fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
//    try {
//      javafx.scene.Parent root = (javafx.scene.Parent) fxmlLoader.load(fxmlLoader.getLocation().openStream());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    return f.getController();
  }

  @Override
  public void run() {
//    final String QUIT = "quit";
//    BufferedWriter writer = null;
//    BufferedReader reader = null;
//    BufferedReader consoleReader = null;
//
//    try {
//      // 创建IO流
//      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//      // 等待用户输入信息
////      consoleReader = new BufferedReader(new InputStreamReader(System.in));
//
//      while (true) {
//
//        String input = controller.getStatus();
//        // 发送消息给服务器
//        writer.write(input + "\n");
//        writer.flush();
//
//        // 读取服务器返回的消息
//        String msg = reader.readLine();
//        System.out.println(msg);
//
////         查看用户是否退出
//        if (QUIT.equalsIgnoreCase(input)) {
//          break;
//        }
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        writer.close(); //关闭之前还会flush一次
//        socket.close();
//        reader.close();
////        consoleReader.close();
//        System.out.println("关闭socket");
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
    }
  }

//    new sedMessThread().start();
//    try {
//      BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
//      while (true) {
//        str = bfr.readLine();//来自server的字符串
//        if ("end".equals(str)) {
//          break;
//        }
//        System.out.println("Server: " + str);
//      }
//      bfr.close();
//      socket.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }


//  class sedMessThread extends Thread {
//
//    Scanner sc = null;
//    OutputStream out = null;
//
//    @Override
//    public void run() {
//      PrintWriter out = null;
//
//      try {
////        sc = new Scanner(System.in);
//        out = new PrintWriter(socket.getOutputStream(), true);
//        String in = "";
//        while (true) {
//          in = controller.getStatus();
//          out.println(in);
//          if ("end".equals(in)) {
//            break;
//          }
//        }
//        sc.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//  }



