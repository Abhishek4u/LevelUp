import java.util.Arrays;
public class StringSet {

    //--------FOR STRING DP QUES MOVE FROM BACKWARDS IF POSSIBLE BCZ IT IS EASY TO THINK------------//

    public static void main(String[] args) {

        solve();

    }

    public static void print(int []arr) {

        for(int ele: arr) System.out.print(ele+ " ");

        System.out.println();
    }
    public static void print2D(int[][] arr) {

        for(int []ar : arr) print(ar);

        System.out.println();
    }

    public static void solve() {

        // stringset();
        String s1 = "rabbbit", s2 = "rabbit";
        int n = s1.length() , m = s2.length();

        int dp[][] = new int[n+1][m+1];
        System.out.println(numDistinct(s1,s2,n,m,dp));
        print2D(dp);
    }

    public static void stringset() {

        // String str = "geekeeksg";
        // int n = str.length();
        // int[][] dp = new int[n][n];

        // // System.out.println(longestPSS(str,0,n - 1,dp));
        // System.out.println(longestPSS_DP(str,0,n - 1,dp));
        // print2D(dp);

        // int len = dp[0][n-1]; // size of substring
        // char[] ans = new char[len];
        // longestPSS_String(str,0,n - 1, dp, ans,0,len-1);

        longestCommonSubsequence("AGGTAB","GXTXAYB");

    }


    // Leetcode 516. Longest Palindromic Subsequence
    // https://leetcode.com/problems/longest-palindromic-subsequence/

    // Longest  Palindromic Subsequence (length)
    public static int longestPSS(String str, int i, int j, int [][]dp) {

        if(i >= j) {
            if(i == j) return dp[i][j] = 1; // single elt will be palidrome 
            else return dp[i][j] = 0;
        }

        if(dp[i][j] != 0) return dp[i][j];

        if(str.charAt(i) == str.charAt(j)) {
            return dp[i][j] = longestPSS(str,i+1,j-1,dp) + 2;
            // bcz last and first char are same so get inner string size + 2

        } else {
            int a = longestPSS(str,i+1,j, dp); // chance to jth elt to include
            int b = longestPSS(str, i,j-1,dp); // chance to ith elt to include

            return dp[i][j] = Math.max(a,b);
        }
    }

    public static int longestPSS_DP(String str, int i, int j, int [][]dp) {

        int n = str.length();
        for(int gap = 0; gap < n;gap++) {
            for(i = 0, j = gap; j < n; i++, j++) {

                if(i >= j) {
                    dp[i][j] = (i == j ? 1 : 0);
                    continue;
                }

                if(str.charAt(i) == str.charAt(j)) dp[i][j] = dp[i+1][j-1] + 2;
                   
                else {
                    int a = dp[i+1][j];
                    int b = dp[i][j-1];

                    dp[i][j] = Math.max(a,b);
                }
            }
        }
        return dp[0][n-1];
    }

    // Moving in dp with max value to print characters
    // THIS IS KNOWN AS REVERSE ENGINEERING
    public static void longestPSS_String(String str,int i, int j, int[][]dp, char[] ans,int si, int ei) {

        if(i >= j) {
            if(i == j) {
                ans[si] = str.charAt(i);
                for(char ch : ans) System.out.print(ch);
                System.out.println();
            }
            return;
        }

        if(str.charAt(i) == str.charAt(j)) {
            ans[si] = ans[ei] = str.charAt(i);
            longestPSS_String(str, i+1, j-1, dp,ans,si+1,ei-1);

        } else if(dp[i+1][j] > dp[i][j-1]) {
            longestPSS_String(str,i+1,j,dp,ans,si,ei);

        } else if(dp[i+1][j] <= dp[i][j-1] ) {
            longestPSS_String(str,i,j-1,dp,ans,si,ei);
        }
    }

    // 1143. Longest Common Subsequence
    // https://leetcode.com/problems/longest-common-subsequence/
    
    public static int longestCommonSubsequence_(String s1, String s2,int i, int j, int[][]dp) {
        
        if(i == s1.length() || j == s2.length() ) {
            return 0; 
            // reached to end in one of the string
        }

        if(dp[i][j] != 0) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)) 
            return dp[i][j] = 1 + longestCommonSubsequence_(s1,s2,i+1,j+1,dp);
        // 1 char is same so return 1 + recursion call for next characters

        else {
            int a = longestCommonSubsequence_(s1,s2,i+1,j,dp);
            int b = longestCommonSubsequence_(s1,s2,i,j+1,dp);

            return dp[i][j] = Math.max(a,b);
        }
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        
        int n = text1.length();
        int m = text2.length();
        // max size for dp defining
        
        int dp[][] = new int[n+1][m+1];
        // n+1,m+1 bcz we will using length() as base case
        int rVal = longestCommonSubsequence_DP(text1,text2,0,0,dp);
        print2D(dp);

        int len = dp[0][0]; // size of lcs
        char[] ans = new char[len];

        longestCommonSubsequence_String(text1,text2,0,0,dp,ans,0);

        return rVal;
    }

    // Same fxn but traversing from backwards
    public static int lcss2(String s1,String s2,int n, int m,int[][] dp) {

        if(n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if(dp[n][m] != 0) return dp[n][m];

        if(s1.charAt(n-1) == s2.charAt(m-1)) {
            // m-1,n-1 bcz n and m are length of strings
            return dp[n][m] = lcss2(s1,s2,n-1,m-1,dp) + 1;

        } else {
            int a = lcss2(s1,s2,n-1,m,dp);
            int b = lcss2(s1,s2,n,m-1,dp);

            return dp[n][m] = Math.max(a,b);
        }
    
    }

    public static int longestCommonSubsequence_DP(String s1, String s2,int i, int j, int[][]dp) {
        
        for(i = s1.length(); i >= 0;i--) {
            for(j = s2.length(); j >= 0;j--) {

                if(i == s1.length() || j == s2.length() ) {
                    dp[i][j] =  0; 
                    continue;
                }

                if(s1.charAt(i) == s2.charAt(j)) 
                    dp[i][j] = 1 + dp[i+1][j+1];
                
                else {
                    int a = dp[i+1][j];
                    int b = dp[i][j+1];

                    dp[i][j] = Math.max(a,b);
                }
            }
        }
        return dp[0][0];
    }

    public static void longestCommonSubsequence_String(String s1, String s2,int i, int j, int dp[][], char[] ans,int idx) {

        if(i == s1.length() || j == s2.length()) {
            for(char ch : ans) System.out.print(ch);
            System.out.println();
            return;
        }

        if(s1.charAt(i) == s2.charAt(j)) {
            ans[idx] = s1.charAt(i);
            longestCommonSubsequence_String(s1,s2,i+1,j+1,dp,ans,idx+1);

        } else if(dp[i+1][j] > dp[i][j+1]) {
            longestCommonSubsequence_String(s1,s2,i+1,j,dp,ans,idx);

        } else {
            longestCommonSubsequence_String(s1,s2,i,j+1,dp,ans,idx);
        }

    }

    // Count Palindromic Subsequences
    // https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    
    // See formula in copy (and teach with exapmle in interview)
    public static int countPS(String str,int i,int j,int[][] dp) {
        if(i>=j){
            return dp[i][j] = ( i == j ) ? 1 : 0;
        }

        int x = countPS(str,i+1,j-1,dp);
        int y = countPS(str,i,j-1,dp);
        int z = countPS(str,i+1,j,dp);
        
        if(str.charAt(i) == str.charAt(j)) dp[i][j] = (x + 1) + (y + z - x);
        else dp[i][j] = (y + z - x);

        return dp[i][j];
    }

    public static int countPS(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        return countPS(str, 0, 0 , dp);
    }

    // Leetcode 115. Distinct Subsequences
    // https://leetcode.com/problems/distinct-subsequences/

    // Reverse movement of string memoization
    public static int numDistinct(String s, String t,int n, int m, int[][] dp) {

        if(n < m) return 0;
        // s string is smaller than t so answer is not possible now

        if(m == 0) return dp[n][m] = 1; // we found 1 answer as we reached at end of string
        
        if(dp[n][m] != 0) return dp[n][m];
        // memoization will give tle bcz 0 has signifance in our answer 
        // and here we are ignoring 0 case so found tabulation code by analyzing dp

        if(s.charAt(n - 1) == t.charAt(m - 1)) {
            // n - 1 bcz we will send the length of string so 
            // that we can use base case as 0 indexed

            int a = numDistinct(s,t,n-1,m-1,dp);
            int b = numDistinct(s,t, n-1,m,dp);
            return dp[n][m] = a + b;

        } else {
            int b = numDistinct(s,t, n-1,m,dp);
            return dp[n][m] = b;
        }
    }

    // Dp for reverse movement memoization version
    public static int numDistinct_DP(String s, String t,int n, int m, int[][] dp) {

        for(n = 0; n <= s.length(); n++) {
            for(m = 0 ; m <= t.length(); m++) {
                if(n < m) {
                    // s became smaller than t so now answer is not possible
                    dp[n][m] = 0;
                    continue;
                } 
                if(m == 0) {
                    // t is finished so now we got 1 answer
                    dp[n][m] = 1; 
                    continue;
                }
                if(s.charAt(n - 1) == t.charAt(m - 1)) {
                    dp[n][m] = dp[n-1][m-1] + dp[n-1][m];
                } else {   
                    dp[n][m] = dp[n-1][m];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    //-----------------------------30Sep-----------------------------

    // Leetcode 940. Distinct Subsequences II
    // https://leetcode.com/problems/distinct-subsequences-ii/

    public int distinctSubseqII(String S) {
        int n = S.length();
        if(n == 0) return 0;
        
        int mod = (int) 1e9 + 7;
        
        S = '$' + S; // for simplicty of 0 index char
        // it will act as empty subsequence 
        
        int dp[] = new int[n+1];
        int []locc = new int[26];
        // last location of characters
        
        Arrays.fill(locc,-1);
        dp[0] = 1; // empty subsequence
        
        for(int i = 1; i <= n;i++) { 
            char ch = S.charAt(i);
            
            dp[i] = (dp[i-1] * 2) % mod;
            // either previous subsquences will come
            // or curr elt will join these subsequences
            
            if(locc[ch - 'a'] != -1) {
                // remove duplicate subsequences (equals to locc[i] - 1) (See copy)
                dp[i] = ( dp[i] % mod - dp[locc[ch - 'a'] - 1] % mod + mod )% mod;
                // add mod (its a property when you do subtract 
                // add mod bcz value can become -ve)
            }
            locc[ch - 'a'] = i;
            // update new location
        }
        return dp[n] - 1;
        // remove empty subsequence
    }

    // Count subsequences of type a^i b^j c^k
    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck/0

    public static int aibjck(String str) {
	    
	    int aCount = 0; // a
	    int bCount = 0; // ab
	    int cCount = 0; // abc
	    
	    for(int i = 0; i < str.length(); i++) {
	        
	        char ch = str.charAt(i);
	        
	        if(ch == 'a') aCount = aCount + ( 1 + aCount); // notIncludeSelf + IncludeSelfWith( alone | prevSet)
	        else if(ch == 'b') bCount = bCount + (aCount + bCount); // notIncludeSelf + IncludeSelfWith( a | prevSet)
	        else if(ch == 'c') cCount = cCount + (bCount + cCount); // notIncludeSelf + IncludeSelfWith( b | prevSet)
	        
	    }
	    return cCount;
	}

    // Leetcode 72. Edit Distance
    // https://leetcode.com/problems/edit-distance/
    
    public int minDistance_(String s1, String s2, int n, int m, int dp[][]) {
        
        if( n == 0 || m == 0 ) {
            return dp[n][m] = n == 0 ? m : n;
        }
        
        if(dp[n][m] != 0) return dp[n][m];
        
        if(s1.charAt(n - 1) == s2.charAt(m - 1)) return dp[n][m] = minDistance_(s1,s2,n-1,m-1,dp);
        else {
            int insert = minDistance_(s1,s2,n,m-1,dp);
            int delete = minDistance_(s1,s2,n-1,m,dp);
            int replace = minDistance_(s1,s2,n-1,m-1,dp);
            
            return dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;
        }
    }

    public int minDistance_DP(String s1, String s2, int n, int m, int dp[][]) {
        
        int N = n, M = m;
        for(n = 0; n <= N;n++) {
            for(m = 0; m <= M;m++) {

                if( n == 0 || m == 0 ) {
                    dp[n][m] = n == 0 ? m : n;
                    continue;
                }
                
                if(s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    
                    dp[n][m] = minDistance_(s1,s2,n-1,m-1,dp);
                } else {

                    int insert = minDistance_(s1,s2,n,m-1,dp);
                    int delete = minDistance_(s1,s2,n-1,m,dp);
                    int replace = minDistance_(s1,s2,n-1,m-1,dp);
                    
                    dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;
                }
            }
        }
        return dp[N][m];
    }

    public int minDistance(String word1, String word2) {
        
        int n = word1.length(), m = word2.length();
        int dp[][] = new int[n+1][m+1];

        // minDistance_(word1, word2, n, m, dp);
        minDistance_DP(word1, word2, n, m, dp);
        print2D(dp);
        return 1;

    } 

    // GFG LCS of three strings
    // https://practice.geeksforgeeks.org/problems/lcs-of-three-strings/0

    public static int LCS(String a, String b, String c, int i, int j, int k, int dp[][][]) {
	    
	    if(i == 0 || j == 0 || k == 0) return dp[i][j][k] = 0;
	    
	    if(dp[i][j][k] != 0) return dp[i][j][k];
	    
	    if(a.charAt(i - 1) == b.charAt(j - 1) && b.charAt(j - 1) == c.charAt(k - 1)) {
	        
	        return dp[i][j][k] = 1 + LCS(a,b,c,i-1,j-1,k-1,dp);
	        
	    } else {
	        int p = LCS(a,b,c, i-1, j, k, dp);
	        int q = LCS(a,b,c, i, j-1, k, dp);
	        int r = LCS(a,b,c, i, j, k-1, dp);
	        
	        return dp[i][j][k] = Math.max(p,Math.max(q,r));
	    }
	}

    // In this we have used static strings for time complexity
    public static int LCS_DP(int i, int j, int k, int dp[][][]) {
	    int I = s1.length(), J = s2.length(), K = s3.length(); 
	    
	    for(i = 0; i <= I; i++) {
            for(j = 0; j <= J; j++) {
                for(k = 0; k <= K; k++) {
                    
            	    if(i == 0 || j == 0 || k == 0){
            	        dp[i][j][k] = 0;
            	        continue;
            	    }
            	    if(s1.charAt(i - 1) == s2.charAt(j - 1) && s2.charAt(j - 1) == s3.charAt(k - 1)) {
            	        
            	         dp[i][j][k] = 1 + dp[i-1][j-1][k-1];
            	        
            	    } else {
            	        int p = dp[i-1][j][k];
            	        int q = dp[i][j-1][k];
            	        int r = dp[i][j][k-1];
            	        
            	        dp[i][j][k] = Math.max(p,Math.max(q,r));
            	    }
                }
            }
	    }
	    return dp[I][J][K];
	}

    // Leetcode 44. Wildcard Matching
    // https://leetcode.com/problems/wildcard-matching/

    public int wildCardMatching(String str1,String str2,int n,int m,int[][] dp){
        
        if(n == 0 && m == 0) return dp[n][m] = 1;
        else if(n == 0 || m == 0) {
            if(m == 1 && str2.charAt(m-1) == '*') return dp[n][m] = 1;
            return dp[n][m] = 0;
        }
        
        if(dp[n][m] != -1) return dp[n][m];
        
        char ch1 = str1.charAt(n-1);
        char ch2 = str2.charAt(m-1);
        int val = -1;
        
        if(ch1 == ch2 || ch2 == '?')
            val = wildCardMatching(str1, str2, n-1, m-1, dp);
        
        else if(ch2 == '*') {
            boolean res = false;
            res = res || wildCardMatching(str1, str2, n-1, m, dp) == 1;
            res = res || wildCardMatching(str1, str2, n, m-1, dp) == 1;
            
            val = res ? 1 : 0;
        }
        else val = 0;
        
        return dp[n][m] = val;
    }
    
    public String removeExtraStar(String s) {
        if(s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        
        sb.append(s.charAt(0));
        int i = 1;
        while( i < s.length()) {
            
            while(i < s.length() && s.charAt(i) == '*' && s.charAt(i-1) == s.charAt(i)) i++;
            
            if(i < s.length()) sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }
    
    public boolean isMatch(String s, String p) {
        p = removeExtraStar(p);
        
        int n = s.length();
        int m = p.length();
        
        int dp[][] = new int[n+1][m+1];
        for(int []a : dp) Arrays.fill(a,-1);
        
        return wildCardMatching(s,p,n,m,dp) == 1;
        
    }

}