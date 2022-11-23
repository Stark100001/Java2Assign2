package application.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Client_Test02 extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    BufferedWriter writer = null;
    BufferedReader reader = null;
    final String QUIT = "quit";

    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainUI.fxml"));
      Pane root = fxmlLoader.load();
      primaryStage.setTitle("Tic Tac Toe");
      primaryStage.setScene(new Scene(root));
      primaryStage.setResizable(false);

      Controller c = fxmlLoader.getController();
      c.connect();
      c.client.start();
      primaryStage.show();

      reader = new BufferedReader(new InputStreamReader(c.client.socket.getInputStream()));
      writer = new BufferedWriter(new OutputStreamWriter(c.client.socket.getOutputStream()));


      String input = c.getStatus();
      // 发送消息给服务器
      writer.write(input + "\n");
      writer.flush();
//
      // 读取服务器返回的消息
     // String msg = reader.readLine();
      System.out.println("aaaaa");


//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        writer.close(); //关闭之前还会flush一次
//        reader.close();
////        consoleReader.close();
//        System.out.println("关闭socket");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}



