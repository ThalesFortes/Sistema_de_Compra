package academy.devdojo.maratonajava.javacore.SistemasDeCompra;

import academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain.*;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.CardService;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.CardServiceImpl;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.CustomerService;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.CustomerServiceImpl;
import academy.devdojo.maratonajava.javacore.SistemasDeCompra.service.dto.Summary;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService =  new CustomerServiceImpl();
        CardService cardService = new CardServiceImpl(customerService);

        int customerId = 0;
        int cardId = 0;
        int purchaseItemId = 0;
        int purchaseId = 0;

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running){
            System.out.println("1 - Adicionar cliente");
            System.out.println("2 - Cadastrar cartão");
            System.out.println("3 - Realizar compra");
            System.out.println("4 - Ver resumo");
            System.out.println("0 - Sair");

            int choice;
            try{
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }


            switch (choice){
                case 1:
                    System.out.println("Adicionando clientes");
                    System.out.println("Insira o nome:");
                    String name = scanner.nextLine();

                    System.out.println("Insira sua idade:");

                    int age;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Opção inválida! Digite um número.");
                        break;
                    }


                    Customer newCustomer = new Customer(customerId, name, age);
                    customerService.addCustomer(newCustomer);
                    customerId++;
                    break;

                case 2:
                    System.out.println("Cadastrando cartão");
                    System.out.println("De quem você quer cadastrar o cartão?");

                    if (customerService.listCustomers().isEmpty()){
                        System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
                        break;
                    }

                    System.out.println("Clientes disponíveis:");
                    for (Customer c : customerService.listCustomers()) {
                        System.out.println(c);
                    }

                    System.out.println("Insira o id do mesmo");
                    int selectedCustomerId;
                    try {
                        selectedCustomerId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Opção inválida! Digite um número.");
                        break;
                    }

                    System.out.println("Insira o numero do cartao");
                    int numberCard;
                    try {
                        numberCard = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Opção inválida! Digite o numero do cartao.");
                        break;
                    }

                    System.out.println("Insira a data de validade, no formado dd/mm/yyyy");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    LocalDate validityDate;
                    try{
                        validityDate = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (DateTimeException e){
                        System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
                        break;
                    }

                    System.out.println("Insira o limite");

                    double limit;
                    try {
                        limit = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Opção inválida! Digite um valor de limite.");
                        break;
                    }

                    Card newCard = new Card(cardId, numberCard , validityDate , limit);
                    cardService.addCard(selectedCustomerId, newCard);
                    cardId++;
                    break;

                case 3:
                    System.out.println("Realizando compra");
                    System.out.println("Informe o id do cliente que esta realizando a compra");

                    if (customerService.listCustomers().isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
                        break;
                    }

                    System.out.println("Clientes disponíveis:");
                    for (Customer c : customerService.listCustomers()) {
                        System.out.println(c);
                    }

                    int purchaseCustomerId;
                    try {
                        purchaseCustomerId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Opção inválida! Digite um valor de limite.");
                        break;
                    }

                    Purchase purchase = new Purchase(purchaseId);
                    purchaseId++;

                    boolean adding = true;

                    while (adding){
                        System.out.println("Insira o nome do item");
                        String itemName = scanner.nextLine();

                        System.out.println("Insira o valor do item");
                        double value;
                        try {
                            value = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                            System.out.println("Opção inválida! Digite um valor de limite.");
                            continue;
                        }

                        PurchaseItem purchaseItem = new PurchaseItem(purchaseItemId, itemName, value);
                        purchaseItemId++;
                        purchase.addItem(purchaseItem);


                        System.out.println("Adicionar mais um item? Digite 'sair' para parar:");
                        String input = scanner.nextLine();
                        if (input.equals("sair")) {
                            adding = false;
                        }
                    }

                    if(purchase.getItems().isEmpty()){
                        System.out.println("Compra vazia! Nada foi adicionado.");
                        break;
                    }


                    System.out.println("Compra vai ser feita no cartao ou dinheiro? Digite 1 para dinheiro e 2 para cartão");
                    int purchaseType;
                    try {
                        purchaseType = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Opção inválida! Digite um valor de limite.");
                        break;
                    }

                    if (purchaseType == 1){
                        customerService.addCashPurchase(purchaseCustomerId, purchase);
                        System.out.println("Compra adicionada");
                    }

                    if (purchaseType == 2 ){
                        List<Card> customerCards = cardService.listCards(purchaseCustomerId);
                        if(customerCards.isEmpty()){
                            System.out.println("Nenhum cartao cadastrado. Cadastre um cartao primeiro.");
                            break;
                        }

                        System.out.println("A compra foi feita em qual cartao? Insira o id");
                        System.out.println("Cartões disponíveis:");
                        for (Card card : customerCards) {
                            System.out.println(card.getId() + " - Cartão nº " + card.getNumberCard());
                        }

                        int selectedCardId;
                        try {
                            selectedCardId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                            System.out.println("Opção inválida! Digite um valor de limite.");
                            break;
                        }

                        Card usedCard = cardService.findCard(purchaseCustomerId, selectedCardId);

                        if (usedCard == null) {
                            System.out.println("Cartão não encontrado.");
                            break;
                        }

                        boolean success = cardService.makePurchase(purchaseCustomerId, selectedCardId, purchase);
                        if (success) {
                            System.out.println("Compra realizada com sucesso!");
                        } else {
                            System.out.println("Saldo insuficiente. Limite disponível: " + usedCard.getAvailableBalance());
                        }

                    }
                    break;

                case 4:
                    System.out.println("Mostrando resumo de compras");
                    System.out.println("Informe o id do cliente que quer ver o resumo");

                    if (customerService.listCustomers().isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
                        break;
                    }

                    System.out.println("Clientes disponíveis:");
                    for (Customer c : customerService.listCustomers()) {
                        System.out.println(c);
                    }

                    int summaryCustomerId;
                    try {
                        summaryCustomerId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                        System.out.println("Opção inválida! Digite um valor de limite.");
                        break;
                    }

                    Summary summary =customerService.createSummary(summaryCustomerId);
                    if (summary == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }

                    System.out.println("Cliente: " + summary.getName());
                    System.out.println("Total gasto: " + summary.getTotalValue());
                    System.out.println("Compras:");

                    for (Purchase c : summary.getPurchases()) {
                        System.out.println("  Compra " + c.getId() + " - Total: " + c.getTotal());
                    }
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}
