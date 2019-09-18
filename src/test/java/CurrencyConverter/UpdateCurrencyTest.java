
package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import CurrencyConverter.App.CurrenciesIndex;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateCurrencyTest {

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

  @Test void updateCurrencyExitTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.updateCurrency();

    assertEquals(
        "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System.lineSeparator()
        ,
        outContent.toString());
  }


  @Test void updateCurrencyUpdateTest(){

    String s = "AUD\nUSD\n1000\nN\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.updateCurrency();

    assertEquals(
        "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System.lineSeparator() +
            "Select TO Currency: (USD, AUD, EURO, POUND, SGD) [CASE INSENSITIVE]" + System.lineSeparator() +
        "Current rate is 0.69. What is the new rate from AUD to USD ?" +System.lineSeparator() +
            "The rate from AUD to USD is now 1000.00" +System.lineSeparator() +
            "Would you like update different rate? Y or N"+System.lineSeparator()
        ,
        outContent.toString());
    CurrenciesIndex from = App.findIndex("AUD");
    CurrenciesIndex to = App.findIndex("USD");
    assertEquals(1000.0,App.rates[from.getIdx()][to.getIdx()]);
  }

  @Test void updateCurrencyInvalidInputTest() {
    String s = "invalid input\n\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.updateCurrency();

    assertEquals(
        "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System
            .lineSeparator() +
            "Please select a valid currency!" + System.lineSeparator() +
            "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System
            .lineSeparator() +
            "Please select a valid currency!" + System.lineSeparator() +
            "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System
            .lineSeparator(),
        outContent.toString());
  }


}

