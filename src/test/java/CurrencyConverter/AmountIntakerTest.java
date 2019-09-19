package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AmountIntakerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void before() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void after() {
        System.setIn(System.in);
        System.setOut(originalOut);
    }

    @Test
    void testingAmountIntakeValidator1() {
        String data1 = "100.0";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.amountIntakerValidator(false), 100.0);
    }

    @Test
    void testingAmountIntakeValidator2() {
        String data1 = "-20.0\n1";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(-20.0, App.amountIntakerValidator(false));
    }

    @Test
    void testingAmountIntakeValidator3() {
        String data1 = "hello\n25";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(25.0, App.amountIntakerValidator(false));
    }
}