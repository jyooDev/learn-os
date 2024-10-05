import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        
        if(args.length < 2){
            System.out.println("Usage: java EchoClient [host address] [port]");
            System.exit(-1);
        }

        String host = args[0]; 
        int port = Integer.parseInt(args[1]); 

        try (Socket client = new Socket(host, port);
             InputStream in = client.getInputStream();
             OutputStream out = client.getOutputStream();
             Scanner sc = new Scanner(System.in);)
        {
            System.out.println("Connected to echo server on " + host + ":" + port);
            String input;
            byte[] buffer = new byte[1024];

            // Read input from the user, send to server, and read the server's echo
            while (true) {
                input = sc.next();
                
                // Send data to the server
                out.write(input.getBytes());
                out.flush();

                // Read echoed data from the server
                int bytesRead = in.read(buffer);
                if (bytesRead == -1) {
                    break;
                }

                String echo = new String(buffer, 0, bytesRead);
                System.out.println(echo);
            }

        } catch (IOException e) {
            System.out.println("Error communicating with server: " + e.getMessage());
        }
    }
}

