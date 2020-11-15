// Stack implemented using generic dataType
// You can add any datatype value
public class Stack_Generic<T> {

    private Object[] st;
    private int size;
    private int tos;
    private int maxSize;

    public Stack_Generic() {
        setValues(10);
    }

    public Stack_Generic(int n) {
        setValues(n);
    }
    
    protected void setValues(int n) {

        this.st = new Object[n];
        this.size = 0;
        this.tos = -1;
        this.maxSize = n;
    }

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
    
    public int capacity() {
        return this.maxSize;
    }

    protected void push_(T val) {

        this.st[++tos] = val;
        this.size++;
    }

    public void push(T val) throws Exception {
        
        if(this.size == this.maxSize) {
            throw new Exception("StackIsFull");
        }

        push_(val);
    }

    protected T peek_() {
        return (T)this.st[tos];
    }

    public T peek() throws Exception {

        if(this.size == 0) {
            throw new Exception("StackIsEmpty");
        }

        return peek_();
    }

    protected T pop_() {
        
        T retVal = (T)this.st[tos];

        this.st[tos--] = 0;
        this.size--;

        return retVal;
    }

    public T pop() throws Exception {

        if(this.size == 0) {
            throw new Exception("StackIsEmpty");
        }

        return (T)pop_();
    }
}