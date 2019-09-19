package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class YesOrNoTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void before() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void after() {
    System.setIn(System.in);
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  void yesOrNoTrue() {
    String s = "y";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertTrue(App.yesOrNo("Sample command"));
  }

  @Test
  void yesOrNoFalse() {
    String s = "n";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertFalse(App.yesOrNo("Sample command"));
  }

  @Test
  void yesOrNoInvalidInput() {
    String s = "invalid input\nhello\ninvalid input\ny";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertTrue(App.yesOrNo("Sample command"));
  }
}
