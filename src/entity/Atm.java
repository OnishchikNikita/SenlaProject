package entity;

public class Atm {
    private int balance;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isEnoughBalance(int amount) {
        return amount <= this.balance;
    }

    public void decreaseBalance(int amount) {
        this.balance -= amount;
    }

    public void increaseBalance(int amount) {
        this.balance += amount;
    }
}
