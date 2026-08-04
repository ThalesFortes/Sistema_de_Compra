package academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Card {
    private final int id;
    private final int numberCard;
    private final LocalDate validityDate;
    private double limit;
    private List<Purchase> purchases = new ArrayList<>();

    public Card(int id, int numberCard, LocalDate validityDate, double limit) {
        this.id = id;
        this.numberCard = numberCard;
        this.validityDate = validityDate;
        this.limit = limit;
    }

    public int getId(){
        return id;
    }

    public int getNumberCard(){
        return numberCard;
    }

    public LocalDate getValidityDate(){
        return validityDate;
    }

    public double getLimit() {
        return limit;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public boolean isValid(){
        return (validityDate.isAfter(LocalDate.now()) && !validityDate.isBefore(LocalDate.now()));
    }

    public double getAvailableBalance(){
        double spent = 0;
        for (Purchase purchase : purchases){
            spent += purchase.getTotal();
        }
        return limit - spent;
    }

    public boolean registerPurchase(Purchase purchase){
        double result = getAvailableBalance() - purchase.getTotal();
        if (result < 0 ){
            return false;
        }
        purchases.add(purchase);
        return true;
    }
}
