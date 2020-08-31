public class l002_coins {

    public static void main(String[] args) {

        solve();
    }

    public static void solve() {

        int[] coins = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(coinChangeInfinitePermutation(coins, 0, tar, ""));
        // System.out.println(coinChangeInfiniteCombination(coins, 0, tar, ""));
        // System.out.println(coinChangeSingleCombination(coins, 0, tar, ""));
        // System.out.println(coinChangeSinglePermutation(coins, 0, new boolean[coins.length], tar, ""));

        System.out.println();

        System.out.println(coinChangeSingleCombination_subseq(coins, 0, tar, ""));
        // System.out.println(coinChangeInfiniteCombination_subseq(coins, 0, tar, ""));
        // System.out.println(coinChangeInfinitePermutation_subseq(coins, 0, tar, ""));
        // System.out.println(coinChangeSinglePermutation_subseq(coins, 0, tar, ""));
    }

    // start loop from 0 always to get permutations
    public static int coinChangeInfinitePermutation(int[] arr, int idx, int tar, String ans) {

        if (tar == 0) {
            System.out.println(ans);

            return 1;
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                count += coinChangeInfinitePermutation(arr, i, tar - arr[i], ans + arr[i]);
            }
        }
        return count;
    }

    // Start loop from idx and send i for recursion call for infinite combinations
    public static int coinChangeInfiniteCombination(int arr[], int idx, int tar, String ans) {

        if (tar == 0) {
            System.out.println(ans);

            return 1;
        }

        int count = 0;

        for (int i = idx; i < arr.length; i++) {

            if (tar - arr[i] >= 0) {
                count += coinChangeInfiniteCombination(arr, i, tar - arr[i], ans + arr[i]);
            }
        }

        return count;
    }

    // for single coin combination start loop from idx and when sending into
    // recursion then send i+1 as we have already included that coin

    public static int coinChangeSingleCombination(int[] arr, int idx, int tar, String ans) {

        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = idx; i < arr.length; i++) {

            if (tar - arr[i] >= 0) {
                count += coinChangeSingleCombination(arr, i + 1, tar - arr[i], ans + arr[i]);
            }
        }

        return count;
    }

    // for permutation use visited array start loop from 0 ans check whether
    // element is already present then do not call otherwise mark and call
    // recursively

    public static int coinChangeSinglePermutation(int[] arr, int idx, boolean[] vis, int tar, String ans) {

        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {

            if (!vis[i] && tar - arr[i] >= 0) {

                vis[i] = true;

                count += coinChangeSinglePermutation(arr, 0, vis, tar - arr[i], ans + arr[i]);

                vis[i] = false;
            }
        }

        return count;

    }

    // -----------------------------SUBSEQUENCE-METHODS-------------------------------

    // for single combination use 2 calls one is for including and
    // another is for excluding and pass idx + 1 as we have finite supply of coins

    // check for array range in base case too
    public static int coinChangeSingleCombination_subseq(int[] arr, int idx, int tar, String ans) {

        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;

        if (tar - arr[idx] >= 0)
            count += coinChangeSingleCombination_subseq(arr, idx + 1, tar - arr[idx], ans + arr[idx]);

        count += coinChangeSingleCombination_subseq(arr, idx + 1, tar, ans);

        return count;
    }

    // for infinite combination send index in including as it is
    // otherwise for exclusion call send index + 1
    public static int coinChangeInfiniteCombination_subseq(int[] arr, int idx, int tar, String ans) {

        if (tar == 0 || idx == arr.length) {

            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if (tar - arr[idx] >= 0) {

            count += coinChangeInfiniteCombination_subseq(arr, idx, tar - arr[idx], ans + arr[idx]);
        }

        count += coinChangeInfiniteCombination_subseq(arr, idx + 1, tar, ans);

        return count;
    }

    // when selecting coin then call with 0 index as we have to select all 
    // permutations of coins so that we can give chance every coin to start

    // when discarding the coin then call recursively with index + 1 
    // as we are discarding that coin
    public static int coinChangeInfinitePermutation_subseq(int arr[], int idx, int tar, String ans) {

        if (tar == 0 || idx == arr.length) {

            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if (tar - arr[idx] >= 0) {

            count += coinChangeInfinitePermutation_subseq(arr, 0, tar - arr[idx], ans + arr[idx]);
        }

        count += coinChangeInfinitePermutation_subseq(arr, idx + 1, tar, ans);

        return count;
    }

    
    // -----------------------------------TRICK FOR BOOLEAN ARRAY----------------------------------------------------------------
    
    // HERE WE HAVE TO USE VISITED ARRAY AS WE HAVE ONLY 1 SUPPLY OF EVERY COIN 
    // SO THERE IS A TRICK FOR BOOLEAN ARRAY THAT JUST MAKE ARRAY VALUE -VE AND CHECK AT EVERY CALL THAT COIN SHOULD NOT BE -VE
    // AND AFTER CALL MAKE THE COIN +VE AGAIN TO USE

    // when selecting the coin call with 0 as it is permutation and we want to give every coin chance to join with another coin
    // when discarding the simply call with idx+1 as we do not want that coin

    public static int coinChangeSinglePermutation_subseq(int[] arr, int idx, int tar, String ans) {

        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if (arr[idx] > 0 && tar - arr[idx] >= 0) {

            int temp = arr[idx];
            arr[idx] = -arr[idx]; // works as boolean visited array as
                                  // we are checking for -ve value

            count += coinChangeSinglePermutation_subseq(arr, 0, tar - temp, ans + temp);

            arr[idx] = -arr[idx];
        }

        count += coinChangeSinglePermutation_subseq(arr, idx + 1, tar, ans);

        return count;
    }

}