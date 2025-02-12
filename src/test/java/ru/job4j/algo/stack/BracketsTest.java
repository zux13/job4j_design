package ru.job4j.algo.stack;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BracketsTest {

    private final Brackets brackets = new Brackets();

    @Test
    void givenValidBracketsWhenIsValidThenTrue() {
        assertThat(brackets.isValid("()")).isTrue();
        assertThat(brackets.isValid("()[]{}")).isTrue();
        assertThat(brackets.isValid("{[]}")).isTrue();
    }

    @Test
    void givenInvalidBracketsWhenIsValidThenFalse() {
        assertThat(brackets.isValid("(]")).isFalse();
        assertThat(brackets.isValid("([)]")).isFalse();
        assertThat(brackets.isValid("]")).isFalse();
        assertThat(brackets.isValid("{")).isFalse();
        assertThat(brackets.isValid("")).isTrue();
    }

    @Test
    public void testEmptyString() {
        assertTrue(brackets.isValid(""));
    }

    @Test
    public void testSingleValidPair() {
        assertTrue(brackets.isValid("()"));
        assertTrue(brackets.isValid("{}"));
        assertTrue(brackets.isValid("[]"));
    }

    @Test
    public void testMultipleValidPairs() {
        assertTrue(brackets.isValid("()[]{}"));
        assertTrue(brackets.isValid("([]){}"));
        assertTrue(brackets.isValid("{[()]}"));
    }

    @Test
    public void testInvalidPairs() {
        assertFalse(brackets.isValid("(]"));
        assertFalse(brackets.isValid("([)]"));
        assertFalse(brackets.isValid("{[}]"));
    }

    @Test
    public void testNestedValidPairs() {
        assertTrue(brackets.isValid("((()))"));
        assertTrue(brackets.isValid("({[()]})"));
        assertTrue(brackets.isValid("{[([{}])]}"));
    }

    @Test
    public void testNestedInvalidPairs() {
        assertFalse(brackets.isValid("({[([)])})"));
        assertFalse(brackets.isValid("{[(])}"));
        assertFalse(brackets.isValid("((({{{[[[))]]}}})))"));
    }

    @Test
    public void testMixedCases() {
        assertFalse(brackets.isValid(")("));
        assertFalse(brackets.isValid("({[)]})"));
        assertFalse(brackets.isValid("{[(])}"));
        assertFalse(brackets.isValid("{[([)])})"));
    }
}