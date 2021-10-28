

public class Testint {
    private static boolean bitsFromByte(byte b, int bit){
        return(b & (1<<bit)) != 0 ;
    }

    static int binToDecimal(int[] array){
        int result = 0;
        int power = (int)Math.pow(2, array.length-1);
        for (int i = 0;i < array.length; i++){
            result += array[i]*power;
            power = power/2;
        }
        return result;
    }
    static int binToDecimal(int[] array,int start,int end){
        int result = 0;
        int power = (int)Math.pow(2,end-start);
        for (int i = start;i <= end; i++){
            result += array[i]*power;
            power = power/2;
        }
        return result;
    }
    public static void main(String[] args) {

        int[] arr_a = {0,1,1,0,1,0};//1a = 26
        int[] arr_b = {0,0,0,1,1,0};//6 = 6
        int[] arr_d = {0,0,1,1,0,1};//D = 13
        int[] arr_x = {1,1,0,0,1,1,0,1,1,1,1,1,1,1,1,1};//expected result is 13 from 2-7

        System.out.println(binToDecimal(arr_a));
        System.out.println(binToDecimal(arr_b));
        System.out.println(binToDecimal(arr_d));
        System.out.println(binToDecimal(arr_x,2,7));

        int[] binary = new int[32];
        byte[] bytes = {-64,80,0,92};//11000000010100000000000001011100
        for(int i = 0; i <= bytes.length-1;i++){
                for (int j = 7; j >= 0; j--) {
                    if (bitsFromByte(bytes[i], j)) {
                        System.out.print(1);
                    } else {
                        System.out.print(0);
                    }
                }

                }
        String[] hex = {"C0","50","00","5C"};
        byte[] b = new byte[4];
        for (int i = 0; i < hex.length; i++){
            b[i] = (byte)((Character.digit((hex[i].charAt(0)),16) << 4)+
                        (Character.digit((hex[i].charAt(1)),16)));
            System.out.println(b[i]);
        }


            }

        }

