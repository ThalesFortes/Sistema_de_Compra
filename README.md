# 🛒 Sistema de Compras

> Aplicação de console em Java que simula um sistema de compras com clientes, cartões de crédito e controle de limite  construída para estudar e praticar **Programação Orientada a Objetos** na prática.

---

## 📖 Sobre o projeto

O **Sistema de Compras** é uma aplicação de linha de comando onde é possível cadastrar clientes, associar cartões de crédito a eles, registrar compras (em dinheiro ou no cartão, com verificação de limite) e gerar um resumo com o total gasto.

Mais do que o resultado final, o objetivo aqui foi **exercitar os fundamentos de Orientação a Objetos**: modelar um domínio real em classes, separar responsabilidades em camadas e programar orientado a interfaces.

Todo o código foi escrito manualmente, **sem geração por IA** — a ideia era realmente treinar lógica, design de classes e os pilares da POO, não só entregar algo que funciona.

---

## 🧩 Conceitos de POO aplicados

Cada pilar da Orientação a Objetos aparece de forma concreta no código:

| Conceito | Onde aparece no projeto |
|---|---|
| **Abstração** | `Person` é uma classe `abstract` — define o que toda pessoa tem em comum, mas não pode ser instanciada diretamente. |
| **Herança** | `Customer` e `Employee` estendem `Person`, reaproveitando `id`, `name` e `age`. |
| **Encapsulamento** | Atributos são `private` e acessados por getters/setters. Listas internas são expostas com `Collections.unmodifiableList()` (em `Purchase` e `Summary`) para proteger o estado do objeto contra alterações externas. |
| **Polimorfismo** | O serviço é programado **por interface** (`CardService`, `CustomerService`) e não pela implementação — o `Main` depende da abstração, não da classe concreta. |
| **Composição** | Relações "tem-um": `Customer` possui listas de `Card` e `Purchase`; um `Card` agrupa `Purchase`; uma `Purchase` agrupa `PurchaseItem`. |

Outros pontos de design que reforcei de propósito:

- **Arquitetura em camadas** — separação clara entre `domain` (regras e entidades), `service` (casos de uso) e `dto` (transporte de dados).
- **Injeção de dependência manual** — `CardServiceImpl` recebe um `CustomerService` pelo construtor, em vez de criá-lo internamente.
- **Padrão DTO** — `Summary` agrega e transporta os dados do resumo sem expor as entidades do domínio.
- **Tratamento de exceções** — entradas do usuário são validadas contra `NumberFormatException` e `DateTimeException`, sem derrubar a aplicação.
- **API moderna de datas** — uso de `java.time.LocalDate` e `DateTimeFormatter`.

---

## ⚙️ Funcionalidades

- ✅ Cadastrar clientes (nome e idade)
- ✅ Cadastrar cartões para um cliente (número, data de validade e limite)
- ✅ Realizar compras **em dinheiro** ou **no cartão**, com verificação de saldo/limite disponível
- ✅ Gerar um **resumo de compras** do cliente, com total gasto e detalhamento

---

## 🗂️ Estrutura do projeto

```
SistemasDeCompra/
├── Main.java                     # Ponto de entrada e menu interativo
├── domain/                       # Entidades e regras de negócio
│   ├── Person.java               # Classe abstrata base
│   ├── Customer.java             # Cliente (herda de Person)
│   ├── Employee.java             # Funcionário (herda de Person)
│   ├── Card.java                 # Cartão + controle de limite
│   ├── Purchase.java             # Compra (agrupa itens)
│   └── PurchaseItem.java         # Item de uma compra
└── service/                      # Camada de serviços (casos de uso)
    ├── CustomerService.java      # Interface
    ├── CustomerServiceImpl.java  # Implementação
    ├── CardService.java          # Interface
    ├── CardServiceImpl.java      # Implementação
    └── dto/
        └── Summary.java          # DTO com o resumo do cliente
```

---

## 🚀 Como executar

Pré-requisito: **JDK 17+** instalado (testado com JDK 21).

```bash
# 1. Compilar todos os arquivos
javac -d out $(find . -name "*.java")

# 2. Executar
java -cp out academy.devdojo.maratonajava.javacore.SistemasDeCompra.Main
```

Depois é só seguir o menu:

```
1 - Adicionar cliente
2 - Cadastrar cartão
3 - Realizar compra
4 - Ver resumo
0 - Sair
```

---

## 🛠️ Tecnologias

- **Java** (JDK 21) — sem frameworks ou bibliotecas externas
- **java.time** para manipulação de datas
- **Scanner** para interação via console

---

## 👤 Autor

**Thales de Abreu Fortes da Silva**

- 💼 LinkedIn: `https://www.linkedin.com/in/thales-de-abreu-fortes-da-silva/`

<p align="center">Feito com ☕ e Java, como exercício de fundamentos de POO.</p>
