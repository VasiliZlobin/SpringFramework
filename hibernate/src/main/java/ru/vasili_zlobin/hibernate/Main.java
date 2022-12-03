package ru.vasili_zlobin.hibernate;

import ru.vasili_zlobin.hibernate.services.GoodsService;

public class Main {
    public static void main(String[] args) {
        System.out.println(GoodsService.getCustomersForProductId(1L));
        System.out.println(GoodsService.getProductsForCustomerId(2L));

        Double cost = GoodsService.getPriceInPurchase(2L, 3L);
        if (cost == null) {
            System.out.println("Покупка отсутствует");
        } else {
            System.out.printf("Цена покупки: %s %n", cost);
        }
    }
}