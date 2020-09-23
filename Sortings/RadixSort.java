public class RadixSort {

    public static void main(String[] args) {

        int arr[] = {32,45,756,75,3,3,23,65,76,8,9,56,435,42,56,76,87,56,34,7,8,9,67,345,25,32666765};

        radixSort(arr);
        display(arr);
    }

    public static void display(int arr[]) {

        for(int ele : arr) System.out.print(ele + " ");
        System.out.println();
    }

    public static void radixSort(int[] arr) {
        // write code here  
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length;i++) max = Math.max(max, arr[i]);
        
        int exp = 1;
        while( exp <= max) {
            countSort(arr,exp);
            exp*=10;
        }
    }

    public static void countSort(int[] arr, int exp) {
        // write code here
        
        int fArr[] = new int[10];
        for(int i = 0; i < arr.length;i++) {
            fArr[arr[i] / exp % 10]++;
        }
        
        // prefix sum array
        for(int i = 1;i < fArr.length;i++) {
            fArr[i] += fArr[i-1];
        }
        
        int ans[] = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            
            int val = arr[i] / exp % 10;
            int pos = fArr[val] - 1;
            ans[pos] = arr[i];
            fArr[val]--;
        }
        // filling in original array
        for(int i = 0; i < arr.length;i++) {
            arr[i] = ans[i];
        }
        System.out.print("After sorting on " + exp + " place ->         ");
        display(arr);
    }
}