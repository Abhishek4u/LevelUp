import java.util.ArrayList;
public class priorityQueue {

    // ----------------------------HEAP IS KNOWN AS PRIORITYQUEUE----------------------
    // 1. FOR VALUE REMOVAL : we have to swap the last value with first value(parent ) and then remove
    //                        and then call downheapify
    // 2. FOR VALUE ADDITION :  we have to add value in priorityQueue and then call upheapify from last index

    // 3. FOR PEEK : simply return the first index value bcz that value is min/max value a/type of priorityQueue

    ArrayList<Integer> pq = new ArrayList<> ();
    private boolean isMaxHeap = true;

    // -------------------------------CONSTRUCTOR-------------------------------

    public priorityQueue(int[] arr, boolean isMax) {

        initialize(isMax);
        for(int ele : arr) pq.add(ele);
        constructHeap();
    }
    
    public priorityQueue() {
        initialize(true);
        // by-default max PriorityQueue
    }

    public void initialize(boolean isMax) {
        this.pq = new ArrayList<> ();
        this.isMaxHeap = isMax;
    }

    public void constructHeap() {

        for(int i = pq.size() - 1; i >= 0 ;i--) {
            downHeapify(i);
            // call downheapify to heapify all the elts starting from leafs
        }
    }

    private int compareTo(int i, int j) {

        if(isMaxHeap) return pq.get(i) - pq.get(j);
        else return pq.get(j) - pq.get(i);
    }

    private void swap(int i, int j) {

        int v1 = pq.get(i);
        int v2 = pq.get(j);

        pq.set(i, v2);
        pq.set(j, v1);
    }

    // used for generating heap and after removal of value again correcting
    // the heap
    private void downHeapify(int pi) { // pi = parentIdx
        int maxIdx = pi; // maxValue index

        int lci = (pi << 1) + 1; // (pi * 2) + 1
        int rci = (pi << 1) + 2;

        if(lci < pq.size() && compareTo(lci, maxIdx) > 0) maxIdx = lci;
        if(rci < pq.size() && compareTo(rci, maxIdx) > 0) maxIdx = rci;

        if(maxIdx != pi) {

            swap(pi, maxIdx);
            downHeapify(maxIdx);
        }
    }

    private void upHeapify(int ci) { // ci = childIdx

        int pi = (ci - 1) >> 1; // (ci - 1) / 2

        if(pi >= 0 && compareTo(ci, pi) > 0) {
            // means maxElt is childIdx Value
            // so we need to swap the value and again do upheapify for parent

            swap(ci, pi);
            upHeapify(pi);
        }
    }

    // ====================================================================

    public int size() {

        return pq.size();
    }

    public boolean isEmpty() {
        return pq.size() == 0;
    }

    // O(logn) (for upheapifying parent nodes till tree height upwards)
    public void add(int val) {
        pq.add(val);
        upHeapify(pq.size() - 1);
    }

    // O(1) we need to access 1st element only
    public int peek() throws Exception {

        if(pq.size() == 0) throw new Exception("NullPointerException");

        return pq.get(0);
    }

    // O(logn) after removal we have to downHeapify the elt from parent node till tree height downwards
    public int poll() throws Exception{

        if(pq.size() == 0) throw new Exception("NullPointerException");

        for(int ele : pq ) System.out.print(ele + " ");
        System.out.println();

        int rv = pq.get(0); // returnValue
        swap(0, pq.size() - 1);
        // swapped top elt now we need to downheapify and remove last elt

        pq.remove(pq.size() - 1);

        downHeapify(0);
        //downHeapify the tree again to get maxElt at top of tree

        return rv;
    }

    //----------------------------------------------------------------------------------

    
    // ONLY FOR DIJKSTRA OR WHERE YOU NEED TO UPDATE VALUE IN PRIORITYQUEUE
    // This function will help in dijkstra where we have to update value in priorityQueue
    // so if we can get index of that value and updates the value then we have to call
    // upheapify or downheapify according to value 

    // public void update(int idx) {

    //     downHeapify(idx);
    //     upHeapify(idx);
    //     // only 1 need to execute according to updated value in tree
    // }

}