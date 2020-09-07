import java.util.HashSet;

public class l004 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {

        // wordBreak();
        // isCryptoValid();

        crossWord();
    }

    // -------------------------------WORD-BREAK----------------------------------

    // at every loop we are starting from idx + 1 as we have to check substring so
    // min should be 1 length
    // and then call recursively passing same index as we will increment the index
    // in loop
    // for eg:--> i character will send 1 index in next call
    public static int wordBreak_(String ques, int idx, String ans, int maxLenWord, HashSet<String> words) {

        if (idx == ques.length()) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = idx + 1; i <= (idx + maxLenWord) && i <= ques.length(); i++) {
            // at i = 1 we are checking 0 to 0 substring thatswhy loop is running <= length

            String str = ques.substring(idx, i);

            if (words.contains(str)) {
                count += wordBreak_(ques, i, ans + str + " ", maxLenWord, words);
            }

        }
        return count;
    }

    // Store words in hashset as we have to check at every step that substring
    // exists in 0(1)
    // also store maxlength and if string doesnot matches with that much length that
    // means string cannot be breaked
    public static void wordBreak() {
        String ques = "ilikesamsungandmango";

        String[] word = { "mobile", "samsung", "sam", "sung", "man", "mango", "icecream", "and", "go", "i", "like",
                "ice", "cream", "ilike" };

        HashSet<String> words = new HashSet<String>();

        int maxLenWord = 0;
        for (String s : word) {

            maxLenWord = Math.max(maxLenWord, s.length());

            words.add(s);
        }

        System.out.println(wordBreak_(ques, 0, "", maxLenWord, words));
    }

    // --------------------------------------------CRYPTO-------------------------------------------

    // Approach :--->
    // 1. Find unique characters
    // 2. Utility (charToNumber, numberUsed)
    // 3. stringToNumber conversion Function
    // 4. Recursion call ( 1 to 9 numbers chances giving)
    // 5. Base case checking that first character should not be mapped to 0 value

    // Here we have to make single string(send,money,more ==> sendmonyr) of unique
    // characters and then in recursion give every character
    // chance to get value from 0 to 9 and in base case check whether it fits to (a
    // + b = c) string or not
    // if not then backtrack

    static String a = "send";
    static String b = "more";
    static String c = "money";
    static int[] charToNumber = new int[26];
    static boolean[] numberUsed = new boolean[10];

    public static int stringToNumber(String str) {

        int num = 0;
        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);
            num = num * 10 + charToNumber[ch - 'a'];
        }

        return num;
    }

    public static int cryptoSolver(String str, int idx) {

        if (idx == str.length()) {

            int aVal = stringToNumber(a);
            int bVal = stringToNumber(b);
            int cVal = stringToNumber(c);

            if (aVal + bVal == cVal && charToNumber[a.charAt(0) - 'a'] != 0 && charToNumber[b.charAt(0) - 'a'] != 0
                    && charToNumber[c.charAt(0) - 'a'] != 0) {

                System.out.println(aVal + "\n+" + bVal + "\n-----------\n=" + cVal);
                System.out.println();
                return 1;
            }

            return 0; // for not going out of range simply return 0 as
                      // evaluated crypto is not valid
        }

        int count = 0;
        char ch = str.charAt(idx);

        for (int num = 0; num <= 9; num++) {

            if (!numberUsed[num]) {

                numberUsed[num] = true;
                charToNumber[ch - 'a'] = num;

                count += cryptoSolver(str, idx + 1);

                charToNumber[ch - 'a'] = 0;
                numberUsed[num] = false;
            }
        }

        return count;
    }

    public static void isCryptoValid() {
        String str = a + b + c;

        int freq[] = new int[26];

        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        str = "";
        for (int i = 0; i < 26; i++) {

            if (freq[i] != 0)
                str += (char) (i + 'a');
        }

        System.out.println(cryptoSolver(str, 0));
    }

    // *****************************************CROSS-WORD******************************

    // check if size of word is bigger than boundary then we cannot place
    // or if there is no - at any point or character present at that location is not
    // matching with
    // character of string then return false

    public static boolean canPlaceH(char[][] board, int r, int c, String word) {

        for (int i = 0; i < word.length(); i++) {

            if (c + i >= board[0].length || board[r][c + i] != '-' && board[r][c + i] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    // while placing keep track of placed indexes so that you can remove only these
    // words when unplacing the word

    public static boolean[] placeH(char[][] board, int r, int c, String word) {

        boolean loc[] = new boolean[word.length()];

        for (int i = 0; i < word.length(); i++) {

            if (board[r][c + i] == '-') {

                board[r][c + i] = word.charAt(i);
                loc[i] = true;
            }
        }

        return loc;
    }

    // unplace the words where loc[] is true bcz only there we had placed the word
    public static void unPlaceH(char[][] board, int r, int c, boolean[] loc) {

        for (int i = 0; i < loc.length; i++) {

            if (loc[i] == true) {
                board[r][i + c] = '-';
            }
        }
    }

    public static boolean canPlaceV(char[][] board, int r, int c, String word) {

        for (int i = 0; i < word.length(); i++) {

            if (r + i >= board.length || board[r + i][c] != '-' && board[r + i][c] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean[] placeV(char[][] board, int r, int c, String word) {

        boolean[] loc = new boolean[word.length()];

        for (int i = 0; i < word.length(); i++) {

            if (board[r + i][c] == '-') {

                board[r + i][c] = word.charAt(i);
                loc[i] = true;
            }
        }

        return loc;
    }

    public static void unPlaceV(char[][] board, int r, int c, boolean[] loc) {

        for (int idx = 0; idx < loc.length; idx++) {

            if (loc[idx] == true) {
                board[r + idx][c] = '-';
            }
        }
    }

    // check every spot of board and if it is empty or matches the first character
    // of word then check in canPlace
    // and if it is true then place and call recursively
    public static int crossWord_(char[][] board, String[] words, int vidx) {

        if (vidx == words.length) {

            displayCrossWord(board);
            return 1;
        }

        String word = words[vidx];

        int count = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == '-' || board[i][j] == word.charAt(0)) {

                    if (canPlaceH(board, i, j, word)) {

                        boolean loc[] = placeH(board, i, j, word);
                        count += crossWord_(board, words, vidx + 1);
                        unPlaceH(board, i, j, loc);
                    }

                    if (canPlaceV(board, i, j, word)) {

                        boolean loc[] = placeV(board, i, j, word);
                        count += crossWord_(board, words, vidx + 1);
                        unPlaceV(board, i, j, loc);
                    }

                }
            }
        }
        return count;
    }

    // check if you can place the whole word or not and if possible place but keep
    // track of placed indexes
    // bcz in backtracking you have to unplace these words
    // take vidx and if it reaches to word[] length that means you have placed all
    // the words so return

    // O ( (2^L) * n * m ) // L is length of word
    public static void crossWord() {

        char[][] board = {  { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                            { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
                            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' },
                            { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
                            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

        String[] words = { "agra", "norway", "england", "gwalior" };

        System.out.println(crossWord_(board, words, 0));

    }

    public static void displayCrossWord(char[][] board) {

        for (char[] ar : board) {
            for (char ch : ar) {

                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}