public class FibonacciThread extends Thread {
    FibonacciArray fibArray;
    int id;

    //constructor
    public FibonacciThread(FibonacciArray fibArray, int thread_id){
        this.fibArray = fibArray;
        this.id = thread_id;
    }
    
    @Override
    public void run(){
        int index = fibArray.getIndex();

        //generate the fibonnaci number at the given index
        while(index < fibArray.getLength()){
            fibArray.fibonacciNum(index);
            index++;
        }
    }

}
