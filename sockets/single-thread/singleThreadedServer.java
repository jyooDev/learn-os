import java.net.*;
import java.io.*;
import java.util.Scanner;

class SingleThreadedServer()
{
    ServerSocket server;
    InputStream in;
    OutputStream out;

    public SingleThreadedServer(int port){
        try{
            server = new ServerSocket(port);
        }
        catch(IOException e){
            ioException.printStackTrace();
        }
    }

    public void run(){
        
        try{
            while(true){
                //Listen for a connection and create client socket
                System.out.println("Waiting for connection...");
                Socket client = server.accept();
                System.out.println("Connection established from " + client.getInetAddress().getHostName());
                
                //Read data from client
                in = client.getInputStream();
                out = client.getOutputStream();

                // Read message from client
                printMessage();

                // Send response to client
                sendMessage();

                // Close connection
                client.close();
            }
        }catch(IOException e){
            ioException.printStackTrace();
        }
    }

    void printMessage(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String message = reader.readLine();
            System.out.println("Received from client: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message to send to client: ");
            String message = sc.nextLine();
        try {    
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
	{
		SingleThreadedServer client = new SingleThreadedServer(6868);
		client.run();
	}
}
