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

}