package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.OrderItemDto;
import ru.geekbrains.march.market.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getProduct().getId(), o.getProduct().getTitle(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
