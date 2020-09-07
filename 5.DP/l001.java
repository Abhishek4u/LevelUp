import java.util.Arrays;
import java.util.LinkedList;
public class l001 {

    public static void main(String[] args) {

        // solve1();
        solve2();
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

    public static void solve1() {

        // int n = 10;
        // int dp[] = new int[n + 1];

        // System.out.println(fibo(n, dp));
        // System.out.println(fibo_DP(n, dp));
        // System.out.println(fibo_Optimized(n));
        // print(dp);

        int n = 5;
        int[][] dp = new int[n][n];

        // System.out.println(mazePathsHV(0, 0, n - 1, n - 1, dp));
        // System.out.println(mazePathsHV_DP(0, 0, n - 1, n - 1, dp));

        System.out.println(mazePathsHVD(0, 0, n - 1, n - 1, dp));
        System.out.println(mazePathsHVD_DP(0, 0, n - 1, n - 1, dp));

        print2D(dp);

    }

    public static void solve2() {

        int n = 10;
        int dp[] = new int[n + 1];

        System.out.println(boardPath1(0, n, dp));
        System.out.println(boardPath1_DP(0, n, dp));
        System.out.println(boardPath1_Opti(0, n, dp));

        int moves[] = { 2, 7, 5, 3 };
        // System.out.println(boardPath_Moves(0, n, moves, dp));
        // System.out.println(boardPath_MovesDP(0, n, moves, dp));

    }

    public static int fibo(int n, int dp[]) {

        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        int a = fibo(n - 1, dp);
        int b = fibo(n - 2, dp);

        return a + b;
    }

    public static int fibo_DP(int n, int dp[]) {

        int N = n;
        for (n = 0; n <= N; n++) {

            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int a = dp[n - 1]; // fibo(n-1,dp);
            int b = dp[n - 2]; // fibo(n-2,dp);

            dp[n] = a + b;

        }

        return dp[N];
    }

    public static int fibo_Optimized(int N) {

        int a = 0;
        int b = 1;

        for (int n = 2; n <= N; n++) {

            int sum = a + b;
            a = b;
            b = sum;

        }
        return b;
    }

    // HORIZONTAL, VERTICAL
    public static int mazePathsHV(int sr, int sc, int er, int ec, int dp[][]) {

        // in fxn er & ec is sent as (n - 1) & (m - 1) so used = here in condition
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        int count = 0;

        // in fxn er & ec is sent as (n - 1) & (m - 1) so used = here in condition
        if (sc + 1 <= ec) {
            count += mazePathsHV(sr, sc + 1, er, ec, dp);
        }

        if (sr + 1 <= er) {
            count += mazePathsHV(sr + 1, sc, er, ec, dp);
        }

        return dp[sr][sc] = count;
    }

    public static int mazePathsHV_DP(int sr, int sc, int er, int ec, int dp[][]) {

        for (int i = er; i >= 0; i--) {

            for (int j = ec; j >= 0; j--) {

                if (i == er && j == ec) {
                    dp[i][j] = 1;
                    continue;
                }

                int count = 0;

                if (j + 1 <= er) {
                    count += dp[i][j + 1]; // mazePathsHV(sr, sc + 1, er, ec, dp);
                }

                if (i + 1 <= ec) {
                    count += dp[i + 1][j]; // mazePathsHV(sr + 1, sc, er, ec, dp);
                }

                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    // HORIZONTAL, VERTICAL, DIAGONAL
    public static int mazePathsHVD(int sr, int sc, int er, int ec, int dp[][]) {

        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }
        int count = 0;

        if (sc + 1 <= er) {
            count += mazePathsHV(sr, sc + 1, er, ec, dp);
        }

        if (sr + 1 <= er) {
            count += mazePathsHV(sr + 1, sc, er, ec, dp);
        }

        if (sr + 1 <= er && sc + 1 <= er) {
            count += mazePathsHV(sr + 1, sc + 1, er, ec, dp);
        }

        return dp[sr][sc] = count;
    }

    public static int mazePathsHVD_DP(int sr, int sc, int er, int ec, int dp[][]) {

        for (int i = er; i >= 0; i--) {

            for (int j = ec; j >= 0; j--) {

                if (i == er && j == ec) {
                    dp[i][j] = 1;
                    continue;
                }

                int count = 0;

                if (j + 1 <= er) {
                    count += dp[i][j + 1]; // mazePathsHV(sr, sc + 1, er, ec, dp);
                }

                if (i + 1 <= ec) {
                    count += dp[i + 1][j]; // mazePathsHV(sr + 1, sc, er, ec, dp);
                }

                if (i + 1 <= er && j + 1 <= er) {
                    count += dp[i + 1][j + 1]; // mazePathsHV(sr + 1, sc + 1, er, ec, dp);
                }

                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    // ----------------------------BOARD VARIATION--------------------------

    public static int boardPath1(int si, int ei, int dp[]) {

        if (si == ei) {
            return dp[si] = 1;
        }

        if (dp[si] != 0) {

            return dp[si];
        }

        int count = 0;

        for (int dice = 1; dice <= 6 && si + dice <= ei; dice++) {

            count += boardPath1(si + dice, ei, dp);

        }

        return dp[si] = count;
    }

    public static int boardPath1_DP(int si, int ei, int dp[]) {

        for (si = ei; si >= 0; si--) {

            if (si == ei) {
                dp[si] = 1;
                continue;
            }
            int count = 0;

            for (int dice = 1; dice <= 6 && si + dice <= ei; dice++) {

                count += dp[si + dice]; // boardPath1(si + i, ei,dp);
            }

            dp[si] = count;
        }
        return dp[0];
    }

    public static int boardPath1_Opti(int si, int ei, int dp[]) {

        LinkedList<Integer> ll = new LinkedList<> ();

        for(si = ei ; si >= 0;si--) {

            if(si >= ei - 1 ) {
                ll.addFirst(1);
                continue;
            }

            if(ll.size() <= 6) ll.addFirst(ll.getFirst() * 2);

            else {

                int lVal = ll.removeLast();

                ll.addFirst(ll.getFirst() * 2 - lVal);
            }
            
        }
        return ll.getFirst();


    }

    public static int boardPath_Moves(int si, int ei, int[] moves, int dp[]) {

        if (si == ei) {
            return dp[si] = 1;
        }

        if (dp[si] != 0) {
            return dp[si];
        }

        // if curr idx value is greater then for loop will break
        // so sort them before using
        Arrays.sort(moves);

        int count = 0;

        for (int i = 0; i < moves.length && si + moves[i] <= ei; i++) {

            count += boardPath_Moves(si + moves[i], ei, moves, dp);
        }
        return dp[si] = count;

    }

    public static int boardPath_MovesDP(int si, int ei, int[] moves, int dp[]) {

        // if curr idx value is greater then for loop will break
        // so sort them before using
        Arrays.sort(moves);

        for (si = ei; si >= 0; si--) {

            if (si == ei) {
                dp[si] = 1;
                continue;
            }

            int count = 0;

            for (int i = 0; i < moves.length && si + moves[i] <= ei; i++) {

                count += dp[si + moves[i]]; // boardPath_Moves(si + moves[i], ei, moves, dp);
            }
            dp[si] = count;
        }

        return dp[0];
    }

}