package org.pshenko.javacore.oop.solid;

import java.math.BigDecimal;

public interface OrderRepository {
    void save(String orderId, BigDecimal total);
}
