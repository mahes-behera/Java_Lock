package com.example.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TestInterface {



   public int empID;
    public String empName;

    public TestInterface(int empID, String empName) {
        this.empID = empID;
        this.empName = empName;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }


    @Override
    public boolean equals(Object o) {
        System.out.println("Calling equals method");

        if (this == o) return true;
        if (!(o instanceof TestInterface)) return false;
        TestInterface that = (TestInterface) o;
        return getEmpID() == that.getEmpID() && Objects.equals(getEmpName(), that.getEmpName());
        //return false;
    }

    @Override
    public int hashCode() {
       // return Objects.hash(getEmpID(), getEmpName());
        return 0;
    }

    public static void main(String[] args) {
        TestInterface t1 = new TestInterface(1, "Emp1");
        TestInterface t2 = new TestInterface(1, "Emp1");

        System.out.println( "t1.hashCode()..>"+t1.hashCode());
        System.out.println( "t2.hashCode()..>"+t2.hashCode());
        System.out.println("Checking equality between alex1 and alex2 = " + t1.equals(t2));

        HashMap<TestInterface, String> map = new HashMap<>();
        TestInterface k1 = new TestInterface(1, "firstKey");
        TestInterface k2 = new TestInterface(2, "secondKey");
        TestInterface k3 = new TestInterface(1, "firstKey");

        System.out.println("storing value for k1");
        map.put(k1, "firstValue");
        System.out.println("storing value for k2");
        map.put(k2, "secondValue");
        System.out.println("storing value for k3");
        map.put(k3, "firstValue");

        System.out.println("retrieving value for k1 and k3"+k1.equals(k3));
        System.out.println("retrieving value for k1"+map.size());
        String v1 = map.get(k1);
        System.out.println("retrieving value for v1"+v1);
        String v2 = map.get(k2);
        System.out.println("retrieving value for v2"+v2);
        String v3 = map.get(k3);
        System.out.println("retrieving value for v3"+v3);
    }

}
