package application.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import tictactoe.Data;

public class Server extends Thread {

  public List<Socket> getSocketList() {
    return socketList;
  }

  private static List<Socket> socketList = new ArrayList<>();
  ServerSocket server = null;
  Socket socket = null;
  int gamecount=1;
  public Server(Socket socket) {
    super("客户端 ：" + (socketList.size() + 1));
    this.socket = socket;
    socketList.add(socket);
    System.out.println("监听到对象" + socket);
    System.out.println(getName() + " 进入棋盘室 ");
    if(socketList.size()>0&&socketList.size()%2==0){
      System.out.println("够两个人，开始配对-1" + socket);

      sendMessage(socket,"游戏"+gamecount+"开始游戏-1");
      sendMessage(socketList.get(socketList.size()-2),"游戏"+gamecount+"开始游戏-2");
      gamecount++;
    }else{
      sendMessage(socket,"请等待");
    }

    /*// 向服务端程序发送数据
    OutputStream ops = null;
    try {
      ops = socket.getOutputStream();
      OutputStreamWriter opsw = new OutputStreamWriter(ops);
      BufferedWriter bw = new BufferedWriter(opsw);

      bw.write("hello world\r\n\r\n");
      bw.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }*/

  }


  public  void  sendMessage(Socket socket1,String message){
    Data data=new Data();
    data.setMessage(message);
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(socket1.getOutputStream());
      oos.writeObject(data);
      oos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public  void  sendData(Socket socket1,Data data){

    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(socket1.getOutputStream());
      oos.writeObject(data);
      oos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  @Override
  public void run() {
    try {
      while (true) {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
          Data data = (Data)in.readObject();
          System.out.println("服务器端收到信息 x" + data.getX()+"y"+data.getY());
          String win=data.getWinner();

          if(win!=null&&!"".equals(win)){
            System.out.println("获胜");
            int i=socketList.indexOf(socket);
            data.setMessage("获胜");
            sendData(socket,data);
            data.setMessage("对手获胜");
            if(i%2==0){
              sendData(socketList.get(i+1),data);
            }else{
              sendData(socketList.get(i-1),data);
            }
            return;
          }
          String mess=data.getMessage();
          if ("平局".equals(mess)){
            System.out.println("平局");
            int i=socketList.indexOf(socket);
            data.setMessage("平局");
            sendData(socket,data);
            data.setMessage("平局");
            if(i%2==0){
              sendData(socketList.get(i+1),data);
            }else{
              sendData(socketList.get(i-1),data);
            }
            return;
          }
          if("退出".equals(mess)){
            System.out.println("退出");
            int i=socketList.indexOf(socket);
         //   sendMessage(socket,"退出");
            if(i%2==0){
              sendMessage(socketList.get(i+1),"对手退出");
            }else{
              sendMessage(socketList.get(i-1),"对手退出");
            }
            return;
          }



          Data data1=new Data();
          data1.setX(data.getX());
          data1.setY(data.getY());
          data1.setMessage("下棋");
          int i=socketList.indexOf(socket);
          if(i%2==0){
            sendData(socketList.get(i+1),data1);
          }else{
            sendData(socketList.get(i-1),data1);
          }

        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }


      }

//      while (true) {
//        out = new PrintWriter(socket.getOutputStream(), true);
//        ans = bfr.readLine();//来自客户端的信息
////        System.out.println(getName() + ": " + ans);//打印出客户端说的话，但我实际上只要ans
//        for (Socket socket1 : socketList) {
//          if (!this.socket.equals(socket1)) {
//            out = new PrintWriter(socket1.getOutputStream(), true);
////            out.println(getName() + ": " + ans);
//          }
//        }
//        if ("end".equals(ans)) {
//          out = new PrintWriter(socket.getOutputStream(), true);
//          out.println("end");
//          System.out.println(getName() + ": " + "退出棋盘室");
//          socketList.remove(this.socket);
//          break;
//        }
//      }
//      bfr.close();
//      out.close();
//      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}