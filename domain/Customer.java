package academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain;
import java.util.List;
import java.util.ArrayList;

public class Customer extends Person {
    private final List<Card> cards = new ArrayList<>();
    private final List<Purchase> purchases = new ArrayList<>();

    public Customer(int id, String name, int age){
        super(id,name,age);
    }

    public List<Card> getCards(){
        return cards;
    }

    public boolean hasCard(){
        return !cards.isEmpty();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public List<Purchase> getPurchases(){
        return purchases;
    }

    public void addPurchase(Purchase purchase){
        this.purchases.add(purchase);
    }
}
