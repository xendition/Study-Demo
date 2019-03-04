package com.michael.demo;

public class Test {

    public static void main(String[] args) {

//        long start = System.currentTimeMillis();
//
//        for (int i = 0; i < 1000000; i++) {
//            System.out.println(i + "->" + Integer.toBinaryString(i) + "->" + miracle(i));
//        }
//
//        long end = System.currentTimeMillis();
//
//        System.out.println((end - start)/1000.0);

        tt();
    }


    public static void tt() {
        for (int i = 1; i < 100; i++) {
            if ((i & (i - 1)) == (i - 1)) {

                System.out.println(i + "最后一位数是1 ->" + Integer.toBinaryString(i));
            } else if ((i & (i - 1)) == (i - 2)) {

                System.out.println(i + "最后一位数是0 ->" + Integer.toBinaryString(i));
            } else if ((i & (i - 1)) == 0) {

            } else {
                System.out.println(i + "不符合要求 " + Integer.toBinaryString(i));
            }
        }
    }

    public static int miracle(int n) {
        int m = n == 0 ? 0 : 1;
        while ((n = (n & (n - 1))) > 0) {
            m++;
        }
        return m;
    }

    public static String binaryToDecimal(int n) {

        char[] chars = new char[32];
        for (int i = 31; i >= 0; i--) {
            chars[31 - i] = ((n >>> i & 1) + "").charAt(0);
        }
        return new String(chars);
//        return new String(chars).replaceAll("^(0+)","");
    }
}
