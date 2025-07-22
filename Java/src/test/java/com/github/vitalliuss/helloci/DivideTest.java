package com.github.vitalliuss.helloci;

import com.epam.tat.module4.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DivideTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(); // pre-condition
    }

    @AfterEach
    void tearDown() {
        calculator = null; // post-condition
    }

    @Order(1)
    @ParameterizedTest(name = "{index} => {0} / {1} = {2}")
    @CsvSource({
            "10, 2, 5",
            "9, 3, 3",
            "-12, -4, 3"
    })
    void testDivide(long a, long b, long expected) {
        assertEquals(expected, calculator.div(a, b));
    }

    @Order(2)
    @Test
    void testDivideByZero_shouldThrowException() {
        assertThrows(NumberFormatException.class, () -> calculator.div(5, 0));
    }
}