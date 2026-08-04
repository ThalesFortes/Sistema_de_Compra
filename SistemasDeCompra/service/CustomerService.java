package academy.devdojo.maratonajava.javacore.SistemasDeCompra.service;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Customer;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Purchase;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.dto.Summary;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    List<Customer> listCustomers();
    Customer findCustomer(int id);
    boolean addCashPurchase(int id, Purchase purchase);
    Summary createSummary(int id);
}
