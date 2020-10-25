public class l002questionsHashMap {

    // 349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        
        HashSet<Integer> map = new HashSet<>();
        for(int ele: nums1) map.add(ele);
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int ele: nums2){
            if(map.contains(ele)){
                list.add(ele);
                map.remove(ele);
            }
        }
        
        int[] ans = new int[list.size()];
        for(int i=0;i<ans.length;i++) ans[i] = list.get(i);
        
        return ans;
    }

    // 350. Intersection of Two Arrays II
    public int[] intersect(int[] nums1, int[] nums2) {

        HashMap<Integer,Integer> map = new HashMap<>();
        for(int ele: nums1) map.put(ele, map.getOrDefault(ele, 0) + 1);

        ArrayList<Integer> list = new ArrayList<>();
        for(int ele: nums2){

            if(map.containsKey(ele)){

                map.put(ele,map.get(ele) - 1);
                list.add(ele);
                
                if(map.get(ele) == 0) map.remove(ele);
            }
        }
        
        int[] ans = new int[list.size()];
        for(int i=0;i<ans.length;i++) ans[i] = list.get(i);
        
        return ans;
        
    }

    // 128. Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        
        for(int ele : nums) map.add(ele);
        
        if(map.size() == 2 && 
           map.contains(Integer.MIN_VALUE) && map.contains(Integer.MAX_VALUE)) return 1;
        // corner case where corner elts(int_min, int_max) are present and if incr/decrement
        // they will become ( 'min changes to max' and 'max changes to min' ) and will give wrong answer
        
        int len = 0;
        for(int ele : nums){
            if(!map.contains(ele)) continue;
            
            map.remove(ele);
            int prev = ele-1;
            int next = ele+1;
            
            while(map.contains(prev)) map.remove(prev--);
            while(map.contains(next)) map.remove(next++);
            
            len = Math.max(len,next - prev - 1);
        }        
        return len;
    }

    // 347. Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<> ();
        
        for(int ele : nums ) {            
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<> ( (a,b) -> {
            return map.get(a) - map.get(b);
        });
        
        for(int ele : map.keySet()) {
            pq.add(ele);
            if(pq.size() == k+1) pq.poll();
            
        }
        int ans[] = new int[pq.size()];
        int idx = 0;
        while(pq.size() != 0) ans[idx++] = pq.poll();
        
        return ans;
    }

    // https://www.geeksforgeeks.org/check-whether-arithmetic-progression-can-formed-given-array/

    // https://practice.geeksforgeeks.org/problems/check-arithmetic-progression/0#
    // Check Arithmetic Progression
    
    // 1. Simple method using PriorityQueue
    public static void checkAP() {

        while(t-- > 0) {
		    
		    int n = scn.nextInt();
		    PriorityQueue<Integer> pq = new PriorityQueue<> ();
		    
		    for(int i = 0; i < n; i++) {
		        
		        pq.add(scn.nextInt());
		    }
		    if(pq.size() <= 2) {
		        System.out.println("YES");
		        continue;
		    }
		    
		    int a = pq.poll(), b = pq.poll();
		    int diff = b - a;
		    
		    boolean isValid = true;
		    while(pq.size() != 0) {
		        a = b;
		        b = pq.poll();
		        if(b - a != diff) {
		            isValid = false;
		            break;
		        }
		    }
		    if(isValid) System.out.println("YES");
		    else System.out.println("NO");
		}
    }

    // 2. Using HashMap and PriorityQueue 
    public void mainsolve () {
        Scanner scn = new Scanner(System.in);

        int t = scn.nextInt();
        while(t-->0){
            int n = scn.nextInt();
            int[] arr = new int[n];

            HashSet<Integer> map = new HashSet<>();

            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
                return b - a;    
            });

            for(int i = 0; i < n;i++){
                arr[i] = scn.nextInt();
                map.add(arr[i]);
                pq.add(arr[i]);
                
                if(pq.size() > 2) pq.remove();
            }

            if(n <= 2) {
                System.out.println("YES");
                continue;
                
            }

            int a = pq.poll();
            int b = pq.poll();

            int d = a - b;
            int ele = b + d;
            int count = 1;
            while(map.contains(ele)){
                map.remove(ele);
                count++;
                ele += d;
            }

            System.out.println((count == n) ? "YES":"NO");
        }
    }

    // Leetcode 760. Find Anagram Mappings
    // Lintcode 813. Find Anagram Mappings
    public int[] anagramMappings(int[] A, int[] B) {
        HashMap<Integer, List<Integer> > map = new HashMap<> ();
        
        int n = A.length;
        for(int i = 0; i < n; i++) {
            
            map.putIfAbsent(B[i], new ArrayList<Integer> ());
            
            map.get(B[i]).add(i);
            // add index in list 
        }
        
        int ans[] = new int[n];
        for(int i = 0; i < n ;i++) {
            
            List<Integer> indices = map.get(A[i]);
            ans[i] = (indices.remove(indices.size() - 1));

            if(indices.size() == 0) map.remove(A[i]);
            // for decreasing time complexity
        }
        return ans;
    }

    class RandomizedSet {
        // LevelUP Oct11
        HashMap<Integer,Integer> map = new HashMap<> ();;
        ArrayList<Integer> list = new ArrayList<>();
        
        /** Initialize your data structure here. */
        public RandomizedSet() {

        }
        
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)) return false;
            
            map.put(val, list.size()); 
            list.add(val);
            
            return true;
        }
        
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)) return false;
            
            int targetIndex  = map.get(val);
            int lastIndex = list.size()-1;
            
            int lastIdxVal = list.get(lastIndex);
            
            map.put(lastIdxVal, targetIndex);     
            map.remove(val);
            // add first then remove(bcz if 1 elt left then it will swap it
            // with itself and if you remove and add then elt will not be removed)
            
            list.set(targetIndex ,lastIdxVal);   
            // update the index of swapped value     
            list.remove(lastIndex);   
            // same for map too (first add and then remove)     
        
            return true;
        }
        
        /** Get a random element from the set. */
        public int getRandom() {
            
            int randIdx = (int) (Math.random() * (list.size())) ;
            return list.get(randIdx);
        }
    }

}