package Recursion;

import java.util.ArrayList;
import java.util.List;

public class l001_basic {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        
        int sr = 0,sc = 0,er = 2,ec = 2;

        System.out.println(mazePath(sr, sc, er, ec, ""));
        // System.out.println(mazePathMulti(sr, sc, er, ec, ""));
        
        // floodFill();

        // System.out.println(allPermutation("ABAB",""));

        // System.out.println(allPermutation_withoutDuplicates("ABAB", ""));

        // List<String> ans = letterCombination_set2("10");

        // System.out.println(ans);
    }

    public static int mazePath(int sr,int sc,int er,int ec, String ans ) {

        if(sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        if(sc + 1 <= ec) count += mazePath(sr, sc+1, er, ec, ans + "H");

        if(sr + 1 <= er) count += mazePath(sr + 1, sc, er, ec, ans + "V"); 

        if(sc+ 1 <= ec && sr + 1 <= er) count += mazePath(sr + 1, sc + 1, er, ec, ans + "D");

        return count;
    }

    public static int mazePathMulti(int sr,int sc,int er,int ec,String ans) {
        if(sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;

        for(int jump = 1; sc + jump <= ec; jump++) count += mazePath(sr, sc + jump, er, ec, ans + "H" + jump);

        for(int jump = 1;sr + jump <= er; jump++) count += mazePath(sr+ jump, sc, er, ec, ans + "V" + jump);
        
        for(int jump = 1; sr + jump <= er && sc + jump <= ec ;jump++) count += mazePath(sr + jump , sc + jump, er, ec, ans + "D" + jump);

        return count;
    }


    // ----------------FLOOD-FILL--------------------

    // ALGO - USE DIRECTION ARRAY

    public static int floodFill_(int sr,int sc,int er,int ec,boolean[][] vis,int[][] dir,String[] dirS,String ans) {

        if(sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        vis[sr][sc] = true;
        int count = 0;

        for(int d = 0;d<4;d++) {

            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >= 0 && r <= er && c >= 0 && c <= ec && vis[r][c] == false) {

                count += floodFill_(r, c, er, ec, vis, dir, dirS, ans + dirS[d]);
            }

        }

        vis[sr][sc] = false;

        return count;
    }

    // WITH JUMP VARIATION - USE JUMP ARRAY AND MULTIPLY VALUE WITH 
    // DIRECTION ARRAY VALUE

    public static int floodFillMulti_(int sr,int sc,int er,int ec,boolean[][] vis,int[][] dir,String[] dirS,String ans) {

        if(sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        vis[sr][sc] = true;

        int count = 0;

        for(int jump = 1; jump <= Math.max(er,ec);jump++) {

            for(int d = 0;d<4;d++) {

                int r = sr + jump*dir[d][0];
                int c = sc + jump * dir[d][1];

                if(r >=0 && r <= er && c >= 0 && c <= ec && vis[r][c] == false) {
                    count += mazePathMulti(r, c, er, ec, ans + dirS[d] + jump);
                }
            }
        }

        vis[sr][sc] = false;

        return count;
    }


    public static void floodFill() {

        int sr = 0,sc = 0,er = 3,ec = 3;

        int[][]dirFour = {{1,0},{-1,0},{0,1},{0,-1}};

        String[] dirFourS = {"D","U","R","L"};

        boolean vis[][] = new boolean[er+1][ec+1];

        // System.out.println(floodFill_(sr,sc,er,ec,vis,dirFour,dirFourS,""));

        System.out.println(floodFillMulti_(sr, sc, er, ec, vis, dirFour, dirFourS, ""));
    }


    public static int allPermutation(String ques,String ans) {
        
        if(ques.length() == 0) {
            System.out.println(ans);
            return 1;

        }

        int count = 0;
        for(int i = 0; i < ques.length() ; i++) {

            String str = ques.substring(0,i) + ques.substring(i+1);

            count += allPermutation(str, ans+ ques.charAt(i));
        }

        return count;
    }

    public static int allPermutation_withoutDuplicates(String ques,String ans) {
        if(ques.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        boolean[] vis = new boolean[26];

        for(int i = 0;i<ques.length();i++) {

            String str = ques.substring(0,i) + ques.substring(i+1);

            if(!vis[ques.charAt(i) - 'A']) {
                
                System.out.println(ques.charAt(i));
                count += allPermutation_withoutDuplicates(str,ans + ques.charAt(i));

                vis[ques.charAt(i) - 'A'] = true;

            }
        }

        return count;
    }


    public static int letterCombination_set2_(String digits,int idx,String[] codes,List<String> ans,String res) {

        if(idx == digits.length()) {
            ans.add(res);
            return 1;
        }

        int cidx = (digits.charAt(idx) - '0');
        String code = codes[cidx];

        int count = 0;

        for(int i = 0;i < code.length();i++) {

            count += letterCombination_set2_(digits, idx + 1, codes, ans, res + code.charAt(i));
        }

        if(idx < digits.length() - 1) {
            cidx = cidx*10 + (digits.charAt(idx+1) - '0');

            if(cidx >= 10 && cidx <= 11) {
                code = codes[cidx];
                for(int i = 0;i < code.length();i++) {
                    count += letterCombination_set2_(digits, idx + 2, codes, ans, res + code.charAt(i));
                }
            }
        }

        return count;
    }


    public static  List<String> letterCombination_set2(String digits) {

        if(digits.length() == 0) return new ArrayList<String>();

        String[] codes = {".,/?", "@#$%", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz","*+-","&^!~"};

        List<String> ans = new ArrayList <String> ();

        letterCombination_set2_(digits,0,codes,ans,"");

        return ans;
    }
}