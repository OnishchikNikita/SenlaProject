package entity;

public class Atm {
    private int balance;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
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
