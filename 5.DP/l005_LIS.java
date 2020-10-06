public class l005_LIS {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        LIS_set();
    }

    // --------------------------OCT-3-CODES(LIS class)---------------------------- //

    // Leetcode 300. Longest Increasing Subsequence
    // https://leetcode.com/problems/longest-increasing-subsequence/

    public static int LIS_DP_(int []arr) {
        int n = arr.length;
        int[] dp = new int[n];
        
        dp[0] = 1;
        int len = dp[0];
        
        for(int i = 1; i < n;i++) {
            dp[i] = 1;
            for(int j = i-1;j >= 0;j--) {
                
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            len = Math.max(len,dp[i]);
        }
        return len;
    }
    
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        return LIS_DP_(nums);
    }

    // my code
    public int LIS_DP(int[] nums,int n,int dp[]) {
        
        dp[n-1] = 1;
        
        for(int i = n-2; i>= 0;i--) {
            int maxIdx = i;
            for(int j = i+1; j < n;j++) {
                if(nums[j] > nums[i] && dp[j] > dp[maxIdx]) maxIdx = j; 
            }
            dp[i] = dp[maxIdx] + 1;
        }
        
        int max = dp[0];
        for(int ele : dp) max = Math.max(ele,max);
        
        return max;
    }

    // left to right increasing
    public static int[] LIS_DP(int []arr) {
        int n = arr.length;
        int[] dp = new int[n];
        
        dp[0] = 1;
        int len = dp[0];
        
        for(int i = 1; i < n;i++) {
            dp[i] = 1;
            for(int j = i-1;j >= 0;j--) {
                
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            len = Math.max(len,dp[i]);
        }
        return dp;
    }

    // left to right decreasing 
    // Longest decreasing subsequence
    public static int[] LDS_DP(int []arr) {

        int n = arr.length;
        int dp[] = new int[n];

        int len = 0;

        for(int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for(int j = i + 1; j < n; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            len = Math.max(len,dp[i]);
        }
        return dp;
    }
    // longest bitonic subsequence
    public static int LBS(int[] arr) {
        int[] LIS = LIS_DP(arr);
        int[] LDS = LDS_DP(arr);

        int n = arr.length;
        int LBS[] = new int[n];

        int maxLen = 0;

        for(int i = 0; i < n;i++) {

            LBS[i] = LIS[i] + LDS[i];

            maxLen = Math.max(maxLen, LBS[i]);
            System.out.print(LBS[i] + "   ");
        }
        return maxLen;
    }

    // Left to right decreasing
    public static int[] LDS_DP_02(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];

        int len = 1;

        for(int i = 1; i < n;i++) {
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--) {
                if(arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[i] + 1);
                }
            }
            len = Math.max(len,dp[i]);
        }

        return dp;
    }

    // right to left increasing
    public static int[] LIS_DP_02(int arr[]) {
        int n = arr.length;
        int dp[] = new int[n];

        int len = 0;

        for(int i = n-1; i >= 0;i--) {
            dp[i] = 1;
            for(int j = i + 1; j < n; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            len = Math.max(dp[i], len);
        }

        return dp;
    }


    // Min no of deletion to sort the array (equal elts also counted in sorted)
    public static int minNoOfDeletion(int[] arr) {
        int n = arr.length;
        int []dp = new int[n];

        int len = 0;
        for(int i = 0;i < n;i++) {
            dp[i] = 1;
            for(int j = i-1;j >= 0;j--) {
                if(arr[j] <= arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            len = Math.max(len,dp[i]);
        }

        return n - len;
        // total length - length of longest lis are the elts need to delete 
        // to make whole array sorted
    }

    public static void LIS_set() {

        int arr[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15,10};
        
        int dp[] = LIS_DP_02(arr);
        for(int ele : dp) System.out.print(ele + "   ");

        // LBS(arr);
    }

    // Leetcode 673. Number of Longest Increasing Subsequence
    // https://leetcode.com/problems/number-of-longest-increasing-subsequence/
    
    public int numOfLongestLIS(int[] arr,int n,int dp[] ){
        
        int count[] = new int[n];
        // dp will serve as maxLength[]
        int maxCount = 0,maxLen = 0;
        
        for(int i = 0; i < n;i++) {
            dp[i] = 1;
            count[i] = 1;
            
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] > arr[j]) {
                    
                    if(dp[j] + 1 > dp[i]) {     
                        
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                        
                    } else if(dp[j] + 1 == dp[i]) {
                        count[i] += count[j]; 
                    }
                }
            }

            if(dp[i] > maxLen) {
                
                maxLen = dp[i];
                maxCount = count[i];
                
            } else if( dp[i] == maxLen ) {
                maxCount += count[i];
            }
        }
        return maxCount;
    }

    public int findNumberOfLIS(int[] nums) {
        
        return numOfLongestLIS(nums,nums.length,new int[nums.length]);
    }

    // Leetcode 354. Russian Doll Envelopes
    // https://leetcode.com/problems/russian-doll-envelopes/

    public int maxEnvelopes(int[][] arr) {
        
        if(arr.length == 0) return 0;
        
        int n = arr.length;
        Arrays.sort(arr, (a,b) -> {
            
            if(a[0] == b[0]) return b[1] - a[1];
            // if one dimension is equal then we cannot put one inside other
            // so sort in decreasing order according to other dimension
            // so it does not contribute to lis
            
            return a[0] - b[0];
        });
        
        int dp[] = new int[n];
        
        int maxLen = 0;
        for(int i = 0; i < n;i++) {
            dp[i] = 1;
            for(int j = i - 1; j >=0; j--) {

                if(arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }

	//  Largest square formed in a matrix 
    // https://practice.geeksforgeeks.org/problems/largest-square-formed-in-a-matrix/0

	public static int maxSquare(int arr[][], int i, int j,int dp[][]) {

		if(dp[i][j] != 0) return dp[i][j];
	    
	    int minSize = (int) 1e9;
	    
	    int dir[][] = {{1,0}, {0,1}, {1,1}};
	    
	    for(int d[] : dir) {
	        int x = i + d[0];
	        int y = j + d[1];
	        
	        if(x >= 0 && y >= 0 && x < arr.length && y < arr[0].length) {
	            
	            if(arr[x][y] == 0) {
	                minSize = 0;
	                break;
	                
	            } else {
	                minSize = Math.min(minSize, maxSquare(arr,x,y,dp));
	            }
	        } else {
                minSize = 0; 
                // it can't be expanded bcz all elts are not present for making square
            }

	    }
	    
	    return dp[i][j] =  minSize == 1e9 ? 1 : minSize + 1;
	}

    public static int findMax(int arr[][]) {
	    int n = arr.length,m = arr[0].length;
	    
	    int max = 0;
		int dp[][] = new int[n][m];
	    
	    for(int i = 0; i < n;i++) {
	        for(int j = 0; j < m;j++) {
	            
	            if(arr[i][j] == 1) {
	                max = Math.max(maxSquare(arr,i,j,dp), max);
	            }
	        }
	    }
	    return max;
	}

    // Leetcode 413. Arithmetic Slices
    // https://leetcode.com/problems/arithmetic-slices/

    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if(n < 3) return 0;
        
        int ans = 0;
        int count = 0;
        
        for(int i = 1; i < n - 1; i++) {
            
            int d1 = A[i] - A[i-1];
            int d2 = A[i+1] - A[i];
            
            if(d1 == d2) ans += (++count);
            else count = 0;
        }
        return ans;
    }

    // --------------------------OCT-5-CODES(LIS class)---------------------------- //

    // Leetcode 1218. Longest Arithmetic Subsequence of Given Difference

    public int longestSubsequence(int[] arr, int d) {
        HashMap<Integer, Integer> hm = new HashMap<> ();
        
        int maxLen = 0;
        for(int ele : arr) {
            int prevElt = ele - d;
            // previous elt of current ap( a0 + d = a1 is => a0 = a1 - d)
            
            hm.put(ele, hm.getOrDefault(prevElt, 0) + 1);
            maxLen = Math.max(maxLen, hm.get(ele));            
        }
        return maxLen;
    }

    // Leetcode 1027. Longest Arithmetic Subsequence

    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        HashMap<Integer,Integer>[] dp = new HashMap[n];
        
        int len = 0;
        for(int i = 0; i < n;i++) dp[i] = new HashMap<Integer,Integer> ();
        
        for(int i = 0; i < n;i++) {
            for(int j = i - 1; j >= 0; j--) {
                int diff = A[i] - A[j];
                
                int currLen = dp[i].getOrDefault(diff,0);
                // present length of curr diff if present

                int newLen = dp[j].getOrDefault(diff,1) + 1;
                // new length
                
                dp[i].put(diff, Math.max(currLen, newLen));
                // compare and put the maxlength of current difference
                
                len = Math.max(len, dp[i].get(diff));
            }
        }
        return len;
    }

    // Maximum sum increasing subsequence 
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1

    public int maxSumIS(int arr[], int n) {  
        int[] dp = new int[n];
        
	    int maxSum = 0;
	    for(int i = n-1;i >= 0;i--) {
	       
            int max = arr[i];
            for(int j  = i+1; j < arr.length; j++) {
                if(arr[j] > arr[i]) {
                    max = Math.max(max, dp[j] + arr[i]);
                }
            }
            dp[i] = max;
	        maxSum = Math.max(maxSum, max);
	    }
	    
	    return maxSum;
	}

    //------------------------------------TODO----------------------------------//

    // Leetcode 446

    //------------------------------------TODO----------------------------------//

    // https://practice.geeksforgeeks.org/problems/longest-alternating-subsequence/0
    // Longest alternating subsequence
    
    public static int maxAlternating(int arr[], int idx) {
	    int n = arr.length;
	    
	    int dp[][] = new int[n][2] ; 
	    // (0 -> decreasing slope, 1 -> increasing slope)
	    
	    int maxLen = 0;
        
	    for(int i = 0; i < n;i++) {
	        
	        dp[i][0] = dp[i][1] = 1;
	        for(int j = i - 1; j >= 0; j--) {
	            
	            if(arr[i] > arr[j]) dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
	            if(arr[i] < arr[j]) dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
	        }
	        
	        maxLen = Math.max(maxLen, Math.max(dp[i][0], dp[i][1]) );
	    }
	    return maxLen;
	}

    public static int BuildingBridges(int[][] arr) {
        int n = arr.length;

        Arrays.sort(arr, (a,b) -> {
            // if(a[0] == b[0] ) return b[1] - a[1];
            // use above condition if you do not want to have
            // 2 or more bridges from same point

            return a[0] - b[0];
        });

        int dp[] = new int[n];
        int len = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i - 1; j >= 0;j--) {
                if(arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            len = Math.max(len, dp[i]);
        }

        return len;
    }


}