package academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Purchase {
    private final int id;
    private List<PurchaseItem> items = new ArrayList<>();

    public Purchase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addItem(PurchaseItem item){
        this.items.add(item);
    }

    public double getTotal(){
        double total = 0;
        for( PurchaseItem item : items ){
            total += item.getValue();
        }
        return total;
    }

    public List<PurchaseItem> getItems(){
        return Collections.unmodifiableList(items);
    }

}
