import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client { // client class
    public static String text; // member variable
    public void setText(String text){
        this.text = text;
    } // get and set method
    public static String getText(){
        return text;
    }

    public static void main(String [] args) throws IOException { // main method
        System.out.println("client started");
        Socket soc = new Socket("localhost",9806); // creating socket
        PrintWriter pr = new PrintWriter(soc.getOutputStream(), true);  // printwriter for to send info to server
        pr.println(getText()); //sending into to server

    }
}
