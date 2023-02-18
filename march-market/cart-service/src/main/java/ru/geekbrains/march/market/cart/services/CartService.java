package ru.geekbrains.march.market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            Cart cart = new Cart();
            carts.put(cartId, cart);
        }
        return carts.get(cartId);
    }

    public void addToCart(String cartId, Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        getCurrentCart(cartId).add(p);
    }

    public void clearCart(String cartId) {
        getCurrentCart(cartId).clear();
    }
}
