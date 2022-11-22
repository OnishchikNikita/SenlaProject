package application;

import entity.Data;
import service.DataService;
import view.Printer;

import java.io.IOException;
import java.util.Scanner;

public class OptionManager {
    public static void processOption(Data data, Scanner in) throws IOException {
        if (!data.getCurrentCard().isAccessible()) {
            Printer.printException(4);
            return;
        }
        Printer.printSuccessfulLogIn();
        String optionNumber;
        boolean isEnough = false;
        while (!isEnough) {
            Printer.printOptionMenu();
            optionNumber = in.next();
            switch (optionNumber) {
                case "1" -> printBalance(data);
                case "2" -> cashOut(data, in);
                case "3" -> cashIn(data, in);
                case "4" -> isEnough = true;
                default -> Printer.printException(9);
            }
        }
    }

    private static void printBalance(Data data) {
        Printer.printBalance(data.getCurrentCard().getBalance());
    }

    private static void cashOut(Data data, Scanner in) throws IOException {
        Printer.printAmountRequest();
        int amountRequest = in.nextInt();
        boolean isEnough = data.getCurrentCard().isEnoughBalance(amountRequest);
        if (!isEnough) { Printer.printException(6); return; }
        isEnough = data.getAtm().isEnoughBalance(amountRequest);
        if (!isEnough) { Printer.printException(7); return; }
        data.getCurrentCard().decreaseBalance(amountRequest);
        data.getAtm().decreaseBalance(amountRequest);
        DataService.updateDatabase(data);
        DataService.unloadDatabase(data);
        Printer.printBalanceActionsResponse();
    }

    private static void cashIn(Data data, Scanner in) throws IOException {
        boolean isEnough = false;
        while (!isEnough) {
            Printer.printAmountRequest();
            int amountRequest = in.nextInt();
            if (amountRequest <= 1000000) {
                data.getCurrentCard().increaseBalance(amountRequest);
                data.getAtm().increaseBalance(amountRequest);
                DataService.updateDatabase(data);
                DataService.unloadDatabase(data);
                Printer.printBalanceActionsResponse();
                isEnough = true;
            } else Printer.printException(8);
        }
    }
}
