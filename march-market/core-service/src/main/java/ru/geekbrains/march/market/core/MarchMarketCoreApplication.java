package ru.geekbrains.march.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarchMarketCoreApplication {
	// Домашнее задание:
	// 1. Разобраться с кодом
	// 2. Добавьте необходимость указания адреса доставки и номера телефона при оформлении заказа
	// 3. Регистрация новых пользователей
	// 4. * Мерж корзин. После того как пользователь залогинился, необходимо на бэке смержить
	// гостевую и корзину пользователя

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketCoreApplication.class, args);
	}
}
