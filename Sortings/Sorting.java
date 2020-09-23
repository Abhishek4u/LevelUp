import java.util.Arrays;
public class Sorting {

    public static void main(String[] args) {

        int arr[] = {32,45,756,75,3,3,23,65,76,8,9,56,435,42,56,76,87,56,34,7,8,9,67,345,25,32666765};

        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // display(mergeSort(arr,0,arr.length-1));
        quickSort(arr, 0, arr.length - 1);        
        display(arr);

    }

    public static void display(int arr[]) {

        for(int ele : arr) System.out.print(ele + " ");
        System.out.println();
    }

    public static void swap(int arr[], int i,int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Bubble Sort 
    // Best - O(n) , Average/Worst - O(n*n)
    public static void bubbleSort(int arr[]) {
        int n = arr.length;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n - i - 1; j++) {

                if(arr[j] > arr[j+1]) swap(arr,j,j+1);
            }
        }
    }

    // Selection Sort
    // Best/Average/Worst : - O(n*n)
    public static void selectionSort(int arr[]) {

        int n = arr.length;

        for(int i = 0; i < n ;i++) {
            int min = i;
            for(int j = i+1; j < n;j++) {

                if(arr[min] > arr[j]) min = j;
            }
            swap(arr,min,i);
        }
    }

    // Insertion Sort
    public static void insertionSort(int arr[]) {
        int n = arr.length;
    
        for(int i = 0; i < n; i++) {
            for(int j = i; j > 0; j--) {

                if(arr[j-1] > arr[j]) swap(arr,j-1,j);

                else {
                    break;
                    // element got its sorted position
                }
            }
        }
    }

    public static int[] merge2SortedArrays(int a[], int b[]) {

        int n = a.length;
        int m = b.length;
        int ans[] = new int[n + m];

        int i = 0,j = 0,k = 0;
        while(i < n && j < m) {

            if(a[i] < b[j]) ans[k++]  = a[i++];

            else ans[k++] = b[j++];
        }

        while(i < n) ans[k++] = a[i++];

        while(j < m) ans[k++] = b[j++];

        return ans;
    }

    // MERGE-SORT
    public static int[] mergeSort(int arr[],int lo, int hi) {

        if(lo == hi) return new int[]{arr[lo]};

        int mid = (lo+hi)/2;

        int left[] = mergeSort(arr,lo,mid);
        int right[] = mergeSort(arr,mid+1,hi);

        int ans[] = merge2SortedArrays(left,right);

        return ans;
    }

    public static int partitionIdx(int arr[], int pivot, int lo, int hi) {

        int i = lo, j = lo;

        while( i <= hi) {

            if(arr[i] <= pivot) {
                swap(arr,i,j);
                i++;
                j++;

            } else if(arr[i] > pivot) {
                i++;
            }
        }

        return j - 1; // bcz it points to 1st bigger elt
    }
    //QUICK SORT
    public static void quickSort(int arr[], int lo, int hi) {

        if(lo <= hi) {
            int pivot = arr[hi];
            int pivotIdx = partitionIdx(arr, pivot, lo, hi);

            quickSort(arr,lo,pivotIdx - 1);
            quickSort(arr,pivotIdx + 1, hi);
        }
    }
}