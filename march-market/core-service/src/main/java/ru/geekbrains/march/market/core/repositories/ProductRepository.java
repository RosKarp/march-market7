package ru.geekbrains.march.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.march.market.core.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}