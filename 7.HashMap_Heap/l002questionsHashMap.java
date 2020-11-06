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

    // Leetcode 380. Insert Delete GetRandom O(1)
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
    
    // https://www.geeksforgeeks.org/maximum-consecutive-ones-or-zeros-in-a-binary-array/
    public static int getMaxLength(boolean arr[], int n) { 
        int count = 0;
        int ans = 0;

        for(int i = 0;i < n; i++) {
            
            if(arr[i] == 1) {
                count++;
                ans = Math.max(ans, count);

            } else {
                count = 0;              
            }
        }
        return ans;
    }

    // 895. Maximum Frequency Stack
    class FreqStack {
        // LevelUP 11Oct
        HashMap<Integer, Integer> freq;
        HashMap<Integer, Stack<Integer>> map;
        int maxFreq;
        
        public FreqStack() {
            this.freq = new HashMap<> ();
            this.map = new HashMap<> ();
            this.maxFreq = 0;
        }
        
        public void push(int x) {
            
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            maxFreq = Math.max(maxFreq,freq.get(x));
            
            map.putIfAbsent(freq.get(x), new Stack<> ());
            
            map.get(freq.get(x)).push(x);
        }
        
        public int pop() {
            
            int rv = map.get(maxFreq).pop(); // return value
            freq.put(rv, freq.get(rv) - 1);
            
            if(freq.get(rv) == 0) freq.remove(rv);
            // if not removed then also works
            
            if(map.get(maxFreq).size() == 0) {
                map.remove(maxFreq);
                maxFreq--;
            }
            return rv;
        }
    }

    //https://www.geeksforgeeks.org/check-two-strings-k-anagrams-not/
    //Sol 1 O(nlogn)
    public static boolean kAnagram(String s1,String s2){
        if(s1.length() != s2.length()) return false;
        
        sort(s1);
        sort(s2);
        int count = 0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i) != s2.charAt(i) && ++count > k) return false;
        }
        return true;
    }

    // Sol 2 O(n) 
    public static boolean kAnagram(String s1,String s2){
        if(s1.length() != s2.length()) return false;
        
        int[] f1 = new int[26];

        for(int i=0;i<s1.length();i++) f1[s1.charAt(i) - 'a']++;
        for(int i=0;i<s1.length();i++) f1[s2.charAt(i) - 'a']--;
        
        int a = 0, b = 0;
        for(int i=0;i<f1.length;i++) {
            if(f1[i] > 0) 
                a += f1[i];
            else
                b += Math.abs(f1[i]);
        }
        
        return b <= k && a >= b;
    }

    // https://www.geeksforgeeks.org/check-anagram-string-palindrome-not/

    // For anagram palindrome every char's freq should be even
    // and for odd length one char can have 1 freq so we will
    // use condition greater than 1 
    // eg : aabaa -> 1 odd length elts(true), aabcaa -> 2 odd length elts(false)

    public static boolean palindromeAnagram(String str){
        int n = str.length();
        int[] freq = new int[26];
        for(int i=0;i<n;i++) freq[str.charAt(i) - 'a']++;

        int oddCount = 0;
        for(int i=0;i<26;i++){
            if((freq[i] % 2) != 0 && ++oddCount > 1) return false;   
            // if first condition satisfies then only increments
        }

        return true;
    }

    // https://www.geeksforgeeks.org/check-if-frequency-of-all-characters-can-become-same-by-one-removal/
    public static boolean sameFreq(String s) {
        
        HashMap<Character, Integer> map = new HashMap<> ();
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        int v1 = 0, count1 = 0, v2 = 0, count2 = 0;
        // v1 and v2 shows 2 values present in hashMap
        // count1 and count2 shows there count
        
        for(char ch : map.keySet()) {
            if(v1 == 0) {
                v1 = map.get(ch);
                count1 = 1;
                
            } else if(v2 == 0 && map.get(ch) != v1) {
                v2 = map.get(ch);
                count2 = 1;
                
            } else if(map.get(ch) == v1) 
                count1++;
              else 
                count2++;
            
            if(count1 > 1 && count2 > 1) return false;
            // if both exceeds the count of 1 means more than 1 values are present
            // which are different
        }
        
        if(count1 == 1 && (v1 - 1 == 0 || v1 - 1 == v2)) return true; 
        // if v1 can be decreased to make equal frequencies
        if(count2 == 1 && (v2 - 1 == 0 || v2 - 1 == v1)) return true; 
        // if v2 can be decreased to make equal frequencies
        
        if(count1 == 0 || count2 == 0) return true;
        // only one frequency is present so true
        
        return false;
        // remaining conditions will give false output
    }

    // 407. Trapping Rain Water II
    // 3d trap water ques

    public int trapRainWater(int[][] arr) {        
        if(arr.length == 0 || arr[0].length == 0) return 0;
        
        int n = arr.length, m = arr[0].length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<> ( (a,b) -> {
            return arr[a/m][a%m] - arr[b/m][b%m];
        });
        
        boolean[][] vis = new boolean[n][m];
        int water = 0;
        int boun = 0; //  Max Boundary
        
        for(int i = 0;i < n; i++) {
            for(int j = 0;j < m; j++) {
                
                if(i == 0 || i == n-1 || j == 0 || j == m-1) {
                    pq.add(i*m + j);
                    vis[i][j] = true;
                }
            }
        }        
        int dir[][] = { {1,0}, {-1,0}, {0,1}, {0,-1} };
        while(pq.size() != 0) {
            int idx = pq.poll();
            
            int r = idx/m;
            int c = idx%m;
            
            water += Math.max(0, boun - arr[r][c]);
            boun = Math.max(boun, arr[r][c]);
            
            for(int d[] : dir) {
                int x = r + d[0];
                int y = c + d[1];
                
                if(x >= 0 && y >= 0 && x < n && y < m && !vis[x][y]) {                    
                    vis[x][y] = true;
                    pq.add(x*m + y);
                }
            }
        }        
        return water;
    }

    // 781. Rabbits in Forest 
    // 1st Code O(n) Simple
     public int numRabbits(int[] answers) {
        
        HashMap<Integer, Integer> map = new HashMap<> ();     
                
        int ans = 0;       
        for(int ele : answers) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
            // initialize a new color
            
            if(map.get(ele) == 1) ans += ele + 1;
            // +1 bcz of own count            
            if(map.get(ele) == ele+1) map.remove(ele);
            // current color count is completed so remove it
        }
        return ans;
    }
    // 2nd Code O(2n) Sumeet Sir Video
    public int numRabbits(int[] answers) {
        
        HashMap<Integer, Integer> map = new HashMap<> ();       
        for(int ele : answers) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        
        int ans = 0;
        for(int key : map.keySet()) {

            int gs = key + 1; // group size
            int reportees = map.get(key);
            
            int ng = (int) Math.ceil(reportees / (gs * 1.0));
            // new group
            ans += ng * gs;
        }
        return ans;
    }

    // 560. Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        if(nums.length == 0) return 0;
        
        HashMap<Integer, Integer> map = new HashMap<> ();
        
        map.put(0, 1) ;
        // bcz if subarray from starting equals to k 
        // means we have 1 possible subarray (sum - k) = 0 
        
        int sum = 0;
        int count = 0;
        
        for(int ele : nums) {
            sum += ele;
            
            if(map.containsKey(sum - k)) count += map.get(sum - k);
            // + value bcz array can contains negative elts and same value
            // could be there in different idx of prefix sum[] after addition
            // and those also will be treated as subarrays      
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            // put curr sum in last bcz it can chage ans too 
            // eg : [1] elt is there only and it will count itself
        }
        return count;
    }

    // Leetcode 974 Subarray Sums Divisible by K
    public int subarraysDivByK(int[] A, int K) {
        
        HashMap<Integer, Integer> map = new HashMap<> ();
        // prefix sum map
        map.put(0, 1);
        // if subarray from starting contributes so 
        //  and if we have 0 remainder then 1 ans possible
        
        int sum = 0;
        int count = 0;
        
        for(int ele : A) {
            
            sum += ele;
            int val = (sum%K +K)%K;
            
            if(map.containsKey(val)) count += map.get(val);
            // if map contains same remainder that means the elts
            // in between are completely divisible by k so add to count
            // ie. no of times the same value appeared 
            
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return count;
    }

    // https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k/0
    public static void main (String[] args) {
        Scanner scn = new Scanner(System.in);
	    int t = scn.nextInt();
	    while(t-->0){
	        int n = scn.nextInt();
	        int k = scn.nextInt();
	        
	        HashMap<Integer,Integer> map = new HashMap<> ();
	        map.put(0, -1);
	        // bcz for index for subarray from starting 
	        
	        int maxLen = 0;
	        int sum = 0;
	        
	        for(int i = 0;i < n; i++) {
	            sum += scn.nextInt();
	            int val = (sum - k);
	            
	            if(map.containsKey(val)) maxLen = Math.max(maxLen, i - map.get(val));
	            
	            if(!map.containsKey(sum)) map.put(sum, i);
	            // we want maxLen so we will push only 1st idx of every subarray sum
	        }
	        System.out.println(maxLen);
	    }
	}

    // https://practice.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by-k1259/1
    int longSubarrWthSumDivByK(int a[], int n, int k) {
        HashMap<Integer,Integer> map = new HashMap<> ();
        map.put(0, -1);
        // bcz for index for subarray from starting 

        int maxLen = 0;
        int sum = 0;
        
        for(int i = 0;i < n; i++) {
            sum += a[i];
            int val = (sum%k + k)%k;
            
            if(map.containsKey(val)) maxLen = Math.max(maxLen, i - map.get(val));
            
            if(!map.containsKey(val)) map.put(val, i);
            // we want maxLen so we will push only 1st idx of every subarrayleftout remainder
        }
        return maxLen;
    }

    

}