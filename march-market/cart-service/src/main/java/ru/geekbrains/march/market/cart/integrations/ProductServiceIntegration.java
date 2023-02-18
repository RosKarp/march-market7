package ru.geekbrains.march.market.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.march.market.api.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto findById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }
//
//    public void clear(String username) {
//        cartServiceWebClient.get()
//                .uri("/api/v1/cart/0/clear")
//                .header("username", username)
//                .retrieve()
//                .toBodilessEntity()
//                .block();
//    }
}
