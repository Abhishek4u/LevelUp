public class linkedlist {

    private class Node{
        int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("[ ");

        Node curr = this.head;

        while(curr != null) {

            sb.append(curr.data);
            if(curr.next != null) sb.append(", ");
            curr = curr.next;
        }
        sb.append(" ]");

        return sb.toString();
    }

    // -----------------------------ADD---------------------------

    private void addFirstNode(Node node) {

        if(this.size == 0) {

            this.head = node;
            this.tail = node;

        } else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {

        Node node = new Node(data);
        addFirstNode(node);

    }

    private void addLastNode(Node node) {

        if(this.size == 0 ) {

            this.head = node;
            this.tail = node;

        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int data) {

        Node node = new Node(data);
        addLastNode(node);
    }

    private void addNodeAt(int idx,Node node) {

        if(idx == 0) addFirstNode(node);

        else if(idx == this.size) addLastNode(node);

        else {

            Node prev = getNodeAt(idx-1);

            Node fwd = prev.next;
            
            prev.next = node;
            node.next = fwd;
        }
        this.size++;

    }

    public void addAt(int idx,int data) throws Exception {

        if(idx < 0 || idx > this.size) throw new Exception("Index out of bounds");

        Node node = new Node(data);
        
        addNodeAt(idx,node);
    }

    // -----------------------------REMOVE---------------------------

    private Node removeFirstNode() {

        Node remNode = this.head;

        if(this.size == 1) {
            this.head = null;
            this.tail = null;

        } else {
            this.head = this.head.next;
        }

        remNode.next = null; // for detaching
        this.size--;
        return remNode;
    }

    public int removeFirst() throws Exception {

        if(this.size == 0) throw new Exception("Empty List");

        Node remNode = removeFirstNode();
        return remNode.data;
    }

    private Node removeLastNode() {

        Node remNode = this.tail;

        if(this.size == 0) {
            this.head = null;
            this.tail = null;

        } else {

            Node secondLastNode = getNodeAt(this.size - 2);
            secondLastNode.next = null;
            this.tail = secondLastNode;
        }
        remNode.next = null; // koi sense hai is baat ki

        this.size--;
        return remNode;
    }

    public int removeLast() throws Exception {

        if(this.size == 0) throw new Exception("Empty List");

        Node remNode = removeLastNode();

        return remNode.data;
    }

    private Node removeNodeAt(int idx) {

        if(idx == 0) return removeFirstNode();

        else if(idx == this.size) return removeLastNode();

        else {
            
            Node prev = getNodeAt(idx - 1);

            Node remNode = prev.next;
            
            prev.next = remNode.next;

            remNode.next = null;

            this.size--;
            return remNode;
        }
    }

    public int removeAt(int idx) throws Exception {

        if(idx < 0 || idx > this.size) throw new Exception("Empty List");

        Node remNode = removeNodeAt(idx);

        return remNode.data;
    }



    // ------------------------------GET-------------------------------


    public int getFirst() throws Exception {

        if(this.size == 0) throw new Exception("Empty List");

        return this.head.data;
    }

    public int getLast() throws Exception {

        if(this.size == 0) throw new Exception("Empty List");

        return this.tail.data;
    }

    private Node getNodeAt(int idx) {

        Node node = this.head;

        while(idx-- > 0) node  = node.next;

        return node;
    }

    public int getAt(int idx) throws Exception {

        if(idx < 0 || idx >= this.size) throw new Exception("NULL Pointer");

        return getNodeAt(idx).data;
    }

}