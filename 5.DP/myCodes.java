public class myCodes {

    // leetcode 1039
    public int minScoreTriangulation_(int[] arr, int si, int ei, int dp[][]) {
        if(si + 1 == ei) return dp[si][ei] =  0;
        
        if(dp[si][ei] != -1) return dp[si][ei];
        
        int minCost = (int) 1e8;
        for(int cut = si+1; cut < ei; cut++) {
            
            int left = minScoreTriangulation_(arr,si, cut, dp);
            int right = minScoreTriangulation_(arr,cut,ei, dp) ;
            
            int myCost = arr[si] * arr[cut] * arr[ei];
            myCost = left + myCost + right;
            
            minCost = Math.min(minCost, myCost);
        }
        
        return dp[si][ei] =  minCost;
    }
    
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int dp[][] = new int[n][n];
        for(int d[]: dp) Arrays.fill(d, -1);
        
        return minScoreTriangulation_(A,0 ,n-1, dp);
    }
}