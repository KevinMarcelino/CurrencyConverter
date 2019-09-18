/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static CurrencyConverter.App.sumHelper;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
//    private static String data ="defualt";
//    private static InputStream stdin = System.in;
//
//    @BeforeEach
//    public void BeforeTest() {
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        App.input = new Scanner(System.in);
//    }
//
//    @AfterEach
//    public void AfterTest() {
//        System.setIn(stdin);
//    }

//     void testingTest() {
//        String data = "in";
//        InputStream stdin = System.in;
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        App.input = new Scanner(System.in);
//        assertTrue(App.test());
//        Scanner scanner = new Scanner(System.in);
//        System.setIn(stdin);
//    }



    @Test void testShowGreeting(){
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
    void testSum() {
        String data1 = "3" + System.getProperty("line.separator") + "usd 1" + System.getProperty("line.separator") + "euro 1" + System.getProperty("line.separator")+ "pound 1" + System.getProperty("line.separator")+ "aud" + System.getProperty("line.separator");
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.sum(), 4.87);
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
    /*
            Testing case sensitiveness
     */
    @Test
    void testingFindCurrencyIndex1(){
        assertEquals(App.findIndex("USD").toString(),"USD");
    }

    @Test
    void testingFindCurrencyIndex2(){
        assertEquals(App.findIndex("aud").toString(),"AUD");
    }
    @Test
    void testingFindCurrencyIndex3(){
        assertEquals(App.findIndex("SgD").toString(),"SGD");
    }

    /*
            Testing Enum get ID method
     */
    @Test
    void testingFindCurrencyIndex4(){
        assertEquals(App.findIndex("EURO").getIdx(),2);
    }

    /*
            Testing wrong currency input
     */
    @Test
    void testingFindCurrencyIndex5(){
        assertNull(App.findIndex("INR"));
    }


    @Test
    void testingMenu(){
        String data = "convert";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(),"convert");
        System.setIn(stdin);
    }

    @Test
    void testingMenu2(){
        String data = "sum";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(),"sum");
        System.setIn(stdin);
    }
//    @Test
//    void testingMenu3(){
//        String data = "exit";
//        InputStream stdin = System.in;
//
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        App.input = new Scanner(System.in);
//
//        assertEquals(App.menu(),"exit");
//        System.setIn(stdin);
//    }

//    @Test
//    void testingMenu4(){
//        String data = "Google\nsum";
//        InputStream stdin = System.in;
//
//        System.setIn(new ByteArrayInputStream(data.getBytes()));
//        App.input = new Scanner(System.in);
//
//        assertNull(App.menu(),"sum");
//        System.setIn(stdin);
//    }

    App.CurrenciesIndex aud = App.CurrenciesIndex.AUD;
    App.CurrenciesIndex eur = App.CurrenciesIndex.EURO;

    @Test
    void testAmountToConvert() {


        String data = "100";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(100,App.amountToConvert(aud,eur));
        System.setIn(stdin);

    }

    @Test
    void testConvert(){
        assertEquals(62.0,App.convert(aud,eur,100.0));
    }
}
