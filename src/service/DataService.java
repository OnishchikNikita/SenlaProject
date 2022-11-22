package service;

import entity.Card;
import entity.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Map;

public class DataService {
    public static void unloadDatabase(Data data) throws IOException {
        String string = new String(Files.readAllBytes(Paths.get(Data.FILE_PATH)));
        String[] array = string.split(" ");
        ArrayDeque<String> deque = new ArrayDeque<>();
        for (String element : array) {
            deque.addLast(element);
        }
        data.getAtm().setBalance(Integer.parseInt(deque.pop()));
        while (!deque.isEmpty()) {
            String number = deque.pop();
            String status = deque.pop();
            String pin = deque.pop();
            int balance = Integer.parseInt(deque.pop());
            Card card = new Card(number, status, pin, balance);
            String key = card.getNumber();
            data.getCards().put(key, card);
        }
    }

    public static void updateDatabase(Data data) throws IOException {
        StringBuilder stringData = new StringBuilder();
        stringData.append(data.getAtm().getBalance());
        for (Map.Entry<String, Card> card : data.getCards().entrySet()) {
            String number = card.getValue().getNumber();
            String status = card.getValue().getStatus();
            String pin = card.getValue().getPin();
            int balance = card.getValue().getBalance();
            stringData.append(" ").append(number).append(" ").append(status).append(" ").append(pin).append(" ").append(balance);
        }
        File database = new File(Data.FILE_PATH);
        FileWriter databaseWriter = new FileWriter(database, false);
        databaseWriter.write(stringData.toString());
        databaseWriter.close();
    }

    public static boolean isContainNumber(Data data, String cardNumber) {
        for (Map.Entry<String, Card> card : data.getCards().entrySet()) {
            if (card.getValue().getNumber().equals(cardNumber)) return true;
        }
        return false;
    }
}
