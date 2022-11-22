package application;

import entity.Data;
import service.DataService;
import view.Printer;

import java.io.IOException;
import java.util.Scanner;

public class Authorization {
    public static void runAuthorization(Data data, Scanner in) throws IOException {
        DataService.unloadDatabase(data);
        checkNumber(data, in);
        checkPin(data, in);
    }

    private static void checkNumber(Data data, Scanner in) {
        Printer.printCardNumberRequest();
        String cardNumber = in.next();
        while (!DataService.isContainNumber(data, cardNumber)) {
            Printer.printException(2);
            cardNumber = in.next();
        }
        data.setCurrentCardKey(cardNumber);
    }

    private static void checkPin(Data data, Scanner in) throws IOException {
        int attempts = 0;
        Printer.printPinCodeRequest();
        String pin = in.next();
        attempts++;
        while (!data.getCurrentCard().isLegitPin(pin)) {
            if (attempts == 3) {
                Printer.printException(5);
                data.getCurrentCard().block();
                DataService.updateDatabase(data);
                DataService.unloadDatabase(data);
                return;
            }
            Printer.printException(3);
            pin = in.next();
            attempts++;
        }
    }
}
