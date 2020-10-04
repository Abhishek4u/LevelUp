import java.util.Arrays;
public class l003 {
    public static void main(String[] args) {

    }

    //---------------------------------STARTS--FROM--OCT1---------------------------------

    // https://leetcode.com/problems/decode-ways/submissions/
    // 91. Decode Ways
    
    // O(2power (n)) time
    public int numDecodings_(String s,int idx,int[] dp) {
        
        if(idx == s.length()) return dp[idx] = 1;
        
        if(dp[idx] != -1) return dp[idx];
        
        int num = s.charAt(idx) - '0';
        if( num == 0) return 0;
        
        int count = 0;
        
        count += numDecodings_(s,idx+1,dp);
        
        if(idx+1 < s.length()) {
            int num2 = s.charAt(idx+1) - '0';
            
            if(num*10 + num2 <= 26) count += numDecodings_(s,idx+2,dp);
        }
        return dp[idx] = count;
    }
    
    // O(n) time, O(n) space
    public int numDecodings_DP(String s,int idx,int[] dp) {
        
        for(idx = s.length(); idx >= 0; idx--) {
                        
            if(idx == s.length()) {
                dp[idx] = 1;
                continue;
            }
            int num = s.charAt(idx) - '0';
            if( num == 0) {
                dp[idx] =  0;
                continue;
            }
            int count = 0;
            count += dp[idx+1];

            if(idx+1 < s.length()) {
                int num2 = s.charAt(idx+1) - '0';

                if(num*10 + num2 <= 26) count += dp[idx+2];
            }
            dp[idx] = count; 
        }
        return dp[0];
    }
    // O(n) time, O(1) space
    public int numDecodings_Opti(String s,int idx,int[] dp) {
        // len+1 , len
        int a = 1,b = 0;
        for(idx = s.length() - 1; idx >= 0; idx--) {
                        
            int num = s.charAt(idx) - '0';
            int sum = 0;
            
            if( num != 0) {
                sum += a;

                if(idx+1 < s.length()) {
                    int num2 = s.charAt(idx+1) - '0';

                    if(num*10 + num2 <= 26) sum += b;
                }
            }
            b = a;
            a = sum;
        }
        return a; // 0th index
    }

    public int numDecodings(String s) {
        
        int dp[] = new int[s.length()+1];

        Arrays.fill(dp,-1);
        // for better compexity

        // return numDecodings_(s,0,dp);
        // return numDecodings_DP(s,0,dp);
        return numDecodings_Opti(s,0,dp);
    }
    
    // Leetcode 639. Decode Ways II
    // https://leetcode.com/problems/decode-ways-ii/

    // using long bcz modulo can overflow
    public long numDecodings_(String s,int idx,long dp[]) {
        
        if(idx == s.length()) return dp[idx] = 1;
        
        if(dp[idx] != -1) return dp[idx];
        char ch = s.charAt(idx);
        
        if( ch == '0') return dp[idx] = 0;
        
        long count = 0;
        if(ch >= '1' && ch <= '9')  count = (count % mod  + numDecodings_(s,idx+1,dp) % mod ) % mod;
        
        else if(ch == '*')  count = (count % mod  +  9 * numDecodings_(s,idx+1,dp) % mod ) % mod;
        
        if(idx+1 < s.length()) {

            char ch2 = s.charAt(idx+1);
            
            if(ch != '*' && ch2 != '*') {
                int num = (ch - '0') * 10 + (ch2 - '0');
                if(num <= 26) 
                    count = (count % mod  + numDecodings_(s,idx+2,dp) % mod ) % mod;
            
            } else if(ch == '*' && ch2 != '*') {
                
                if(ch2 >= '0' && ch2 <= '6') count = (count % mod + 2 * numDecodings_(s,idx+2,dp) % mod) % mod;
                if(ch2 >= '7' && ch2 <= '9')  count = (count % mod + numDecodings_(s,idx+2,dp) % mod ) % mod;
                
            } else if( (ch == '1' || ch == '2') && ch2 == '*') {
                
                if(ch == '1')  count = (count % mod  + 9 * numDecodings_(s,idx+2,dp) % mod ) % mod;
                if(ch == '2')  count = (count % mod  + 6 * numDecodings_(s,idx+2,dp) % mod ) % mod;
            
            } else if( ch == '*' && ch2 == '*') 
                count = (count % mod  + 15 * numDecodings_(s,idx+2,dp) % mod ) % mod;
        }
        return dp[idx] = count;
    }

    long mod = (int) 1e9 + 7;
    public int numDecodings(String s) {
        
        long dp[] = new long[s.length()+1];
        Arrays.fill(dp,-1);
        return (int)numDecodings_(s,0,dp);
    }

    // --------------------------OCT-3-CODES(LIS class)---------------------------- //

	//  Largest square formed in a matrix 
    // https://practice.geeksforgeeks.org/problems/largest-square-formed-in-a-matrix/0

	public static int maxSquare(int arr[][], int i, int j,int dp[][]) {

		if(dp[i][j] != 0) return dp[i][j];
	    
	    int minSize = (int) 1e9;
	    
	    int dir[][] = {{1,0}, {0,1}, {1,1}};
	    
	    for(int d[] : dir) {
	        int x = i + d[0];
	        int y = j + d[1];
	        
	        if(x >= 0 && y >= 0 && x < arr.length && y < arr[0].length) {
	            
	            if(arr[x][y] == 0) {
	                minSize = 0;
	                break;
	                
	            } else {
	                minSize = Math.min(minSize, maxSquare(arr,x,y,dp));
	            }
	        } else {
                minSize = 0; 
                // it can't be expanded bcz all elts are not present for making square
            }

	    }
	    
	    return dp[i][j] =  minSize == 1e9 ? 1 : minSize + 1;
	}

    public static int findMax(int arr[][]) {
	    int n = arr.length,m = arr[0].length;
	    
	    int max = 0;
		int dp[][] = new int[n][m];
	    
	    for(int i = 0; i < n;i++) {
	        for(int j = 0; j < m;j++) {
	            
	            if(arr[i][j] == 1) {
	                max = Math.max(maxSquare(arr,i,j,dp), max);
	            }
	        }
	    }
	    return max;
	}

    // Leetcode 413. Arithmetic Slices
    // https://leetcode.com/problems/arithmetic-slices/

    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if(n < 3) return 0;
        
        int ans = 0;
        int count = 0;
        
        for(int i = 1; i < n - 1; i++) {
            
            int d1 = A[i] - A[i-1];
            int d2 = A[i+1] - A[i];
            
            if(d1 == d2) ans += (++count);
            else count = 0;
        }
        return ans;
    }

    // Leetcode 1027
    // Leetcode 1218
}