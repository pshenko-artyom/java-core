package org.pshenko.javacore.stream;

import org.pshenko.javacore.stream.model.Customer;
import org.pshenko.javacore.stream.model.Order;
import org.pshenko.javacore.stream.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Main {
    private static final LocalDate BASE_DATE = LocalDate.of(2021, 1, 1);

    public static void main(String[] args) {
        List<Product> products = seedProducts();
        List<Customer> customers = seedCustomers(products);

        // Задание 1: Получите список продуктов из категории "Books" с ценой более 100.
        List<Product> booksWithPriceMore100 = customers.stream()
            .flatMap(customer -> customer.getOrders().stream())
            .flatMap(order -> order.getProducts().stream())
            .filter(book -> "Books".equals(book.getCategory()) && book.getPrice().compareTo(new BigDecimal("100")) > 0)
            .distinct()
            .toList();
        System.out.println("\nЗадание 1: Получите список продуктов из категории \"Books\" с ценой более 100:");
        booksWithPriceMore100.forEach(System.out::println);

        // Задание 2: Получите список заказов с продуктами из категории "Children's products".
        List<Order> ordersWithChildrenProducts = customers.stream()
            .map(Customer::getOrders)
            .flatMap(Set::stream)
            .filter(order -> order.getProducts().stream().anyMatch(product -> "Children's products".equals(product.getCategory())))
            .distinct()
            .toList();
        System.out.println("\nЗадание 2: Получите список заказов с продуктами из категории \"Children's products\": ");
        ordersWithChildrenProducts.forEach(System.out::println);

        // Задание 3: Получите список продуктов из категории "Toys" и примените скидку 10% и получите сумму всех
        //продуктов
        List<Product> allToys = customers.stream()
            .flatMap(customer -> customer.getOrders().stream())
            .flatMap(order -> order.getProducts().stream())
            .filter(product -> "Toys".equals(product.getCategory()))
            .distinct()
            .toList();
        System.out.println("\nЗадание 3: Получите список продуктов из категории \"Toys\" и примените скидку 10% и " +
            "получите сумму всех");
        allToys.forEach(System.out::println);

        BigDecimal toysPriceWithDiscount = allToys.stream()
            .map(toy -> toy.getPrice().multiply(new BigDecimal("0.9")))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Price with discount: " + toysPriceWithDiscount);
        System.out.println();

        // Задание 4. Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2026 и 01-апр-2026.
        List<Product> lvl2CustomerProducts = customers.stream()
            .filter(customer -> customer.getLevel().equals(2L))
            .flatMap(customer -> customer.getOrders().stream())
            .filter(order -> !order.getOrderDate().isBefore(LocalDate.of(2021, 2, 1))
                && !order.getOrderDate().isAfter(LocalDate.of(2021, 4, 1)))
            .flatMap(order -> order.getProducts().stream())
            .distinct()
            .toList();
        System.out.println("\nЗадание 4. Получите список продуктов, заказанных клиентом второго уровня между " +
            "01-фев-2026 и 01-апр-2026: ");
        lvl2CustomerProducts.forEach(System.out::println);

        // Задание 5: Получите топ 2 самые дешевые продукты из категории "Books".
        List<Product> topTwoCheapestBookProducts = customers.stream()
            .flatMap(customer -> customer.getOrders().stream())
            .flatMap(order -> order.getProducts().stream())
            .filter(product -> "Books".equals(product.getCategory()))
            .distinct()
            .sorted(Comparator.comparing(Product::getPrice))
            .limit(2)
            .toList();
        System.out.println("\nЗадание 5: Получите топ 2 самые дешевые продукты из категории \"Books\"");
        topTwoCheapestBookProducts.forEach(System.out::println);

        // Задание 6: Получите 3 самых последних сделанных заказа.
        List<Order> threeLatestOrders = customers.stream()
            .flatMap(customer -> customer.getOrders().stream())
            .sorted(Comparator.comparing(Order::getOrderDate).reversed())
            .limit(3)
            .toList();
        System.out.println("\nЗадание 6: Получите 3 самых последних сделанных заказа.");
        threeLatestOrders.forEach(System.out::println);

        //Задание 7: Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните
        //список их продуктов.
        System.out.println("\nЗадание 7: Получите список заказов, сделанных 15-марта-2021, выведите id заказов в " +
            "консоль и затем верните список их продуктов.");
        List<Product> productsFrom15MarchOrders = customers.stream()
            .flatMap(customer -> customer.getOrders().stream())
            .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
            .peek(order -> System.out.println("Id заказа, сделанного 15 марта: " + order.getId()))
            .flatMap(order -> order.getProducts().stream())
            .distinct()
            .toList();
        productsFrom15MarchOrders.forEach(System.out::println);

        //Задание 8: Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.
        System.out.println("\nЗадание 8: Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.");
        BigDecimal totalFebruaryPrice = customers.stream()
            .flatMap(customer -> customer.getOrders().stream())
            .filter(order -> !order.getOrderDate().isBefore(LocalDate.of(2021, 2, 1))
                && order.getOrderDate().isBefore(LocalDate.of(2021, 3, 1)))
            .flatMap(order -> order.getProducts().stream())
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Сумма всех заказов в феврале равна: " + totalFebruaryPrice);

        //Задание 9: Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.
        System.out.println("\nЗадание 9: Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.");
        List<Order> orders14March = customers.stream()
            .flatMap(customer -> customer.getOrders().stream())
            .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 14)))
            .toList();
        System.out.println("Заказы на 14 марта 2021 года: ");
        orders14March.forEach(System.out::println);

        List<BigDecimal> ordersTotals = orders14March.stream()
            .map(order ->
                order.getProducts().stream()
                    .map(Product::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add))
            .toList();
        BigDecimal average = ordersTotals.isEmpty()
            ? BigDecimal.ZERO
            : ordersTotals.stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(orders14March.size()), 2, RoundingMode.HALF_UP);
        System.out.println("Средний чек заказа 14 марта 2021 года: " + average);
    }


    private static List<Customer> seedCustomers(List<Product> products) {
        Product laptop = products.get(0);
        Product smartphone = products.get(1);
        Product headphones = products.get(2);
        Product keyboard = products.get(3);
        Product mouse = products.get(4);
        Product monitor = products.get(5);
        Product printer = products.get(6);
        Product kettle = products.get(7);
        Product coffeeMachine = products.get(8);
        Product vacuumCleaner = products.get(9);
        Product lamp = products.get(10);
        Product backpack = products.get(11);
        Product cleanCode = products.get(12);
        Product effectiveJava = products.get(13);
        Product javaConcurrency = products.get(14);
        Product streamsInJava = products.get(15);
        Product childrenPuzzle = products.get(16);
        Product toyCar = products.get(17);
        Product buildingBlocks = products.get(18);
        Product doll = products.get(19);

        // Customer 1 — John Smith
        Order johnOrder1 = createOrder(1001L, 0, "NEW", Set.of(laptop, mouse));
        Order johnOrder2 = createOrder(1002L, 10, "PAID", Set.of(smartphone, headphones, backpack));
        Order johnOrder3 = createOrder(1003L, 42, "SHIPPED", Set.of(monitor, keyboard, cleanCode));
        Order johnOrder4 = createOrder(1004L, 50, "DELIVERED", Set.of(kettle, coffeeMachine));
        Order johnOrder5 = createOrder(1005L, 58, "CANCELLED", Set.of(printer, lamp));

        // Customer 2 — Mary Johnson, level 2
        Order maryOrder1 = createOrder(2001L, 5, "PAID", Set.of(smartphone, lamp, toyCar));
        Order maryOrder2 = createOrder(2002L, 20, "SHIPPED", Set.of(vacuumCleaner, childrenPuzzle));
        Order maryOrder3 = createOrder(2003L, 41, "DELIVERED", Set.of(coffeeMachine, kettle, headphones));
        Order maryOrder4 = createOrder(2004L, 45, "NEW", Set.of(laptop, backpack, buildingBlocks));
        Order maryOrder5 = createOrder(2005L, 73, "PAID", Set.of(monitor, keyboard, mouse, cleanCode));

        // Customer 3 — Alex Brown
        Order alexOrder1 = createOrder(3001L, 2, "NEW", Set.of(keyboard, mouse));
        Order alexOrder2 = createOrder(3002L, 12, "PAID", Set.of(printer, javaConcurrency));
        Order alexOrder3 = createOrder(3003L, 30, "SHIPPED", Set.of(laptop, monitor));
        Order alexOrder4 = createOrder(3004L, 73, "DELIVERED", Set.of(smartphone, headphones));
        Order alexOrder5 = createOrder(3005L, 55, "CANCELLED", Set.of(vacuumCleaner, lamp));

        // Customer 4 — Emily Davis
        Order emilyOrder1 = createOrder(4001L, 72, "DELIVERED", Set.of(coffeeMachine, javaConcurrency));
        Order emilyOrder2 = createOrder(4002L, 18, "PAID", Set.of(kettle, backpack));
        Order emilyOrder3 = createOrder(4003L, 35, "NEW", Set.of(laptop, printer, mouse));
        Order emilyOrder4 = createOrder(4004L, 49, "SHIPPED", Set.of(monitor, keyboard, lamp));
        Order emilyOrder5 = createOrder(4005L, 57, "PAID", Set.of(smartphone, vacuumCleaner, effectiveJava));

        // Customer 5 — David Wilson
        Order davidOrder1 = createOrder(5001L, 9, "NEW", Set.of(headphones, mouse));
        Order davidOrder2 = createOrder(5002L, 72, "SHIPPED", Set.of(laptop, coffeeMachine, toyCar));
        Order davidOrder3 = createOrder(5003L, 40, "DELIVERED", Set.of(monitor, printer, doll));
        Order davidOrder4 = createOrder(5004L, 44, "PAID", Set.of(smartphone, kettle, lamp, streamsInJava));
        Order davidOrder5 = createOrder(5005L, 52, "CANCELLED", Set.of(keyboard, backpack, vacuumCleaner, childrenPuzzle));

        return List.of(
            new Customer(1L, "John Smith", 3L, Set.of(
                johnOrder1, johnOrder2, johnOrder3, johnOrder4, johnOrder5
            )),
            new Customer(2L, "Mary Johnson", 2L, Set.of(
                maryOrder1, maryOrder2, maryOrder3, maryOrder4, maryOrder5
            )),
            new Customer(3L, "Alex Brown", 1L, Set.of(
                alexOrder1, alexOrder2, alexOrder3, alexOrder4, alexOrder5
            )),
            new Customer(4L, "Emily Davis", 4L, Set.of(
                emilyOrder1, emilyOrder2, emilyOrder3, emilyOrder4, emilyOrder5
            )),
            new Customer(5L, "David Wilson", 5L, Set.of(
                davidOrder1, davidOrder2, davidOrder3, davidOrder4, davidOrder5
            ))
        );
    }

    private static List<Product> seedProducts() {
        return List.of(
            new Product(1L, "Laptop", "Electronics", new BigDecimal("999.99")),
            new Product(2L, "Smartphone", "Electronics", new BigDecimal("699.99")),
            new Product(3L, "Headphones", "Accessories", new BigDecimal("89.99")),
            new Product(4L, "Keyboard", "Accessories", new BigDecimal("49.99")),
            new Product(5L, "Mouse", "Accessories", new BigDecimal("19.99")),
            new Product(6L, "Monitor", "Electronics", new BigDecimal("249.99")),
            new Product(7L, "Printer", "Electronics", new BigDecimal("159.99")),
            new Product(8L, "Kettle", "Home Appliances", new BigDecimal("29.99")),
            new Product(9L, "Coffee Machine", "Home Appliances", new BigDecimal("129.99")),
            new Product(10L, "Vacuum Cleaner", "Home Appliances", new BigDecimal("189.99")),
            new Product(11L, "Lamp", "Home Appliances", new BigDecimal("15.99")),
            new Product(12L, "Backpack", "Accessories", new BigDecimal("45.99")),
            new Product(13L, "Clean Code", "Books", new BigDecimal("120.00")),
            new Product(14L, "Effective Java", "Books", new BigDecimal("150.00")),
            new Product(15L, "Java Concurrency in Practice", "Books", new BigDecimal("180.00")),
            new Product(16L, "Streams in Java", "Books", new BigDecimal("110.00")),
            new Product(17L, "Children's Puzzle", "Children's products", new BigDecimal("25.00")),
            new Product(18L, "Toy Car", "Toys", new BigDecimal("35.00")),
            new Product(19L, "Building Blocks", "Toys", new BigDecimal("75.00")),
            new Product(20L, "Doll", "Toys", new BigDecimal("45.00"))
        );
    }

    private static Order createOrder(long id, int dayOffset, String status, Set<Product> products) {
        LocalDate orderDate = BASE_DATE.plusDays(dayOffset);
        LocalDate deliveryDate = orderDate.plusDays(2L + (id % 6));

        return new Order(
            id,
            orderDate,
            deliveryDate,
            status,
            products
        );
    }
}