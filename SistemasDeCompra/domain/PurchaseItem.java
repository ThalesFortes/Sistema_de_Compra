package academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain;

public class PurchaseItem {
    private final int id;
    private final String item;
    private final double value;

    public PurchaseItem(int id, String item, double value){
        this.id = id;
        this.item = item;
        this.value = value;
    }

    public int getId(){
        return id;
    }

    public String getItem(){
        return item;
    }

    public double getValue(){
        return value;
    }
}
