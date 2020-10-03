public class l005 {

    public int LIS(int[] nums,int n,int dp[]) {
        
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

    public int LDS(int[] nums,int n,int dp[]) {
        
        for(int i = 0; i< n;i++) {
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

    public int LBS(int[] nums,int n,int dp) {
        
        int lis = LIS(nums,n,dp);
        int lds = LDS(nums,n,dp);
        int ans = 0;
        int lbs[] = new int[n];
        for(int i = 0; i < n;i++) {
            int len = lis[i] + lds[i] - 1;
            ans = Math.max(len,ans);
        }
        return ans;
    }

}