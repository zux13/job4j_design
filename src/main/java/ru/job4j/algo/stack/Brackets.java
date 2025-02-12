package ru.job4j.algo.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* По оригинальному условию задачи аргументом передается строка, содержащая только символы '([{}])' */
class Brackets {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put(')', '(');
        bracketPairs.put('}', '{');
        bracketPairs.put(']', '[');

        for (char c : s.toCharArray()) {
            if (bracketPairs.containsKey(c)) {
                char expected = bracketPairs.get(c);
                char actual = stack.isEmpty() ? '*' : stack.pop();
                if (actual != expected) {
                    return false;
                }
            } else if (isOpeningBracket(c)) {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    private boolean isOpeningBracket(char c) {
        return c == '('
                || c == '{'
                || c == '[';
    }
}