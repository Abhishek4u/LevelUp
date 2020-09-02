import java.util.ArrayList;

public class l005_sudoku {

    public static void main(String[] args) {

        sudoku();
    }

    public static void display(int[] board) {

        for (int ele : board)
            System.out.print(ele + " ");

        System.out.println();
    }

    public static void display2D(int[][] board) {

        for (int[] a : board)
            display(a);

        System.out.println();
    }

    // this fxn is to check whether the placing value should not
    // be in that row,column and submatrix then return true otherwise return false

    public static boolean isValidToPlaceNumber(int[][] board, int r, int c, int num) {

        // rows checking
        for (int i = 0; i < 9; i++) {

            if (board[r][i] == num)
                return false;
        }

        // column checking
        for (int i = 0; i < 9; i++) {

            if (board[i][c] == num)
                return false;
        }

        // matrix checking
        r = (r / 3) * 3;
        c = (c / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[r + i][c + j] == num)
                    return false;
            }
        }

        return true;
    }

    // BASIC APPROACH USING ONE INDEX(THINK LIKE 1D ARRAY)
    // at every spot give chance to every number to place if it fits
    // at that spot then place it and call recursion
    // BASE CASE : When reached at outside of array
    // If number is present at any spot then call for next index as we dont have to
    // check that

    public static int sudokuSolver(int[][] board, int vidx) {

        if (vidx == 81) {
            display2D(board);
            return 1;
        }

        int r = vidx / 9;
        int c = vidx % 9;

        if (board[r][c] != 0)
            return sudokuSolver(board, vidx + 1); // number already present

        int count = 0;

        for (int num = 1; num <= 9; num++) {

            if (isValidToPlaceNumber(board, r, c, num)) {
                board[r][c] = num;

                count += sudokuSolver(board, vidx + 1);

                board[r][c] = 0;
            }
        }

        return count;
    }

    // use location of zeroes arraylist it will save extra calls
    public static int sudokuSolver_02(int[][] board, int vidx, ArrayList<Integer> locOfZeroes) {

        if (vidx == locOfZeroes.size()) {

            display2D(board);

            return 1;
        }

        int twoDLoc = locOfZeroes.get(vidx);

        int r = twoDLoc / 9;
        int c = twoDLoc % 9;

        int count = 0;

        for (int num = 1; num <= 9; num++) {

            if (isValidToPlaceNumber(board, r, c, num)) {

                board[r][c] = num;

                count += sudokuSolver_02(board, vidx + 1, locOfZeroes);

                board[r][c] = 0;
            }
        }

        return count;
    }
    
    // ------------------------SEE BITMASKING FILE FIRST-----------------------------
   
    static int[] rows ;
    static int[] cols ;
    static int[][] mat ;


    // check of isvalidToPlace can be done using bits
    // for any row or column or matrix make a mask of number which is going to be placed
    // then bitwise (and) with rows[r] if result is 0 means number is not present

    public static int sudokuSolver_BitMasking(int [][] board,int vidx,ArrayList<Integer> locOfZeroes) {

        if(vidx == locOfZeroes.size()) {

            display2D(board);

            return 1;
        }

        int twoDLoc = locOfZeroes.get(vidx);

        int r = twoDLoc/9;
        int c = twoDLoc%9;

        int count = 0 ;

        for(int num = 1; num <= 9;num++) {

            int mask = (1 << num);

            // if number is not present in any of the locations
            if((rows[r] & mask) == 0 && (cols[c] & mask) == 0 && (mat[r/3][c/3] & mask) == 0 ) {
                
                rows[r] ^= mask; // toggles the num value at current row
                cols[c] ^= mask; // toggles the num value at current col
                mat[r/3][c/3] ^= mask; // toggles the value at submatrix location
                board[r][c] = num;
                
                count += sudokuSolver_BitMasking(board,vidx+1,locOfZeroes);

                rows[r] ^= mask; 
                cols[c] ^= mask;
                mat[r/3][c/3] ^= mask;
                board[r][c] = 0;
            }
        }

        return count;
    }

    public static void sudoku() {

        int[][] board = {{3, 0, 0, 6, 0, 0, 0, 9, 2},
                        {5, 2, 0, 0, 0, 0, 4, 0, 8},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        rows = new int[9];
        cols = new int[9];
        mat = new int[3][3];

        // System.out.println(sudokuSolver(board, 0));

        ArrayList<Integer> locOfZeroes = new ArrayList<Integer>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == 0) {

                    locOfZeroes.add((i * 9) + j);

                } else {

                    int val = board[i][j];

                    int mask = (1 << val);
                    rows[i] ^= mask;

                    cols[j] ^= mask;

                    mat[i/3][j/3] ^= mask; // submatrix (see fig in copy)
                }
            }
        }

        // System.out.println(sudokuSolver_02(board, 0, locOfZeroes));

        System.out.println(sudokuSolver_BitMasking(board,0,locOfZeroes));
    }
}