// Class On 1stSep
//SS6522

// Implementation of Stack Using Array[]
public class Stack {

    private int st[];
    private int size;
    private int tos;
    private int maxSize;

    // pre-defined size of stack[] is 10
    public Stack() {
        setValues(10);
    }

    // user-defined size of stack[]
    public Stack(int n) {
        setValues(n);
    }
    
    // making protected so that we can use it in other classes too
    protected void setValues(int n) {
        this.st = new int[n];
        this.size = 0;
        this.tos = -1;
        this.maxSize = n;
    }

    // for printing the elts
    @Override
    public String toString() {      

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for(int i = tos; i >= 0; i--) {
            sb.append(st[i]);

            if(i != 0) sb.append(", ");
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

    // it will give maxCapactiy of stack[]
    public int capacity() {
        return this.maxSize;
    }

    // push_() fxn without exception
    // means all corner cases are covered in push() fxn
    protected void push_(int val) {
        this.st[++tos] = val;
        this.size++;
    }

    // for corner cases and it will call push_() for addition
    public void push(int val) throws Exception {
        if(this.size == maxSize) {
            throw new Exception("StackIsFull");
        }
        push_(val);
    }

    protected int peek_() {
        return this.st[tos];
    }

    // to see elt in top of stack
    public int peek() throws Exception {

        if(this.size == 0) {
            throw new Exception("StackIsEmpty");
        }

        return peek_();
    }

    protected int pop_() {
        int retVal = this.st[tos];

        this.st[tos--] = 0;
        this.size--;

        return retVal;
    }

    // to remove the element
    public int pop() throws Exception {

        if(this.size == 0) {
            throw new Exception("StackIsEmpty");
        }

        return pop_();
    }

}