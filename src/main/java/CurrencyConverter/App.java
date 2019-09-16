
package CurrencyConverter;
import java.util.*;
import java.util.Scanner;

public class App {
    static Map<String, String> UsernamesAndPasswords = new HashMap<String, String>();
    static Scanner input = new Scanner(System.in);
    //                                       USD       AUD        EURO        POUND     SGD
    private static double [][] rates  =     {{ 1     ,  1.46    ,  0.9     ,   0.8    ,  1.38  }, // Usd to USD, AUD,...
            { 0.69  ,  1       ,  0.62    ,   0.56   ,  0.95  }, // AUD to USD, AUD ....
            { 1.11  ,  1.61    ,  1       ,   0.9    ,  1.52  }, // EURO to USD, AUD ...
            { 1.24  ,  1.8     ,  1.12    ,   1      ,  1.70  },
            { 0.73  ,  1.06    ,  0.66    ,   0.59   ,  1     }};

    private static void showGreeting(){
        String greeting =   "********************************************************\n" +
                "********************************************************\n" +
                "****************** Currency Converter ******************\n" +
                "********************************************************\n" +
                "********************************************************\n";
        System.out.println(greeting);
    }

    private static void showExitGreeting(){
        String greeting =   "********************************************************\n" +
                "********************************************************\n" +
                "************************* BYE **************************\n" +
                "********************************************************\n" +
                "********************************************************\n";
        System.out.println(greeting);
    }

    private static void accVerification(String account){
        if(account.equals("admin")){
            adminAcc();
        }
        else{
            userAcc();
        }
    }

    private static String whoAreYou(){
        String identity;
        while(true) {
            System.out.println("Who are you? (ADMIN / USER)");
            String identityInput = input.nextLine();
            identity=identityInput.toLowerCase();
            if(identity.equals("user")){
                break;
            }
            else if(identity.equals("admin")){
                System.out.print("Enter a Username: ");
                String Username=input.nextLine();
                System.out.print("Enter Password: ");
                String Password=input.nextLine();
                if (UsernamesAndPasswords.containsKey(Username) && UsernamesAndPasswords.containsValue(Password)){
                    break;
                }
                 if(UsernamesAndPasswords.containsKey(Username)==false){
                System.out.println("Incorrect Username");
                }
             if(UsernamesAndPasswords.containsValue(Password)==false){
                System.out.println("Incorrect Password");
                }
            }
            else{
                System.out.println("Invalid input.");
                System.out.println("");
            }
        }
        return identity;
    }

    private static void updateCurrency(){
        while(true) {
            System.out.println("Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] ");
            String stringFrom = input.nextLine();

            if (stringFrom.toLowerCase().equals("exit")) {
                return;
            }
            index from = findIndex(stringFrom);

            if (from == null) {
                continue;
            }
            System.out.println("Select TO Currency: (USD, AUD, EURO, POUND, SGD) [CASE INSENSITIVE]");
            String stringTo = input.nextLine();

            index to = findIndex(stringTo);

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



    private static void driver(){

        while (true){
            System.out.println("Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] ");
            String stringFrom = input.nextLine();
            String[] stringLength = stringFrom.split("\\s+");
            index currency1 = null;
            index currency2 = null;
            index currency3 = null;
            index from = null;


            if(stringLength.length == 3) {
                currency1 =  findIndex(stringLength[0]);
                if(currency1 == null) {
                    continue;
                }
                currency2 =  findIndex(stringLength[1]);
                if(currency2 == null) {
                    continue;
                }
                currency3 =  findIndex(stringLength[2]);
                if(currency3 == null) {
                    continue;
                }
            } else if(stringLength.length == 1) {
                if(stringFrom.toLowerCase().equals("exit")){
                    return;
                }
                from = findIndex(stringFrom.trim());

                if(from == null){
                    continue;
                }
            }

            System.out.println("Select TO Currency: (USD, AUD, EURO, POUND, SGD) [CASE INSENSITIVE]");
            String stringTo = input.nextLine();

            index to = findIndex(stringTo);

            if(to == null){
                continue;
            }


            if(stringLength.length == 1) {
                System.out.println(String.format("How much %s you want to convert To %s ? :", from, to));
                double moneyAmount = input.nextDouble();
                double answer = moneyAmount*rates[from.getIdx()][to.getIdx()];
                System.out.println(String.format(" %.2f %s is %.2f %s.\n",moneyAmount, from ,answer , to));
            } else if(stringLength.length == 3){
                System.out.println(String.format("How much %s, %s and %s do you want to convert To %s ? (Please enter the amount in the order you entered the currency):",currency1 ,currency2 ,currency3 ,to));
                String moneyAmount = input.nextLine();
                String[] moneyAmountList = moneyAmount.split("\\s+");
                if(moneyAmountList.length != 3) {
                    System.out.println("Please enter 3 amounts!!!\n");
                    continue;
                } else {
                    double currency1Amount =  Double.parseDouble(moneyAmountList[0]);
                    double currency2Amount =  Double.parseDouble(moneyAmountList[1]);
                    double currency3Amount =  Double.parseDouble(moneyAmountList[2]);
                    double answer = currency1Amount*rates[currency1.getIdx()][to.getIdx()] + currency2Amount*rates[currency2.getIdx()][to.getIdx()] + currency3Amount*rates[currency3.getIdx()][to.getIdx()];
                    System.out.println(String.format("The sum of %.2f %s, %.2f %s and %.2f %s is %.2f %s.\n",currency1Amount, currency1, currency2Amount, currency2, currency3Amount, currency3, answer, to));
                }


            }
            System.out.println("Would you like to make another conversion? Y or N");
            String anotherOne = input.next();
            if(anotherOne.toUpperCase().equals("N")) {
                return;
            }

            input.nextLine();

        }
    }

    public static void main(String[] args) {
        UsernamesAndPasswords.put("John","Fox");
        showGreeting();
        accVerification(whoAreYou());
        showExitGreeting();

    }


    private static index findIndex(String string){
        switch (string.toLowerCase()){
            case "usd": return index.USD;
            case "aud": return index.AUD;
            case "euro": return index.EURO;
            case "pound": return index.POUND;
            case "sgd": return index.SGD;
            default:
                System.out.println("PLEASE SELECT A VALID CURRENCY!!\n");
                return null;
        }
    }

    enum index{
        USD(0), AUD(1), EURO(2), POUND(3), SGD(4);

        private int idx;
        index(int i){
            idx = i;
        }

        public int getIdx() {
            return idx;
        }
    }

}
