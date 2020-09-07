public class questions {
    
    // leetcode 70. Climbing Stairs

    // You are climbing a stair case. It takes n steps to reach to the top.
    // Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

    public int climbStairs(int n) {
        if(n == 1 || n == 0) return n;
        
        // return climbStairs_(0,n,new int[n+1]);
        return climbStairs_DP(0,n,new int[n+1]);
    }
    
    // Memoization
    public int climbStairs_(int i,int n,int dp[]) {
        
        if(i == n) {
            return dp[i] = 1;
        }
        
        if(dp[i] != 0) return dp[i];
        
        int count = 0;
        
        if(i + 1 <= n) count += climbStairs_(i + 1, n, dp);
        
        if(i + 2 <= n) count += climbStairs_(i + 2, n, dp);
        
        return dp[i] = count;
    }
    
    // Tabulation
    public int climbStairs_DP(int i,int n,int dp[]) {
        
        
        for(i = n ; i >= 0;i--) {
            
            if(i == n) {
                dp[i] = 1;
                continue;
            }

            int count = 0;

            if(i + 1 <= n) count += dp[i+1]; //climbStairs_(i + 1, n, dp);

            if(i + 2 <= n) count += dp[i+2]; //climbStairs_(i + 2, n, dp);

            dp[i] = count; 
        }
        return dp[0];
    }

    // leetcode 746. Min Cost Climbing Stairs

    
    // On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
    // Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach 
    // the top of the floor, and you can either start from the step with index 0, or the step with index 1. 

    // (NOT OPTIMIZED 2 calls are required)
    public int minCostClimbingStairs(int[] cost) {
        
        int n = cost.length;
        
        return Math.min(minCostClimbingStairs_DP(cost,0,new int[n]), 
                        minCostClimbingStairs_DP(cost,1,new int[n]));
    }
    
    public int minCostClimbingStairs_DP(int[] cost,int i, int dp[]) {
        
        if(i == cost.length) {
            return 0;
        }
        
        if(dp[i] != 0) return dp[i];
        
        int minVal = 0;
        
        if( i + 1 <= cost.length) {
            int val = minCostClimbingStairs_DP(cost,i+1,dp);
            
            minVal = val;
        } 
            
        if( i + 2 <= cost.length) {
            
            int val = minCostClimbingStairs_DP(cost,i+2,dp);
            
            minVal = Math.min( minVal, val );
        }
        
        return dp[i] = minVal + cost[i];
    }

    // OPTIMIZED (1  call only)
    public int minCostClimbingStairs(int[] cost) {
        
        int n = cost.length;
        int dp[] = new int[n+1];
        
        // int ans = minCostClimbingStairs_(cost,n,dp);
        int ans = minCostClimbingStairs_DP(cost,n,dp);
        
        return ans;
    }
    
    // MEMOIZATION
    public int minCostClimbingStairs_(int[] cost,int n,int dp[]) {
        
        if(n == 0 || n == 1) return dp[n] = cost[n];
        
        if(dp[n] != 0) return dp[n];
        
        int ans = Math.min(minCostClimbingStairs_(cost,n-1,dp),
                           minCostClimbingStairs_(cost,n-2,dp));
        
        return dp[n] = ans + (n != cost.length ?  cost[n] : 0);
    }
    
    // TABULATION
    public int minCostClimbingStairs_DP(int[] cost,int n,int dp[]) {
        
        for(n = 0; n <= cost.length; n++) {
            
            if(n == 0 || n == 1) {
            
                dp[n] = cost[n];
                continue;
            }

            int ans = Math.min(dp[n-1],dp[n-2]);

            dp[n] = ans + (n != cost.length ?  cost[n] : 0);
            
        }
        return dp[cost.length];
    }
}
