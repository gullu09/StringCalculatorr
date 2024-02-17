package com.example.demo;

//package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringCalculatorr {
	public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        // Check for a custom delimiter
        Matcher matcher = Pattern.compile("//(.+)\n(.*)").matcher(numbers);
        String delimiter = ",";
        if (matcher.matches()) {
            delimiter = Pattern.quote(matcher.group(1));
            numbers = matcher.group(2);
        }

        // Split the numbers based on the delimiter or newline
        String[] numbersArray = numbers.split("[" + delimiter + "\n]");

        // Convert the numbers to integers and filter out empty strings
        List<Integer> numbersList = new ArrayList<>();
        for (String num : numbersArray) {
            if (!num.isEmpty()) {
                numbersList.add(Integer.parseInt(num));
            }
        }

        // Handle negative numbers
        List<Integer> negatives = new ArrayList<>();
        for (int num : numbersList) {
            if (num < 0) {
                negatives.add(num);
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negatives);
        }

        // Sum the numbers
        int sum = 0;
        for (int num : numbersList) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(add(""));          // Output: 0
        System.out.println(add("1"));         // Output: 1
        System.out.println(add("1,5"));       // Output: 6
        System.out.println(add("1\n2,3"));     // Output: 6
        System.out.println(add("//;\n1;2"));   // Output: 3
    }

}
