package academy.devdojo.maratonajava.javacore.SistemasDeCompra.service;

import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Card;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.Purchase;

import java.util.List;

public interface CardService {
    void addCard(int customerId, Card card);
    List<Card> listCards(int customerId);
    Card findCard(int id, int cardId);
    boolean makePurchase(int customerId, int cardId, Purchase purchase);
}
