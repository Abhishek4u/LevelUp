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
    // If number is present at any spot then call for next index as we dont have to check that 

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

        System.out.println(sudokuSolver(board, 0));
    }
}