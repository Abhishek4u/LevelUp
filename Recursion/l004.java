import java.util.HashSet;
import java.util.function.Function;

public class l004 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {

        // wordBreak();
        isCryptoValid();
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

    // Here we have to make single string(send,money,more ==> sendmonyr) of unique characters and then in recursion give every character 
    // chance to get value from 0 to 9 and in base case check whether it fits to (a + b = c) string or not
    // if not then backtrack

    static String a = "send";
    static String b = "more";
    static String c = "money";
    static int[] charToNumber = new int[26];
    static boolean[] numberUsed = new boolean[10];

    public static int stringToNumber(String str) {

        int num = 0;
        for(int i = 0;i < str.length(); i++) {

            char ch = str.charAt(i);
            num = num * 10 + charToNumber[ch - 'a'];
        }

        return num;
    }

    public static int cryptoSolver(String str, int idx) {

        if(idx == str.length()) {

            int aVal = stringToNumber(a);
            int bVal = stringToNumber(b);
            int cVal = stringToNumber(c);

            if(aVal + bVal == cVal && 
        charToNumber[a.charAt(0) - 'a'] != 0 && charToNumber[b.charAt(0) - 'a'] != 0 && charToNumber[c.charAt(0) - 'a'] != 0 ) {

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

            if(!numberUsed[num]) {

                numberUsed[num] = true;
                charToNumber[ch-'a'] = num;

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
}