package com.example.demo;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.stream.*;

public class TestClass1 {

   public static void main(String[] args) {

      List<Integer> l1 = Arrays.asList(1,2,3,4,5);
      List<Integer> l2 = Arrays.asList(3,4,5,6,7);
      List<Integer> l3 = Arrays.asList(6,7,8,9,10);
       List<String> l4 = Arrays.asList("A1","B1");
       List<Integer> l5 = new ArrayList<>();
       l5.addAll(l1);
       l5.addAll(l2);
       l5.addAll(l3);

       for(Integer l11: l5)
           System.out.println(l11);

       System.out.println(Stream.of(l1, l2, l3,l4).flatMap(Collection::stream).collect(Collectors.toSet()));
       //System.out.println("With FlatMap method-->"+s1);

       Set<Integer> s2 =  Stream.concat(Stream.concat(l1.stream(), l2.stream()), l3.stream()).collect(Collectors.toSet());
       System.out.println("With Concate method--> "+s2);

      List<String> Ilist = Arrays.asList("1","2");
        List<String> Ilist2 =Ilist.stream().map(x -> (x.equals("1") ? "1 - One" : x.equals("2") ? "1 - Two":x )).collect(Collectors.toList());
        System.out.println(Ilist2);
        //List<String> strList = Arrays.asList("a","","d","e","f");
//        strList.stream().filter(x -> !x.isEmpty()).forEach(x -> System.out.println(x));

        String  iarr[] = {"ad", "vv", "", "dd"};
        //Arrays.stream(iarr).filter(x -> x.length() > 1).forEach(a -> System.out.println(a));

        List<Integer> ilist = Arrays.asList(4,2,2,1,5);
       // ilist.stream().map(x -> x * x).distinct().forEach(a -> System.out.println(a));

        List<String> strList2 = Arrays.asList("aa", "","dd","","");
        //System.out.println(strList2.stream().filter(a -> a.isEmpty()).count());

        List<String> sList = strList2.stream().filter(a -> !a.isEmpty()).collect(Collectors.toList());
       // System.out.println(sList);

        String sstr = strList2.stream().filter(a -> !a.isEmpty()).collect(Collectors.joining(","));
        //System.out.println(sstr);

        Random rn = new Random();
        //rn.ints().limit(10).sorted().forEach(x -> System.out.println(x));

        //Find only the list which have value in the list
        List<String>  lstr1 = Arrays.asList("aa", "", "dd", "asd", "","3");
        List<String> lstr2 = lstr1.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
        //System.out.println(lstr2);

        List<Integer> lInt1 = Arrays.asList(1,4,2,5);
        IntSummaryStatistics summaryStatis = lInt1.stream().mapToInt(x -> x).summaryStatistics();
        //System.out.println("summary max -->"+summaryStatis.getMax());
        //System.out.println("summary count-->"+summaryStatis.getCount());
        //System.out.println("summary avg-->"+summaryStatis.getAverage());

        List<String> lStr11 = Arrays.asList("saa", "", "ddd", "33", "", "aa");
         boolean isMatch = lStr11.stream().anyMatch(x -> x.isEmpty());
        //System.out.println(isMatch);

        List<String> reStr = Arrays.asList("saa", "", "ddd", "33", "", "aa");

        Optional reduce1 = reStr.stream().reduce((x,y) -> x +" - "+y);
       // reduce1.ifPresent(System.out::println);

        List<String> findF = Arrays.asList("saa", "", "ddd", "aa", "", "aa");
        Optional findF2 = findF.stream().filter(x -> x.startsWith("aa")).reduce((x,y) -> x);
       // findF2.ifPresent(System.out::println);

        //IntStream.rangeClosed(1, 7).forEach(System.out::println);
       // LongStream.rangeClosed(1, 3).forEach(System.out::println);
    }
}
        /*
        //Find smallest and highest number in an unsorted array
        int arr[] = {4, 1, 5, 2};
        int sm = 0;
        int mx = 0;
        for (int i = 0; i < arr.length; i++) {
            sm = arr[0];
            mx = arr[0];
            if (sm > arr[i]) {
                sm = arr[i];
            }
            if (mx < arr[i]) {
                mx = arr[i];
            }
        }
        System.out.println(sm);
        System.out.println(mx);
    }
}
        /*
        //Remove duplicate elements in array

    int a[] = {1,1,3};
        HashMap<Integer,Boolean> m =  new HashMap<>();
        for(int i=0; i<a.length; i++)
        {
            if(m.get(a[i]) == null)
            {
                System.out.print(a[i] + " ");
                m.put(a[i], true);
            }
        }
    }

        /*
        //Remove the repeasted character in a String
        String str = "This is mahes";
        List<Character> lc = new ArrayList<>();

        for(int i=0; i< str.length(); i++)
        {
            char ch = str.charAt(i);
            if(lc.contains(ch))
            {
                lc.remove((Character)ch);
            }
            else
                lc.add(ch);
        }
        String str3 = lc.stream().map(x -> x.toString()).reduce("",String :: concat);
        System.out.println(str3);

               /*
        //Remove a character from String with lamda
        String str = "This is Mahes", str2 = "is";
        StringBuilder out = new StringBuilder();
        int []  ss = str.chars().filter(x -> x != 'a').toArray();

        String abc = new String(ss, 0, ss.length);


        StringBuilder sb = new StringBuilder();
        for(int i=0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if(!str2.contains(Character.toString(ch)))
            {
                sb.append(Character.toString(ch));
            }
        }
        System.out.println(sb.toString());
        */
        /*
        //Find the numbers inside a string
        String str1 = "abc1ds2df3";
        int count =0;
        for(int i =0; i< str1.length();i++)
        {
            if(Character.isDigit(str1.charAt(i)))
            count++;
        }
        System.out.println(count);
        System.out.println(str1.chars().filter(Character :: isDigit).count());

        //How many times a character appear in a string
        String str = "abcdc";
        char ch = 'c';
        int counter = 0;
        for(int i = 0; i < str.length();i++)
        {
            if(str.charAt(i) == ch)
                counter++;
        }
        System.out.println(counter);


        // find Anagrams of a string
        String str1 = "first", str2 = "striif", str3="";

        char strArr1 []  = str1.toCharArray();
        char strArr2[] = str2.toCharArray();
        Arrays.sort(strArr1);
        Arrays.sort(strArr2);

       if(Arrays.equals(strArr1, strArr2))
        {
            System.out.println("Anagrams");
        }
        else
            System.out.println("Not anagrams");

        for(int i = 0; i< str2.length(); i++)
        {
            char s2Ch = str2.charAt(i);
            if(str1.contains(Character.toString(s2Ch)))
            {
                str3 = str3 + Character.toString(s2Ch);
            }
        }

        if(str1.length() == str3.length())
        {
            System.out.println("Anagrams");
        }
        else
            System.out.println("Not anagrams");

         */


        /*
        //Find Non Match character of a String
        String text = "abcci";
        HashMap<Character, Integer> hMap = new HashMap<>();
        int valC = 0, cC = 0;

        for(int i=0; i< text.length(); i++)
        {
            char ch = text.charAt(i);
            if(ch == 'a' || ch == 'e' || ch == 'i'
            || ch == 'o' || ch == 'u')
            {
                valC = valC + 1;
            }
            else
                cC = cC +1;
        }
        System.out.println("valC-->"+valC);
        System.out.println("CC-->"+cC);
    }

        //Find Non Match character of a String
        String text = "abcc";
        HashMap<Character, Integer> hMap = new HashMap<>();

        for(int i=0; i< text.length(); i++)
        {
            char ch = text.charAt(i);
            if(hMap.containsKey(ch))
            {
                int chGet = hMap.get(ch);
               hMap.put(ch, ++chGet);
            }else
                hMap.put(ch, 1);
        }

        Set<Character> chSet = hMap.keySet();
        for(Character str : chSet)
        {
            if(hMap.get(str) == 1) {
                System.out.println(str +" -----"+hMap.get(str));
            }
        }
    }
    /*
        /*
        //Find Match character of a String

        String text = "abcc";
        HashMap<Character, Integer> hMap = new HashMap<>();

        for(int i=0; i< text.length(); i++)
        {
            char ch = text.charAt(i);
            if(hMap.containsKey(ch))
            {
                int chGet = hMap.get(ch);
                hMap.put(ch, ++chGet);
            }else
                hMap.put(ch, 1);
        }

        Set<Character> chSet = hMap.keySet();
        for(Character str : chSet)
        {
            if(hMap.get(str) > 1) {
                System.out.println(str +" -----"+hMap.get(str));
            }
        }
    }
/*
         /*
        String strOriginal = "abcdab", matchC = "cd";
        List<Character> listC = new LinkedList();

        for(int i = 0; i< strOriginal.length(); i++)
        {
            char ch = strOriginal.charAt(i);
            if(matchC.contains(Character.toString(ch)))
            {
                listC.add(strOriginal.charAt(i));
            }
        }
        System.out.println("========="+listC.stream().map(x -> x.toString()).reduce(String :: concat));
    }


    //Palindrom
    public static void main(String[] args){
    String strOriginal = "aba", reverse = "";

    for(int i = strOriginal.length() - 1; i >= 0; i--)
    {
        reverse = reverse + strOriginal.charAt(i);
    }
        System.out.println(reverse);
       if(strOriginal.equals(reverse))
        {
            System.out.println("Palindrom");
        }
        else
            System.out.println("Not Palindrom");
    }
    */

