public class Dqueue extends queue {

    Dqueue() {
        super();
    }
    
    Dqueue(int n) {
        super(n);
    }

    @Override
    public void add(int val) {

        if(super.size() == super.capacity()) {

            int size = super.size();
            int temp[] = new int[size];

            for(int i = 0;i < size; i++) temp[i] = super.remove_();

            super.setValues(size*2);

            for(int ele : temp) super.add_(ele);
        }
        super.add_(val);
    }
}