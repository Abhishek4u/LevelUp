public class client {
    
    public static void main(String[] args) throws Exception {

        linkedlist ll = new linkedlist();

        for(int i = 0; i <= 20; i++) {
            ll.addLast(i*10);
        }

        ll.addAt(10,3200000);
        System.out.println(ll);
    }
}