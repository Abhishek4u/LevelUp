public class heapSort {

    public static int compareTo(int[][] arr, boolean isMax, int i, int j) {

        // if(isMax) return arr[i] - arr[j];
        // else return arr[j]- arr[i];

        if(isMax) return arr[i][0] - arr[j][0];
        else return arr[j][0] - arr[i][0];
    }

    public static void swap(int[][] arr, int i, int j) {

        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void downHeapify(int[][] arr, int n, boolean isMax, int pi) {
        // here n = lastIdx of array

        int maxIdx = pi;
        
        int lci = (pi << 1) + 1;
        int rci = (pi << 1) + 2;

        if(lci <= n && compareTo(arr,isMax, lci, maxIdx) >= 0) maxIdx = lci;
        if(rci <= n && compareTo(arr,isMax, rci, maxIdx) >= 0) maxIdx = rci;

        if(maxIdx != pi) {

            swap(arr, maxIdx, pi);
            downHeapify(arr, n, isMax, maxIdx);
        }
    }

    public static void main(String[] args) {

        // int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        int[][] arr = { {10, 20}, {30, -2}, {-3, -4}, {5, 6}, {7, 8}, {9, 22}, {11, 13} };

        int n = arr.length - 1;
        boolean isMax = true;
        
        for(int i = n ;i >= 0; i--) downHeapify(arr, n, isMax, i);

        for(int i = 0; i <= n; i++) {

            swap(arr, 0, n - i); 
            // swapping the values to last 
            
            // and decreasing the range of array for downHeapifying
            downHeapify(arr, n-i-1, isMax, 0);
        }

        for(int []a : arr) System.out.println(a[0] + " " + a[1]);
    }
}