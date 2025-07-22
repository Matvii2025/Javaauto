package com.github.vitalliuss.helloci;
import com.epam.tat.module4.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class AddTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(); // pre-condition
    }

    @AfterEach
    void tearDown() {
        calculator = null; // post-condition
    }

    @ParameterizedTest(name = "{index} => {0} + {1} = {2}")
    @CsvSource({
            "1, 2, 3",
            "-1, -2, -3",
            "0, 0, 0",
            "-3, 3, 0"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }
}
