package academy.devdojo.maratonajava.javacore.SistemasDeCompra.service;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Customer;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Card;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Purchase;

import java.util.Collections;
import java.util.List;

public class CardServiceImpl implements CardService{
    private final CustomerService customerService;

    public CardServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void addCard(int customerId, Card card){
        Customer customer = customerService.findCustomer(customerId);
        if (customer == null){
            return;
        }
        customer.addCard(card);
    }

   @Override
    public List<Card> listCards(int customerId){
        Customer customer = customerService.findCustomer(customerId);
        if (customer == null){
            return  Collections.emptyList();
        }
        if(customer.hasCard()){
            return customer.getCards();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Card findCard(int id, int cardId){
        Customer customer = customerService.findCustomer(id);
        if (customer == null){
            return null;
        }
        if(customer.hasCard()){
            List<Card> cards =  listCards(id);
            for (Card card : cards){
                if ( cardId == card.getId()){
                    return card;
                }
            }
            return  null;
        }
        return null;
    }

    @Override
    public boolean makePurchase(int customerId, int cardId, Purchase purchase){
        Card card = findCard(customerId, cardId);
        if (card != null){
            return  card.registerPurchase(purchase);
        }
        return false;
    }

}
