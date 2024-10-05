import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.Random;

public class RemoteFortuneImpl extends UnicastRemoteObject implements RemoteFortune {
    private final String[] fortuneHolder = {
        "A fresh start will put you on your way.",
        "Adventure awaits, but only if you're willing to step outside your comfort zone.",
        "Unexpected events will soon bring you joy.",
        "Your persistence will soon pay off in a big way.",
        "Someone from your past will reappear in your life with good news.",
        "A new perspective will lead to a breakthrough.",
        "You will soon make a new friend who changes your life.",
        "Positive energy will bring you unexpected good fortune.",
        "Your talents will soon be recognized by someone important.",
        "An exciting opportunity will come your way soon."
    };

    public RemoteFortuneImpl() throws RemoteException{
        super();
    }

    @Override
    public String getFortune() throws RemoteException{
        Random random = new Random();
        int fortune_index = random.nextInt(fortuneHolder.length);
        String fortune = fortuneHolder[fortune_index];
        return fortune;
    }
}
