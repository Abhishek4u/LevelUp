// Using Stack and making Stack as dynamic stack which will increase
// its size when needed
public class DStack extends Stack {

    public DStack() {
        super();
    }

    public DStack(int n) {
        super(n);
    }

    public DStack(int arr[]) {
        super.setValues(arr.length * 2);

        for(int ele : arr) {
            super.push_(ele);
            // use push_() here as we are handling the exceptions 
            // so here we do not need to write throws exception
        }
    }

    @Override
    public void push(int val) {

        if(super.size() == super.capacity()) {
            // if stack is full then double the size of stack
            int size = super.size();
            int temp[] = new int[size];

            for(int i = 0;i < size; i++) {
                temp[i] = super.pop_();
                // use pop_() as we are calling valid request every time
                // so we donot need to write throws exception in function 
            }

            super.setValues(size*2);
            // make stack[] size double of previous size

            for(int i = size - 1; i >= 0; i--) {
                super.push_(temp[i]);
                // use push_() as we are calling carefully by increasing the size
            }
        }

        super.push_(val);
        // use push_() as we already increased the size if size was full in above if-statement
    }

}