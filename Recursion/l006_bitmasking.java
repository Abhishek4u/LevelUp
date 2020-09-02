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
    //  bitwise add with n, if result is non zero then there is a bit at that location
    public static int countBits(int n ) {

        int count = 0;

        for(int i = 0;i < 32;i++) {
            int mask = (1 << i);

            if((n & mask) != 0) {
                count++;
            }
        }
        return count;
    }


    // counting bits without mask

    // in this simply tripleRightShift the number and do bitwise and with n,
    // if result is non zero then there is a bit at that location
    public static int countBits_2( int n) {

        int count = 0;

        while(n != 0) {

            if((n & 1) != 0) count++;

            n >>>= 1; // ZeroFillRightShift operator
        }

        return count;
    }

    


}