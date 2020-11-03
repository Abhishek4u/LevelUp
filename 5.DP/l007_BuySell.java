public class l007_BuySell {

    //--------------------BUY-SELL VARIATIONS CLASS(ON 31ST OCT)--------------------------

    // Leetcode 121. Best Time to Buy and Sell Stock
    // ONLY ONE TRANSACTION ALLOWED

    public int maxProfit1(int[] prices) {        
        int T0 = 0;
        // T0 is 0 bcz we have 0 stock in hand
        // (means no profit no loss as we didn't sold anything yet)        
        int T1 = (int) -1e9;
        // T1 is (-ve)infinity bcz for buying stock before opening market
        // we have to give too much money(briber(risbat)) to buy a stock
        
        for(int i = 0;i < prices.length; i++) {
            
            T0 = Math.max(T0, T1 + prices[i]); // selling
            T1 = Math.max(T1, 0 - prices[i]); // buying  
            // 0 - prices[i] bcz we can buy only 1 stock
            // so we have to pay money for it
        }
        return T0; // T0 contains max profit after selling 
    }
    
    public int maxProfit(int[] prices) {
        
        return maxProfit1(prices);
    }

    // Leetcode 122. Best Time to Buy and Sell Stock II
    // You can do any no of transactions

    // In description given that you can sell and buy stock at same day
    public int maxProfit1(int[] prices) {
        int T0 = 0; // sell variable
        int T1 = (int) -1e9; // buy variable
        
        for(int i = 0;i<prices.length; i++) {
            
            T0 = Math.max(T0, T1 + prices[i]); // sell
            // sell the stock which will increment buy amount
            T1 = Math.max(T1, T0 - prices[i]); // buy
            // buy the stock which will use sell amount to buy
        }
        return T0;
    }
    
    // But if given that you can do only 1 transaction then use temp vars to store  
    // the latest result and at end of loop update T0 and T1)
    public int maxProfit2(int[] prices) {
        int T0 = 0; // sell variable
        int T1 = (int) -1e9; // buy variable
        
        for(int i = 0;i<prices.length; i++) {
            
            int temp0 = Math.max(T0, T1 + prices[i]); // sell
            int temp1 = Math.max(T1, T0 - prices[i]); // buy
            
            T0 = temp0;
            T1 = temp1;
        }
        return T0;
    }
    
    public int maxProfit(int[] prices) {
        
        // return maxProfit1(prices);   
        return maxProfit2(prices);
    }   

    // Leetcode 309. Best Time to Buy and Sell Stock with Cooldown
    public int maxProfit(int[] prices) {
        
        int T0 = 0; // sell
        int T1 = (int) -1e9; // buy
        int T2 = 0; // second last T0
        
        for(int i = 0;i < prices.length; i++) {            
            int temp = T0;
            
            T0 = Math.max(T0, T1 + prices[i]); // sell
            T1 = Math.max(T1, T2 - prices[i]); // buy
            // using second last T0(ie. T2) bcz of cooldown
            // to buy new stock again
            T2 = temp;
        }
        return T0;
    }

    // Leetcode 123. Best Time to Buy and Sell Stock III
    // You may complete at most two transactions
    public int maxProfit(int[] arr) {
        
        int T10 = 0; // ek transaction pe holding zero
        int T11 = -(int) 1e9; // ek transaction pe holding ek ki hai.
        
        int T20 = 0; // do transaction pe holding zero
        int T21 = -(int) 1e9; // do transaction pe holding ek ki hai.
        
        for(int val: arr) {
            T20 = Math.max(T20, T21 + val); // sell
            T21 = Math.max(T21, T10 - val); // buy
            
            T10 = Math.max(T10, T11 + val); // sell
            T11 = Math.max(T11, 0 - val); // buy
        }
        return T20;
    }

    // Leetcode 188. Best Time to Buy and Sell Stock IV
    // K transactions allowed

    public int maxProfit(int k, int[] prices) {
        if(prices.length == 0 || k == 0) return 0;
        
        if(k > (prices.length >>> 1 )){
            // we can do at max len/2 transations 
            // and if it exceeds then we can do infinite transactions logic(BS2(LC-122) )
            int T0 = 0;
            int T1 = -(int)1e9;

            for(int val : prices){
                T0 = Math.max(T0,T1 + val);
                T1 = Math.max(T1,T0 - val);
            }

            return T0; 
        }        
        int[] Ti0 = new int[k + 1]; // sell
        int[] Ti1 = new int[k + 1]; // buy
        Arrays.fill(Ti1,-(int)1e9);

        for(int val : prices){
            for(int K = k; K > 0; K--){
                
                Ti0[K] = Math.max(Ti0[K], Ti1[K] + val); // sell
                Ti1[K] = Math.max(Ti1[K], Ti0[K - 1] - val); // buy
            }
        }
        return Ti0[k];
    }

    // Leetcode 714. Best Time to Buy and Sell Stock with Transaction Fee
    // infinite transactions with fee

    // same as infinite transactions with included fee
    // so you have to just include fee also(can include while buying or selling)
    public int maxProfit(int[] prices, int fee) {
        
        int T0 = 0; // sell
        int T1 = (int) -1e9; // buy
        
        for(int val : prices) {
            int T0_prev = T0;
            // we will use prev value in T1 bcz 
            // we are using brokerage fee too
            
            // you can use fee while buying or selling
            T0 = Math.max(T0, T1 + val); // sell
            T1 = Math.max(T1, T0_prev - val - fee); // buy
        }
        return T0;
    }

    // ------------------------------USING INCLUSIVE EXCLUSIVE PRINCIPAL-----------------------------
    
    // GFG Count number of binary strings without consecutive 1’s
    // https://practice.geeksforgeeks.org/problems/consecutive-1s-not-allowed1912/1

    // We can use 0 with binary numbers both ending 0's and 1's
    // We can use 1 with only binary numbers ending with 0's to avoid consecutive 1's
    long countStrings(int n) {
        if(n == 0) return 0;
        int mod = (int)1e9 + 7;
        
        long zeroes = 1;
        long ones = 1;
        
        for(int i = 1; i < n; i++) {
            long prevZeroes = zeroes;
            
            zeroes = (zeroes%mod + ones%mod)%mod;
            ones = prevZeroes;
        }
        return (ones%mod + zeroes%mod)%mod; // total no of count
    }

    // Count possible ways to construct buildings
    // https://www.geeksforgeeks.org/count-possible-ways-to-construct-buildings/


    // Leetcode 256 Paint House II 
    // LintCode 516. Paint House II 
    // Time - > O(n*(k + k)) -> O(n*k)

    public int[] setMin(int dp[][], int i) {
        int min1 = (int) 1e9,min2 = (int) 1e9;
        
        for(int j = 0;j < dp[0].length; j++) {
            if(dp[i][j] <= min1) {
                min2 = min1;
                min1 = dp[i][j];
            } else if(dp[i][j] < min2) {
                min2 = dp[i][j];
            }
        }
        return new int[] {min1, min2};
    }
    
    public int minCostII(int[][] costs) {
        // write your code here
        if(costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        
        int dp[][] = new int[n][k];
        
        for(int j = 0;j < k; j++) {
            dp[0][j] = costs[0][j];
        }
        
        int val[] = setMin(dp, 0);
        int min1 = val[0];
        int min2 = val[1];
        
        for(int i = 1;i < n; i++) {
            for(int j = 0;j < k; j++) {
                int minVal = (min1 != dp[i-1][j]) ? min1 : min2;
                // if minValue is current col's then we have to choose 2nd minValue
                // as we cannot choose same color in same color
                
                dp[i][j] = minVal + costs[i][j];
            }
            int temp[] = setMin(dp, i);
            min1 = temp[0];
            min2 = temp[1];
        }
        
        int ans = (int) 1e9;
        for(int j = 0; j < k; j++) {
            ans = Math.min(ans, dp[n-1][j]);
        }
        return ans;
    }

}