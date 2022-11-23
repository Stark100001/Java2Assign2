package application.ServerAndClient;
import java.util.Scanner;

public class test02_Client {
  public static void main(String[] args) {
    System.out.print("输入服务器地址: ");
    Scanner sc = new Scanner(System.in);
    Client_pro client = new Client_pro(sc.next(), 8082);
    client.start();
  }
}
