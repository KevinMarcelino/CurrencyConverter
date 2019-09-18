package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class whoAreYouTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;


  @BeforeEach
  public void before(){
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void after(){
    System.setIn(System.in);
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void whoAreYouAdminTest(){

    String s = "admin";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertEquals("admin", App.whoAreYou());
  }

  @Test void whoAreYouUserTest(){
    String s = "user";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertEquals("user", App.whoAreYou());
  }

  @Test void whoAreYouInvalidInputTest(){
    String s = "I'm invalid\nI'm also invalid\nadmin";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertEquals("admin", App.whoAreYou());
    assertEquals("Who are you? (ADMIN / USER)" + System.lineSeparator() +
        "Invalid input" + System.lineSeparator() +
        "Who are you? (ADMIN / USER)" + System.lineSeparator() +
        "Invalid input" + System.lineSeparator() +
        "Who are you? (ADMIN / USER)" + System.lineSeparator(), outContent.toString());
  }

}