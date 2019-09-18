package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class AdminAccTest {
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
  public void adminAccExitTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccCheckRateTest(){
    String s = "check rate\nexit\n\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
        "Would you like to convert or sum the money? Enter exit to exit. " + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccUpdateCurrencyTest(){
    String s = "update currency\nexit\n\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccInvalidInputTest(){
    String s = "I'm Invalid\nI'm Invalid\nHELLLOOO\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccCombinationTest(){
    String s = "check rate\nexit\n\nI'm Invalid\nupdate currency\nexit\n\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Would you like to convert or sum the money? Enter exit to exit. " + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator()
        ,
        outContent.toString());
  }


}