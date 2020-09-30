import java.util.Arrays;
import java.util.LinkedList;
public class l001 {

    //---------------------------------------SEE PREVIOUS.java FILE FIRST---------------------------------//

    public static void main(String[] args) {
        int n = 4, k = 2;
        // System.out.println(count_of_ways(n,k,new int[n+1][n+1]));

        minDistance("horse","ros");
    
    }

    public static void print(int[] arr) {

        for (int ele : arr)
            System.out.print(ele + "\t");

        System.out.println();
    }

    public static void print2D(int[][] arr) {

        for (int[] ar : arr) {

            print(ar);
        }
        System.out.println();
    }

    public static int mazePath_Multi(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
        if(sr==er && sc == ec){
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];
        
        int count = 0;
        for(int d = 0; d<dir.length;d++){   
           for(int jump = 1;jump <= Math.max(er,ec) ; jump++){
               int r = sr + jump*dir[d][0];
               int c = sc + jump*dir[d][1];

               if(r>=0 && c>=0 && r <= er && c<=ec)
                  count+=mazePath_Multi(r,c,er,ec,dp,dir);
               else break;
           }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_MultiDP(int sr,int sc,int er,int ec,int[][] dp,int[][] dir){
               
        for(sr = er ; sr>=0 ; sr--){
            for(sc = ec; sc>=0 ; sc--){
                if(sr==er && sc == ec){
                   dp[sr][sc] = 1;
                   continue;
                }
        
                
                int count = 0;
                for(int d = 0; d<dir.length;d++){   
                   for(int jump = 1;jump <= Math.max(er,ec) ; jump++){
                       int r = sr + jump*dir[d][0];
                       int c = sc + jump*dir[d][1];
        
                       if(r>=0 && c>=0 && r <= er && c<=ec)
                          count+=dp[r][c];
                       else break;
                   }
                }
        
                dp[sr][sc] = count;
            }
        }

        return dp[0][0];
    }

    public static int boardPath(int sp,int ep,int[] dp){
        if(sp==ep){
            return dp[sp] = 1;
        }

        int count = 0;
        for(int dice = 1;dice <= 6 && sp+dice <=ep;dice++){
            count+= boardPath(sp+dice,ep,dp);
        }

        return dp[sp] = count;
    }

    public static int boardPath_DP(int sp,int ep,int[] dp){

        for(sp=ep; sp >= 0 ; sp--){
            if(sp==ep){
                dp[sp] = 1;
                continue;
            }
    
            int count = 0;
            for(int dice = 1;dice <= 6 && sp+dice <= ep;dice++){
                count+= dp[sp + dice];//boardPath(sp+dice,ep,dp);
            }
    
            dp[sp] = count;            
        }

        return dp[0];
    }

    public static int boardPath1_Opti(int si, int ei, int dp[]) {

        // We will use linkedlist by observation of dp that
        // we can store 7 elts in ll ans then if we want to add then 
        // add first node of ll twice and subtract that by lastnode 
        // and now remove that last node
        LinkedList<Integer> ll = new LinkedList<>();

        for(si = ei; si >= 0;si--) {

            if(si >= ei - 1) {
                // for first 2 elts base case value is 1(observe in dp)
                ll.addFirst(1);
                continue;
            } 

            if(ll.size() <= 6) ll.addFirst(ll.getFirst() * 2);
            // new dp value is twice of last value

            else {
                // size is 7 now 
                int lVal = ll.removeLast(); // last value
                int fVal = ll.getFirst() ; // first value

                ll.addFirst(fVal * 2 - lVal);
            }
        }
        return ll.getFirst();
    }

    // Leetcode 746 Min Cost Climbing Stairs
    // https://leetcode.com/problems/min-cost-climbing-stairs/

    public int minCostClimbingStairs(int[] cost, int n ,int[] dp) {
        
        if(n <= 1) return dp[n] = cost.length;

        if(dp[n] != 0) return dp[n];

        int a = minCostClimbingStairs(cost, n - 1, dp);
        int b = minCostClimbingStairs(cost, n - 2, dp);

        return dp[n] = Math.min(a,b) + ( n != cost.length ? cost[n] : 0);
    }

    public int minCostClimbingStairs_DP(int[] cost, int n ,int[] dp) {
        
        int N = n;
        for(n = 0; n <= N ; n++){
            if(n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int a = minCostClimbingStairs(cost, n - 1, dp);
            int b = minCostClimbingStairs(cost, n - 2, dp);

            dp[n] = Math.min(a,b) + (n != cost.length ? cost[n] : 0);
        }
        return dp[n];
    }

    public int minCostClimbingStairs_opti(int[] cost,int n, int dp[]) {
        
        int a = cost[0];
        int b = cost[1];
        // base cases bcz dp filling starts from start of array
        for(int idx = 2;idx <= n ;idx++) { 

            int temp = Math.min(a,b) + (idx != n ? cost[idx] : 0);
            a = b;
            b = temp;
        }
        return b;
    }

    public  int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[cost.length + 1];

        int ans = minCostClimbingStairs_opti(cost,n,dp);
        // int ans = minCostClimbingStairs_DP(cost,n,dp);
        return ans;
    }

    // https://www.geeksforgeeks.org/friends-pairing-problem/

    public static int friendsPairing(int n,int[] dp){
        if(n <= 1) return dp[n] = 1;

        if(dp[n] != 0) return dp[n];

        int single = friendsPairing(n-1, dp);
        int pairUp = friendsPairing(n-2, dp) * (n - 1);
        // can pair up with n-1 persons so multiplied

        return dp[n] = single + pairUp;
    }

    public static int friendsPairing_DP(int n,int[] dp){
        // dp of size(n+1)
        int N = n;
        for(n = 0; n <= N;n++) {

            if(n <= 1) {
                dp[n] = 1;
                continue;
            }

            int single = friendsPairing(n-1, dp);
            int pairUp = friendsPairing(n-2, dp) * (n - 1);

            dp[n] = single + pairUp;

        }
        return dp[n];
    }

    public static int friendsPairing_opti(int n) {

        int N = n;
        int a = 1, b =  1;
        for(n = 2; n <= N;n++) {
            
            int temp = (n-1)*a; // pair up
            temp = temp + b; // single
            
            a = b;
            b = temp;
        }
        return b;
    }

    public static int count_of_ways(int n, int k, int[][] dp)  {

        if(k > n) return 0; //  every can not be filled so return 0
        if(k == 1 || k == n) {
            // 1 space is available or 
            // no of people = k rooms so there will be 1 way only
            return dp[n][k] = 1;
        }

        if(dp[n][k] != 0) return dp[n][k];

        int ownSet = count_of_ways(n-1, k-1, dp);// will make own team 
        int partOfSet = count_of_ways(n-1, k, dp) * k;
        // will become part of team and can go in any team so multiplied by k

        return dp[n][k] = ownSet + partOfSet;
    }

}