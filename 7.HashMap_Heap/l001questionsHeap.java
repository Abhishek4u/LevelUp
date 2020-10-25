public class l001questionsHeap {

    // Leetcode 215 Kth Largest Element in an Array

     public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<> ();
        
        for(int ele : nums) {
            pq.add(ele);
            
            if(pq.size() == k+1) pq.poll();
        }
        return pq.poll();
    }

    // Leetcode 703. Kth Largest Element in a Stream
    class KthLargest {
        // LevelUp Oct10
        PriorityQueue<Integer> pq = new PriorityQueue<> ();
        int size = 0;
        
        public KthLargest(int k, int[] nums) {
            
            this.size = k;
            for(int ele : nums) {
                pq.add(ele);
                
                if(pq.size() > this.size) pq.poll();
            }
        }
        
        public int add(int val) {
            
            pq.add(val);   
            
            // for maintaining k elts pop one elt
            if(pq.size() > this.size) pq.poll();  
            
            // return kth largest elt
            return pq.peek();
        }
    }

    // 378. Kth Smallest Element in a Sorted Matrix
    public int kthSmallest(int[][] matrix, int k) {
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<> ((a,b) -> {
            return matrix[a / m][a % m] - matrix[b / m][b % m];
        });
        
        for(int i = 0; i < n ;i++) pq.add(i * m + 0);
        // all first elt of every row
        
        while(--k > 0) {
            int idx = pq.poll();
            
            int r = idx / m;
            int c = idx % m;
            
            c++; // move to next column
            if(c < m) pq.add(r * m + c);
        }
        
        int idx = pq.poll();
        
        return matrix[idx / m][idx % m];
    }

    // 973. K Closest Points to Origin
    public int[][] kClosest(int[][] points, int K) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<> ((a,b) -> {
            // distance formula
            int p1 = (a[1] - 0)*(a[1] - 0) + (a[0] - 0)*(a[0] - 0);
            int p2 = (b[1] - 0)*(b[1] - 0) + (b[0] - 0)*(b[0] - 0);
            
            return p2 - p1;  
        });
        
        for(int[] ar : points) {
            pq.add(ar);
            
            if(pq.size() == K+1) pq.poll();
        }
        
        int ans[][] = new int[K][2];
        
        int i = 0;
        while(pq.size() != 0) ans[i++] = pq.poll();
        
        return ans;
    }

    // Sort a nearly sorted (or K sorted) array
    // https://www.geeksforgeeks.org/nearly-sorted-algorithm/

    public static void nearlySorted(int[] arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        k++;

        int n = arr.length;
        int idx = 0; 
        for(int i = 0; i < n;i++){
            pq.add(arr[i]);
            if(pq.size() > k){
                arr[idx++] = pq.poll();
            }
        }

        while(pq.size()!=0) arr[idx++] = pq.poll();
    }

    // Leetcode 356 Line Reflection
    // Lintcode 908

    public boolean isReflected(int[][] points) {
        HashSet<Sintr> map new HashMap<> ();
        int min = (int) 1e9;
        int max = -(int) 1e9;

        for(int [] p : points) {
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);

            String str = p[0] + @ + p[1];
            map.add(str);
        }

        int sum = max + min;
        for(int[] p : points) {
            String str = (sum - p[0]) + @ p[1];

            if(!map.contains(str)) return false;
        }
        return true;
    }
}