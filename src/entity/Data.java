package entity;

import java.util.HashMap;
import java.util.Map;

public class Data {
    public static final String FILE_PATH = "D:\\Projects\\SenlaTestProject\\src\\resource\\database.txt";
    private Atm atm;
    private Map<String, Card> cards;
    private String activeCardKey;

    public Data () {
        cards = new HashMap<>();
        atm = new Atm();
    }

    public Card getCurrentCard() {
        return this.cards.get(activeCardKey);
    }

    public Atm getAtm() {
        return atm;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public void setCurrentCardKey(String currentCardKey) {
        this.activeCardKey = currentCardKey;
    }
}
