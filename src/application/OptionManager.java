package application;

import entity.Data;
import service.DataService;
import view.Printer;

import java.util.Scanner;

public class OptionManager {
    public static void processOption(Data data, Scanner in) {
        if (!data.getCurrentCard().isAccessible()) {
            Printer.printException(4);
            return;
        }
        Printer.printSuccessfulLogIn();
        boolean isEnough = false;
        while (!isEnough) {
            Printer.printOptionMenu();
            String optionNumber = in.next();
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

    private static void cashOut(Data data, Scanner in) {
        Printer.printValueRequest();
        String string = in.next();
        try {
            int value = Integer.parseInt(string);
            if (value < 0) { Printer.printException(10); return; }
            boolean isEnoughBalance = data.getCurrentCard().isEnoughBalance(value);
            if (!isEnoughBalance) { Printer.printException(6); return; }
            isEnoughBalance = data.getAtm().isEnoughBalance(value);
            if (!isEnoughBalance) { Printer.printException(7); return; }
            data.getCurrentCard().decreaseBalance(value);
            data.getAtm().decreaseBalance(value);
            DataService.updateDatabase(data);
            DataService.unloadDatabase(data);
            Printer.printBalanceActionsResponse();
        } catch (Exception exception) {
            Printer.printException(10);
        }
    }

    private static void cashIn(Data data, Scanner in) {
        boolean isEnough = false;
        while (!isEnough) {
            Printer.printValueRequest();
            Printer.printValueRange();
            String string = in.next();
            try {

                int value = Integer.parseInt(string);
                if (value <= 1000000 & value >= 0) {
                    data.getCurrentCard().increaseBalance(value);
                    data.getAtm().increaseBalance(value);
                    DataService.updateDatabase(data);
                    DataService.unloadDatabase(data);
                    Printer.printBalanceActionsResponse();
                    isEnough = true;
                } else Printer.printException(8);
            } catch (Exception exception) {
                Printer.printException(10);
            }
        }
    }
}
