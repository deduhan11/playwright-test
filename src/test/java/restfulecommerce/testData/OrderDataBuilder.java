package restfulecommerce.testData;

import net.datafaker.Faker;

public class OrderDataBuilder {
    private static final Faker FAKER = new Faker();

    public static OrderData getNewOrder() {
        int userId = FAKER.number().numberBetween(2, 4);
        int productId = FAKER.number().numberBetween(331,333);
        int productAmount = FAKER.number().numberBetween(400, 903);
        int quantity = FAKER.number().numberBetween(1, 5);
        int taxAmount = FAKER.number().numberBetween(10,50);
        int totalAmount = (productAmount * quantity) + taxAmount;

        return OrderData.builder()
                .userId(String.valueOf(userId))
                .productId(String.valueOf(productId))
                .productName(FAKER.commerce().productName())
                .productAmount(productAmount)
                .qty(quantity)
                .taxAmt(taxAmount)
                .totalAmt(totalAmount)
                .build();
    }

    public static OrderData getOrderDataWithMissingProductId() {
        int userId = FAKER.number().numberBetween(2, 4);
        int productAmount = FAKER.number().numberBetween(400, 903);
        int quantity = FAKER.number().numberBetween(1, 5);
        int taxAmount = FAKER.number().numberBetween(10,50);
        int totalAmount = (productAmount*quantity)+taxAmount;

        return OrderData.builder()
                .userId(String.valueOf(userId))
                .productName(FAKER.commerce().productName())
                .productAmount(productAmount)
                .qty(quantity)
                .taxAmt(taxAmount)
                .totalAmt(totalAmount)
                .build();
    }
}