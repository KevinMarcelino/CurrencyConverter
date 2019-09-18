package CurrencyConverter;

public class Controller {

  public boolean isExit(String command) {
    if (command.toLowerCase().equals("exit")) {
      return true;
    }
    return false;
  }

  public boolean isCheckRate(String command){
    if (command.toLowerCase().equals("check rate")) {
      return true;
    }
    return false;
  }

  public boolean isUpdateCurrency(String command){
    if (command.toLowerCase().equals("update currency")) {
      return true;
    }
    return false;
  }

  public boolean yesOrNo(String command){
    if(command.toLowerCase().equals("y")){
      return true;
    }
    return false;
  }


}
