

public class Testint {
    private static boolean bitsFromByte(byte b, int bit){
        return(b & (1<<bit)) != 0 ;
    }

    private static boolean bitsFromInt(int i, int bit){
        return(i & (1<<bit)) != 0 ;
    }

    static void byteToBinary(byte byteVal, int[] bits){
        for (int i = 7; i >= 0; i--) {
            if (bitsFromByte(byteVal, i)) {
                System.out.print(1);
                bits[7-i] = 1;
            } else {
                System.out.print(0);
                bits[7-i] = 0;
            }
        }
    }
    public static void byteArrayToBinary(byte[] bytes, int[] binary){
    int BL = 0;
    int BR = 7;
    int [] temp = new int[8];
        for (int i = 0; i < bytes.length; i++) {
            byteToBinary(bytes[i],temp);
            for (int j = BL; j <= BR  ; j++) {//0-7,8-15,16-23,24-31
                binary[j] = temp[j-BL];
            }
            BL += 8;
            BR += 8;
        }

    }

    static void intToBinary( int value, int[] bits){
        for (int i = 31; i >= 0; i--) {
            if (bitsFromInt(value, i)) {
                System.out.print(1);
                bits[31-i] = 1;
            } else {
                System.out.print(0);
                bits[31-i] = 0;
            }
        }
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
        System.out.println("Conversions...");
        int NumberTest = 93;
        byte bt = (byte)NumberTest;
        int[] t = new int[8];
        System.out.print("Original number:" + NumberTest);
        System.out.print("\nByte Cast number:" + bt);
        System.out.print("\nTo Binary Method: ");
        byteToBinary((byte)NumberTest,t);
        System.out.println("\nConversion from Binary to Decimal: " + binToDecimal(t));

        System.out.println("int to binary...\nFrom Method:");
        int ITB = 1000000000;
        int[] u = new int[32];
        intToBinary(ITB,u);
        System.out.println("\nArray content");
        for (int i = 0; i < u.length; i++) {
            System.out.print(u[i]);
        }
        System.out.println("\nHex String Converted to bytes:\nC0, 50, 00, 5C");
        String[] hex = {"C0","50","00","5C"};
        byte[] b = new byte[4];
        for (int i = 0; i < hex.length; i++){
            b[i] = (byte)((Character.digit((hex[i].charAt(0)),16) << 4)+
                    (Character.digit((hex[i].charAt(1)),16)));
            System.out.print(b[i] + ", ");
        }

        System.out.println("\nArray Tests");
        int[] arr_a = {0,1,1,0,1,0};//1a = 26
        int[] arr_b = {0,0,0,1,1,0};//6 = 6
        int[] arr_d = {0,0,1,1,0,1};//D = 13
        int[] arr_x = {1,1,0,0,1,1,0,1,1,1,1,1,1,1,1,1};//expected result is 13 from 2-7

        System.out.println("Hex: 1a, Binary:011010, Decimal from method: "+ binToDecimal(arr_a) + ", Expected: 26");
        System.out.println("Hex: 6, Binary:011010, Decimal from method: "+ binToDecimal(arr_b) + ", Expected: 6");
        System.out.println("Hex: D, Binary:011010, Decimal from method: "+ binToDecimal(arr_d) + ", Expected: 13");
        System.out.println("Hex: CDFF, Binary:1100110111111111, Decimal from method: "+ binToDecimal(arr_x,2,7) + ", Expected: 13");

        int[] binary = new int[32];
        byte[] bytes = {-64,80,0,92};//
        System.out.println("Calculating binary from Bytes {-64,80,0,92}\n From Method: ");
        byteArrayToBinary(bytes,binary);
        System.out.println("\nBinary Array bits: ");
        for (int i = 0; i < 32; i++) {
            System.out.print(binary[i]);
        }

//        for(int i = 0; i <= bytes.length-1;i++){
//                for (int j = 7; j >= 0; j--) {
//                    if (bitsFromByte(bytes[i], j)) {
//                        System.out.print(1);
//                    } else {
//                        System.out.print(0);
//                    }
//                }
//
//                }



            }

        }


