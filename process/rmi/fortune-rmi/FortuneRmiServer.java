import java.rmi.RemoteException;
import java.rmi.registry.*;

public class FortuneRmiServer {
    public static void main(String[] args)
    {
        try
        {
            // Create an object of the interface
            System.out.println("Constructing server implementation...");
            RemoteFortuneImpl object = new RemoteFortuneImpl();

            // rmiregistry within the server JVM with port number 7000
            System.out.println("Binding server implementations to registry...");
            Registry registry = LocateRegistry.createRegistry(7000);

            //Binds the remote object by the name fortune
            registry.rebind("fortune", object);
            System.out.println("Waiting for invocations from clients...\n\n");
        }
        catch(RemoteException e)
        {
            System.out.println(e);
        }
    }
}