public class l001 {

    public static int BS01(int arr[], int ele) {

        int si = 0, ei = arr.length - 1;

        while(si <= ei) {

            int mid = (si + ei) / 2;

            if(arr[mid] == ele) return mid;
            
            if(arr[mid] > ele) ei = mid - 1;
            else si = mid + 1;
        }

        return -1;
    }

    // Stream of numbers and you have to find first occurence of element
    public static int firstOcc(int arr[], int ele) {

        int si = 0, ei = arr.length - 1;

        while(si <= ei) {

            int mid = (si + ei) >> 1;

            if(arr[mid] == ele) {

                if(mid - 1 >= 0 && arr[mid-1] == ele) ei = mid - 1;
                else return mid; 

            } else if(arr[mid] > ele) ei = mid-1;
            
            else si = mid+1;
        }
        return -1;
    }

    public static int lastOcc(int[] arr, int ele) {

        int si = 0, ei = arr.length - 1;

        while(si <= ei) {

            int mid = (si + ei) / 2;

            if(arr[mid] == ele) {
                if(mid + 1 < arr.length && arr[mid+1] == ele) si = mid + 1;
                else return arr[mid];

            } else if(arr[mid] > ele) ei = mid - 1;
            else si = mid+1;
        }
        return -1;
    }

    // To find nearest elements simply run binarySearch and when both pointers will cross each other
    // those points can be the potential answer so check subtracting both value and whichever
    // pointer's value is less return that pointer
    public static int nearestElement(int[] arr, int ele) {

        int si = 0, ei = arr.length - 1;

        while(si <= ei ) {

            int mid = (si + ei) >> 1;

            if(arr[mid] == ele) {
                return mid;

            } else if(arr[mid] > ele) ei = mid - 1;
            else si = mid + 1;
        }
        if(ei < 0 ) return si;
        else if(si >= arr.length) return ei;
        else return (ele - arr[ei]) < (arr[si] - ele) ? ei : si;

    }

    // Expected position of placing an element
    public static int binarySearchExpectedPosition(int[] arr, int ele) {

        int si = 0, ei = arr.length; 
        // ei = length bcz we will put elt in right side of perfect index location
        // eg : in [10] we want to add 11 then si = 0,ei = 1
        //      and ans is 1 location
        
        while(si < ei) {
            int mid = (si + ei) >> 1;

            if(arr[mid] < ele) si = mid+1;
            // position lies on right-size
            else ei = mid;
            // moving to mid only bcz 
            // we will add elt in right side of perfect location
        }

        return ei; // correct position found 
    }

    public static void main(String[] args) {

        int arr[] = {10, 20, 26, 30, 40, 55, 60, 80};

        System.out.println(BS01(arr, 30));
        System.out.println(firstOcc(arr, 30));
        // System.out.println(BS01(arr, 30));

        System.out.println(binarySearchExpectedPosition(arr, 23));
    }

}