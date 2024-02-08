package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenParsingWithEmptyArguments() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenParsingArgumentDoesNotContainSymbol() {
        NameLoad nameLoad = new NameLoad();
        String illegalArgument = "key2:value2";
        assertThatThrownBy(()->nameLoad.parse("key1=value1", illegalArgument, "key3=value3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(illegalArgument)
                .hasMessageContaining("does not contain the symbol");
    }

    @Test
    void whenParsingArgumentDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String illegalArgument = "=value2";
        assertThatThrownBy(()->nameLoad.parse("key1=value1", illegalArgument, "key3=value3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(illegalArgument)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void whenParsingArgumentDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String illegalArgument = "key2=";
        assertThatThrownBy(()->nameLoad.parse("key1=value1", illegalArgument, "key3=value3"))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .contains(illegalArgument)
                .contains("does not contain a value")
                .startsWith("this name:")
                .endsWith("value");
    }
}