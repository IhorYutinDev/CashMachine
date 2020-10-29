package mycode.command;
import mycode.exception.InterruptOperationException;
import mycode.CashMachine;
import mycode.ConsoleHelper;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()+
            ".resources.login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        String stringUserCardNumber;
        String stringUserPin;
        while (true) {
            long userCardNumber;
            while (true) {
                ConsoleHelper.writeMessage("Enter please card number(12 simbols):");
                stringUserCardNumber = ConsoleHelper.readString();
                try {
                    if (stringUserCardNumber.length() != 12) {
                        throw new NumberFormatException();
                    }
                    userCardNumber = Long.parseLong(stringUserCardNumber);
                    break;
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }
            int userPinCode;
            while (true) {
                ConsoleHelper.writeMessage("Enter please card number(4 simbols):");
                stringUserPin = ConsoleHelper.readString();
                try {
                    if (stringUserPin.length() != 4) {
                        throw new NumberFormatException();
                    }
                    userPinCode = Integer.parseInt(stringUserPin);
                    break;
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }

            if (validCreditCards.containsKey(stringUserCardNumber) && validCreditCards
                    .getString(stringUserCardNumber).equals(stringUserPin)) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),userCardNumber));
                break;
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCardNumber));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }
}


