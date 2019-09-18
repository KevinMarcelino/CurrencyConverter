package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class AccVerificationTest {
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
  public void AccVerificationAdminTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.accVerification("admin");
    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void AccVerificationUserTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.accVerification("user");
    assertEquals(
        "What do you want to do? (Check Rate / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  //There is no invalid input test as it gets filtered before reaching this method
}