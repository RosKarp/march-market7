package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.exceptions.IllegalStateException;
import ru.geekbrains.march.market.cart.services.CartService;
import ru.geekbrains.march.market.cart.utils.Cart;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        if (username != null && !username.trim().isEmpty()) {       // вход только авторизованным
            if (!cartService.getCurrentCart(guestCartId).getItems().isEmpty()) {
                Cart authUserCart = cartService.getCurrentCart(username);            // слияние корзин после авторизации
                Cart anonimCart = cartService.getCurrentCart(guestCartId);
                authUserCart.getItems().addAll(anonimCart.getItems());
                authUserCart.setTotalPrice(authUserCart.getTotalPrice().add(anonimCart.getTotalPrice()));
                anonimCart.clear();
            }
            String currentCartId = selectCartId(username, guestCartId);
            return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
        } else { throw new IllegalStateException("Необходима авторизация");}
    }

    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        if (username != null && !username.trim().isEmpty()) {       // вход только авторизованным
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
        } else { throw new IllegalStateException("Необходима авторизация");}
    }

    @GetMapping("/{guestCartId}/clear")
    public void clearCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        if (username != null && !username.trim().isEmpty()) {       // вход только авторизованным
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    } else { throw new IllegalStateException("Необходима авторизация");}
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }
}
