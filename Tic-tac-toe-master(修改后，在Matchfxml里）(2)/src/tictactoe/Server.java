package tictactoe;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws  Exception{
        ServerSocket ss = new ServerSocket(8087);
        Socket socket1 = ss.accept();
        Socket socket2 = ss.accept();
        Data data = null;
        ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
        oos.writeObject(data);
        int i = 0;
        while(true) {
            System.out.println(i++);
            ObjectInputStream ois1 = new ObjectInputStream(socket1.getInputStream());
            ObjectOutputStream oos2 = new ObjectOutputStream(socket2.getOutputStream());
            data = (Data)ois1.readObject();
            oos2.writeObject(data);
            oos2.flush();

            ObjectInputStream ois2 = new ObjectInputStream(socket2.getInputStream());
            ObjectOutputStream oos1 = new ObjectOutputStream(socket1.getOutputStream());
            data = (Data)ois2.readObject();
            oos1.writeObject((data));
            oos1.flush();
            if(null != data.getWinner())
                break;
        }
    }
}
