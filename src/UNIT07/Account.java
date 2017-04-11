package UNIT07;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Account {

    private final String name;
    private int cash;

    public Account(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public synchronized void deposite(int amount) {
        cash += amount;
    }

    public synchronized void withdraw(int amount) {
        cash -= amount;
    }
}
