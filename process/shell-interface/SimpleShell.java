import java.io.*;
import java.util.*;

public class SimpleShell {
    public static void main(String[] args) throws IOException{
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.print("Jsh> ");
            commandLine = console.readLine();
            
            // if the user entered a return, just loop again
            if (commandLine.equals("")){
                continue;
            }
            else{
                try {
                    List<String> commands = parseCommand(commandLine);
                    ProcessBuilder pb = new ProcessBuilder(commands);
                    Process process = pb.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    StringBuilder output = new StringBuilder();
                    String inputLine;
                    while ( (inputLine = reader.readLine()) != null) 
                    {
                        output.append(inputLine);
                        output.append("\n");
                    }
                    String result = output.toString();
                    System.out.print(result);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            
        }
    }

    public static List<String> parseCommand(String command){
        String[] commands = command.split(" ");
        List<String> result = Arrays.asList(commands);
        return result;
    }
}
