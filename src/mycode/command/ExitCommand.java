package mycode.command;


import mycode.exception.InterruptOperationException;
import mycode.CashMachine;
import mycode.ConsoleHelper;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String stringExit = ConsoleHelper.readString();
        if (stringExit.equals("y")) {
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        } else {
            return;
        }
    }
}
