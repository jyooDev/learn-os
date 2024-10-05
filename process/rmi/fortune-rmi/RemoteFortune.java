import java.rmi.*;

public interface RemoteFortune extends Remote
{
  public abstract String getFortune() throws RemoteException;
}