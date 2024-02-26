package com.personal.profile.striverlist.questions.countingDigits;

public class CountingDigits {

    public static void main(String[] args) {
        int n = 345;

        int result = countDigits(n);

        System.out.println(result);
    }

    static int countDigits(int n){
        int digit;
        int num = n;
        int count = 0;

        while(n != 0) {
            digit = n % 10;

            if(digit != 0 && num % digit == 0) count++;

            n = n/10;
        }

        return count;
    }
}
