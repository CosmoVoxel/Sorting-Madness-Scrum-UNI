//package pl.put.poznan.sorting.logic;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//
//        String[] algs = {"BubbleSort", "QuickSort", "InsertionSort", "MergeSort", "SelectionSort", "HeapSort"};
//        Integer[] array = {7, 21, 3, 19, 4};
//        Person[] people = {
//                new Person("John", 25),
//                new Person("Alice", 30),
//                new Person("Bob", 20)
//        };
//        SortingMadness program = new SortingMadness<>(people, algs, "name");
//
//        List<SortingResult> res = program.getResult();
//        for (SortingResult result : res) {
//            System.out.println("Algorithm: " + result.getAlgorithm());
//            Object[] a = result.getSortedData();
//            System.out.println("Execution Time (ns): " + result.getExecutionTimeNs());
//            System.out.println("----------------------------------------");
//        }
//    }
//
//    @Data
//    @AllArgsConstructor
//    public static class Person {
//        public String name;
//        public int age;
//    }
//
//
//}


