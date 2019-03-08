import java.util.PriorityQueue;
import java.util.Random;

/**
 * I am testing code in this class
 */
public class Tester {

    public static PriorityQueue heapQueue = new PQHeap();
    //the maximum number of elements in queue
    public static final int N_MAX = 100000;
    
    public static Random randGen = new Random();

    public static void main(String[] args) 
    {
        addRandomMax(heapQueue);
        removeTiming(heapQueue);
        System.out.println("--------------------");
        addTiming(heapQueue);
    }
    /**
     * add N_MAX random elements to P-Queue
     * this method is called before 
     * starting the timing experiment
     */
    public static void addRandomMax(PriorityQueue pqueue)
    {
        for (int i = 0; i < N_MAX; i++)
        {
            pqueue.add( Tester.randGen.nextInt(N_MAX) );   
        }
    }

    public static void removeTiming(PriorityQueue pqueue) 
    {
        int step = 1000;

        for (int i = 1; i <= N_MAX; i++) 
        {      
            if(i % step == 0) // do not record all removals
            {
                long infinitesimalStartTime;
                long infinitesimalEndTime;

                infinitesimalStartTime = System.nanoTime();
                pqueue.remove();
                infinitesimalEndTime = System.nanoTime();
                //(N_MAX - i) + " " + (infinitesimalEndTime - infinitesimalStartTime)
                System.out.println((infinitesimalEndTime - infinitesimalStartTime));
            }
            else 
            {
                pqueue.remove();
            }
        }
    }
    public static void addTiming(PriorityQueue pqueue) 
    {
        int step = 1000;

        for (int i = 1; i <= N_MAX; i++) 
        {      
            if(i % step == 0) // do not record all removals
            {
                long infinitesimalStartTime;
                long infinitesimalEndTime;
                int randomInput = Tester.randGen.nextInt(N_MAX);
                infinitesimalStartTime = System.nanoTime();
                pqueue.add(randomInput);
                infinitesimalEndTime = System.nanoTime();
                //(N_MAX - i) + " " + (infinitesimalEndTime - infinitesimalStartTime)
                System.out.println((infinitesimalEndTime - infinitesimalStartTime));
            }
            else 
            {
                pqueue.add( Tester.randGen.nextInt(N_MAX) );
            }
        }
    }
    
}