import java.util.Arrays;
public class l004_targetSet {

    // TargetSet / CoinChange ---------------------------------------

    // Infinite Permutation
    public static int coinChangePermutation(int[] arr,int tar,int dp[]) {
        if(tar == 0) {
            return dp[tar] = 1;
        }

        if(dp[tar] != 0) return dp[tar];

        int count = 0;
        for(int ele : arr) {
            if(tar - ele >= 0) {
                count += coinChangePermutation(arr,tar - ele,dp);
            }
        }

        return dp[tar] = count;
    }

    public static int coinChangePermutation_DP(int[] arr,int tar,int dp[]) {
        int Tar = tar;

        for(tar = 0; tar <= Tar;tar++) {

            if(tar == 0) {
                dp[tar] = 1;
                continue;
            }

            int count = 0;
            for(int ele : arr) {
                if(tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
            dp[tar] = count;
        }
        return dp[Tar];
    }

    // Using 1d DP
    public static int coinChangeCombination_DP(int arr[],int tar,int dp[]) {

        int Tar = tar;
        dp[0] = 1;

        for(int ele : arr) {
            for(tar = ele; tar <= Tar; tar++) {
                
                // if(tar - ele >= 0) {
                //will work without check
                dp[tar] += dp[tar - ele];
            }
        }
        return dp[Tar];
    }


    // Leetcode 322. Coin Change
    // https://leetcode.com/problems/coin-change/
    
    // Using Permutation
    public int coinChange_(int[] coins, int tar,int dp[]) {
        if(tar == 0) return dp[tar] =  0;
        
        if(dp[tar] != -1) return dp[tar]; 
        
        int minCoin  = (int) 1e8;
        for(int ele : coins) {
            if(tar - ele >= 0) {
                minCoin  = Math.min(minCoin ,coinChange_(coins,tar - ele,dp) + 1);
            }
        }
        
        return dp[tar] = minCoin ;
    }
    
    public int coinChange_DP(int[] coins, int tar,int dp[]) {
        int Tar = tar;
        dp[0] =  0;
        
        for(tar = 1; tar <= Tar;tar++) {
            int minCoin  = (int) 1e8;
            
            for(int ele : coins) {
                if(tar - ele >= 0) {
                    int ans = dp[tar - ele];
                    minCoin  = Math.min(minCoin ,ans + 1);
                }
            }
            dp[tar] = minCoin ;
        }
        
        return dp[Tar];
    }

    public int coinChange(int[] coins, int amount) {
        
        int dp[] = new int[amount+1];
        Arrays.fill(dp,-1);
        
        // int ans = coinChange_(coins,amount,dp);
        int ans = coinChange_DP(coins,amount,dp);
        return ans == (1e8) ? -1 : ans;
    }

    //https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    // same as coin change combination.

    // TargetSum
    // Same as SingleCoinCombination
    public static int targetSum(int arr[], int idx,int tar,int[][] dp) {

        if(tar == 0 || idx == arr.length) {
            return dp[idx][tar] = (tar == 0) ? 1 : 0;
        }

        if(dp[idx][tar] != 0) return dp[idx][tar];

        if(tar - arr[idx] >= 0) dp[idx][tar] += targetSum(arr,idx+1,tar - arr[idx],dp);
        dp[idx][tar] += targetSum(arr,idx+1,tar,dp);

        return dp[idx][tar];
    }

    // ReverseOrder Movement
    public static int targetSum2(int arr[], int n,int tar,int[][] dp) {

        if(tar == 0 || n == 0) {
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        }

        if(dp[n][tar] != 0) return dp[n][tar];

        if(tar - arr[n] >= 0) dp[n][tar] += targetSum2(arr,n - 1,tar - arr[n],dp);
        dp[n][tar] += targetSum2(arr,n - 1,tar,dp);

        return dp[n][tar];
    }

    // Check the code(Doubt)
    public static int targetSum_DP(int[] arr,int N,int Tar,int[][] dp) {

        for(int n = 0; n <= N;n++) {
            for(int tar = 0;tar <= Tar;tar++) {

                if(tar == 0 || n == 0) {
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }

                if(tar - arr[n] >= 0) dp[n][tar] += dp[n-1][tar - arr[n]];
                dp[n][tar] += dp[n-1][tar];

            }
        }
        return dp[N][Tar];
    }

    //----------------------------2OCT--------------------------

    // Leetcode 494. Target Sum
    // https://leetcode.com/problems/target-sum/
    
    public int findTargetSumWays_(int[] nums,int n,int sum, int tar, int dp[][]) {
        
        if(n == 0) {
            if(sum == tar) return dp[n][sum] = 1;
            return dp[n][sum] = 0;
        } 
        if(dp[n][sum] != -1) return dp[n][sum];
        
        int count = 0;
        count += findTargetSumWays_(nums,n - 1,sum - nums[n-1],tar,dp); // number is +ve
        // for negative sum call
        count += findTargetSumWays_(nums,n - 1,sum + nums[n-1],tar,dp); // number is -ve
        // for positive sum call
        return dp[n][sum] = count;
    }
    
    public int findTargetSumWays_DP(int[] nums,int N,int SUM, int tar, int dp[][]) {
        dp[0][0 + SUM] = 1; // bcz target is shifted
        
        for(int n = 1; n <= N;n++) {
            for(int sum = 0; sum <= 2*SUM; sum++) {

                int count = 0;
                // checking condition bcz here in dp we started sum from 0
                if( sum - nums[n-1] >= 0 ) count += dp[n-1][sum - nums[n-1]];
                if( sum + nums[n-1] <= 2*SUM ) count += dp[n-1][sum + nums[n-1]];

                dp[n][sum] = count;                
            }            
        }
        return dp[N][tar];
    }

    public int findTargetSumWays(int[] nums, int tar) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int sum = 0;
        for(int ele : nums) sum += ele;
        
        if( tar > sum || tar < -sum ) return 0;
        
        int dp[][] = new int[n+1][2 * sum + 1]; // for -ve values too
        
        // for(int[] d : dp) Arrays.fill(d,-1);
        // FILL DP WITH -1 FOR MEMO BCZ 0 HAS SIGNIFANCE IN ANSWER
        // return findTargetSumWays_(nums,n,sum,tar+sum,dp);
        
        return findTargetSumWays_DP(nums,n,sum,tar+sum,dp);
        // tar + sum bcz we do not have -ve index as tar can be -ve
        // so tar is shifted now
    }

    // Leetcode 416. Partition Equal Subset Sum
    // https://leetcode.com/problems/partition-equal-subset-sum/

    public boolean getTarget(int nums[], int Tar, int N, boolean dp[][]) {
        
        for(int n = 0 ; n<= N;n++) {
            for(int tar = 0; tar <= Tar; tar++) {
                
                if(n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0 ? true : false);
                    continue;
                }
                
                if(tar - nums[n-1] >= 0) 
                    dp[n][tar] = dp[n][tar] || dp[n-1][tar - nums[n-1]];
                dp[n][tar] = dp[n][tar] || dp[n-1][tar];

            }
        }
        return dp[N][Tar];
    }

    public boolean canPartition(int[] nums) {
        
        int totalSum = 0;
        for(int ele : nums) totalSum += ele;
        
        if(totalSum%2 != 0) return false;
        
        int tar = totalSum/2;
        boolean dp[][] = new boolean[nums.length+1][tar+1];
        return getTarget(nums,tar,nums.length,dp);
    }

    // 01 Knapsack
    // https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem/0

    public static int knapSack01(int[] weight,int[] price,int n,int cap,int[][] dp){
        if(n == 0 || cap == 0){
            return dp[n][cap] = 0;
        }
        
        if(dp[n][cap] != -1) return dp[n][cap];
        
        int maxVal = 0;
        if(cap - weight[n-1] >=0 ) maxVal = Math.max(maxVal, knapSack01(weight,price,n-1,cap-weight[n-1],dp) + price[n-1]);
        maxVal = Math.max(maxVal, knapSack01(weight,price,n-1,cap,dp)); 

        return dp[n][cap] = maxVal;
    }

    public static void knapSack(int[] weight,int[] value,int cap){
        int n = weight.length;

        int[][] dp = new int[n+1][cap+1];
        for(int[] d: dp) Arrays.fill(d,-1);
        
        int ans = knapSack01(weight,value,n,cap,dp);
    }

    //https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
    
    // Using 2d dp
    public static int knapSack_unbounded(int N, int W, int val[], int wt[],int[][] dp){
        if(N==0 || W==0){
            return dp[N][W] = 0;
        }
        
        if(dp[N][W]!= -1) return dp[N][W];
        
        int maxVal = 0;
        if(W-wt[N-1]>=0) maxVal = Math.max(maxVal,knapSack_unbounded(N,W-wt[N-1],val,wt,dp) + val[N-1]);
        maxVal = Math.max(maxVal,knapSack_unbounded(N-1,W,val,wt,dp));
        
        return dp[N][W] = maxVal;
        
    }
    // 1d dp
    public static int knapSack_unbounded_DP(int N, int W, int val[], int wt[],int[][] dp){
        dp[0] = 0;
        for(int i = 0;i<N;i++){
            for(int w = wt[i]; w <= W;w++){
                dp[w] = Math.max(dp[w],dp[w-wt[i]] + val[i]);
            }
        }

        return dp[W];
    }

    static int knapSack(int N, int W, int val[], int wt[])
    {
        int[][] dp = new int[N+1][W+1];
        
        // for(int[] d: dp) Arrays.fill(d,-1);
        // return knapSack_unbounded(N,W,val,wt,dp);

        return knapSack_unbounded_DP(N,W,val,wt,dp);
    }

    // Using 1d dp recursion ( coins permutation logic)
    public static int unboundedKnapsack(int w,int val[], int wt[], int dp[]) {
        if(w == 0) return dp[w] =  0;
        
        if(dp[w] != -1) return dp[w];
        
        int max = 0;
        for(int i = 0; i < wt.length;i++) {
            int ele = wt[i];
            if(w - ele >= 0) max = Math.max(unboundedKnapsack(w - ele,val,wt,dp) + val[i], max);
            
        }
        return dp[w] = max;
    }

    static int knapSack2(int N, int W, int val[], int wt[])
    {
        // code here
        int dp[] = new int[W+1];
        Arrays.fill(dp,-1);
        
        return unboundedKnapsack(W,val,wt,dp);
    }
    
    

}