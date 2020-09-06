import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class questions {

    // 1. RAT IN A MAZE
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    public static ArrayList<String> printPath(int[][] m, int n) {
        // Your code here

        if (n == 0 || m[n - 1][n - 1] == 0 || m[0][0] == 0) {
            return new ArrayList<>();
        }

        int dir[][] = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
        String[] dirS = { "D", "L", "R", "U" };

        ArrayList<String> res = new ArrayList<>();

        floodFill_(0, 0, n - 1, n - 1, m, dir, dirS, "", res);

        return res;

    }

    public static int floodFill_(int sr, int sc, int er, int ec, int[][] vis, int dir[][], String dirS[], String ans,
            ArrayList<String> res) {

        if (sr == er && sc == ec) {
            res.add(ans);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = 0; // mark it here bcz we can go multiple times in recursion from above loop

        for (int d = 0; d < 4; d++) {

            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            String direction = dirS[d];

            if (r >= 0 && r <= er && c >= 0 && c <= ec && vis[r][c] == 1) {

                count += floodFill_(r, c, er, ec, vis, dir, dirS, ans + direction, res);

            }

        }
        vis[sr][sc] = 1;

        return count;
    }

    // 2. leetcode 1219
    // Q. => In a gold mine grid of size m * n, each cell in this mine has
    // an integer representing the amount of gold in that cell, 0 if it is empty.

    // check from every point(recursion) and compare and return maxGold

    public int getMaximumGold(int[][] grid) {

        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        int n = grid.length;
        int m = grid[0].length;

        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

        int maxGold = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] != 0) {

                    maxGold = Math.max(maxGold, getMaximumGold_(grid, i, j, n, m, dir));
                }
            }
        }

        return maxGold;
    }

    // get max gold from all directions and then add your own gold value and return
    // for marking and unmarking simply make gold value (-ve)
    public int getMaximumGold_(int[][] grid, int sr, int sc, int n, int m, int[][] dir) {

        grid[sr][sc] = -grid[sr][sc]; // for marking ( do it here otherwise if you do it inner loop
                                      // value might change bcz we are making value -ve)

        int maxGold = 0;

        for (int d = 0; d < 4; d++) {

            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < n && grid[r][c] > 0) {
                // greater than 0 bcz 0 cant be taken and we marked location using -ve value

                maxGold = Math.max(maxGold, getMaximumGold_(grid, r, c, n, m, dir));
                // take max gold from all locations
            }

        }
        grid[sr][sc] = -grid[sr][sc]; // for unmarking

        // now add your own gold to maxgold
        maxGold += grid[sr][sc]; // own contribution

        return maxGold;

    }

    // leetcode 980

    // On a 2-dimensional grid, there are 4 types of squares:

    // 1 represents the starting square. There is exactly one starting square.
    // 2 represents the ending square. There is exactly one ending square.
    // 0 represents empty squares we can walk over.
    // -1 represents obstacles that we cannot walk over.

    // Return the number of 4-directional walks from the starting square to the
    // ending square, that walk over every non-obstacle square exactly once.

    // count no of steps to be taken and get location of start index, end index
    // now call recursively

    public int uniquePathsIII(int[][] grid) {

        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        int n = grid.length, m = grid[0].length;

        int sr = 0, sc = 0, er = 0, ec = 0, countSteps = 0;

        int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 0 || grid[i][j] == 2)
                    countSteps++;

                if (grid[i][j] == 2) {
                    er = i;
                    ec = j;

                } else if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
            }
        }

        return uniquePathsIII_(grid, sr, sc, er, ec, countSteps, dir);
    }

    // store the index value and mark the index using any negative value, Call
    // recursion in 4 directions and decrease count steps

    // In base case if reached destination and have travelled all the valid moves
    // the return 1 else return 0
    public int uniquePathsIII_(int[][] grid, int sr, int sc, int er, int ec, int countSteps, int[][] dir) {

        if (sr == er && sc == ec) {

            return countSteps == 0 ? 1 : 0;

        }

        int temp = grid[sr][sc];
        grid[sr][sc] = -2;

        int count = 0;

        for (int d = 0; d < 4; d++) {

            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] >= 0) {

                count += uniquePathsIII_(grid, r, c, er, ec, countSteps - 1, dir);
            }
        }

        grid[sr][sc] = temp;

        return count;

    }

    // leetcode 46. Permutations

    // Q. Given a collection of distinct integers, return all possible permutations.

    // in recursion take boolean vis array and start loop from 0 always bcz it is
    // permutations
    // if curr idx elt is not visited then add elt in arraylist and call and when
    // coming back remove from al and unmark the elt
    // at last check if count = arr.size then add ans arraylist to res

    // NOTE : NON PREMITIVE DATA-TYPES ARE MADE IN HEAP SO IF YOU CHANGE THEIR DATA
    // IT WILL BE CHANGED IN
    // EVERY LOCATION. SO WHEN STORING ARRAYLIST IN RES ARRAYLIST MAKE A NEW LIST
    // AND THEN ADD

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<Integer>();

        boolean vis[] = new boolean[nums.length];

        permute_(nums, 0, vis, res, ans);

        return res;
    }

    public void permute_(int[] arr, int count, boolean[] vis, List<List<Integer>> res, List<Integer> ans) {

        if (count == arr.length) {

            // ans arraylist changes in every call so make a new al in heap and add that to
            // res list
            List<Integer> val = new ArrayList<>(ans);
            res.add(val);

            return;
        }

        for (int i = 0; i < arr.length; i++) {

            if (vis[i] == false) {

                vis[i] = true;
                ans.add(arr[i]);

                permute_(arr, count + 1, vis, res, ans);

                ans.remove(ans.size() - 1);
                vis[i] = false;
            }
        }

    }

    // leetcode 47. Permutations II ( unique permutations only)

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();

        int n = nums.length;
        boolean[] vis = new boolean[n];

        Arrays.sort(nums);

        permuteUnique_1(nums, 0, vis, res, smallAns);

        return res;
    }

    // Using hashSet
    // this approach does not require sorting of array

    // use hashSet for adding elements and check if elt is not visited then call and
    // mark visited ,and add in hashSet

    public void permuteUnique_1(int[] arr, int count, boolean[] vis, List<List<Integer>> res, List<Integer> smallAns) {

        if (count == arr.length) {

            List<Integer> ans = new ArrayList<>(smallAns);

            res.add(ans);
            return;
        }

        HashSet<Integer> duplicates = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {

            if (!vis[i] && !duplicates.contains(arr[i])) {

                vis[i] = true;
                duplicates.add(arr[i]);

                smallAns.add(arr[i]);

                permuteUnique_1(arr, count + 1, vis, res, smallAns);

                smallAns.remove(smallAns.size() - 1);

                vis[i] = false;
            }
        }

    }

    // O(1) space Efficient approach
    public void permuteUnique_(int arr[], int count, boolean[] vis, List<List<Integer>> res, List<Integer> smallAns) {

        if (count == arr.length) {

            List<Integer> ans = new ArrayList<>(smallAns);

            res.add(ans);
            return;
        }

        // take previous elt to track last visited elt bcz we have sorted array already
        // so same elts will be together and we have track of last elt so if it was
        // previously
        // visited then do not visit again.

        int prev = -(int) 1e8;

        for (int i = 0; i < arr.length; i++) {

            if (!vis[i] && arr[i] != prev) {

                // mark the curr element so it cannot be visited next time
                prev = arr[i];

                vis[i] = true;

                smallAns.add(arr[i]);

                permuteUnique_(arr, count + 1, vis, res, smallAns);

                smallAns.remove(smallAns.size() - 1);

                vis[i] = false;

            }
        }
    }

    // leetcode 17. Letter Combinations of a Phone Number
    // Q. Given a string containing digits from 2-9 inclusive, return all possible
    // letter combinations that the number could represent.

    // A mapping of digit to letters (just like on the telephone buttons) is given
    // below. Note that 1 does not map to any letters.

    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0)
            return new ArrayList<>();

        String[] codes = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        // no representation given for 0 and 1 index

        List<String> res = new ArrayList<>();

        letterCombinations_(digits, 0, "", codes, res);

        return res;
    }

    // do not substring the ques string instead use idx for faster operations
    public void letterCombinations_(String ques, int idx, String ans, String[] codes, List<String> res) {

        if (idx == ques.length()) {

            res.add(ans);
            return;
        }

        int cIdx = (int) (ques.charAt(idx) - '0');

        String code = codes[cIdx];

        for (int i = 0; i < code.length(); i++) {

            char ch2 = code.charAt(i);

            letterCombinations_(ques, idx + 1, ans + ch2, codes, res);
        }

    }

    // leetcode 91. Decode Ways
    // A message containing letters from A-Z is being encoded to numbers using the
    // following mapping:

    // 'A' -> 1
    // 'B' -> 2
    // ...
    // 'Z' -> 26

    // Given a non-empty string containing only digits, determine the total number
    // of ways to decode it.

    public int numDecodings(String s) {

        if (s.length() == 0)
            return 0;

        return numDecodings_(s, 0);
    }

    public int numDecodings_(String s, int idx) {

        if (idx == s.length()) {

            return 1;
        }

        int count = 0;

        char ch = s.charAt(idx);
        int cidx = ch - '0';

        // no mapping to 0 is given so check this currentIdx value and if 0 then do not
        // call
        if (cidx != 0) {

            count += numDecodings_(s, idx + 1);

        }

        // check if 2 length string left and if its combined idx maps to any
        // character then call for idx+2 as we have taken 2 characters

        if (cidx != 0 && idx < s.length() - 1) {

            cidx = cidx * 10 + (s.charAt(idx + 1) - '0');

            if (cidx >= 10 && cidx <= 26) {

                count += numDecodings_(s, idx + 2);
            }

        }
        return count;
    }

    // *******************************************SUDOKU-VARIATIONS**********************************************************************

    // LEETCODE 37. Sudoku Solver

    // using arraylist of locations and traversing over them

    public void solveSudoku(char[][] board) {

        ArrayList<Integer> locOfZeroes = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') {
                    locOfZeroes.add(i * 9 + j);
                    // storing the location of i and j and it can be retrieved
                    // using(for row : value/9, for col : value%9)
                }
            }
        }

        solveSudoku_(board, 0, locOfZeroes);
    }

    public boolean isValidToPlace(char[][] board, int r, int c, int num) {

        // row checking
        for (int i = 0; i < 9; i++) {
            if (board[i][c] - '0' == num) {
                return false;
            }
        }
        // column checking
        for (int i = 0; i < 9; i++) {

            if (board[r][i] - '0' == num) {
                return false;
            }
        }
        // matrix checking

        // starting row and column of submatrix
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[sr + i][sc + j] - '0' == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solveSudoku_(char[][] board, int vIdx, List<Integer> locOfZeroes) {

        if (vIdx == locOfZeroes.size()) {

            return true;
        }

        int twoDLoc = locOfZeroes.get(vIdx);

        int r = twoDLoc / 9;
        int c = twoDLoc % 9;

        for (int num = 1; num <= 9; num++) {

            if (isValidToPlace(board, r, c, num)) {

                board[r][c] = (char) (num + '0');

                if (solveSudoku_(board, vIdx + 1, locOfZeroes))
                    return true;

                board[r][c] = '.';
            }
        }

        return false;
    }

    // LEETCODE 37. Sudoku Solver ( TIME EFFICIENT)

    // APPROACH 2 (USING INTEGER ARRAY AND BIT_MASKING FOR VALIDATION)
    static int rows[];
    static int cols[];
    static int mat[][];

    public void solveSudoku(char[][] board) {

        rows = new int[9];
        cols = new int[9];
        mat = new int[3][3];

        ArrayList<Integer> locOfZeroes = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') {
                    locOfZeroes.add(i * 9 + j);

                } else {
                    int val = (int) board[i][j] - '0';
                    int mask = (1 << val);

                    rows[i] ^= mask;
                    cols[j] ^= mask;

                    mat[(i / 3)][(j / 3)] ^= mask;
                }
            }
        }

        solveSudokuUsingBits(board, 0, locOfZeroes);
    }

    public boolean solveSudokuUsingBits(char[][] board, int vIdx, List<Integer> locOfZeroes) {

        if (vIdx == locOfZeroes.size()) {

            return true;
        }

        int twoDLoc = locOfZeroes.get(vIdx);

        int r = twoDLoc / 9;
        int c = twoDLoc % 9;

        for (int num = 1; num <= 9; num++) {

            int mask = (1 << num);

            // number is not present in any of the location
            if ((rows[r] & mask) == 0 && (cols[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {

                // mark the number
                rows[r] ^= mask;
                cols[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;

                board[r][c] = (char) (num + '0');

                if (solveSudokuUsingBits(board, vIdx + 1, locOfZeroes)) {
                    return true;
                }

                board[r][c] = '.';
                // unmark the number
                rows[r] ^= mask;
                cols[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;

            }
        }

        return false;
    }

    // LEETCODE 36. Valid Sudoku

    // USE ABOVE LOGIC BUT HERE WE HAVE TO CHECK ONLY THAT BOARD VALUES ARE
    // VALID OR NOT, SO USE BIT_MASKING TO CHECK

    // validating using bits
    public boolean isValidSudoku(char[][] board) {
        int rows[] = new int[9];
        int cols[] = new int[9];
        int mat[][] = new int[3][3];
        
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                
                if(board[i][j] != '.') {
                    
                    int val = board[i][j] - '0';
                    
                    int mask = (1 << val);
                    
                    if( (rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i/3][j/3] & mask) == 0 ) {
                        
                        rows[i] ^= mask;
                        cols[j] ^= mask;
                        mat[i/3][j/3] ^= mask;
                        
                    } else {
                        
                        return false;
                        // condition does not satisfied that means current number
                        // is already present(could be in row,col,or matrix)
                    }
                }
            }
        }
        
        return true; // all numbers satisfy the sudoku constraints
    }

}