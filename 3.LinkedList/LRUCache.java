// Class on 31stAug
// SS6459 or SS14579

public class LRUCache {
    //-----------------------lru-cache----------------
    //------------think like apps in mobile in recent apps-------------

    
    // HERE WE WILL USE NODE AS DOUBLY-LINKED-LIST FOR STORING RECENT APPS AND 
    //  FOR FRONT WE WILL USE TAIL (FOR ADDITION OR UPDATION OF LIST OF APP THAT IS OPENED RECENTLY)
    //  FOR BACK WE WILL USE HEAD (FOR REMOVAL OF LRU APP)

    // HASHMAP TO STORE THE ADDRESS OF EACH NODE SO WE CAN ACCESS THEM IN O(1)

    private class Node {

        int key;
        int value;
        
        Node next = null;
        Node prev = null;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head = null;
    private Node tail = null;

    private int size = 0;
    private int maxSize = 0;

    HashMap<Integer, Node> map = new HashMap <> ();
    
    // --------------CONSTRUCTOR-------------
    public LRUCache(int capacity) {
        this.maxSize = capacity;
        this.size = 0; // initial size
    }
    
    // ----TO GET THE VALUE AND PUT THE APP IN FRONT----
    public int get(int key) {
        if( !map.containsKey(key) ) return -1;

        Node node = map.get(key);
        removeNodeFromLL(node); // remove Node from linkedList

        addLast(node); // add node to last of ll(we treat it as front like mobile)

        return node.value;
    }
    
    // ----TO UPDATE THE VALUE OF APP OR PUT NEW APP AND CHANGE THE LOCATION OF APP TO FRONT----
    public void put(int key, int value) {

        if( map.containsKey(key) ) {
            // if map has key then put in front and update value
            Node node = map.get(key);

            removeNodeFromLL(node);
            addLast(node);

            if(node.value != value) {
                this.tail.value = value;
                // node.value = value;
            }
        } else {
            // if not present then add in ll and in map
            Node node = new Node(key, value);
            addLast(node);
            map.put(key, node);

            if(this.size > this.maxSize) {
                // remove least used app ( node pointing to head )
                Node remNode = this.head;

                removeNodeFromLL(remNode);
                map.remove(remNode.key); 
                // remove the key of this node from hashMap
            }
        }
    }
        
    public void addLast(Node node) {

        if(this.head == null) {
            this.head = node;
            this.tail = node;

        } else {
            this.tail.next = node;
            node.prev = this.tail;

            this.tail = node;
        }

        this.size++;
    }

    public void removeNodeFromLL(Node node) {

        if(this.size == 1) {
            this.head = null;
            this.tail = null;

        } else if(this.head == node) {
            this.head = this.head.next;
            
            this.head.prev = null;
            node.next = null;

        } else if(this.tail == node) {
            this.tail = node.prev;

            this.tail.next = null;
            node.prev = null;
            
        } else {
            Node prev = node.prev;
            Node next = node.next;

            prev.next = next;
            next.prev = prev;

            node.next = null;
            node.prev = null;
        }
        this.size--;
    }
    
}