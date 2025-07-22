package com.github.vitalliuss.helloci;

import com.epam.tat.module4.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubtractTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @ParameterizedTest(name = "{index} => {0} - {1} = {2}")
    @CsvSource({
            "5, 2, 3",
            "10, 5, 5",
            "0, 3, -3"
    })
    void testSubtract(long a, long b, long expected) {
        assertEquals(expected, calculator.sub(a, b));
    }
}