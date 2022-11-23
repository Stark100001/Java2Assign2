package tictactoe;

import bean.User;
import dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class reg extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.print("请输入用户名：");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.print("请输入密码：");
        scan = new Scanner(System.in);
        String read1 = scan.nextLine();
        UserDAO dao = new UserDAO();
        User user = dao.reg(read, read1);
        System.out.print("注册成功");

    }
}
