public class questions {

    // Leetcode 74
    // https://leetcode.com/problems/search-a-2d-matrix/

    // 1. Using binary search
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        
        int n = matrix.length, m = matrix[0].length;
        
        int si = 0, ei = n*m-1;
        
        while(si <= ei) {
            int mid = (si + ei) / 2;
            int i = mid / m;
            int j = mid % m;
            
            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] > target) ei = mid - 1;
            else si = mid + 1;
        }
        return false;
    }

    // 2. Using two pointers approach
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        
        int n = matrix.length, m = matrix[0].length;
        
        int si = 0, ei = matrix[0].length - 1;
        
        while(si < n && ei >= 0) {
            
            if(matrix[si][ei] == target) return true;
            else if(matrix[si][ei] > target) ei = ei - 1;
            else if(matrix[si][ei] < target) si = si + 1;
        }
        return false;
    }

    // 34. Find First and Last Position of Element in Sorted Array
    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

    public int[] searchRange(int[] nums, int target) {        
        int ans[] = {findFirst(nums, target), findLast(nums, target)};
        
        return ans;
    }
    
    public int findFirst(int[] arr, int ele) {        
        int si = 0, ei = arr.length - 1;
        
        while(si <= ei) {            
            int mid = (si + ei) / 2;
            
            if(arr[mid] == ele) {                
                if(mid-1 >= 0 && arr[mid-1] == ele) ei = mid - 1;
                else return mid;
                
            } else if(arr[mid] > ele) ei = mid - 1;
            
            else si = mid + 1;
        }
        return -1;
    }
    
    public int findLast(int[] arr, int ele) {        
        int si = 0, ei = arr.length - 1;
        
        while(si <= ei) {            
            int mid = (si + ei) / 2;
            
            if(arr[mid] == ele) {
                if(mid + 1 < arr.length && arr[mid+1] == ele) si = mid + 1;
                else return mid;
                
            } else if(arr[mid] > ele) ei = mid - 1;
            
            else si = mid + 1;
        }
        return -1;
    }

    // 658. Find K Closest Elements
    // https://leetcode.com/problems/find-k-closest-elements/
    // O(logn + k) (k can reach to n) so O(n)
    public List<Integer> findClosestElements(int[] A, int k, int x) {
        List<Integer> ans = new ArrayList<> ();
        
        for(int ele : A) ans.add(ele);
        
        int n = A.length;
        
        if(x <= A[0]) return ans.subList(0, k);
        else if(x >= A[n-1]) return ans.subList(n - k, n);
        else {
            int idx = Collections.binarySearch(ans, x);
            // binary search works only in list
            
            if(idx < 0) {                
                idx = -idx - 1;
                // when elt not found bs return its (-ve)insertion point - 1
                // so we need to correct it
                // ie : idx = (-idx - 1);
                // for correct value put idx = (-idx - 1)
                // ie : idx = (- (-idx - 1) -1)
                // idx = (idx + 1 - 1)
                // idx = idx
            }
            
            // we will use k  far indexes in both si and ei bcz array idx can be on right side or left side
            // and k/2 elts cannot be there in both sides so we have to use k instead of (k/2) + 1
            // eg : [1,2,3,4,5,6] , x = 2.5, k = 6
            // and here if we have used k/2 + 1 then on left side of 2 there are no k/2 elts present 
            // which means we have to consider right sides elts too so we will use k for both si and ei points

            // Now set points to k points further from idx (if possible)
            int si = Math.max(0, idx - k);
            // if not possible to extend till -k we have to extend to 0th index
            int ei = Math.min(n-1, idx + k);
            // same in right if possible to extend to that point then extend it or 
            // extend it till last index of element
            
            while(ei - si > k - 1 ) {
                // we want k elts so will stop loop when ei - si > k - 1
                // bcz ei and si are indexes and they have one more elt than their value
                // eg : k = 5, ei(4) - si(0) = 4 but elts are A[0], A[1], A[2], A[3], A[4] ie 5 elts
                // so we will use k - 1 for discarding extra elt ie(5 - 0 == 5) which is wrong answer
                if(x - A[si] > A[ei] - x) {
                    si++;
                    // difference of left-side elt is greater than right side
                    // so it means we have to discard this elt bcz we want elt nearer to x
                } else ei--;
                // means diff of right side elt is greater than = x so decrement
            }
            return ans.subList(si, ei+1);           
            // return the exact k elts sublist now (ie. elts from si to ei(included))
        }
    }

    // 300. Longest Increasing Subsequence
    // https://leetcode.com/problems/longest-increasing-subsequence/

    // Using Binary-Search
    // O(nlogn) (only gives length )
    public int lengthOfLIS(int[] nums) {
        if(nums.length <= 1) return nums.length;
        
        List<Integer> list = new ArrayList<> ();
        list.add(nums[0]);
        
        for(int i = 1; i < nums.length; i++) {
            
            int idx = Collections.binarySearch(list, nums[i]);
            if(idx >= 0) {
                // means elt is already present in list so no need
                // to add again 
                continue;
            }
            idx = - idx - 1; 
            // Collections binarySearch gives encoded value when elt not found in list
            // and ie (- idx - 1) so we can get correct index using ( - (-idx - 1) -1) 
            // ie idx
            
            if(idx == list.size() ) list.add(nums[i]); // means elt is biggest so add in last
            else list.set(idx, nums[i]); // means elt is smaller than present elt so update the elt
        }
        
        return list.size(); 
        // the size of list is actual the length of longest increasing subsequence
    }

    // Using own BinarySearch O(nlogn)
    public int lengthOfLIS(int[] nums) {
        if(nums.length <= 1) return nums.length;
        
        List<Integer> list = new ArrayList<> ();
        list.add(nums[0]);
        
        for(int i = 1; i < nums.length; i++) {            
            int si = 0, ei = list.size();            
            while(si < ei) {
                int mid = (si + ei) >> 1;
                
                if(list.get(mid) < nums[i]) si = mid + 1;
                else ei = mid;
            }
            int idx = ei;
            
            if(list.size() == idx) list.add(nums[i]);
            else list.set(idx, nums[i]);
        }        
        return list.size();
    }

    // -----------------------------------------------------------

    // GFG https://www.geeksforgeeks.org/counting-inversions/
    // 1. My Code (Same as sir's Code ) ) O(nlogn)
    static long inversionCount(long arr[], long N)
    {
        // Your Code Here
        if(N <= 1) return 0;
        return inversionCountUsingMergeSort(arr, 0, (int)N-1);
    }
    
    public static long inversionCountUsingMergeSort(long arr[], int si, int ei) {
        if(si >= ei) return 0;
        
        int mid = (si + ei) / 2;

        long count = 0;

        count += inversionCountUsingMergeSort(arr, si, mid);
        count += inversionCountUsingMergeSort(arr, mid+1, ei);

        count += totalInversionCount(arr, si, mid, ei);
        // this will merge sorted arrays and also count the number of inversions happened at this state

        return count;
    }
    
    public static long totalInversionCount(long arr[], int si, int mid, int ei) {
        long count = 0;

        int i = si; // left subarray of merge sort(sorted array) starting index
        int j = mid+1; // right subarray starting index (sorted array) starting index

        long temp[] = new long[ei - si + 1]; // to store merge sorted arrays
        int k = 0; // used for moving in temp[]

        while(i <= mid && j <= ei) {
            // will run this loop till end of both sorted arrays
            
            if(arr[i] <= arr[j] ) {
                temp[k++] = arr[i++];
                // left subarray elt is smaller that right[] so we can move that
                // as it will not do inversions with right[] elts bcz all elts above j are also
                // bigger than j elt (which is bigger than i elt)

            } else if(arr[j] < arr[i]) {
                // number of elts present in left subarray (from i to mid)
                // will do inversion so add in count
                count += ((mid + 1) - i); // mid + 1 bcz we want length of elts 
                temp[k++] = arr[j++]; // now we counted inversions
                // so we can move to next point in right[] bcz the elt at i point
                // can create inversions with j+1 too so we will not move i 
            }
        }

        while( i <= mid) temp[k++] = arr[i++];
        // put left out elts in sorted[]
        while( j <= ei) temp[k++] = arr[j++];

        // Now copy all elts of temp[] array in arr[] bcz we want to sort array[] too
        int idx = si;
        k = 0;

        while( k < temp.length) arr[idx++] = temp[k++];

        return count; // return the number of inversions
    }

    // 2. Sir's Code
    public static int inversionCount_(int[] arr,int[] sortedArray,int si,int ei){
        if(si >= ei) return 0;
        
        int mid = (si + ei) / 2;
        int count = 0;
        
        count += inversionCount_(arr,sortedArray,si,mid);
        count += inversionCount_(arr,sortedArray,mid + 1,ei);

        count += totalInversion(arr,sortedArray,si,mid + 1, ei);
        return count;
    }

    public static int totalInversion(int[] arr,int[] sortedArray,int si,int mid,int ei){
        int count = 0;
        int i = si, j = mid, k = si;
        while(i <= mid - 1 && j <= ei){
            if(arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else{
                sortedArray[k++] = arr[j++];
                count += mid - i;
            }
        }

        while(i <= mid - 1) sortedArray[k++] = arr[i++];
        while(j <= ei) sortedArray[k++] = arr[j++];

        while(si <= ei) arr[si] = sortedArray[si++]; 
        return count;
    }

    public static int inversionCount(int[] arr){
        if(arr.length <= 1) return 0;

        int n = arr.length;
        int[] sortedArray = new int[n];

        return inversionCount_(arr,sortedArray,0,n-1);
    }

    // Leetcode 240
    // https://leetcode.com/problems/search-a-2d-matrix-ii/
    // O(n+m)
    public boolean searchMatrix(int[][] mat, int target) {
        if(mat.length == 0 || mat[0].length == 0) return false;
        
        int n = mat.length, m = mat[0].length;
        
        int i = 0, j = m - 1;
        
        while( i < n && j >= 0) {
            if(mat[i][j] == target) return true;
            
            else if(mat[i][j] < target) i++;
            else if(mat[i][j] > target) j--;
        }
        return false;
    }

    // 875. Koko Eating Bananas
    // https://leetcode.com/problems/koko-eating-bananas/
    // SS13085

    // In this we will decide speed of eating using binary search 
    // and check if it can eat in current speed then we will decrease ei = mid 
    // ei = mid bcz mid is also a answer
    // if we cannot eat then will do si = mid+1 bcz at mid it cannot eat so discard that point
    public int minEatingSpeed(int[] piles, int H) {
        
        int si = 1, ei = 1000000;
        // SI : -> set si = 1 bcz koko can eat min 1 banana
        // EI : -> either find max elt in array or use max range
        // bcz binary search will make it correct in some iterations
        
        while(si < ei) {
            
            int eatingSpeed = (si + ei) >> 1;
            
            if( isPossibleToEat(piles, eatingSpeed, H) ) ei = eatingSpeed;
            // ei will point to valid speed every time
            else si = eatingSpeed + 1;
        }
        return ei;
    }
    
    public boolean isPossibleToEat(int[] piles, int speed, int timeLimit) {
        
        int currTime = 0;
        for(int bananas : piles) {
            
            // currTime += (int)Math.ceil(bananas/(speed * 1.0));
            currTime += ( ((bananas - 1)/speed) + 1);
            // Math ceil is slow so use this to get hours of current elt
            
            if(currTime > timeLimit) return false;
            // koko cannot eat bananas in current speed(increase speed)
        }        
        return true;
    }

    // Leetcode 774 SS13106
    // Lintcode 848. Minimize Max Distance to Gas Station
    // https://www.lintcode.com/problem/minimize-max-distance-to-gas-station/description

    // we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
    // Return the smallest possible value of D.
    public double minmaxGasDist(int[] arr, int K) {
        double si = 0.0, ei = 1e8, mid = 0.0;

        while(ei - si > 1e-5) {

            mid = (si + ei) / 2.0;
            if(isValidToPlaceGasStation(arr, mid, k)) ei = mid;
            else si = mid;
            // we can add 1e-5 in si but binary search will handle it so leave it
        }

        return ei; // si and ei will also give right ans
    }
    
    // some places can be empty 
    public boolean isValidToPlaceGasStation(int[] arr, double dis, int k) {
        int gasStationCount = 0;

        int n = arr.length;
        for(int i = 1; i < n; i++) {

            double x = (arr[i] - arr[i-1]) / dis - (1e-6 + 1e-6) / dis; 
            // adding - (1e-6 + 1e-6) for not considering both arr[i], arr[i-1] points
            
            gasStationCount += (int) x;
            // adding number of stations (ie. x  which is no. of stations placed b/w arr[i] and arr[i-1])

            if(gasStationCount > k) return false;
            // stations are increased so not possible with current distance
        }
        return true;        
    }


    // 33. Search in Rotated Sorted Array
    // https://leetcode.com/problems/search-in-rotated-sorted-array/

    // 1st Approach O(n + logn)

    // First find pivot elt index(smallest elt) 
    // now run 2 binary search on left side and right side of pivot elt
    // Note that pivot elt should be inclusive in both iterations of binary search
    public int search(int[] nums, int target) {
        
        int n = nums.length;
        
        int pivotIdx = findPivot(nums);
        
        int si = 0, ei = pivotIdx;
        while(si <= ei) {
            int mid = (si + ei) / 2;
            
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) si = mid+1;
            else ei = mid - 1;
        }        
        si = pivotIdx;
        ei = n-1;
        
        while(si <= ei) {
            int mid = (si + ei) / 2;
            
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) si = mid+1;
            else ei = mid - 1;
        }
        return -1;        
    }
    
    public int findPivot(int arr[] ) {        
        int si = 0, ei = arr.length - 1;
        
        while(si <= ei) {
            
            int mid = (si + ei) / 2;
            
            if(arr[mid] > arr[ei]) {
                si = mid+1;
                // if mid is bigger means mid can never be smaller elt and ans
                // will always will be from idx mid+1
                
            } else if(arr[mid] < arr[si]) {
                ei = mid;
                // if mid is smaller than si means mid can be answer or
                // more elts on left side can be answer so we will move ei = mid
                // bcz only mid elt can also be smallest elt present in array
            } 
            else ei = mid - 1;
                // if array is normal(or subarray is normal) (normal means sorted)
                // then we have to move ei to mid - 1 bcz ans will be on left side of
                // mid elt
        }
        return si;
    } 

    // 2. O(n)
    public int search(int[] nums, int target) {        
        int lo = 0, hi = nums.length - 1;
        
        while(lo <= hi) { 
            // lo <= hi bcz if one elt is there than that will be the answer           
            int mid = (lo + hi) >> 1;
            
            if(nums[mid] == target) return mid;
            
            else if(nums[mid] >= nums[lo]) {
                // if left sub-array is sorted then check in this
                if(nums[lo] <= target && target < nums[mid]) {
                    // no need to check target = nums[mid] bcz if that has to happen
                    // then program had returned mid 
                    
                    // if both condition are correct means elt will be in this range
                    // so move hi  to mid - 1
                    hi = mid - 1;
                } else lo = mid+1;
                // if not then range is above mid index
            } else {
                if(nums[mid] < target && target <= nums[hi]) {
                    // if correct then range is right side of mid
                    lo = mid + 1;
                } else hi = mid - 1;
                // if not then range is mid - 1
            }
        }
        return -1;
    }   

    // sir's code
    public int search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length-1;
        
        while(lo <= hi){
            int mid = (lo + hi ) >> 1;
            if(arr[mid] == target) return mid;
            else if(arr[lo] <= arr[mid]){
                if(arr[lo] <= target && target < arr[mid]) hi = mid - 1;
                else lo = mid + 1;
            }else{
                if(arr[mid] < target && target <= arr[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        
        return -1;
    }

    // Leetcode 81
    // 81. Search in Rotated Sorted Array II
    // https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

    // Same as above ques but here duplicates elts are also present
    public boolean search(int[] arr, int data) {        
        int lo = 0, hi = arr.length - 1;
        
        while(lo <= hi) {            
            int mid = (lo + hi) >> 1;
            // in this ques we cant decide where to move if all elts are equal
            // then we will try to decrease the range 
            // either decrease hi or increase lo 
            // but then you also have to check first if arr[hi] == ele
            // then we have to return eg : [3,1] 
            if(arr[mid] == data || arr[hi] == data) return true;
            
            // in below conditions we will check for strict < bcz if elts are same we  
            // cannot decide where to move eg : [6,6,7,6,6,6,6] or [5,5,5,5,4,5]
            // so if use = then it can go in wrong subarray 
            
            else if(arr[lo] < arr[mid]) {
                if(arr[lo] <= data && data < arr[mid]) hi = mid - 1;
                else lo = mid + 1;
                
            } else if(arr[mid] < arr[hi]) {
                if(arr[mid] < data && data <= arr[hi]) lo = mid+1;
                else hi = mid - 1;
                
            } else {
                hi--;
                // we are unable to decide the range so we will decrease the range
                // Note you have to check this elt in first condition if elt == arr[hi]
                // and you do not check then it will give wrong ans
                // eg : [3,1] 
                
                // YOU CAN USE lo++ too here and in first if arr[lo] == data
            }
        }
        return false;
    }

    // 786. K-th Smallest Prime Fraction
    // https://leetcode.com/problems/k-th-smallest-prime-fraction/
    // SS13194
    
    // think like 2d(rows and cols will be A[] elts on both sides) array and fill that array with the fractions value
    // now you will find that every col's first cell contains smallest value than other cells(in same col)
    // so we will put these in priorityQueue and when we will remove from priorityQueue we will put
    // next cell of same column as that value is greater than popped cell in same column
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<> ( (a,b) -> {
            
            return A[a[0]] * A[b[1]] - A[a[1]] * A[b[0]];
            // to avoid decimal fractions you can use cross-multiply here
        });
        
        for(int i = 0; i < n; i++) {
            
            pq.add( new int[] {0, i});
            // put indexes of fractions in priorityQueue
        }        
        while(--K > 0) {
            int val[] = pq.remove();
            
            val[0]++;
            if(val[0] < val[1]) {
                // we will put elts till its diagonal index(ie. (0,0), (1,1)) 
                // bcz ans not possible after adding above as they will create
                // different pairs (means j elt can pair with 0 to j -1) 
                // eg : [1,2,3,4,5] and 3/1 is not possible so we will stop at diagonal
                
                pq.add(val);
            }
        }
        
        int ans[] = pq.poll();
        ans[0] = A[ans[0]]; // bcz we had placed indexes in priority queue
        ans[1] = A[ans[1]];
        
        return ans;
    }

    // 4. Median of Two Sorted Arrays
    // https://leetcode.com/problems/median-of-two-sorted-arrays/
    // 26Oct (Explanation) Code on 27Oct
    // SS13214
    // O(n + m) time and O(1) space
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        
        if(n > m) {
            return findMedianSortedArrays(nums2, nums1);
            // we want min size array on left side and if not
            // then send in recursion smaller array in left side
        }
        
        int oMid = (n + m + 1) >> 1; // overall mid (ie. the no of elts we need to have)        
        int si = 0, ei = n; // for smaller[] ie. nums1[]
        // here larger[] is nums2[] (ie. n < m)
        
        while(si <= ei) {
            
            int sMid = (si + ei) >> 1; // smaller[] mid
            int lMid = oMid - sMid; // larger array mid
            // ie : oMid = sMid + lMid
            // => lMid = oMid - sMid
            
            int sl = (sMid == 0) ? (int) -1e8 : nums1[sMid - 1]; // smaller[] left side elt of mid
            int sr = (sMid == n) ? (int) 1e8 : nums1[sMid]; // smaller[] mid 
            
            int ll = (lMid == 0) ? (int) -1e8 : nums2[lMid - 1]; // larger[] left side elt of mid
            int lr = (lMid == m) ? (int) 1e8 : nums2[lMid]; // larger[] mid
            
            if(sl > lr) {
                // means in smaller[] left of mid we are having big elts
                // and we want the arrays to be sorted(ie. sl < lr) so we have to 
                // move left in smaller[] and larger[] range will move right side eventually 
                // in next iteration of while loop using overall mid - smaller[] mid
                ei = sMid - 1;
                
            } else if(ll > sr) {
                // means larger[] array has big elt than smaller[] so we have to move
                // right side in smaller[] array so that the smaller[] can accomodate
                // larger[] left side of mid elts in the range to make them sorted
                // and this will make larger[] range to move left side
                si = sMid + 1;
                
            } else {
                // means the elts on left of sl and right on lr are sorted 
                // and the elts on left of ll and right of sr are sorted
                int leftBoundaryMax = Math.max(sl, ll); // so get max elt on left side of mid
                int rightBoundaryMax = Math.min(sr, lr); // get min elt on right side of mid
                // we want min and max elts bcz in no range the median is calculated in 
                //middle elts and max on left side and min on right side will give these elts
                
                if((n+m) % 2 == 0) return (leftBoundaryMax + rightBoundaryMax) / 2.0;
                // even no of elts so median is from 2 elts 
                else return leftBoundaryMax;
                // odd no. of elts so middle elt is leftBoundaryMax ( max of sl and ll)
            }
        }
        return 0.0; // just for formality 
    }

}