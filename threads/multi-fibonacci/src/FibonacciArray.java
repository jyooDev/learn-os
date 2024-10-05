//shared data

public class FibonacciArray {
    private int index;
    private int length;
    private int[] fibArray;

    //constructor
    public FibonacciArray(int length){
        this.index = 0;
        this.length = length;
        this.fibArray = new int[length];
    }

    //get methods
    public int getLength(){
        return length;
    }
    public int getIndex(){
        return index;
    }

    public int getValue(int index){
        return fibArray[index];
    }

    //method to print the sequence of fibonacci numbers
    public void print(){
        for(int i = 0; i < length; i ++){
            System.out.print(fibArray[i] + " ");
        }
    }
    
    //method to calculate the fibonacci sequence
    public int fibonacciNum(int index){
        switch (index) {
            case 0:
                fibArray[index] = 0;
                break;
            case 1:
                fibArray[index] = 1;
                break;
            default:
                fibArray[index] = fibonacciNum(index-1) + fibonacciNum(index-2);
                break;
        }
        return fibArray[index];
    }

}
