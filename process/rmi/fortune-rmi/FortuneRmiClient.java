import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FortuneRmiClient
{
    public static void main(String args[])
    {
        try
        {
            // lookup method to find reference of remote object
            Registry registry = LocateRegistry.getRegistry(7000);
            RemoteFortune fortuneServer =
                (RemoteFortune)registry.lookup("fortune");
            String fortune = fortuneServer.getFortune();
            System.out.println(fortune);
        }
        catch(RemoteException | NotBoundException e)
        {
            System.out.println(e);
        }
    }
}