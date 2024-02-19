package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculatorr {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = getCustomDelimiter(numbers);
        String[] numbersArray = extractNumbers(numbers, delimiter);
        List<Integer> numbersList = convertToNumbers(numbersArray);
        handleNegatives(numbersList);

        return sumNumbers(numbersList);
    }

    private static String getCustomDelimiter(String numbers) {
        Matcher matcher = Pattern.compile("//\\[(.+)\\]\n(.*)").matcher(numbers);
        String delimiter = ",";
        if (matcher.matches()) {
            delimiter = Pattern.quote(matcher.group(1));
        }
        return delimiter;
    }

    private static String[] extractNumbers(String numbers, String delimiter) {
        return numbers.split("[" + delimiter + "\n]");
    }

    private static List<Integer> convertToNumbers(String[] numbersArray) {
        List<Integer> numbersList = new ArrayList<>();
        for (String num : numbersArray) {
            if (!num.isEmpty()) {
                numbersList.add(Integer.parseInt(num));
            }
        }
        return numbersList;
    }

    private static void handleNegatives(List<Integer> numbersList) {
        List<Integer> negatives = new ArrayList<>();
        for (int num : numbersList) {
            if (num < 0) {
                negatives.add(num);
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negatives);
        }
    }

    private static int sumNumbers(List<Integer> numbersList) {
        int sum = 0;
        for (int num : numbersList) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
       
        System.out.println(add(""));                  
        System.out.println(add("1"));                 
        System.out.println(add("1,5"));               
        System.out.println(add("1\n2,3"));             
        System.out.println(add("//;\n1;2"));           
        System.out.println(add("1,1000,3"));          
        System.out.println(add("//[@]\n1@2@3"));      
    }
}
