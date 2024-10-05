import java.io.*;
import java.net.*;
import java.util.Scanner;

class SingleThreadedClient
{
    final String DISCONNECT_MSG = "disconnect";

    Socket client;
    InputStream in;
    OutputStream out;
    Scanner sc;

    public SingleThreadedClient(String host, int port){
        try{
            client = new Socket(host, port);
        } catch(IOException e){
            e.getMessage();
        }
        sc = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Connection established from " + client.getLocalAddress().getHostName());
        try{
                while(client.isConnected()){
                // Read message from server
                getServerMessage();
    
                // Send response to server
                String client_message = sendMessage();
                System.out.println(client_message.toLowerCase());    
                if(client_message.toLowerCase().equals(DISCONNECT_MSG))
                {
                    // Close connection
                    client.close();
                    System.out.println("Disconnect from server and exit...");
                    break;
                }
            } 
        }catch(IOException e){
                e.getMessage();
        }finally{
            sc.close();
        }
    }

    void getServerMessage(){
        try {
            in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String message = reader.readLine();
            System.out.println("Received from server: " + message);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    String sendMessage(){
        String message;
        System.out.print("Enter message to send to server: ");
            message = sc.nextLine();
        try {
            out = client.getOutputStream();    
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(message);
        } catch (IOException e) {
            e.getMessage();
        }
        return message;
    }


    public static void main(String args[])
	{
        if (args.length != 2){
            System.out.println("You need to pass two arguments, host and port.");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
		SingleThreadedClient client = new SingleThreadedClient(host, port);
		client.run();
	}
} 