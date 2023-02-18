package ru.geekbrains.march.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.entities.Product;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product p) {
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setTitle(p.getTitle());
        productDto.setPrice(p.getPrice());
        productDto.setCategoryTitle(p.getCategory().getTitle());
        return productDto;
    }
}
