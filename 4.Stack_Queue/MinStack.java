// LEETCODE 155
// SS7082
public class MinStack {

    // Eqn is (Flag (wrong info)) : 2x - min( for insertion) (less than newMin)
    // For Old min : 2*newMin - Flag
    
    Stack<Long> st; // stack can overflow
    long minSF = 0; // min-so-far
    
    public MinStack() {
        st = new Stack<> ();
    }
    
    public void push(int val) {
        long x = val;
        
        if(st.size() == 0) {
            st.push(x);
            minSF = x;
            return;
        }
        
        if(x < minSF) {
            st.push( (x - minSF) + x); // 2x - minSF
            minSF = x;
            
        } else st.push(x);        
    }
    
    public void pop() {
        if(st.peek() < minSF) {
            minSF = (minSF - st.peek()) + minSF; // 2*newMin - Flag
        }
        
        st.pop();
    }
    
    public int top() {
        if(st.peek() < minSF) {
            return (int)minSF;
            
        } else return (int)((long)st.peek());
    }
    
    public int getMin() {
        return (int)minSF;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */