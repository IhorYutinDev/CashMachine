package mycode.command;
import mycode.CashMachine;
import mycode.ConsoleHelper;
import mycode.CurrencyManipulator;
import mycode.CurrencyManipulatorFactory;
import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean hasMoneyAnyMan = false;
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        for (CurrencyManipulator curMan : currencyManipulators) {
            if (curMan.hasMoney()) {
                hasMoneyAnyMan = true;
                break;
            }
        }
        if (hasMoneyAnyMan) {
            currencyManipulators.forEach(currencyManipulator ->
                    ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - "
                            + currencyManipulator.getTotalAmount()));
        } else {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
