import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static String getRet(String str) {
        if (str.equals("Hi") ||str.equals("hi") ) { // responses for the chat bot
            String ret = "Hello, im your friendly bot, For tips, 1 , 2 or 3";
            return ret;
        } else if (str.equals("1")) {
            String tip1 = "It is also important to get your checkers over to the other side as quickly as possible";
            return tip1;
        }else if(str.equals("2")){
            String tip2 = "One good tip is to move your pieces in groups and play on the sides to prevent your pieces being taken";
            return tip2;
        }else if(str.equals("3")){
            String tip3 = "Another good tip is to leave the last row of pieces alone so that the opponent cannot have any kings";
            return tip3;
        }
        String end = "Sorry I don't know that command. Please type Hi to see options'";
        return end;
    }

    public static void main (String [] args) throws IOException {
//        System.out.println("waiting....");
        ServerSocket ss = new ServerSocket(9806); // creating server socket
        Socket soc = ss.accept(); // socket to accept client
    }
}