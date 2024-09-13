import java.net.*;
import java.io.*;
import java.util.Scanner;

class SingleThreadedClient
{
    Socket client;
    InputStream in;
    OutputStream out;

    public SingleThreadedClient(String host, int port){
        try{
            client = new Socket(host, port);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            //Read data from client
            in = client.getInputStream();
            out = client.getOutputStream();

            // Read message from server
            readMessage();
            // Send response to server
            sendMessage();
            // Close connection
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    void readMessage(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String message = reader.readLine();
            System.out.println("Received from server: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message to send to server: ");
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
		SingleThreadedClient client = new SingleThreadedClient("localhost", 6868);
		client.run();
	}
} 