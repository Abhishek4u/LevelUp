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
            // distance formula -> (x2 - x1)^2 + (y2 - y1)^2
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

    // Screenshot 12302 ownwards

    // 1. Sir's Code (Without division)
    public boolean isReflected(int[][] points) {
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        HashSet<String> map = new HashSet<> ();
        
        for(int []p : points) {
            min = Math.min(min, p[0]);
            max = Math.max(max, p[0]);
            
            String str = p[0] + "@" + p[1];
            map.add(str);
            
        }
        
        int sum = (min + max);
        for(int []p : points) {
            
            String str = (sum - p[0]) + "@" + p[1];
            
            if(!map.contains(str)) return false;
        }
        return true;
    }
    // 2. Modified Sir's Code
    public boolean isReflected(int[][] points) {
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        HashSet<String> plane = new HashSet<> ();
        
        for(int pts[] : points) {
            
            min = Math.min(pts[0], min);
            max = Math.max(pts[0], max);
            
            plane.add(pts[0] + "@" + pts[1]);
        }
        
        for(int pts[] : points) {
            
            int reflectedX = pts[0] - min;
            reflectedX = max - reflectedX;
            
            if(!plane.contains(reflectedX + "@" + pts[1])) return false;
        }
        
        return true;
    }

    // 3. My Code (Using division operator)
    public boolean isReflected(int[][] points) {
        // Write your code here
        HashSet<String> set = new HashSet<> ();
        float mid = 0;
        for(int pts[] : points) {
            set.add(pts[0] + "@" + pts[1]);
            
            mid += pts[0];
        }
        mid /= points.length;

        for(int pts[] : points) {
            
            float x = mid - pts[0];
            x = x + mid;
            float y = pts[0] - mid ;
            y = mid - y;
            
            String str = (int)(x) + "@" + pts[1];
            String str2 = (int)(y) + "@" + pts[1];

            if(!set.contains(str) && !set.contains(str2)) return false;
        }
        return true;
    }
    
    
    public int trap(int[] height) {
        if(height.length <= 2) return 0;
        
        int n = height.length;        
        int lMax = 0, li = 0, rMax = 0, ri = n-1;
        
        int ans = 0;
        while(li <= ri) {
            lMax = Math.max(lMax, height[li]);
            rMax = Math.max(rMax, height[ri]);
            
            if(lMax <= rMax ){
                ans += lMax - height[li++];
                // no need to check for negative value as we are always
                // updating the lMax and rMax with current idx value
            } else {
                // else move right side
                ans += rMax - height[ri--];
            }
        }
        return ans;
    }
}