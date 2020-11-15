public class client {
    public static void main(String[] args) throws Exception {

        Stack_Generic st = new Stack_Generic();
        // Stack_Generic<Integer> st = new Stack_Generic();
        // you can define stack as any-dataType

        for(int i = 0; i < 5; i++) {
            st.push((char) ( (int)(Math.random() * 26) + 65) + " ");
        }

        System.out.println(st);

        st.push(new int[] { 1, 2, 3, 4 } );
        st.push("*Welcome To Amazon, Abhishek*");
        st.push(true);
        st.push("lpa");
        st.push(32);
        
        System.out.println(st);
    }
}