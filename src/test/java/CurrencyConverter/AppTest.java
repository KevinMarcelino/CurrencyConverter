/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CurrencyConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AppTest {
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
    void testFromCurrency() {
        String data = "usd";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), App.CurrenciesIndex.USD);
        System.setIn(stdin);
    }

    @Test
    void testToCurrency() {
        String data = "aud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), App.CurrenciesIndex.AUD);
        System.setIn(stdin);
    }

    @Test
    void testSumHelper() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.USD, 10.0));
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.EURO, 10.0));
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.POUND, 10.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.AUD);
        double sum2 = 48.7;
        assertEquals(sum1, sum2);
    }

    @Test
    void testSumHelper2() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.USD, 0.0));
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.EURO, 0.0));
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.SGD, 0.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.AUD);
        double sum2 = 0.0;
        assertEquals(sum1, sum2);
    }

    @Test
    void testSumHelper3() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.USD, 1000.0));
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.AUD, 1000.0));
        sumList.add(new Pair<App.CurrenciesIndex, Double>(App.CurrenciesIndex.POUND, 1000.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.EURO);
        double sum2 = 2640;
        assertEquals(sum1, sum2);
    }
}
