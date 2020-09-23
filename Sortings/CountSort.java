public class CountSort {

    public static void main(String[] args) {

        int arr[] = {32,45,756,75,3,3,23,65,76,8,9,56,435,42,56,76,87,56,34,7,8,9,67,345,25,32666765};

        countSort(arr,3,32666765);
        display(arr);

    }

    public static void display(int arr[]) {

        for(int ele : arr) System.out.print(ele + " ");
        System.out.println();
    }

    public static void countSort(int[] arr, int min, int max) {

        int fArr[] = new int[max - min + 1];

        // frequency array
        for(int val : arr) {
            
            int idx = val - min;
            fArr[idx]++;
        }

        // prefix sum array of frequency array
        int []pre = new int[max - min + 1];
        pre[0] = fArr[0];
        
        for(int i = 1; i < pre.length; i++) {

            pre[i] = pre[i-1] + fArr[i];
        }

        int ans[] = new int[max - min + 1];

        for(int i = arr.length - 1; i >= 0;i--) {

            int val = arr[i];

            // position of value , index  is pos - 1
            int pos = pre[val - min] - 1;

            ans[pos] = val;

            // decrement frequency index
            pre[val - min]--;
        }

        // it fills only sorted elts bcz all sorted elts come in front in array
        // copy ans to array as we want to sort arr without returning it
        for(int i = 0; i < arr.length; i++) {
            arr[i] = ans[i];
        }
    }
}