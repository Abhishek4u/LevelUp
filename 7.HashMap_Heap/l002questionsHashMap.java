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

    // 974 (If you want to print the subarrays too then use this code)
    public int subarraysDivByK(int[] A, int K) {
        
        HashMap<Integer, Integer> map = new HashMap<> ();
        HashMap<Integer, ArrayList<Integer> > sub = new HashMap<> ();
        HashSet<ArrayList<Integer> > set = new HashSet<> ();
        
        // prefix sum map
        map.put(0, 1);
        sub.put(0, new ArrayList<> ());
        sub.get(0).add(0);
        // if subarray from starting contributes so 
        //  and if we have 0 remainder then 1 ans possible
        
        int sum = 0;
        int count = 0;
        int i = -1;
        for(int ele : A) {
            i++;
            sum += ele;
            int val = (sum%K +K)%K;
            
            if(map.containsKey(val)) {
                count += map.get(val);
                
                ArrayList<Integer> idxes = sub.get(val);
                for(int idx : idxes) {
                    ArrayList<Integer> vals = new ArrayList<> ();

                    for(; idx <= i; idx++) {
                        vals.add(A[idx]); 
                    }
                    set.add(vals);
                }
            }
            // if map contains same remainder that means the elts
            // in between are completely divisible by k so add to count
            // ie. no of times the same value appeared 
            
            map.put(val, map.getOrDefault(val, 0) + 1);
            sub.putIfAbsent(val, new ArrayList<> ());
            sub.get(val).add(i+1);
        }
        for(ArrayList<Integer> vals : set) System.out.println(vals);
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

    // 525. Contiguous Array (Longest subarray with Equal no of 0 and 1)
    public int findMaxLength(int[] nums) {
        
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<> ();
        map.put(0, -1);
        // for if subarray starting from 0 makes an answer
        
        int sum = 0;
        int maxLen = 0;
        for(int i = 0;i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            // if 0 is present we will add -1 for that elt
            
            if(map.containsKey(sum)) maxLen = Math.max(maxLen, i - map.get(sum));
                                     
            if(!map.containsKey(sum)) map.put(sum, i);
            // add only once(means dont update) bcz we want maxLength
        }
        return maxLen;
    }

    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s/0#
    // Count Subarrays with equal 1s and 0s 
    public int countSubArrays(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int count = 0;
        int sum = 0;
             
        for(int i = 0; i<nums.length; i++){
            sum  += nums[i] == 1 ? 1 : -1;
            int val = sum - 0;
            if(map.containsKey(val)) count += map.get(val);   
            map.put(sum,map.getOrDefault(sum,0) + 1);
        }         
        return count;
    }

    // 930. Binary Subarrays With Sum
    public int numSubarraysWithSum(int[] A, int S) {
        
        HashMap<Integer, Integer> map = new HashMap <> ();
        map.put(0,1);
        // if prefix sum - S == 0 means we have valid 1 subarray
        
        int sum = 0; // prefix sum
        int count = 0;
        
        for(int ele : A) {
            sum += ele;
            
            if(map.containsKey(sum - S)) count += map.get(sum - S);
            // if contains that val then add to count no of times 
            // that value is present in map
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // Number of subarrays having sum less than K
    // https://www.geeksforgeeks.org/number-subarrays-sum-less-k/
    // --------------------------------SLIDING WINDOW TEHCNIQUE----------------------------
    public int numOfSubArraySumLessThanK(int[] arr, int k) {

        int si = 0, ei = 0;
        int n = arr.length;

        int count = 0;
        int sum = 0;

        // while ei point does not crosses last elt
        while(ei < n) {
            
            sum += arr[ei++];
            while(sum > k && si < ei) {
                // si < ei bcz si can move ahead of ei if val is >= k
                sum -= arr[si++];
            }
            count += (ei - si);
        }
        return count;
    }

    // Leetcode 930 Using Sliding Window

    // Using sliding window less than k method
    // for eg. (k < 10) - (k < 9) will give (k = 10)
    public int numSubarraysWithSum(int[] A, int S) {
        return numOfSubArraySumLessThanK(A, S) - numOfSubArraySumLessThanK(A, (S-1));
    }

    // Leetcode 3. Longest Substring Without Repeating Characters
    // Using 256bits Constant[] space
    // Using array (Fast 2ms)
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        
        int n = s.length();
        int maxLen = 1; // single char
        boolean ch[] = new boolean[256];
        
        int i = 0, j = 0;
        
        while(j < n) {
            // if same char is present then decrease sliding window size
            // until 0 occurence of char at [j] remains
            while(ch[s.charAt(j)] == true && i <= j) {
                ch[s.charAt(i++)] = false;
            }
            // now we will have only 1 occurence of char
            ch[s.charAt(j++)] = true;
            maxLen = Math.max(maxLen, j - i);            
        }
        return maxLen;
    }

    // Using HashSet (Slower 6ms)
    public int lengthOfLongestSubstring2(String s) {
        if(s.length() == 0) return 0;
        
        int n = s.length();
        int maxLen = 1;
        
        HashSet<Character> set = new HashSet<> ();
        
        int si = 0, ei = 0;
        
        while(ei < n) { 
            
            while(set.contains(s.charAt(ei)) && si <= ei) {
                set.remove(s.charAt(si++));
            }            
            set.add(s.charAt(ei++));
            maxLen = Math.max(maxLen, ei - si);
        }
        return maxLen;
    }

    // -------------------------------------19thOct------------------------------------------
    // 76. Minimum Window Substring
    // https://www.youtube.com/watch?v=eS6PZLjoaq8
    public String minWindow(String s, String t) {
        
        int ns = s.length(); // length of s string
        int nt = t.length(); // length of t String
        int freq[] = new int[128];
        for(int i = 0;i < nt; i++) freq[t.charAt(i)]++;
        
        int count = nt;
        int si = 0, ei = 0, len = (int) 1e8;
        int head = 0;
        
        while(ei < ns) {            
            if(freq[s.charAt(ei++)]-- > 0 ) count--;
            // first it checks then increments the value(postIncrement)

            // we will remove elts while we do not get invalid window
            // ie. one elt is not present in list
            while( count == 0 && si < ei) {
                // will remove until we have all chars present
                
                if(ei - si < len) len = ei - (head = si); 
                // update to minimum length
                
                if(freq[s.charAt(si++)]++ == 0) count++;  
                // this condition will be true when we have all chars present
                // and we will remove 1 char to move to next window
            }
        }
        return len == (int) 1e8 ? "" : s.substring(head, head + len);
    }

    //  GFG Smallest distinct window 
    // https://practice.geeksforgeeks.org/problems/smallest-distant-window/0

    // Same as above ques
    public static int smallestDistinctWindow(String s) {
	    int freq[] = new int[128];
	    int count = 0;
	    
	    for(int i = 0;i < s.length(); i++) {
	        if(freq[s.charAt(i)] == 0) {
	            freq[s.charAt(i)]++;
	            count++;
	        }
	    }

	    int n = s.length();
	    int si = 0, ei = 0;
	    int len = (int) 1e8;
	    
	    while(ei < n) {
	        if(freq[s.charAt(ei++)]-- > 0) count--;
	        
	        while(count == 0 && si < ei) {
	            len = Math.min(len, ei - si);
	            
	            if(freq[s.charAt(si++)]++ == 0) count++;
	        }
	    }
	    return len;
	}

    // Leetcode 340 Longest Substring with AtMost K Distinct Chars
    // Lintcode 386. Longest Substring with At Most K Distinct Characters
    // myCode (Same as Sir's Code)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int n = s.length();
        int freq[] = new int[128];
        
        int si = 0, ei = 0;
        int count = 0, maxLen = 0;
        
        while(ei < n) {
            if(freq[s.charAt(ei++)]++ == 0) count++;
            // it will check first then increment frequency
            
            while(count > k && si < ei) {
                if( --freq[s.charAt(si++)] == 0) count--;
                // it will decrement first then check 
            }
            maxLen = Math.max(maxLen, ei - si);
        }
        return maxLen;
    }

    // Leetcode 159 Longest Substring with atmost two distinct characters
    // Lintcode 928. Longest Substring with At Most Two Distinct Characters
    // Same as above (Here k = 2)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Write your code here
        
        int n = s.length();
        int freq[] = new int[128];
        
        int si = 0, ei = 0;
        int count = 0, maxLen = 0;
        
        while(ei < n) {
            if(freq[s.charAt(ei++)]++ == 0) count++;
            // it will check first then increment frequency
            
            while(count > 2 && si < ei) {
                if( --freq[s.charAt(si++)] == 0) count--;
                // it will decrement first then check 
            }
            maxLen = Math.max(maxLen, ei - si);
        }
        return maxLen;
    } 

    // 239. Sliding Window Maximum

    // 1. Using PriorityQueue(Intuitive One) O(nlogn) worst and avg is O(nlogk)
    public int[] maxSlidingWindow2(int[] nums, int k) {        
        int n = nums.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<> ((a, b) -> {
            // arr[i], idx
            return b[0] - a[0];
        });
        
        int ans[] = new int[n - k + 1];
        int idx = 0;
        
        for(int i = 0; i < n; i++) {
            while(pq.size() != 0 && pq.peek()[1] <= i - k) pq.poll();
            // it will remove all larger elts that are not in current window
            // it will not remove smaller elts but we need only larger elts
            // so those elts will never be added in answer
            
            pq.add(new int[] {nums[i], i});
            // add elt and index in array
            
            if(i >= k - 1) ans[idx++] = pq.peek()[0];     
            // fill the answer array after moving k elts ahead(window size)
        }  
        return ans;
    }
    
    // 2. Optimization using Deque O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        int n = nums.length;
        
        ArrayDeque<Integer> que = new ArrayDeque<> ();
        // que of index
        
        int ans[] = new int[n - k + 1];
        int idx = 0;
        
        for(int i = 0; i < n; i++) {
            
            while(que.size() != 0 && que.getFirst() <= i - k) que.removeFirst();
            // remove elts which are out of range of current window size
            
            while(que.size() != 0 && nums[que.getLast()] <= nums[i]) que.removeLast();
            // removeLast elts and if elts are smaller than current elt then remove
            // as we want to store only largest elts in que for current window
            // REMOVEFRONT WILL NOT REMOVE ALL SMALLER ELTS SO USE REMOVELAST HERE
            // eg . [1,3,1,2,0,5] , k = 3
            que.addLast(i);
            
            if( i >= k - 1) ans[idx++] = nums[que.getFirst()];
        }
        return ans;
    }

    // https://www.geeksforgeeks.org/length-largest-subarray-contiguous-elements-set-1/
    // without repetition of number
    public static void largestSubArray(int[] arr) {
        int len = 0;
        int n = arr.length;

        int min = 0, max = 0;
        int len = 0;

        for(int i = 0;i < n; i++) {
            min = max = arr[i];

            for(int j = i+1; j < n; j++) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                if(max - min == j - i) {
                    len = Math.max(len, j - i);
                    // if elts are contigous then their difference will 
                    // be equal to difference of idx of subarray
                }
                
            }
        }
        return len;
    }

    // with repitition of number
    // same as above one but if no is repeated in any subarray then
    // we will discard that subarray
    public static void largestSubArray(int[] arr) {
        int len = 0;
        int n = arr.length;

        int min = 0,max = 0;
        int len = 0;

        HashSet<Integer> set = new HashSet<> ();

        for(int i = 0;i < n; i++) {
            min = max = arr[i];
            set.add(arr[i]);

            for(int j = i+1; j < n; j++) {
                if(set.contains(arr[j])) {
                    // no is repeated so we will discard this subarray
                    break;
                }
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                if(max - min == j - i) {
                    len = Math.max(len, j - i);
                }
            }
            set.clear();
            // clear the set for reuse in next subarray
        }
        return len;
    }

    

}