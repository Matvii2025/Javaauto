package com.github.vitalliuss.helloci;

import com.epam.tat.module4.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MultiplyTest {

    Calculator calculator;

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @AfterEach
    void clean() {
        calculator = null;
    }

    @ParameterizedTest(name = "{index} => {0} * {1} = {2}")
    @CsvSource({
            "2, 3, 6",
            "-1, 5, -5",
            "0, 10, 0"
    })
    void testMultiply(long a, long b, long expected) {
        assertEquals(expected, calculator.mult(a, b));
    }
}