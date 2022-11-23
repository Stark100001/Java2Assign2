package application.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.net.Socket;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


  private final int PLAY_1 = 1;
  private final int PLAY_2 = 2;
  private final int EMPTY = 0;
  private final int BOUND = 90;
  private final int OFFSET = 15;
  Client client = null;


  public int[][] getChessBoard() {
    return chessBoard;
  }

  @FXML
  private Pane base_square;

  public Rectangle getGame_panel() {
    return game_panel;
  }

  @FXML
  private Rectangle game_panel;

  private boolean TURN = false;//交换玩家的变量 false为先是Player2下（❌）
  private final int[][] chessBoard = new int[3][3];
  private final boolean[][] flag = new boolean[3][3];


  public boolean connect() {
    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("请输入服务器地址：");
      String serverAddress = sc.next();
      client = new Client(serverAddress, 8082);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
      game_panel.setOnMouseClicked(event -> {
        int x = (int) (event.getX() / BOUND);
        int y = (int) (event.getY() / BOUND);
        if (refreshBoard(x, y)) {
          TURN = !TURN;//交换玩家
          judgeWin();
        }
      });
    }




  public boolean refreshBoard(int x, int y) {
    if (chessBoard[x][y] == EMPTY) {
      chessBoard[x][y] = TURN ? PLAY_1 : PLAY_2;
      drawChess();
      return true;
    }
    return false;
  }

  public void drawChess() {
    for (int i = 0; i < chessBoard.length; i++) {
      for (int j = 0; j < chessBoard[0].length; j++) {
        if (flag[i][j]) {
          // This square has been drawing, ignore.
          continue;
        }
        switch (chessBoard[i][j]) {
          case PLAY_1:
            drawCircle(i, j);
            break;
          case PLAY_2:
            drawLine(i, j);
            break;
          case EMPTY:
            // do nothing
            break;
          default:
            System.err.println("Invalid value!");
        }
      }
    }
  }

  public void judgeWin() {
    //Player1 Wins
    for (int i = 0; i < chessBoard.length; i++) {
      if ((chessBoard[i][0] == 1 && chessBoard[i][1] == 1 && chessBoard[i][2] == 1) || (
          chessBoard[0][i] == 1 && chessBoard[1][i] == 1 && chessBoard[2][i] == 1)) {
        System.out.println("Play1 Win!");
      }
    }

    for (int i = 0; i < chessBoard.length; i++) {
      for (int j = 0; j < chessBoard[0].length; j++) {
        if (i == 0 && j == 0) {
          if (chessBoard[i][j] == 1 && chessBoard[i + 1][j + 1] == 1
              && chessBoard[i + 2][j + 2] == 1) {
            System.out.println("Play1 Win!");
          }
        } else if (i == 0 && j == 2) {
          if (chessBoard[i][j] == 1 && chessBoard[i + 1][j - 1] == 1
              && chessBoard[i + 2][j - 2] == 1) {
            System.out.println("Play1 Win!");
          }
        } else if (i == 2 && j == 0) {
          if (chessBoard[i][j] == 1 && chessBoard[i - 1][j + 1] == 1
              && chessBoard[i - 2][j + 2] == 1) {
            System.out.println("Play1 Win!");
          }
        } else if (i == 2 && j == 2) {
          if (chessBoard[i][j] == 1 && chessBoard[i - 1][j - 1] == 1
              && chessBoard[i - 2][j - 2] == 1) {
            System.out.println("Play1 Win!");
          }
        }
      }
    }

    //Player2 Wins

    for (int i = 0; i < chessBoard.length; i++) {
      if ((chessBoard[i][0] == 2 && chessBoard[i][1] == 2 && chessBoard[i][2] == 2) || (
          chessBoard[0][i] == 2 && chessBoard[1][i] == 2 && chessBoard[2][i] == 2)) {
        System.out.println("Play2 Win!");
      }
    }

    for (int i = 0; i < chessBoard.length; i++) {
      for (int j = 0; j < chessBoard[0].length; j++) {
        if (i == 0 && j == 0) {
          if (chessBoard[i][j] == 2 && chessBoard[i + 1][j + 1] == 2
              && chessBoard[i + 2][j + 2] == 2) {
            System.out.println("Play2 Win!");
          }
        } else if (i == 0 && j == 2) {
          if (chessBoard[i][j] == 2 && chessBoard[i + 1][j - 1] == 2
              && chessBoard[i + 2][j - 2] == 2) {
            System.out.println("Play2 Win!");
          }
        } else if (i == 2 && j == 0) {
          if (chessBoard[i][j] == 2 && chessBoard[i - 1][j + 1] == 2
              && chessBoard[i - 2][j + 2] == 2) {
            System.out.println("Play2 Win!");
          }
        } else if (i == 2 && j == 2) {
          if (chessBoard[i][j] == 2 && chessBoard[i - 1][j - 1] == 2
              && chessBoard[i - 2][j - 2] == 2) {
            System.out.println("Play2 Win!");
          }
        }
      }
    }

  }


  public void drawCircle(int i, int j) {
    Circle circle = new Circle();
    base_square.getChildren().add(circle);
    circle.setCenterX(i * BOUND + BOUND / 2.0 + OFFSET);
    circle.setCenterY(j * BOUND + BOUND / 2.0 + OFFSET);
    circle.setRadius(BOUND / 2.0 - OFFSET / 2.0);
    circle.setStroke(Color.RED);
    circle.setFill(Color.TRANSPARENT);
    flag[i][j] = true;
  }

  public void drawLine(int i, int j) {
    Line line_a = new Line();
    Line line_b = new Line();
    base_square.getChildren().add(line_a);
    base_square.getChildren().add(line_b);
    line_a.setStartX(i * BOUND + OFFSET * 1.5);
    line_a.setStartY(j * BOUND + OFFSET * 1.5);
    line_a.setEndX((i + 1) * BOUND + OFFSET * 0.5);
    line_a.setEndY((j + 1) * BOUND + OFFSET * 0.5);
    line_a.setStroke(Color.BLUE);

    line_b.setStartX((i + 1) * BOUND + OFFSET * 0.5);
    line_b.setStartY(j * BOUND + OFFSET * 1.5);
    line_b.setEndX(i * BOUND + OFFSET * 1.5);
    line_b.setEndY((j + 1) * BOUND + OFFSET * 0.5);
    line_b.setStroke(Color.BLUE);
    flag[i][j] = true;
  }


  public String getStatus() {
    String[] s = new String[9];
    String panel = "";
    for (int i = 0; i < this.getChessBoard().length; i++) {
      for (int j = 0; j < this.getChessBoard()[i].length; j++) {
        s[3 * i + j] = Integer.toString(this.getChessBoard()[i][j]);
      }
    }
    for (int i = 0; i < s.length; i++) {
      panel = panel + s[i];
    }
    return panel;
  }

  public void sendMessage() {
      String str = getStatus();
      try {
        OutputStream out = client.socket.getOutputStream();
        //传出数据
        out.write(str.getBytes());
        client.socket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }







