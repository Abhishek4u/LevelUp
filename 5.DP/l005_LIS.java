public class l005_LIS {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        LIS_set();
    }

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


    

}