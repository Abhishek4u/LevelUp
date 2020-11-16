public class queue {

    private int[] que;
    private int head;
    private int tail;

    private int size;
    private int maxSize;

    public queue() {
        setValues(10);
    }

    public queue(int n) {
        setValues(n);
    }

    public void setValues(int n) {
        this.que = new int[n];
        this.size = 0;

        this.maxSize = n;

        this.head = 0;
        this.tail = 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for(int i = 0;i < this.size; i++) {

            int idx = (this.head + i) % this.maxSize;
            sb.append(this.que[idx]);
            if(i != this.size - 1) sb.append(", "); 

        }        
        sb.append(']');
        return sb.toString();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.maxSize;
    }

    protected void add_(int val) {
        this.que[tail] = val;
        tail = (++tail % this.maxSize);

        this.size++;
    }

    public void add(int val) throws Exception {
        if(this.size == this.maxSize) {
            throw new Exception("QueueIsFull");
        }

        add_(val);
    }

    protected int peek_() {

        return this.que[this.head];
    }

    public int peek() throws Exception {
        if(this.size == 0) {
            throw new Exception("QueueIsEmpty");
        }

        return peek_();
    }

    protected int remove_() {
        int retVal = this.que[this.head];
        
        this.que[this.head] = 0;

        head = (++head % this.maxSize);
        this.size--;

        return retVal;
    }

    public int remove() throws Exception {
        if(this.size == 0) {
            throw new Exception("QueueIsEmpty");
        }

        return remove_();
    }

}