public class l003_queens {
    public static void main(String[] args) {
        solve();

    }

    public static void solve() {

        // boolean[] box = new boolean[6];
        // int tnq = 3; // total no of queens

        // System.out.println(oneDQueenCombination(box, 0, tnq, 0, ""));
        // System.out.println(oneDQueenPermutation(box,0, tnq, 0, ""));

        boolean[][] box = new boolean[4][4];
        int tnq = 4;

        // System.out.println(twoDWQueenCombination(box, 0, tnq, ""));
        // System.out.println(twoDQueenPermutation(box, 0, tnq, ""));
        // System.out.println(twoDQueenFloorWise(box, 0, tnq, ""));

        nQueen();
    }

    // ---------------------------------------------------------------------------------------------------

    // one queen occupies one room then next queens will occupy idx+1 room always in
    // combination

    // total no of queens, queen placed so far
    public static int oneDQueenCombination(boolean[] box, int idx, int tnq, int qpsf, String ans) {

        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = idx; i < box.length; i++) {

            count += oneDQueenCombination(box, i + 1, tnq, qpsf + 1, ans + "b" + i + "q" + qpsf + " ");
        }

        return count;
    }

    // one queen occupies 1 room room then next queens can choose any room expect
    // previous queen's
    // rooms. So we have to use 0 for next recursive calls and use visited array for
    // occupied rooms

    public static int oneDQueenPermutation(boolean[] box, int idx, int tnq, int qpsf, String ans) {

        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = 0; i < box.length; i++) {

            if (box[i] == false) {

                box[i] = true;

                count += oneDQueenPermutation(box, 0, tnq, qpsf + 1, ans + "b" + i + "q" + qpsf + " ");

                box[i] = false;
            }
        }

        return count;
    }

    // Think 2d array as 1d and solve as above code, send i + 1 for call and start
    // loop with index
    // FOR INDEX ==> 1. FOR ROW => DIVIDE i with arr[0].length
    // 2. FOR COLUMN => MODULO j with arr[0].length

    public static int twoDWQueenCombination(boolean[][] box, int idx, int tnq, String ans) {

        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        int n = box.length;
        int m = box[0].length;

        for (int i = idx; i < n * m; i++) {

            int r = i / m;
            int c = i % m;

            count += twoDWQueenCombination(box, i + 1, tnq - 1, ans + "(" + r + ", " + c + ") ");
        }

        return count;

    }

    // For permutation send 0 as index for calls but use visited array to illustrate
    // that room is occupied
    // After coming back unmark arr[i][j]
    public static int twoDQueenPermutation(boolean[][] box, int idx, int tnq, String ans) {

        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        int n = box.length;
        int m = box[0].length;

        for (int i = 0; i < n * m; i++) {

            int r = i / m;
            int c = i % m;

            if (box[r][c] == false) {

                box[r][c] = true;

                count += twoDQueenPermutation(box, 0, tnq - 1, ans + "(" + r + ", " + c + ") ");

                box[r][c] = false;
            }
        }

        return count;
    }

    // combination floorwise
    // In this you just have to run for loop only for column
    // and when calling send row +1
    // because entire floor is occupied by one queen

    public static int twoDQueenFloorWise(boolean[][] box, int row, int tnq, String ans) {

        if (tnq == 0 || row == box.length) {

            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        int m = box[0].length;

        for (int i = 0; i < m; i++) {

            count += twoDQueenFloorWise(box, row + 1, tnq - 1, ans + "(" + row + ", " + i + ") ");
        }

        return count;
    }

    // -------------------------------------NQUEEN-----------------------------------------

    // check using direction array ie. upperleft,upper cols,upper right,same row
    // use radius loop for checking all conditions in one loop and if radius grows
    // then exit loop as we have arrived in outofbound place

    public static boolean isSafeToPlaceQueen(boolean[][] box, int r, int c) {

        int[][] dirC = { { -1, 0 }, { 0, -1 }, { -1, 1 }, { -1, -1 } };

        for (int[] d : dirC) {

            for (int rad = 1; rad <= Math.max(box.length, box[0].length); rad++) { // radius

                int x = r + rad * d[0];
                int y = c + rad * d[1];

                if (x >= 0 && x < box.length && y >= 0 && y < box[0].length) {

                    if (box[x][y] == true)
                        return false;

                } else
                    break; // radius is increased now(out of range)

            }
        }

        return true;
    }

    // Treat 2d as 1d array and check if isSafe to place then place the queen
    // and mark before calling recursively and unmark at coming back, send i + 1 for
    // next call

    public static int nQueenCombination(boolean[][] box, int idx, int tnq, String ans) {

        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = box.length;
        int m = box[0].length;

        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            if (isSafeToPlaceQueen(box, r, c)) {

                box[r][c] = true;

                count += nQueenCombination(box, i + 1, tnq - 1, ans + "(" + r + ", " + c + ") ");

                box[r][c] = false;
            }
        }

        return count;
    }

    // In best we are using row wise call(same as floor ques) and check if isSafe
    // then mark and call and come back and unmark the position
    // here we have saved 1 check is(same row check cause we are placing only one
    // queen in every row)

    public static int nQueenCombination_best(boolean[][] box, int row, int tnq, String ans) {

        if (tnq == 0 || row == box.length) {

            if (tnq == 0) {

                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        int m = box[0].length;

        for (int i = 0; i < m; i++) {

            if (isSafeToPlaceQueen(box, row, i)) {

                box[row][i] = true;

                count += nQueenCombination_best(box, row + 1, tnq - 1, ans + "(" + row + ", " + i + ") ");

                box[row][i] = false;
            }
        }

        return count;

    }

    public static void nQueen() {
        int n = 4, m = 4, tnq = 4;

        boolean[][] box = new boolean[n][m];

        // System.out.println(nQueenCombination(box, 0, tnq, ""));
        System.out.println(nQueenCombination_best(box, 0, tnq, ""));
    }

}