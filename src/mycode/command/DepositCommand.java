package mycode.command;

import mycode.exception.InterruptOperationException;
import mycode.CashMachine;
import mycode.ConsoleHelper;
import mycode.CurrencyManipulator;
import mycode.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        String[] denominationAndQuantity = ConsoleHelper.getValidTwoDigits(code);
        int nominal = Integer.parseInt(denominationAndQuantity[0]);
        int amount = Integer.parseInt(denominationAndQuantity[1]);
        CurrencyManipulator currencyManipulator;
        currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        currencyManipulator
                .addAmount(nominal, amount);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format")
                , nominal * amount, code));
    }
}
