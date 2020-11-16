public class client {

    public static void main(String[] args) throws Exception {

        queue que = new queue();

        for(int i = 0; i < 10; i++) {
            que.add((int) (Math.random() * 3200000));
        }
        System.out.println(que);

        for(int i = 0; i < 5; i++) que.remove();
        System.out.println(que);

        for(int i = 0; i < 5; i++) {
            que.add((int) (Math.random() * 3200000));
        }
        System.out.println(que);
        
        System.out.println(que.peek());
    }
}