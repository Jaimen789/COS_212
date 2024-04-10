@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap(int capacity) {
	super(capacity);
    }

    ////// You may not change any code above this line //////

    ////// Implement the functions below this line //////

    @Override
    public void insert(T elem) {

        //Your code goes here
        mH[currentSize] = elem;
        int current = currentSize;

        while(current > 0 && mH[current].compareTo((T) mH[parent(current)]) < 0){
            swap(current, parent(current));
            current = parent(current);
        }

        currentSize++;
    }

    public T removeMin() {

        Comparable<T> popped = mH[0];

        mH[0] = mH[--currentSize];
        minHeapify(0);

        return (T) popped;

       
    }

    public void delete(T elem) {
    
        int i;
        for (i = 0; i < currentSize; i++) {
            if (elem.equals(mH[i])) {
                break;
            }
        }
    
        if (i == currentSize) {
            // Element not found in the heap
            return;
        }
    
        swap(i, currentSize - 1);
        currentSize--;
    
        minHeapify(i);

    }

    private int parent(int pos){

        return pos / 2;
    }

    private int leftChild(int pos){

        return (2 * pos);
    }

    private int rightChild(int pos){

        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos){

        if(pos > (currentSize / 2)){
            return true;
        }

        return false;
    }

    private void swap(int fpos, int spos){


        Comparable<T> temp;

        temp = mH[fpos];
        mH[fpos] = mH[spos];
        mH[spos] = temp;
    }

    private void minHeapify(int pos){
        if (isLeaf(pos)) {
            return;
        }

        int left = leftChild(pos);
        int right = rightChild(pos);
        int minPos = pos;

        if (left < currentSize && mH[left].compareTo((T) mH[minPos]) < 0) {
            minPos = left;
        }
        
        if (right < currentSize && mH[right].compareTo((T) mH[minPos]) < 0) {
            minPos = right;
        }
        

        if(minPos != pos){
            swap(pos, minPos);
            minHeapify(minPos);
        }

    }

    

}