package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class FoolTest {

    /* Использован механизм рефлексии для тестирования приватных методов */
    private String invokeCorrectAnswer(int startAt) throws Exception {
        Method method = Fool.class.getDeclaredMethod("correctAnswer", int.class);
        method.setAccessible(true);
        return (String) method.invoke(null, startAt);
    }

    private boolean invokeIsCorrectAnswer(String answer, int startAt) throws Exception {
        Method method = Fool.class.getDeclaredMethod("isCorrectAnswer", String.class, int.class);
        method.setAccessible(true);
        return (boolean) method.invoke(null, answer, startAt);
    }

    @Test
    void whenDivisibleBy3And5ThenFizzBuzz() throws Exception {
        assertEquals("FizzBuzz", invokeCorrectAnswer(15));
        assertEquals("FizzBuzz", invokeCorrectAnswer(30));
    }

    @Test
    void whenDivisibleBy3OnlyThenFizz() throws Exception {
        assertEquals("Fizz", invokeCorrectAnswer(3));
        assertEquals("Fizz", invokeCorrectAnswer(6));
    }

    @Test
    void whenDivisibleBy5OnlyThenBuzz() throws Exception {
        assertEquals("Buzz", invokeCorrectAnswer(5));
        assertEquals("Buzz", invokeCorrectAnswer(10));
    }

    @Test
    void whenNotDivisibleBy3Or5ThenNumber() throws Exception {
        assertEquals("1", invokeCorrectAnswer(1));
        assertEquals("2", invokeCorrectAnswer(2));
        assertEquals("4", invokeCorrectAnswer(4));
    }

    @Test
    void whenCorrectAnswerThenIsCorrectAnswerTrue() throws Exception {
        assertTrue(invokeIsCorrectAnswer("Fizz", 3));
        assertTrue(invokeIsCorrectAnswer("Buzz", 5));
        assertTrue(invokeIsCorrectAnswer("FizzBuzz", 15));
        assertTrue(invokeIsCorrectAnswer("7", 7));
    }

    @Test
    void whenIncorrectAnswerThenIsCorrectAnswerFalse() throws Exception {
        assertFalse(invokeIsCorrectAnswer("Fizz", 5));
        assertFalse(invokeIsCorrectAnswer("Buzz", 3));
        assertFalse(invokeIsCorrectAnswer("FizzBuzz", 7));
        assertFalse(invokeIsCorrectAnswer("10", 5));
    }
}
