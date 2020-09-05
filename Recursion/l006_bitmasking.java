import java.util.List;
import java.util.ArrayList;
public class l006_bitmasking {

    public static void main(String[] args) {

    }

    // Q : You have to on the kth bit

    // TO on the bit take a mask at kth bit and then
    // bitwise (OR) with n and the kth bit will be turned on
    public static int offOn(int n, int k) {

        int mask = (1 << k);

        n = (n | mask);

        return n;
    }

    // Q : You have to off the kth bit

    // To off the bit take the mask and complement it
    // then take bitwise(AND) with n then kth bit will be turned on
    public static int onOff(int n, int k) {

        int mask = (1 << k);

        mask = (~mask);

        n = (n & mask);

        return n;
    }

    // To count the bits simply make a mask at ith position and take
    // bitwise add with n, if result is non zero then there is a bit at that
    // location
    public static int countBits(int n) {

        int count = 0;

        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);

            if ((n & mask) != 0) {
                count++;
            }
        }
        return count;
    }

    // counting bits without mask

    // in this simply tripleRightShift the number and do bitwise and with n,
    // if result is non zero then there is a bit at that location
    public static int countBits_2(int n) {

        int count = 0;

        while (n != 0) {

            if ((n & 1) != 0)
                count++;

            n >>>= 1; // ZeroFillRightShift operator
        }

        return count;
    }

    // this mask sets the last bit only and by taking xor it removes the last bit
    public static int countBits_3 ( int n ) {

        int count = 0;
        
        while( n!= 0) {

            count++;
            
            int mask = (n & (-n) );
            n ^= mask;
        }
        return count;
    }

    // this method removes the last bit
    public static int countBits_4 ( int n ) {

        int count = 0;

        while( n != 0 ) {
            count++;

            n &= (n-1);
        }

        return count;
    }

    // LEETCODE 231

    // if num & num-1 == 0 that means num is power of two
    public boolean isPowerOfTwo(int n) {

        return n > 0 && (n & (n - 1)) == 0;
    }

    // LEETCODE 342

    // For a number to be power of 4 it needs to be power of 2 so check first if
    // number is power of 2 or not

    // in power of 4 numbers they have even no of zeroes in it so first count count
    // zeroes & then bitmask (And) with 1
    // and if ans == 0 that means number is power of 4 because even numbers have
    // zero in lsb bit
    public boolean isPowerOfFour(int num) {

        // power of 2 check
        if (num > 0 && (num & (num - 1)) == 0) {

            int countZeroes = 0;
            while (num != 1) {
                num >>>= 1;
                countZeroes++;
            }

            return (countZeroes & 1) == 0;

        } else {
            return false;
        }
    }

    // leetcode 342 another approach

    public boolean isPowerOfFour_2(int num) {

        // power of 2 check
        if (num > 0 && (num & (num - 1)) == 0) {

            // if n - 1 is divisible by 3 bcz every previous number of power of 4 number is
            // divisible by 3
            return (num - 1) % 3 == 0;
        }
        return false;
    }

    // leetcode 136
    // Given a non-empty array of integers, every element appears twice except for
    // one. Find that single one.

    // simply xor bcz it removes repeated values
    public int singleNumber(int[] nums) {

        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            ans ^= nums[i];
        }
        return ans;
    }

    // leetcode 137
    // Q. Given a non-empty array of integers, every element appears three times except
    // for one, which appears exactly once. Find that single one.

    // Ans : -  for k repeated numbers simply run a loop for 32 bits and at every bit give chance
    // to every number to bitmask(and) with mask value (1 << i) bcz if we bitmask (And) with any number
    // it gives 1 if both bits are set, if res != 0 that means bit is set then count 
    //  at end of forEach loop simply modulus the count value with k and if != 0 then bitwise(OR) with ans variable

    // *********************THIS LOGIC WILL WORK FOR K REPEATED NUMBERS ALSO****************************************

    public int singleNumber2(int[] nums) {

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;

            int mask = (1 << i);
            for (int ele : nums) {

                if ((ele & mask) != 0)
                    count++;
            }
            if (count % 3 != 0)
                ans |= mask;
        }
        return ans;
    }


    // Leetcode 260 

    // Q. Given an integer array nums, in which exactly two elements appear only once and all the other elements 
    // appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.

    // take xor value ,this xor value contains 2 numbers we have to exract them 
    // so make the mask  using (num & -num) (it will give last set bit), this set bit shows that one number's bit is 0 at this location
    // and another's number bit is 1 at this location 

    // So now we need to separate 2 sets 
    //  1 : if ele & mask == 0 then put those elts in 1 set
    // 2 : if ele & mask != 0 then put those elts in 2nd set
    // Now xor these sets only single occurence elts will be these So return them
   
    public int[] singleNumber3(int[] nums) {
        int xorValue = 0;
        
        for(int ele : nums) xorValue ^= ele;
        
        // will give the last set value
        int mask = ( xorValue & ( - xorValue) );
        
        // numbers will be of 2 groups
        int a = 0;
        int b = 0;
        
        for(int ele : nums ) {
            
            // if first group then move to 1(simply take xor it will remove repeated elts)
            if(( ele & mask ) == 0) a ^= ele;
            
            // else 2nd group
            else b ^= ele;
        }
        
        // after xor only the single occurence number will be there
        return new int[]{a,b};   
    }

    // leetcode 169 (Majority Element)

    // Given an array of size n, find the majority element.
    // The majority element is the element that appears more than n/2 times

    // Ans. Using Boyer-Moore Voting Algorithm
    // ie. if curr number is same as idx then count++ else count--, if the count == 0 that means current
    // elt is majority so change the idx ans make count to 1 again

    public int majorityElement(int[] nums) {
        
        if(nums.length == 0 ) return -1;
        
        int n = nums.length;
        int idx = 0;
        int count = 1;
        
        for(int i = 1;i < n;i++) {
            
            if(nums[i] == nums[idx]) {
                
                count++;
            } else {
                count--;
            }
            if(count == 0) {
                idx = i;
                count = 1;
            }
        }
        
        // to verify greater element exists 
        // otherwise no element is there having greater frequency than length/2
        // this checking is not needed in this question
        count = 0;
        for(int ele : nums) {
            
            if( ele == nums[idx] ) count++;
        }
        return  nums[idx];
    }

    // leetcode 78 (subsets)
    
    // If you bitmask(and) the elts with outer loop of 0 to 2power n then you will get
    // the set bit  and do bitmask (and) with inner j loop you will get set numbers (you can check by making binary representation)
    // so add these numbers in array and after finish of loop add in res array

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res =  new ArrayList<> ();

        int n = nums.length;
        for(int i = 0; i < (1 << n) ;i++) {

            ArrayList<Integer> ans = new ArrayList<Integer> ();

            for(int j = 0; j < n;j++) {
                
                int mask = (1 << j);
                if((i & mask) != 0) ans.add(nums[j]);
            }
            res.add(ans);
        }
        return res;
    }

}