package academy.devdojo.maratonajava.javacore.SistemasDeCompra.service;

import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Card;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Customer;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Purchase;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.dto.Summary;


import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private final  List<Customer> customers = new ArrayList<>();

    @Override
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    @Override
    public List<Customer> listCustomers(){
        return customers;
    }

    @Override
    public Customer findCustomer(int id){
        for (Customer customer : customers){
            if (customer.getId() == id){
                return customer;
            }
        }
        return null;
    }

    @Override
    public boolean addCashPurchase(int id , Purchase purchase){
        Customer customer = findCustomer(id);
        if (customer == null){
            return false;
        }
        customer.addPurchase(purchase);
        return true;
    }

    @Override
    public Summary createSummary(int id) {
        List<Purchase> allPurchases = new ArrayList<>();
        double total = 0;
        Customer customer = findCustomer(id);
        if (customer == null){
            return null;
        }

        for (Card card : customer.getCards()){
            allPurchases.addAll(card.getPurchases());
        }

        allPurchases.addAll(customer.getPurchases());


        for (Purchase purchase : allPurchases) {
            total += purchase.getTotal();
        }

        return new Summary(customer.getName(),total,allPurchases);
    }
}
