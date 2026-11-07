package org.pshenko.javacore.stream;

import org.pshenko.javacore.stream.model.Customer;
import org.pshenko.javacore.stream.model.Order;
import org.pshenko.javacore.stream.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Main {
    private static final LocalDate BASE_DATE = LocalDate.of(2026, 11, 1);

    public static void main(String[] args) {
        List<Customer> customers = seedCustomers();
    }

    private static List<Customer> seedCustomers() {
        List<Product> products = seedProducts();

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

        // Клиент 1 — Иван Петров
        Order ivanOrder1 = createOrder(1001L, 0, "НОВЫЙ", Set.of(laptop, mouse));
        Order ivanOrder2 = createOrder(1002L, 5, "ОПЛАЧЕН", Set.of(smartphone, headphones, backpack));
        Order ivanOrder3 = createOrder(1003L, 12, "ОТПРАВЛЕН", Set.of(monitor, keyboard));
        Order ivanOrder4 = createOrder(1004L, 20, "ДОСТАВЛЕН", Set.of(kettle, coffeeMachine));
        Order ivanOrder5 = createOrder(1005L, 28, "ОТМЕНЕН", Set.of(printer, lamp));

        // Клиент 2 — Мария Смирнова
        Order mariaOrder1 = createOrder(2001L, 1, "ОПЛАЧЕН", Set.of(smartphone, lamp));
        Order mariaOrder2 = createOrder(2002L, 6, "ОТПРАВЛЕН", Set.of(vacuumCleaner));
        Order mariaOrder3 = createOrder(2003L, 13, "ДОСТАВЛЕН", Set.of(coffeeMachine, kettle, headphones));
        Order mariaOrder4 = createOrder(2004L, 21, "НОВЫЙ", Set.of(laptop, backpack));
        Order mariaOrder5 = createOrder(2005L, 29, "ОПЛАЧЕН", Set.of(monitor, keyboard, mouse));

        // Клиент 3 — Алексей Кузнецов
        Order alexeyOrder1 = createOrder(3001L, 2, "НОВЫЙ", Set.of(keyboard, mouse));
        Order alexeyOrder2 = createOrder(3002L, 7, "ОПЛАЧЕН", Set.of(printer));
        Order alexeyOrder3 = createOrder(3003L, 14, "ОТПРАВЛЕН", Set.of(laptop, monitor));
        Order alexeyOrder4 = createOrder(3004L, 22, "ДОСТАВЛЕН", Set.of(smartphone, headphones));
        Order alexeyOrder5 = createOrder(3005L, 30, "ОТМЕНЕН", Set.of(vacuumCleaner, lamp));

        // Клиент 4 — Елена Попова
        Order elenaOrder1 = createOrder(4001L, 3, "ДОСТАВЛЕН", Set.of(coffeeMachine));
        Order elenaOrder2 = createOrder(4002L, 8, "ОПЛАЧЕН", Set.of(kettle, backpack));
        Order elenaOrder3 = createOrder(4003L, 15, "НОВЫЙ", Set.of(laptop, printer, mouse));
        Order elenaOrder4 = createOrder(4004L, 23, "ОТПРАВЛЕН", Set.of(monitor, keyboard, lamp));
        Order elenaOrder5 = createOrder(4005L, 31, "ОПЛАЧЕН", Set.of(smartphone, vacuumCleaner));

        // Клиент 5 — Дмитрий Соколов
        Order dmitryOrder1 = createOrder(5001L, 4, "НОВЫЙ", Set.of(headphones, mouse));
        Order dmitryOrder2 = createOrder(5002L, 9, "ОТПРАВЛЕН", Set.of(laptop, coffeeMachine));
        Order dmitryOrder3 = createOrder(5003L, 16, "ДОСТАВЛЕН", Set.of(monitor, printer));
        Order dmitryOrder4 = createOrder(5004L, 24, "ОПЛАЧЕН", Set.of(smartphone, kettle, lamp));
        Order dmitryOrder5 = createOrder(5005L, 32, "ОТМЕНЕН", Set.of(keyboard, backpack, vacuumCleaner));

        return List.of(
            new Customer(1L, "Иван Петров", 3L, Set.of(ivanOrder1, ivanOrder2, ivanOrder3, ivanOrder4, ivanOrder5)),
            new Customer(2L, "Мария Смирнова", 2L, Set.of(mariaOrder1, mariaOrder2, mariaOrder3, mariaOrder4, mariaOrder5)),
            new Customer(3L, "Алексей Кузнецов", 1L, Set.of(alexeyOrder1, alexeyOrder2, alexeyOrder3, alexeyOrder4, alexeyOrder5)),
            new Customer(4L, "Елена Попова", 4L, Set.of(elenaOrder1, elenaOrder2, elenaOrder3, elenaOrder4, elenaOrder5)),
            new Customer(5L, "Дмитрий Соколов", 5L, Set.of(dmitryOrder1, dmitryOrder2, dmitryOrder3, dmitryOrder4, dmitryOrder5))
        );
    }

    private static List<Product> seedProducts() {
        return List.of(
            new Product(1L, "Ноутбук", "Электроника", new BigDecimal("79990.00")),
            new Product(2L, "Смартфон", "Электроника", new BigDecimal("49990.00")),
            new Product(3L, "Наушники", "Аксессуары", new BigDecimal("8990.00")),
            new Product(4L, "Клавиатура", "Аксессуары", new BigDecimal("3490.00")),
            new Product(5L, "Мышь", "Аксессуары", new BigDecimal("1990.00")),
            new Product(6L, "Монитор", "Электроника", new BigDecimal("24990.00")),
            new Product(7L, "Принтер", "Электроника", new BigDecimal("15990.00")),
            new Product(8L, "Чайник", "Бытовая техника", new BigDecimal("2990.00")),
            new Product(9L, "Кофеварка", "Бытовая техника", new BigDecimal("12990.00")),
            new Product(10L, "Пылесос", "Бытовая техника", new BigDecimal("18990.00")),
            new Product(11L, "Лампа", "Бытовая техника", new BigDecimal("1590.00")),
            new Product(12L, "Рюкзак", "Аксессуары", new BigDecimal("4590.00"))
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
