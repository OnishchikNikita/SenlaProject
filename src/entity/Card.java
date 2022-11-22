package entity;

public class Card {
    private final String number;
    private String status;
    private final String pin;
    private int balance;

    public Card(String number, String status, String pin, int balance) {
        this.number = number;
        this.status = status;
        this.pin = pin;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public boolean isAccessible() {
        return status.contains("active");
    }

    public void block() {
        this.status = "blocked";
    }

    public boolean isLegitPin(String pin) {
        return this.pin.equals(pin);
    }

    public boolean isEnoughBalance(int value) {
        return value <= this.balance;
    }

    public void decreaseBalance(int value) {
        this.balance -= value;
    }

    public void increaseBalance(int value) {
        this.balance += value;
    }
}
