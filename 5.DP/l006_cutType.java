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

    //------------------------------------OCT6------------------------------------//

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

    // -----------------------------------------OCT7--------------------------------

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

    // Leetcode 1039. Minimum Score Triangulation of Polygon

    // ScreenShot11600 ownwards
    // A triangle will be separated from whole polygon and then created partitions
    // will do the same in recursion
    public int minScoreTriangulation(int arr[], int si, int ei, int[][] dp) {
        if(si + 1 == ei) return dp[si][ei] = 0;
        // 2 points cannot make a triangle
        
        if(dp[si][ei] != -1) return dp[si][ei];
        
        int minAns = (int) 1e8;
        for(int cut = si+1; cut < ei; cut++) {
            
            int leftTree = minScoreTriangulation(arr,si,cut, dp);
            int rightTree = minScoreTriangulation(arr,cut,ei, dp);
            
            // you partition yourselves
            // i will calculate my value 
            int myAns = arr[si] * arr[cut] * arr[ei];
            myAns = leftTree + myAns + rightTree;
            
            minAns =  Math.min(minAns, myAns);
        }        
        return dp[si][ei] = minAns;
    }
    
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        
        int dp[][] = new int[n][n];
        for(int d[] : dp) Arrays.fill(d,-1);
        
        return minScoreTriangulation(A,0, n-1, dp);        
    }

    // Leetcode 132 Palindrome Partitioning II
    public int minCut_02_DP(String str, int SI, int EI, int[] dp, boolean[][] isPal) {
        
        // can use EI here also 
        for(int si = EI - 1; si >= SI; si--) {
            if(isPal[si][EI]) {
                dp[si] = 0;
                continue;
            }
            int minAns = (int) 1e9;
            for(int cut = si; cut < EI; cut++) {
                if(isPal[si][cut]) {
                    minAns = Math.min(minAns, dp[cut+1] + 1);
                }
            }
            dp[si] = minAns;
        }
        return dp[SI];
    }
    // in this recursion only si will change so we will use 1d dp
    public int minCut_02(String str, int si, int ei, int[]dp, boolean[][] isPal) {
        
        if(isPal[si][ei] ) return dp[si] = 0;
        if(dp[si] != -1) return dp[si];
        
        int minAns = (int) 1e8;
        // you can use here <= ei also but this will also work
        // bcz if you left with single char it is always palindrome  
        // and if you use then it will send "" string 
        for(int cut = si; cut < ei; cut++) {
            
            // we will call only when curr string is palindrome(1 length is also palindrome)
            if(isPal[si][cut]) {
                int myAns = minCut_02(str, cut + 1, ei, dp,isPal) + 1;
                // +1 bcz i made a cut here
                minAns = Math.min(minAns, myAns);
            }
        }
        return dp[si] = minAns;
    }
        // O(n*n*n)
    public int minCut_01(String str, int si, int ei, int[][] dp,boolean[][] isPal) {

        if(isPal[si][ei]) return dp[si][ei] = 0; 
        // if palidrome then no need to cut
                
        if(dp[si][ei] != -1) return dp[si][ei];
        
        int minVal = (int) 1e8;
        for(int cut = ei-1; cut >= si; cut--) {
            // run untill ei - 1 bcz for whole string we already checked in if condition

            int leftTree = minCut_01(str,si, cut,dp,isPal);
            int rightTree = minCut_01(str, cut+1,ei, dp,isPal);
            // draw cases in copy to understand the calls (how these cuts are made)
            
            // add +1 means you made a cut at this idx
            int myCost = leftTree + 1 + rightTree;
            minVal = Math.min(minVal, myCost);
        }
        
        return dp[si][ei] = minVal;
    }
    
    public void isPal(String s,boolean[][] pal, int n) {
        
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                
                if(gap == 0) pal[i][j] = true; // single char
                else if(gap == 1) pal[i][j] = s.charAt(i) == s.charAt(j);
                // 2 chars
                else {
                    pal[i][j] = ( s.charAt(i) == s.charAt(j) && pal[i+1][j-1] );
                    // above 2 chars
                }
            }
        }
    }
    public int minCut(String s) {
        int n = s.length();
        
        boolean[][] isPalindrome = new boolean[n][n];
        isPal(s, isPalindrome, n);
        
        //BruteForce
        // int dp[][] = new int[n][n];
        // for(int []d : dp) Arrays.fill(d,-1);
        // return minCut_01(s, 0, n-1, dp, isPalindrome);
       
        // // Optimized
        // int dp2[] = new int[n];
        // Arrays.fill(dp2,-1);
        // return minCut_02(s,0, n-1, dp2, isPalindrome);
        
        // Tabulation
        int dp3[] = new int[n];
        return minCut_02_DP(s,0,n-1,dp3,isPalindrome);
    }

    // Leetcode 45. Jump Game II

    //Will give TLE
    public int jump_rec(int[] arr, int idx, int[] dp) {
        
        if(idx == arr.length - 1) return dp[idx] = 0;
        if(dp[idx] != -1) return dp[idx];
        
        int minAns = (int) 1e9;
        for(int jump = idx+1; jump < arr.length && jump <= arr[idx] + idx; jump++) {
            
            minAns = Math.min(minAns, jump_rec(arr,jump , dp) + 1);
        }        
        return dp[idx] = minAns;
    }
    
    // TLE
    public int jump_DP(int[] nums) {
        int n = nums.length;
        if(n == 0 || n == 1) return 0;
        
        int dp[] = new int[n];
        dp[n-1] = 0;
        
        for(int i = n-2; i >= 0;i--) {            
            int loc = nums[i];
            dp[i] = (int) 1e9;
            
            for(int j = i+1; j <= i+loc && j < n; j++) {
                
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[0];
    }
    
    public int jump(int[] nums) {
        
        int n = nums.length;
        
        int dp[] = new int[n];
        // Arrays.fill(dp,-1);
        // return jump_rec(nums, 0, dp);
        
        return jump_dp(nums, 0, dp);
    }

    // OPTIMIZED ONE (Accepted)
    // Think like you have to take min no of buses to reach to end
    // So you will take longest route bus and after it reached then again
    // take longest route bus
    public int jump(int[] nums) {
        int n = nums.length;
        
        int maxEnding = 0; // current bus last stop
        int maxPossibleJump = 0; // max longest route bus
        
        int jump = 0; // no of buses changed
        
        for(int i = 0; i < n - 1; i++) {
            // -1 bcz if current bus reaches to end if condition will increment
            // jump as maxEnding can be equal to i
            
            maxPossibleJump = Math.max( maxPossibleJump, nums[i] + i );
            
            if(maxEnding == i) {
                // current bus route ended so take 
                // another longest route bus
                
                maxEnding = maxPossibleJump;
                jump++;
            }
        }
        return jump;
    }

    
}