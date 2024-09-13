import java.io.*;
import java.net.*;
import java.util.Scanner;

class SingleThreadedServer
{
    final String DISCONNECT_MSG = "disconnect";
    
    ServerSocket server;
    InputStream in;
    OutputStream out;
    Socket client;
    Scanner sc; 

    public SingleThreadedServer(int port){
        try{
            server = new ServerSocket(port);
        }
        catch(IOException e){
            e.getMessage();
        }
        sc = new Scanner(System.in);
    }

    public void run(){       
        try{
            while(true){
                //open connection
                if (client == null){
                    connect();
                }
                // Send response to client
                sendMessage();                
                // Read message from client
                String client_message = getClientMessage();
                if(client_message.toLowerCase().equals(DISCONNECT_MSG))
                {
                    // Close connection
                    System.out.println("Disconnect from client.");
                    client.close();
                    client = null;
                }
            }
        }catch(IOException e){
            e.getMessage();
        }finally {
            sc.close(); // Close the scanner when the server terminates
        }
    }

    void connect(){
        //Listen for a connection and create client socket
        System.out.println("Waiting for connection...");
        try {
            client = server.accept();            
        } catch (IOException e) {
        }
        System.out.println("Connection established from " + client.getInetAddress().getHostName());
    }

    String getClientMessage(){
        String message = "";
        try {
            in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            message = reader.readLine();
            System.out.println("Received from client: " + message);
        } catch (IOException e) {
            e.getMessage();
        }
        return message;
    }

    void sendMessage(){
        System.out.print("Enter message to send to client: ");
        String message = sc.nextLine();
        try {
            out = client.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(message);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void main(String args[])
	{
        if(args.length != 1){
            System.out.println("You need to pass only one argument, port.");
            return;
        }

        int port = Integer.parseInt(args[0]);
		SingleThreadedServer client = new SingleThreadedServer(port);
		client.run();
	}
}
