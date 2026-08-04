package academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.dto;

import java.util.ArrayList;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Purchase;
import java.util.List;
import java.util.Collections;


public class Summary {
    private final String name;
    private final double totalValue;
    private final List<Purchase> purchases;

    public Summary(String name, double totalValue, List<Purchase> purchases){
        this.name = name;
        this.totalValue = totalValue;
        this.purchases = Collections.unmodifiableList(purchases);
    }

    public String getName(){
        return name;
    }

    public double getTotalValue(){
        return totalValue;
    }

    public List<Purchase> getPurchases(){
        return purchases;
    }
}
