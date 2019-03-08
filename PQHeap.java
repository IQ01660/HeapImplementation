/**
 * @author Ikram Gabiyev
 * @author Github: IQ01660
 * Notes:
 * remember root is at position 1,
 * so position = i + 1
 * the depth start at 0
 * the first element at each level 2^d (depth)
 */

public class PQHeap implements PriorityQueue {
    //the array for the heap
    private Integer[] data;
    //number of elements - size
    private int numElts;
    
    //constructor
    public PQHeap() 
    {
        this.data = new Integer[10];
        this.numElts = 0;
    }

    //public methods

    /**
     * Adding a new element to the heap
     */
    @Override
    public void add(Integer toAdd) 
    {

        //checking whether array is full
        //and resizing it
        if(this.numElts == this.data.length) 
        {
            this.resize();
        }

        //now that we are sure we have space
        //add a new element.
        //Note: next available space is at index "numElts"
        this.data[numElts] = toAdd;

        //increment the number of elements
        this.numElts++;
        
        //sifting Up: the conditions are in
        //the method itself
        //Note: numElts here is position of added element-
        //NOT index
        this.siftUp(numElts);
    }

    /**
     * removing the largest element - at the root
     */
    @Override
    public Integer remove() 
    {
        //checking if the size of array is 0
        if(this.isEmpty()) 
        {
            System.out.println("NO ELEMENTS IN THE P-QUEUE");
            return 0;
        }

        //the largest element at the root
        Integer toreturn = this.data[0];

        //putting the last node on the root
        this.data[0] = this.data[this.numElts - 1];

        //decrementing the num of elements
        this.numElts--;

        //sifting down starting from root: position 1
        siftDown(1);
        
        return toreturn;
    }

    /**
     * number of elements in
     * the PQueue
     */
    @Override
    public int size() 
    {
        return numElts;
    }

    /**
     * shows whether PQueue is empty
     */
    @Override
    public boolean isEmpty() 
    {
        if (this.numElts == 0) {
            return true;
        }
        return false;
    }

    //private methods

    private void siftUp(int pos) 
    {
        //parent at floor(pos/2) - position, not index
        int parentPos = (int) Math.floor( ((double)pos) / 2 ); 

        //stop if at root OR parent is larger (or equal): in this order
        if(pos == 1 || this.data[parentPos - 1] >= this.data[pos - 1]) 
        {
            return ;
        }

        //otherwise swap with the parent
        else 
        {
            Integer temp = this.data[pos - 1];
            this.data[pos - 1] = this.data[parentPos - 1];
            this.data[parentPos - 1] = temp;

            //and then siftUp with the parent's position
            this.siftUp(parentPos);
        }
    }

    private void siftDown(int pos) 
    {
        //stop if no children
        //Note: I should check whether children indeces exist
        //so that I don't get ArrayOutOfBoundsException
        //I added some helper methods

        //does it have any children?
        if(!leftChildExists(pos)) 
        {
            return ;
        }

        //now it definitely has at least left child
        //next we determine the largest child available
        int largestChildPos = pos * 2;
        Integer largestChild = this.data[largestChildPos - 1];
        if (rightChildExists(pos) && largestChild < this.data[pos * 2]) 
        {
            largestChildPos = pos * 2 + 1;
            largestChild = this.data[largestChildPos - 1]; 
        }

        //stop if you are larger than your largest child
        if (this.data[pos - 1] > largestChild) {
            return ;
        }

        //otherwise swap with the largest child
        Integer temp = this.data[pos - 1];
        this.data[pos - 1] = this.data[largestChildPos - 1];
        this.data[largestChildPos - 1] = temp;

        //sift Down again with new position
        this.siftDown(largestChildPos);
    }

    /**
     * doubles the size of the 
     * array every time it is
     * full, copying all existing
     * elements
     */
    private void resize() 
    {
        Integer[] temp = new Integer[numElts * 2];
        for (int i = 0; i < data.length; i++)
        {
            temp[i] = this.data[i];
        }
        this.data = temp;
    }

    /**
     * checking whether left
     * child exists
     */
    private boolean leftChildExists(int parentPos) 
    {
        if (this.numElts < parentPos * 2) 
        {
            return false;
        }

        return true;
    }

    /**
     * checking whether right child exists
     */
    private boolean rightChildExists(int parentPos) 
    {
        if ( this.numElts < (parentPos * 2 + 1) ) 
        {
            return false;
        }

        return true;
    }

    /**
     * THIS IS FOR TESTING
     */
    public void printQueue() 
    {
        for(int i = 0; i < this.numElts; i++)
        {
            System.out.print(this.data[i] + " ");
        }
    }
}