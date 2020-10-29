package mycode.command;



import mycode.exception.InterruptOperationException;
import mycode.exception.NotEnoughMoneyException;
import mycode.CashMachine;
import mycode.ConsoleHelper;
import mycode.CurrencyManipulator;
import mycode.CurrencyManipulatorFactory;

import java.util.*;

class WithdrawCommand implements Command {
    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory
                .getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        int amount;

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));

            String stringWithdraw = ConsoleHelper.readString();
            try {
                amount = Integer.parseInt(stringWithdraw);
                if (amount < 1) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    throw new NumberFormatException();
                }
                if (!currencyManipulator.isAmountAvailable(amount)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
            }
        }
        Map<Integer, Integer> mapAmount;
        try {
            mapAmount = currencyManipulator.withdrawAmount(amount);
            TreeMap<Integer, Integer> sortedMapAmount = new TreeMap<>(Collections.reverseOrder());
            sortedMapAmount.putAll(mapAmount);
            sortedMapAmount.forEach((k, v) -> ConsoleHelper.writeMessage(String.format("\t%d - %d", k, v)));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),
                    amount, currencyManipulator.getCurrencyCode()));
        } catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
        }
    }
}
