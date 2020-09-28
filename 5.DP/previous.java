import java.util.Arrays;
import java.util.LinkedList;
public class previous {

    public static void main(String[] args) {

        // solve();
        // solve2();
        goldMineProblem();
    }

    public static void print(int[] arr) {

        for(int ele: arr) System.out.print(ele+ "\t");

        System.out.println();
    }

    public static void print2D(int[][] arr) {

        for(int[] ar : arr) print(ar);

        System.out.println();
    }

    public static void solve() {

        int n = 5;
        // int dp[] = new int[n+1];
        // System.out.println(fibo_DP(n,dp));
        // print(dp);
        // System.out.println(fibo_opti(n));

        // int sr = 0,sc = 0, er = 5,ec = 5;
        // int dp[][] = new int[er][ec];
        // System.out.println(mazePathsHVD_dp(sr,sc,er,ec, dp));
        // print2D(dp);

        int si = 0, ei = 10;
        int dp[] = new int[ei+1];
        // System.out.println(boardPath1_DP(si,ei,dp));
        // print(dp);
        System.out.println(boardPath1_Opti(si, ei, dp));

    }

    public static void solve2() {

        int n = 10;
        int dp[] = new int[n + 1];

        int moves[] = { 2, 7, 5, 3 };
        // System.out.println(boardPath_Moves(0, n, moves, dp));
        // print(dp);
        // System.out.println(boardPath_Moves_DP(0, n, moves, dp));
        // print(dp);

    }

    public static int fibo(int n, int dp[]) {

        if(n == 1 || n == 0) return dp[n] =  n;

        if(dp[n] != 0) return dp[n];

        int a = fibo(n-1, dp);
        int b = fibo(n-2, dp);

        return dp[n] =  a + b;
    }

    public static int fibo_DP(int N, int dp[]) {

        for(int n = 0; n <= N; n++) {

            if(n == 1 || n == 0) {
                dp[n] =  n;
                continue;
            }

            int a = fibo(n-1, dp);
            int b = fibo(n-2, dp);

            dp[n] =  a + b;

        }
        return dp[0];
    }

    public static int fibo_opti(int N) {

        int a = 0, b = 1;

        for(int n = 2; n <= N;n++) {

            int temp = a + b;
            a = b;
            b = temp;
        }

        return b;
    } 

    // HORIZONTAL, VERTICAL
    public static int mazePathsHV(int sr, int sc, int er, int ec, int dp[][]) {

        if(sr == er-1 && sc == ec-1) return dp[sr][sc] =  1;

        if(dp[sr][sc] != 0) return dp[sr][sc];

        int count = 0;
        int dir[][] = { { 0, 1}, {1, 0}};

        for(int d[] : dir) {

            int r = sr + d[0];
            int c = sc + d[1];

            if(r >= 0 && c >= 0 && r < er && c < ec) {

                count += mazePathsHV(r,c,er,ec,dp);
            }
        } 

        return dp[sr][sc] =  count;
    }

    public static int mazePathsHV_dp(int sr, int sc, int er, int ec, int dp[][]) {

        int dir[][] = { { 0, 1}, {1, 0}};

        for(sr = er-1;sr >= 0; sr--) {

            for(sc = ec -1; sc >= 0;sc--) {
                
                if(sr == er-1 && sc == ec-1) {
                    dp[sr][sc] =  1;
                    continue;
                }

                int count = 0;

                for(int d[] : dir) {

                    int r = sr + d[0];
                    int c = sc + d[1];

                    if(r >= 0 && c >= 0 && r < er && c < ec) {

                        count += dp[r][c]; //mazePathsHV(r,c,er,ec,dp);
                    }
                }
                dp[sr][sc] = count; 
            }
        }
        return dp[0][0];
    }

    // HORIZONTAL, VERTICAL, DIAGONAL
    public static int mazePathsHVD_dp(int sr, int sc, int er, int ec, int dp[][]) {

        int dir[][] = { { 0, 1}, {1, 0},{1,1}};

        for(sr = er-1;sr >= 0; sr--) {

            for(sc = ec -1; sc >= 0;sc--) {
                
                if(sr == er-1 && sc == ec-1) {
                    dp[sr][sc] =  1;
                    continue;
                }

                int count = 0;

                for(int d[] : dir) {

                    int r = sr + d[0];
                    int c = sc + d[1];

                    if(r >= 0 && c >= 0 && r < er && c < ec) {

                        count += dp[r][c]; //mazePathsHV(r,c,er,ec,dp);
                    }
                }
                dp[sr][sc] = count; 
            }
        }
        return dp[0][0];
    }


    // ----------------------------BOARD VARIATION--------------------------

    public static int boardPath1(int si, int ei, int dp[]) {

        if(si == ei) return dp[si] =  1;

        if(dp[si] != 0) return dp[si];

        int count = 0;
        for(int dice = 1; dice + si <= ei && dice <= 6; dice++) {

            count += boardPath1(si + dice, ei, dp);
        }

        return dp[si] = count;
    }

    public static int boardPath1_DP(int si, int ei, int dp[]) {

        for(si = ei; si >= 0;si--) {

            if(si == ei) {
                dp[si] =  1;
                continue;
            } 

            int count = 0;
            for(int dice = 1; dice + si <= ei && dice <= 6; dice++) {

                count += dp[si + dice]; //boardPath1(si + dice, ei, dp);
            }

            dp[si] = count;
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

    // BOARD WITH MOVEMENT ARRAY

    public static int boardPath_Moves(int si, int ei, int[] moves, int dp[]) {

        if(si == ei) return dp[si] =  1;

        if(dp[si] != 0) return dp[si];

        int count = 0;

        // if curr idx value is greater then for loop will break
        // bcz we have sorted already so next greater elt can be used
        // so sort them before using
        Arrays.sort(moves);

        for(int i = 0; i < moves.length && si + moves[i] <= ei; i++) {

            count += boardPath_Moves(si + moves[i], ei, moves, dp);
        }

        return dp[si] = count;
    }

    public static int boardPath_Moves_DP(int si, int ei, int[] moves, int dp[]) {

        // if curr idx value is greater then for loop will break
        // bcz we have sorted already so next greater elt can be used
        // so sort them before using
        Arrays.sort(moves);

        for(si = ei; si >= 0;si--) {

            if(si == ei) {

                dp[si] =  1;
                continue;
            }
            int count = 0;

            for(int i = 0; i < moves.length && si + moves[i] <= ei; i++) {

                count += dp[si + moves[i]]; //boardPath_Moves(si + moves[i], ei, moves, dp);
            }

            dp[si] = count;
        }
        return dp[0];

    }

    // Leetcode 64 Minimum Path Sum
    // https://leetcode.com/problems/minimum-path-sum/

    public static int minPathSum(int[][] grid,int sr,int sc,int[][] dp) {
        
        if(sr==grid.length-1 && sc==grid[0].length-1){
            return dp[sr][sc] = grid[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];

        int minCost = (int)1e8;
        if(sr + 1 < grid.length) minCost = Math.min(minCost,minPathSum(grid,sr+1,sc,dp));
        if(sc + 1 < grid[0].length) minCost = Math.min(minCost,minPathSum(grid,sr,sc+1,dp));
        
        return dp[sr][sc] = minCost + grid[sr][sc];
    }

    
    public static int minPathSum_DP(int[][] grid,int sr,int sc,int[][] dp) {
        
        for(sr=grid.length-1;sr>=0 ;sr--){
            for(sc=grid[0].length-1; sc>=0 ; sc--){

               if(sr==grid.length-1 && sc==grid[0].length-1){
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int minCost = (int)1e8;
                if(sr + 1 < grid.length) minCost = Math.min(minCost,dp[sr+1][sc]);
                if(sc + 1 < grid[0].length) minCost = Math.min(minCost,dp[sr][sc+1]);
        
                dp[sr][sc] = minCost + grid[sr][sc];         
            }
        }

        return dp[0][0];
    }

    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        // int ans= minPathSum(grid,0,0,dp);
        int ans= minPathSum_DP(grid,0,0,dp);

        return ans;
    }

    public static int goldMineProblem(int[][] coins,int sr,int sc,int[][]dp, int dir[][]) {

        if(sc == coins[0].length - 1) return dp[sr][sc] = coins[sr][sc];

        if(dp[sr][sc] != 0) return dp[sr][sc];

        for(int d[] : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if(r >= 0 && c >= 0 && r < coins.length && c < coins[0].length) {

                dp[sr][sc] = Math.max(dp[sr][sc], goldMineProblem(coins, r,c,dp,dir));
            }
        }

        dp[sr][sc] += coins[sr][sc];

        return dp[sr][sc];
    }

    public static int goldMineProblem_DP(int[][] coins,int sr,int sc,int[][] dp,int[][] dir){
        
        for(sc = coins[0].length - 1 ; sc >= 0; sc--) {
            for(sr = coins.length - 1;sr >= 0; sr--) {

                if(sc==coins[0].length-1){
                    dp[sr][sc] = coins[sr][sc];
                    continue; 
                }
                
                for(int d=0;d<3;d++){
                     int r = sr + dir[d][0];
                     int c = sc + dir[d][1];
         
                     if(r>=0 && c>=0 && r < coins.length && c < coins[0].length){
                        dp[sr][sc] = Math.max(dp[sr][sc], dp[r][c]  + coins[sr][sc]);
                     }
                 }
            }
        }
        int max = 0;
        for(int i = 0; i < coins.length;i++) {
            max = Math.max(max, coins[i][0]);
        }
        return max;
    }

    public static int goldMineProblem(){
        int[][] coins={{10, 33, 13, 15},
                        {22, 21, 04, 1},
                        {5, 0, 2, 3},
                        {0, 6, 14, 2}};
        int[][] dp = new int[coins.length][coins[0].length];
        int[][] dir = {{-1,1},{0,1},{1,1}};

        int max = 0;
        // for(int i=0;i<coins.length;i++){
        //     max = Math.max(max, goldMineProblem(coins,i,0,dp,dir));
        // }

        max=goldMineProblem_DP(coins,0,0,dp,dir);

        print2D(dp);
        return max;
    }

}