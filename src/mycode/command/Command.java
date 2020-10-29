package mycode.command;
import mycode.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
