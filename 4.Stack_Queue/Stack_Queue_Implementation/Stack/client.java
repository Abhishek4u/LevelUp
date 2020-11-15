
// To run DStack file use javac client.java && java client 
// Dont use java client.java as it will start finding client in java which is not present
public class client {
    // Class On 1stSep
    //SS6522
    
    public static void main(String[] args) throws Exception{

        // Stack st = new Stack();
        DStack st = new DStack();

        for(int i = 0; i < 10; i++) {
            st.push((int)(Math.random() * 520));
        }

        System.out.println(st);

        st.push(52);

        System.out.println(st);
    }
}