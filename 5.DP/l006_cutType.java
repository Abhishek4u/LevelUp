import java.util.Arrays;
public class l006_cutType {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // mcm();
        // minMaxValue();
        OBST();
    }

    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    // Matrix Chain Multiplication Recursive
    public static int mcm_rec(int[] arr, int si, int ei, int[][] dp) {

        if(si + 1 == ei) {
            // only 1 matrix left and we do not have any cost for that
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != 0) return dp[si][ei];

        int myAns = (int) 1e8;
        for(int cut = si+1; cut < ei; cut++) {
            
            int leftTree = mcm_rec(arr,si,cut,dp);
            int rightTree = mcm_rec(arr,cut,ei,dp);

            int myCost = leftTree + arr[si] * arr[cut] * arr[ei] + rightTree;
            // multiplying cost of left and right tree

            myAns = Math.min(myAns, myCost);
        }
        return dp[si][ei] = myAns;
    }

    // Matrix Chain Multiplication DP
    public static int mcm_DP(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;

        for(int gap = 0; gap < n; gap++) {
            for(int si = 0,ei = gap; ei < n; si++, ei++) {

                if(si + 1 == ei) {
                    // only 1 matrix left and we do not have any cost for that
                    dp[si][ei] = 0;
                    continue;
                }
                int myAns = (int) 1e8;

                for(int cut = si+1; cut < ei; cut++) {
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];

                    int myCost = leftTree + arr[si] * arr[cut] * arr[ei] + rightTree;
                    // multiplying cost of left and right tree

                    myAns = Math.min(myAns, myCost);
                }
                dp[si][ei] = myAns;
            }
        }
        return dp[SI][EI];
    }

    public static void mcm() {
        int[] arr= {1,2,3,4,3,7,5,10,45,234};
        int n = arr.length;

        int[][] dp = new int[n][n];
        System.out.println(mcm_DP(arr,0,n-1,dp));

        print2D(dp);
    }

    //------------------------------------OCT7------------------------------------//

    // https://practice.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication/0
    
    public static int mcm_DP_String(int []arr, int SI, int EI, int[][] dp) {

        int n = arr.length;
        String[][] sdp = new String[n][n]; // stringDP

        for(int gap = 1; gap < n; gap++) {
            for(int si = 0, ei = gap; ei < n; si++, ei++) {

                if(si + 1 == ei) {
                    String s = "" + (char) (si + 'A');
                    sdp[si][ei] = s;
                    dp[si][ei] = 0;
                    continue;
                }
                int myAns = (int) 1e8;
                String s = "";
                for(int cut = si + 1; cut < ei; cut++) {
                    int leftTree = dp[si][cut];
                    int rightTree = dp[cut][ei];

                    int myCost = leftTree + arr[si] * arr[cut] * arr[ei] + rightTree;

                    if(myCost < myAns) {
                        myAns = myCost;
                        s = '(' + sdp[si][cut] + sdp[cut][ei] + ')';
                    }
                }
                dp[si][ei] = myAns;
                sdp[si][ei] = s;
            }
        }
        System.out.println(sdp[SI][EI]);
        return dp[SI][EI];
    }

    // Minimum and Maximum values of an expression with * and +
    // https://www.geeksforgeeks.org/minimum-maximum-values-expression/

    public static class minMaxPair {
        int minVal = (int) 1e8;
        int maxVal = 0;

        minMaxPair(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        minMaxPair() {

        }

        public String toString() {
            return "(" + this.minVal + ", " + this.maxVal + ')';
        }
    }

    public static int evaluate(char ch, int v1, int v2) {
        if(ch == '+') return v1 + v2;
        if(ch == '-') return v1 - v2;
        else return v1 * v2;
        // multiply case
    }

    // https://www.geeksforgeeks.org/minimum-maximum-values-expression/

    // * and + operators only
    public static minMaxPair minMaxValue(String str,int si, int ei, minMaxPair[][] dp) {

        if(si == ei) {
            int val = str.charAt(si) - '0';
            return dp[si][ei] = new minMaxPair(val,val);
        }

        if(dp[si][ei] != null) return dp[si][ei];

        minMaxPair myAns = new minMaxPair();
        for(int cut = si+1; cut < ei; cut += 2) {

            minMaxPair leftTree = minMaxValue(str, si, cut-1, dp);
            minMaxPair rightTree = minMaxValue(str, cut+1, ei, dp);

            char ch = str.charAt(cut);

            int min = Math.min(myAns.minVal, evaluate(ch, leftTree.minVal, rightTree.minVal) );
            int max = Math.max(myAns.maxVal, evaluate(ch, leftTree.maxVal, rightTree.maxVal) );

            myAns.minVal = min;
            myAns.maxVal = max;
        }
        return dp[si][ei] = myAns;
    }

    public static minMaxPair minMaxValue_DP(String str,int SI, int EI, minMaxPair[][] dp) {
        int n = str.length();

        for(int gap = 0; gap < n; gap++) {
            for(int si = 0, ei = gap; ei < n; si++, ei++) {

                if(si == ei) {
                    int val = str.charAt(si) - '0';
                    dp[si][ei] = new minMaxPair(val,val);
                    continue;
                }

                minMaxPair myAns = new minMaxPair();
                for (int cut = si+1; cut < ei; cut += 2) {
                
                    minMaxPair leftTree = dp[si][cut - 1];
                    minMaxPair rightTree = dp[cut+1][ei];

                    char ch = str.charAt(cut);

                    int min = Math.min(myAns.minVal, evaluate(ch, leftTree.minVal, rightTree.minVal) );
                    int max = Math.max(myAns.maxVal, evaluate(ch, leftTree.maxVal, rightTree.maxVal) );

                    myAns.minVal = min;
                    myAns.maxVal = max;
                }
                dp[si][ei] = myAns;
            }
        }
        return dp[SI][EI];
    }

    // all combinations of min and max values using operantors
    public static minMaxPair evalCombination(char operator, minMaxPair p1, minMaxPair p2) {

        int a = evaluate(operator, p1.minVal, p2.minVal);
        int b = evaluate(operator, p1.minVal, p2.maxVal);
        int c = evaluate(operator, p1.maxVal, p2.minVal);
        int d = evaluate(operator, p1.maxVal, p2.maxVal);

        minMaxPair p = new minMaxPair();
        p.minVal = Math.min( Math.min(a,b), Math.min(c,d) );
        p.maxVal = Math.max( Math.max(a,b), Math.max(c,d) );

        return p;
    }

    // +, - and * operators
    public static minMaxPair minMaxValue_02(int[] numArr, char[] chArr, int si, int ei, minMaxPair[][] dp) {

        if(si == ei) {
            int val = numArr[si];
            return dp[si][ei] = new minMaxPair(val,val);
        }

        if(dp[si][ei] != null) return dp[si][ei];

        minMaxPair myAns = new minMaxPair();
        for(int cut = si; cut < ei; cut++) {

            minMaxPair leftTree = minMaxValue_02(numArr, chArr, si, cut, dp);
            minMaxPair rightTree = minMaxValue_02(numArr, chArr, cut+1, ei, dp);

            char ch = chArr[cut];
            minMaxPair p = evalCombination( ch, leftTree, rightTree );

            myAns.minVal = Math.min( myAns.minVal, p.minVal );
            myAns.maxVal = Math.max( myAns.maxVal, p.maxVal );
        }
        return dp[si][ei] = myAns;
    }

    public static void minMaxValue() {
        String str = "1+2*3+4*5";
        
        int n = str.length();
        minMaxPair[][] dp = new minMaxPair[n][n];
        minMaxPair ans = minMaxValue(str, 0, n-1, dp);

        System.out.println(ans.minVal + " , " + ans.maxVal);

        for(minMaxPair []d : dp) {
            for(minMaxPair e : d) {
                System.out.print(e + "\t");
            }
            System.out.println();
        }
    }

    // Leetcode 312. Burst Balloons

    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if(dp[si][ei] != -1) return dp[si][ei];
        
        int liVal = (si - 1 == -1) ? 1 : nums[si - 1];
        // left index value( if out of bounds use 1(multiplicative identity))
        
        int riVal = (ei + 1 == nums.length) ? 1 : nums[ei + 1];
        // right index value( if out of bounds use 1(multiplicative identity))

        int myCost = 0;
        for(int cut = si; cut <= ei; cut++) {
            
            int leftTree = (cut == si) ? 0 : maxCoins(nums, si, cut - 1, dp);
            int rightTree = (cut == ei) ? 0 : maxCoins(nums, cut + 1, ei, dp);
            // cut == si/ei is base case where calculations of val is not possible
            // bcz we will burst that balloon after other balloons
            
            int myVal = leftTree + liVal * nums[cut] * riVal + rightTree;
            myCost = Math.max(myCost, myVal );
        }
        return dp[si][ei] = myCost;
    }
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int [][]dp = new int[n][n];
        for(int[] d: dp) Arrays.fill(d, -1);
        // filling with -1 bcz 0 can also be balloon(given in ques)
        
        return maxCoins(nums, 0, n-1, dp);        
    }

    // Optimal Binary Search Tree
    // https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/

    // Note if you use level in this approach then it will give wrong answer
    // To use level you have to use 3d dp as level will also change in recursion call
    public static int OBST(int freq[], int si, int ei, int[][] dp, int[] prefixSum) {

        if(dp[si][ei] != 0) return dp[si][ei];

        int myRes = (int) 1e8;
        for(int cut = si; cut <= ei; cut++) {

            int leftTree = cut == si ? 0 : OBST(freq, si, cut-1, dp, prefixSum);
            // if you call in cut == si then in rec call si > ei which is wrong
            // so we handled this base case here
            int rightTree = cut == ei ? 0 : OBST(freq, cut+1, ei, dp, prefixSum);
            // if you call in cut == ei then in rec call si > ei which is wrong
            // so we handled this base case here

            int myAns = prefixSum[ei] - (si == 0 ? 0 : prefixSum[si - 1]);
            // we used prefix array for increasing the count
            // of child values( freq* lvl) according to ques

            myAns = leftTree + myAns + rightTree;

            myRes = Math.min(myRes, myAns);
        }
        return dp[si][ei] = myRes;
    }

    public static void OBST() {
        int freq[] = {34,8,50};
        int val[] = {10,12,20};

        int n = val.length;

        int dp[][] = new int[n][n];

        int prefixSum[] = new int[n];

        int prev = 0;
        for(int i = 0; i < n;i++) {
            prefixSum[i] = prev + freq[i];
            prev = prefixSum[i];
        }

        System.out.println(OBST(freq, 0, n-1, dp, prefixSum ));
        // we do not need to send val array as we just need to minimize
        // the cost and this cost can be minimized without val[]
        print2D(dp);
    }

}