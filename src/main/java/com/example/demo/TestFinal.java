package com.example.demo;

public class TestFinal {
    final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        TestFinal tf = new TestFinal();
        tf.sb.append("test");
        String abc1 = "ABC";
        String abc2 = "AB" + "C";
       //tring abc3 =  abc2.concat("C");
        System.out.println(abc1);
        //System.out.println(abc3);
        System.out.println(abc1 == abc2.concat("C"));
    }
}
