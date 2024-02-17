package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class StringCalculatorrTest {
	@Test
    public void testEmptyString() {
        assertEquals(0, StringCalculatorr.add(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(1, StringCalculatorr.add("1"));
    }

    @Test
    public void testTwoNumbers() {
        assertEquals(6, StringCalculatorr.add("1,5"));
    }

    @Test
    public void testNewLineDelimiter() {
        assertEquals(6, StringCalculatorr.add("1\n2,3"));
    }

    @Test
    public void testCustomDelimiter() {
        assertEquals(3, StringCalculatorr.add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumbers() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> StringCalculatorr.add("1,-2,3,-4"));
        assertEquals("Negative numbers not allowed: -2, -4", exception.getMessage());
    }

    @Test
    public void testCustomDelimiterAnyLength() {
        assertEquals(6, StringCalculatorr.add("//[***]\n1***2***3"));
    }

    @Test
    public void testMultipleCustomDelimiters() {
        assertEquals(6, StringCalculatorr.add("//[*][%]\n1*2%3"));
    }
}
