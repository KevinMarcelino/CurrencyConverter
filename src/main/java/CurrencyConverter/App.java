/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CurrencyConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    static Scanner input = new Scanner(System.in);
    //                                       USD       AUD        EURO        POUND     SGD
    private static double [][] rates  =     {{ 1     ,  1.46    ,  0.9     ,   0.8    ,  1.38  }, // Usd to USD, AUD,...
                                            { 0.69  ,  1       ,  0.62    ,   0.56   ,  0.95  }, // AUD to USD, AUD ....
                                            { 1.11  ,  1.61    ,  1       ,   0.9    ,  1.52  }, // EURO to USD, AUD ...
                                            { 1.24  ,  1.8     ,  1.12    ,   1      ,  1.70  },
                                            { 0.73  ,  1.06    ,  0.66    ,   0.59   ,  1     }};

    public static String showGreeting(){
        String greeting =   "********************************************************\n" +
                            "********************************************************\n" +
                            "****************** Currency Converter ******************\n" +
                            "********************************************************\n" +
                            "********************************************************\n";
        System.out.println(greeting);
        return greeting;
    }

    public static String showExitGreeting(){
        String greeting =   "********************************************************\n" +
                            "********************************************************\n" +
                            "************************* BYE **************************\n" +
                            "********************************************************\n" +
                            "********************************************************\n";
        System.out.println(greeting);
        return greeting;
    }




    private static void updateCurrency(){
        while(true) {
            System.out.println("Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] ");
            String stringFrom = input.nextLine();

            if (stringFrom.toLowerCase().equals("exit")) {
                return;
            }
            CurrenciesIndex from = findIndex(stringFrom);

            if (from == null) {
                continue;
            }

            System.out.println("Select TO Currency: (USD, AUD, EURO, POUND, SGD) [CASE INSENSITIVE]");
            String stringTo = input.nextLine();

            CurrenciesIndex to = findIndex(stringTo);

            if (to == null) {
                continue;
            }

            System.out.println(String.format("Current rate is %.2f. What is the new rate from %s to %s ?",
                    rates[from.getIdx()][to.getIdx()], from, to));

            double newRate = input.nextDouble();
            rates[from.getIdx()][to.getIdx()] = newRate;

            System.out.println(String.format("The rate from %s to %s is now %.2f", from, to, newRate));

            System.out.println("Would you like update different rate? Y or N");
            String anotherOne = input.next();
            if(anotherOne.toUpperCase().equals("N")) {
                return;
            }
            input.nextLine();
        }
    }

    private static void adminAcc(){
        String adminOption;
        while(true){
            System.out.println("What do you want to do? (Update Currency / Check Rate / exit)");
            adminOption = input.nextLine();
            if(adminOption.toLowerCase().equals("exit")){
                return;
            }else if(adminOption.toLowerCase().equals("check rate")){
                driver();
                input.nextLine();
            }else if(adminOption.toLowerCase().equals("update currency")){
                updateCurrency();
                input.nextLine();
            }else{
                System.out.println("Invalid input");
                System.out.println("");
            }
        }
    }

    private static void userAcc(){
        String userOption;
        while(true){
            System.out.println("What do you want to do? (Check Rate / exit)");
            userOption = input.nextLine();
            if(userOption.toLowerCase().equals("exit")){
                return;
            }else if(userOption.toLowerCase().equals("check rate")){
                driver();
                input.nextLine();
            }else{
                System.out.println("Invalid input");
                System.out.println("");
            }
        }
    }


    public static String wrongCurrencyMessage(String currency){
        String message = String.format("%s is not a valid currency %.2f \nPlease enter again!", currency );
        System.out.println(message);
        return message;
    }

    public static double sum(){
        List<Pair<CurrenciesIndex,Double>> sumList = new ArrayList<>();

        System.out.println("How many currencies you want to sum up? ");
        int x= input.nextInt(); input.nextLine();

        for(int i = 0; i < x; i++){
            CurrenciesIndex currenciesIndex;
            double amount;

            do{
                System.out.println(String.format("Enter the %d currency and the amount", i+1));
                String[] currncyList = input.nextLine().split(" ");            // Handle length of array < 1
                currenciesIndex = findIndex(currncyList[0]);
                if(currenciesIndex == null){
                    wrongCurrencyMessage(currncyList[0]);
                }
                amount = Double.parseDouble(currncyList[1]);                        // HANDLE
            }
            while (currenciesIndex ==null);

            sumList.add(new Pair<>(currenciesIndex,amount));
        }

        System.out.printf("Enter the currency would like to convert to:\n");

        CurrenciesIndex toCurrency = toCurrency();

        double answer =  sumHelper(sumList, toCurrency);
        System.out.println("Answer is "+ answer);
        return answer;
    }

    private static double sumHelper (List<Pair<CurrenciesIndex, Double>> sumList, CurrenciesIndex currency){

        double sum = 0;

        for(Pair i: sumList){
            sum += ((double) i.getValue())*rates[((CurrenciesIndex) i.getKey()).getIdx() ][currency.getIdx()];
        }
        return sum;
    }



    public static CurrenciesIndex fromCurrency(){
        CurrenciesIndex fromCurrency;
        do{
            System.out.println("Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] ");
            String inputString = input.nextLine();

            if(inputString.toLowerCase().contains("exit")){
                return null;
            }
            else if (inputString.toLowerCase().contains("sum")){

            }
            fromCurrency = findIndex(inputString);
        }
        while (fromCurrency== null);

        return fromCurrency;
    }

    public static CurrenciesIndex toCurrency(){
        CurrenciesIndex fromCurrency;
        do{
            System.out.println("Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] ");
            String inputString = input.nextLine();

            if(inputString.toLowerCase().contains("exit")){
                return null;
            }

            fromCurrency = findIndex(inputString);
            fromCurrency = findIndex(inputString);
        }
        while (fromCurrency== null);

        return fromCurrency;
    }


    public static double amountToConvert(CurrenciesIndex from, CurrenciesIndex to){
        System.out.println(String.format("How much %s you want to convert to %s?", from, to));
        return input.nextDouble();
    }

    public static double convert(CurrenciesIndex from, CurrenciesIndex to, double amount){

        double answer = amount*rates[from.getIdx()][to.getIdx()];
        System.out.println(String.format("%.2f %s is %.2f %s to!\n", amount, from, answer, to));
        return answer;
    }

    public static String menu(){
        System.out.println("Would you like to convert or sum the money?");
        String command = input.nextLine();

        switch (command.toLowerCase()){
            case "convert": return "convert";
            case "sum": return "sum";
            default:{
                System.out.println("Invalid Input Please try again!");
                return menu();
            }
        }
    }


    private static void driver(){

        while (true){
            String todo = menu();
            if (todo.equals("convert")) {
                CurrenciesIndex from = fromCurrency();
                CurrenciesIndex to = toCurrency();
                double amount = amountToConvert(from, to);
                convert(from, to,amount);
            }
            else{
                sum();
            }

            System.out.println("Would you like to make another conversion or Sum? Y or N");
            String anotherOne = input.next();
            if(anotherOne.toUpperCase().equals("N")) {
                return;
            }

            input.nextLine();
        }
    }

    public static void main(String[] args) {

        showGreeting();
        driver();
        showExitGreeting();

    }


    private static CurrenciesIndex findIndex(String string){
        switch (string.toLowerCase()){
            case "usd": return CurrenciesIndex.USD;
            case "aud": return CurrenciesIndex.AUD;
            case "euro": return CurrenciesIndex.EURO;
            case "pound": return CurrenciesIndex.POUND;
            case "sgd": return CurrenciesIndex.SGD;
            default:
                System.out.println("PLEASE SELECT A VALID CURRENCY!!\n");
                return null;
        }
    }

    enum CurrenciesIndex {
        USD(0), AUD(1), EURO(2), POUND(3), SGD(4);

        private int idx;
        CurrenciesIndex(int i){
            idx = i;
        }

        public int getIdx() {
            return idx;
        }
    }

}
