import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {

        if(args.length < 1){
            System.out.println("Usage: java EchoServer [port]");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);  // The port the server listens on

        try (ServerSocket serverSocket = new ServerSocket(port)) 
        {
            System.out.println("Echo server started on port " + port + ".\nWaiting for client connection...");
            
            while (true) {
                try (Socket client = serverSocket.accept();
                     InputStream in = client.getInputStream();
                     OutputStream out = client.getOutputStream()) 
                {
                    System.out.println("Client connected: " + client.getInetAddress());
                    
                    // Create byte buffer to store the bytes read from the input stream
                    byte[] buffer = new byte[1024];
                    int bytesLength;

                    // Read from the client and echo the data back
                    while ((bytesLength = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesLength);
                        String echo = new String(buffer);
                        System.out.println("Echo message back to client: " +  echo);
                    }
                    
                    System.out.println("Client disconnected");
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}

