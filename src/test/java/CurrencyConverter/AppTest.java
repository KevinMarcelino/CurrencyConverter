/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static CurrencyConverter.App.sumHelper;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
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
    void testShowGreeting() {
        String greeting1 = App.showGreeting();
        String greeting2 = "********************************************************\n" +
                "********************************************************\n" +
                "****************** Currency Converter ******************\n" +
                "********************************************************\n" +
                "********************************************************\n";
        assertEquals(greeting1, greeting2);
    }

    @Test
    void testShowExitGreeting() {
        String exit1 = App.showExitGreeting();
        String exit2 = "********************************************************\n" +
                "********************************************************\n" +
                "************************* BYE **************************\n" +
                "********************************************************\n" +
                "********************************************************\n";
        assertEquals(exit1, exit2);
    }


    @Test
    void testWrongCurrencyMessage() {
        String wrongCurrency = App.wrongCurrencyMessage("Yen");
        String wrongCurrencyMessage = "Yen is not a valid currency\nPlease enter again!";
        assertEquals(wrongCurrency, wrongCurrencyMessage);
    }


    @Test
    void testFromCurrency1() {
        String data = "usd";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), App.CurrenciesIndex.USD);
    }

    @Test
    void testFromCurrency2() {
        String data = "exit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), null);
    }


    @Test
    void testToCurrency1() {
        String data = "aud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), App.CurrenciesIndex.AUD);
    }

    @Test
    void testToCurrency2() {
        String data = "exit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), null);
    }

    @Test
    void testSum() {
        String data1 = "4\n-1\n3\nusd 1\neuro 1\npound\npound 1\naud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.sum();

        assertEquals("How many currencies you want to sum up? " + System.lineSeparator() +
                "The maximum number you can sum is 3\nPlease enter again" + System.lineSeparator() +
                "You have entered an invalid number\nPlease try again" + System.lineSeparator() +
                "Enter the 1 currency and the amount" + System.lineSeparator() +
                "Enter the 2 currency and the amount" + System.lineSeparator() +
                "Enter the 3 currency and the amount" + System.lineSeparator() +
                "Invalid input, Please provide with a Currency and Amount!" + System.lineSeparator() +
                "Enter the 3 currency and the amount" + System.lineSeparator() +
                "Enter the currency would like to convert to:\nSelect To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "Answer is 4.87" + System.getProperty("line.separator"), outContent.toString());
    }

    @Test
    void testSum2() {
        String data1 = "word\n2\nYen\nYen 100\nusd 100\neuro -1\neuro amount\neuro 100\naud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.sum();

        assertEquals( "How many currencies you want to sum up? " + System.lineSeparator() +
                "You must input a number\nPlease try again" + System.lineSeparator() +
                "Enter the 1 currency and the amount" + System.lineSeparator() +
                "Invalid input, Please provide with a Currency and Amount!" + System.lineSeparator() +
                "Enter the 1 currency and the amount" + System.lineSeparator() +
                "Please select a valid currency!" + System.lineSeparator() +
                "Yen is not a valid currency\nPlease enter again!" + System.lineSeparator() +
                "Enter the 1 currency and the amount" + System.lineSeparator() +
                "Enter the 2 currency and the amount" + System.lineSeparator() +
                "Invalid Amount given" + System.lineSeparator() +
                "Enter the 2 currency and the amount" + System.lineSeparator() +
                "input must be a number" + System.lineSeparator() +
                "Enter the 2 currency and the amount" + System.lineSeparator() +
                "Enter the currency would like to convert to:\nSelect To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "Answer is 307.0" + System.getProperty("line.separator"), outContent.toString());
    }


    @Test
    void testSumHelper() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<>(App.CurrenciesIndex.USD, 10.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.EURO, 10.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.POUND, 10.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.AUD);
        double sum2 = 48.7;
        assertEquals(sum1, sum2);
    }

    @Test
    void testSumHelper2() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<>(App.CurrenciesIndex.USD, 0.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.EURO, 0.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.SGD, 0.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.AUD);
        double sum2 = 0.0;
        assertEquals(sum1, sum2);
    }

    @Test
    void testSumHelper3() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<>(App.CurrenciesIndex.USD, 1000.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.AUD, 1000.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.POUND, 1000.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.EURO);
        double sum2 = 2640;
        assertEquals(sum1, sum2);
    }

    /*
            Testing case sensitiveness
     */
    @Test
    void testingFindCurrencyIndex1() {
        assertEquals(App.findIndex("USD").toString(), "USD");

    }

    @Test
    void testingFindCurrencyIndex2() {
        assertEquals(App.findIndex("aud").toString(), "AUD");
    }

    @Test
    void testingFindCurrencyIndex3() {
        assertEquals(App.findIndex("SgD").toString(), "SGD");
    }

    /*
            Testing Enum get ID method
     */
    @Test
    void testingFindCurrencyIndex4() {
        assertEquals(App.findIndex("EURO").getIdx(), 2);
    }

    /*
            Testing wrong currency input
     */
    @Test
    void testingFindCurrencyIndex5() {
        assertNull(App.findIndex("INR"));
    }


    @Test
    void testingMenu() {
        String data = "convert";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "convert");
    }

    @Test
    void testingMenu2() {
        String data = "sum";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "sum");
    }

    @Test
    void testingMenu3() {
        String data = "exit";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "exit");
    }

    @Test
    void testingMenu4() {
        String data = "Google\nsum";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "sum");
    }


    @Test
    void testAmountToConvert() {
        App.CurrenciesIndex aud = App.CurrenciesIndex.AUD;
        App.CurrenciesIndex eur = App.CurrenciesIndex.EURO;
        String data = "100";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(100, App.amountToConvert(aud, eur));

    }

    @Test
    void testConvert() {
        App.CurrenciesIndex aud = App.CurrenciesIndex.AUD;
        App.CurrenciesIndex eur = App.CurrenciesIndex.EURO;
        assertEquals(62.0, App.convert(aud, eur, 100.0));
    }

    @Test //aud to usd
    void testingDriver() {
        String data1 = "convert\naud\nusd\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals("Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "How much AUD you want to convert to USD?" + System.lineSeparator() +
                "10.00 AUD is 6.90 USD!\n" + System.lineSeparator() +
                "Would you like to make another conversion or Sum? Y or N" + System.lineSeparator(), outContent.toString());
    }

    @Test  //aud to euro
    void testingDriver2() {
        String data1 = "convert\naud\neuro\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals("Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "How much AUD you want to convert to EURO?" + System.lineSeparator() +
                "10.00 AUD is 6.20 EURO!\n" + System.lineSeparator() +
                "Would you like to make another conversion or Sum? Y or N" + System.lineSeparator(), outContent.toString());
    }

    @Test  //aud to pound
    void testingDriver3() {
        String data1 = "convert\naud\npound\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals("Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "How much AUD you want to convert to POUND?" + System.lineSeparator() +
                "10.00 AUD is 5.60 POUND!\n" + System.lineSeparator() +
                "Would you like to make another conversion or Sum? Y or N" + System.lineSeparator(), outContent.toString());
    }

    @Test  //aud to sgd
    void testingDriver4() {
        String data1 = "convert\naud\nsgd\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals("Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "How much AUD you want to convert to SGD?" + System.lineSeparator() +
                "10.00 AUD is 9.50 SGD!\n" + System.lineSeparator() +
                "Would you like to make another conversion or Sum? Y or N" + System.lineSeparator(), outContent.toString());
    }

    @Test  //aud to sgd
    void testingDriverExit() {
        String data1 = "convert\naud\nsgd\n10\nY\nexit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();





        assertEquals("Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                "How much AUD you want to convert to SGD?" + System.lineSeparator() +
                "10.00 AUD is 9.50 SGD!\n" + System.lineSeparator() +
                "Would you like to make another conversion or Sum? Y or N" + System.lineSeparator() +
                "Would you like to convert or sum the money? Enter exit to exit." +System.lineSeparator(), outContent.toString());
    }

    @Test
    void testingAmountIntakeValidator1(){
        String data1 = "100.0";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.amountIntakerValidator(false), 100.0);
    }

    @Test
    void testingAmountIntakeValidator2(){
        String data1 = "-20.0\n1";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(-20.0,App.amountIntakerValidator(false));
    }

    @Test
    void testingAmountIntakeValidator3(){
        String data1 = "hello\n25";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(25.0,App.amountIntakerValidator(false));
    }

}


