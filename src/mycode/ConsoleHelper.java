package mycode;
import mycode.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String result = "";
        try {
            result = bis.readLine();
        } catch (IOException e) {
        }
        if (result.toUpperCase().equals("EXIT")) {

            throw new InterruptOperationException();
        }
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String currencyCode;
        while (true) {
            currencyCode = readString();
            if (currencyCode.length() != 3) {
                writeMessage(res.getString("invalid.data"));
            } else break;
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
            try {
                String stringTwoDig = readString();
                String[] result = stringTwoDig.split(" ");
                if (result.length == 2 && Integer.parseInt(result[0]) > 0 && Integer.parseInt(result[1]) > 0) {
                    return result;
                } else throw new NumberFormatException();
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        while (true) {
            writeMessage(String.format("Please, choice a command 1 - %s, 2 - %s, 3 - %s, 4 - %s",
                    res.getString("operation.INFO"), res.getString("operation.DEPOSIT"),
                    res.getString("operation.WITHDRAW"), res.getString("operation.EXIT")));
            try {
                String operCod = readString();
                int numOper = Integer.parseInt(operCod);
                if (numOper > 0 && numOper < 5) {
                    return Operation.getAllowableOperationByOrdinal(numOper);
                }
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage(){
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
