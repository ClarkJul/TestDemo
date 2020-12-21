package com.example.lib;

public class StringTest {
    public static void main(String[] args){
        String a="123";
        String b="123";
        String c=a+b;
        String d="123123";
        System.out.println(a==b);
        System.out.println(c=="123123");
        System.out.println(c==(a+b));
        System.out.println(c==d);
    }
}
