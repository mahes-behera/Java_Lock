package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Customer {
    private String name;
    private int rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }



    public Customer(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public static void main(String[] args) {

            String[] strArray = {"Rohit","Shikar","Virat","Dhoni"};

            int[] intArray = {2,3,1,5,7,1,0};

            //Stream display names list from collection of list
            int minValue = IntStream.of(intArray).min().getAsInt();
        //System.out.println(minValue);

            //Stream distinct sorted then display only even numbers from list
            IntStream.of(intArray).distinct().sorted().filter(num -> num % 2 == 0).forEach(System.out::println);

            //Stream distinct sorted then display only first 3 numbers
            IntStream.of(intArray).distinct().sorted().limit(3).forEach(System.out::println);

            //Stream distinct sorted then skip first 3 numbers
            IntStream.of(intArray).distinct().sorted().skip(3).forEach(System.out::println);

            boolean oddNumber = IntStream.of(intArray).anyMatch(num -> num % 2 ==1); // Is any number odd
        //System.out.println(oddNumber);
            boolean allOddNumber = IntStream.of(intArray).allMatch(num -> num % 2 ==1); // are all odd numbers
        //System.out.println(allOddNumber);

            IntStream.range(1,10).forEach(System.out::println);
            int [] arrint = IntStream.range(1,10).toArray();
        //System.out.println(Arrays.stream(arrint).max());
            List numbers = IntStream.range(1,10).boxed().collect(Collectors.toList());
        //numbers.forEach(System.out::println);


        Customer john = new Customer("John P.", 15);
        Customer sarah = new Customer("Sarah M.", 200);
        Customer charles = new Customer("Charles B.", 150);
        Customer mary = new Customer("Mary T.", 15);

        List<Customer> customers = Arrays.asList(john, sarah, charles, mary);

        //Stream get Customer object whose rating greater then 100 and name starts with C
        List<Customer> cList = customers.stream().filter(x -> x.getRating() > 100 && x.getName().startsWith("C")).collect(Collectors.toList());
        //cList.forEach(x -> System.out.println(x.getRating()));

        //Stream display names list from collection of list
        List<String> lNameFromObject = customers.stream().map(Customer:: getName).collect(Collectors.toList());
        //lNameFromObject.forEach(System.out::println);

        //Stream Sort by rating in descending then print their name
        List<String> cList2 = customers.stream().sorted((e1, e2) -> ((Integer) e2.getRating()).compareTo(e1.getRating())).map(Customer::getName)
            .collect(Collectors.toList());
        //cList2.forEach(System.out::println);

        //Stream Sort by rating in descending then filter the start name with S limit to 2
        List<String> empNameList = customers.stream().sorted(Comparator.comparingInt(Customer::getRating).reversed()).limit(2)
                .filter(e -> e.getName().startsWith("S")).map(emp -> emp.getName()).collect(Collectors.toList());
        //empNameList.forEach(System.out::println);

        //Stream Sort by rating in descending then filter the start name with S limit to 2
        String empNameListWithComma = customers.stream().sorted(Comparator.comparingInt(Customer::getRating).reversed()).limit(4)
                .map(emp -> emp.getName()).collect(Collectors.joining(","));
        // System.out.println(empNameListWithComma);

        //Stream Reduce method
        Integer sumWithReduce = customers.stream().map(Customer::getRating).reduce(0, (a, b) -> a+b);
        //System.out.println("sumWithReduce value..>"+sumWithReduce);

        //With method reference
        List<Customer> methodRefEx = customers.stream().filter(Customer :: isOver100).collect(Collectors.toList());
        //methodRefEx.forEach(x -> System.out.println(x.getRating()));

       Map<Integer, Long>  groupByrating = customers.stream().collect(Collectors.groupingBy(Customer::getRating,Collectors.counting()));
        //groupByrating.keySet().forEach(e -> System.out.println(e));
        //groupByrating.values().forEach(e -> System.out.println(e));
        groupByrating.forEach((x, y) -> {System.out.println("key --"+x+" Value -->"+y);});


        //Group by Rating
        Map<Integer , List<Customer>> groupByRating = customers.stream().collect(Collectors.groupingBy(Customer::getRating));

        groupByRating.forEach((x,y) -> {
            //System.out.println("key  ->"+x+ " value  ->"+y.stream().forEach(k-> System.out.println(k.getName())));
        });
        Set<Integer> listOfCus2 = groupByRating.keySet();
        listOfCus2.forEach(e-> System.out.println(e.toString()));
        List<Customer> valuesHashmap = groupByRating.get(150);
        valuesHashmap.stream().forEach(x -> System.out.println(x.getName()));

        List<Customer> listOfCus = groupByRating.get(15);
        //listOfCus.stream().forEach(e -> System.out.println(e.getName()));
    }

    private  boolean isOver100()
    {
        return this.rating > 100;
    }


}
